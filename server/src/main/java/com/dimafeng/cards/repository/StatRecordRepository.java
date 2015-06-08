package com.dimafeng.cards.repository;

import com.dimafeng.cards.model.StatRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatRecordRepository extends MongoRepository<StatRecord, String> {

}
