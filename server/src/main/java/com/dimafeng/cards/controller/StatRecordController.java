package com.dimafeng.cards.controller;

import com.dimafeng.cards.model.StatRecord;
import com.dimafeng.cards.repository.StatRecordRepository;
import com.dimafeng.cards.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
}
