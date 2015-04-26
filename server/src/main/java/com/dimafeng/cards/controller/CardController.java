package com.dimafeng.cards.controller;

import com.dimafeng.cards.exception.OperationNotPermited;
import com.dimafeng.cards.model.Card;
import com.dimafeng.cards.model.Playlist;
import com.dimafeng.cards.model.User;
import com.dimafeng.cards.repository.CardRepository;
import com.dimafeng.cards.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.Authentication;

public class CardController implements CRUDMapping<Card, Card> {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserService userService;

    @Override
    public void processBeforeSave(Card item, Authentication authentication) throws Exception {
        item.setUserId(userService.getUser(authentication).getId());
    }

    @Override
    public MongoRepository<Card, String> getRepository() {
        return cardRepository;
    }

    @Override
    public void checkRight(Card element, Authentication authentication) {
        User user = userService.getUser(authentication);
        if (user.getId().equals(element.getUserId())) {
            throw new OperationNotPermited();
        }
    }
}
