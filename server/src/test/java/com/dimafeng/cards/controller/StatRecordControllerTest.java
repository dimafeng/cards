package com.dimafeng.cards.controller;


import com.dimafeng.cards.model.StatRecord;
import com.dimafeng.cards.repository.StatRecordRepository;
import com.dimafeng.cards.utils.DateUtils;
import org.javatuples.Pair;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

public class StatRecordControllerTest {

    @Test
    public void test() {
        StatRecordController statRecordController = new StatRecordController();

        statRecordController.repository = mock(StatRecordRepository.class);
        when(statRecordController.repository.findByUserIdAndDateAfter(any(), any())).thenReturn(Arrays.asList(
                createStatRecord(createDate(1), Arrays.asList(
                         Pair.with("1", 1),
                        Pair.with("2", 1),
                        Pair.with("3", -1),
                        Pair.with("4", -1)
                )),
                createStatRecord(createDate(2), Arrays.asList(
                        Pair.with("1", 1),
                        Pair.with("2", -1),
                        Pair.with("3", -1),
                        Pair.with("4", -1)
                )),
                createStatRecord(createDate(3), Arrays.asList(
                        Pair.with("1", 1),
                        Pair.with("2", 1),
                        Pair.with("3", 1),
                        Pair.with("4", 1)
                )),
                createStatRecord(createDate(1), Arrays.asList(
                        Pair.with("1", -1),
                        Pair.with("2", 1),
                        Pair.with("3", 1),
                        Pair.with("4", -1)
                ))
        ));

        System.out.println(statRecordController.getStatistic(null));
    }

    private StatRecord createStatRecord(Date date, List<Pair<String, Integer>> statistic) {
        StatRecord statRecord = new StatRecord();
        statRecord.setDate(date);
        statRecord.setStatistic(statistic.stream().collect(Collectors.toMap(Pair::getValue0, Pair::getValue1)));
        return statRecord;
    }

    private Date createDate(int shift) {
        return DateUtils.asDate(LocalDate.now().minus(shift, ChronoUnit.DAYS));
    }
}