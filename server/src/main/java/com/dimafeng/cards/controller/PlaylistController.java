package com.dimafeng.cards.controller;

import com.dimafeng.cards.exception.OperationNotPermited;
import com.dimafeng.cards.model.Card;
import com.dimafeng.cards.model.Playlist;
import com.dimafeng.cards.model.User;
import com.dimafeng.cards.repository.CardRepository;
import com.dimafeng.cards.repository.PlaylistRepository;
import com.dimafeng.cards.repository.UserRepository;
import com.dimafeng.cards.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.Sort.*;

@Controller
@RequestMapping("/playlists")
public class PlaylistController implements CRUDMapping<Playlist, Playlist> {

    @Autowired
    PlaylistRepository repository;

    @Autowired
    UserService userService;

    @Autowired
    CardRepository cardRepository;

    @Override
    public void processBeforeSave(Playlist item, Authentication authentication) throws Exception {
        item.setUserId(userService.getUser(authentication).getId());
    }

    @Override
    public MongoRepository<Playlist, String> getRepository() {
        return repository;
    }

    @Override
    public void checkRight(Playlist element, Authentication authentication) {
        User user = userService.getUser(authentication);
        if (!user.getId().equals(element.getUserId())) {
            throw new OperationNotPermited();
        }
    }

    @Override
    public List<Playlist> getAll(Authentication authentication) {
        User user = userService.getUser(authentication);
        return repository.findByUserId(user.getId());
    }

    @RequestMapping(value = "/{id}/cards", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Card> getCards(@PathVariable("id") String id, Authentication authentication) {
        Playlist one = get(id, authentication);
        return Optional.ofNullable(one.getCardIds())
                .map(e -> cardRepository.findByIds(e, new Sort(new Order(Direction.DESC, "date"))))
                .orElse(Collections.emptyList());
    }
}
