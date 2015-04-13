package com.dimafeng.cards.repository;

import com.dimafeng.cards.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>
{
    User findByEmail(String email);
}
