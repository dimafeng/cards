package com.dimafeng.cards.repository;

import com.dimafeng.cards.model.StatRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface StatRecordRepository extends MongoRepository<StatRecord, String> {

    List<StatRecord> findByUserIdAndDateAfter(String userId, Date date);
}
