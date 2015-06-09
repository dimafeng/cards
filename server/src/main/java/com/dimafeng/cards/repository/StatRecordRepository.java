package com.dimafeng.cards.repository;

import com.dimafeng.cards.model.StatRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface StatRecordRepository extends MongoRepository<StatRecord, String> {

    List<StatRecord> findByDateAfter(Date date);
}
