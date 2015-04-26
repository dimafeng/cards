package com.dimafeng.cards.controller;

import com.dimafeng.cards.exception.OperationNotPermited;
import com.dimafeng.cards.model.Card;
import com.dimafeng.cards.model.User;
import com.dimafeng.cards.repository.CardRepository;
import com.dimafeng.cards.repository.PlaylistRepository;
import com.dimafeng.cards.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cards")
public class CardController implements CRUDMapping<Card, Card> {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PlaylistRepository playlistRepository;

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

    @Override
    public List<Card> getAll(Authentication authentication) {
        User user = userService.getUser(authentication);
        return cardRepository.findByUserId(user.getId());
    }
}
