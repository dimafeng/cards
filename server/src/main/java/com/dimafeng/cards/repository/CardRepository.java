package com.dimafeng.cards.repository;

import com.dimafeng.cards.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CardRepository extends MongoRepository<Card, String> {
    List<Card> findByUserId(String id);
}
