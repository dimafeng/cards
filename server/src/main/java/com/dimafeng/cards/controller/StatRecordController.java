package com.dimafeng.cards.controller;

import com.dimafeng.cards.model.StatRecord;
import com.dimafeng.cards.repository.StatRecordRepository;
import com.dimafeng.cards.service.UserService;
import com.dimafeng.cards.utils.DateUtils;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Controller
@RequestMapping("/statRecords")
public class StatRecordController implements CRUDMapping<StatRecord, StatRecord> {

    @Autowired
    StatRecordRepository repository;

    @Autowired
    UserService userService;

    @Override
    public void processBeforeSave(StatRecord item, Authentication authentication) throws Exception {
        item.setDate(new Date());
        item.setUserId(userService.getUser(authentication).getId());
    }

    @Override
    public void delete(@PathVariable("id") String id, Authentication authentication) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MongoRepository<StatRecord, String> getRepository() {
        return repository;
    }

    @RequestMapping("/stat")
    @ResponseBody
    public Map<Date, Pair<Integer, Integer>> getStatistic() {

        Map<Date, Pair<Integer, Integer>> result = repository
                .findByDateAfter(DateUtils.asDate(LocalDate.now().minus(1, ChronoUnit.MONTHS)))
                .stream()
                .flatMap(e ->
                        e.getStatistic().entrySet().stream().map(v ->
                                        Triplet.with(e.getDate(), v.getKey(), v.getValue())
                        ))
                .collect(groupingBy(Triplet::getValue0))
                .entrySet()
                .stream()
                .map(e -> Pair.with(
                        e.getKey(),
                        e.getValue()
                                .stream()
                                .collect(groupingBy(Triplet::getValue1, summarizingInt(Triplet::getValue2)))
                                .values()
                                .stream().reduce(Pair.<Integer, Integer>with(0,0), (p, v) -> {
                                    if(v.getSum()>0) {
                                        return Pair.with(p.getValue0() + 1, p.getValue1());
                                    } else {
                                        return Pair.with(p.getValue0(), p.getValue1() + 1);
                                    }
                                }, (a , b) -> Pair.with(a.getValue0() + b.getValue0(),
                                a.getValue1() + b.getValue1())))
                ).collect(toMap(Pair::getValue0, Pair::getValue1));

        return result;
    }
}
