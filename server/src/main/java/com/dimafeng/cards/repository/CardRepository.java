package com.dimafeng.cards.repository;

import com.dimafeng.cards.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardRepository extends MongoRepository<Card, String> {
}
