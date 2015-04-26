package com.dimafeng.cards.repository;

import com.dimafeng.cards.model.Card;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CardRepository extends MongoRepository<Card, String> {
    List<Card> findByUserId(String id);

    @Query("{id: {$in: ?0}}")
    List<Card> findByIds(List<String> ids, Sort sort);
}
