package com.dimafeng.cards.controller;

import com.dimafeng.cards.exception.OperationNotPermited;
import com.dimafeng.cards.model.Playlist;
import com.dimafeng.cards.model.User;
import com.dimafeng.cards.repository.PlaylistRepository;
import com.dimafeng.cards.repository.UserRepository;
import com.dimafeng.cards.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/playlists")
public class PlaylistController implements CRUDMapping<Playlist, Playlist> {

    @Autowired
    private PlaylistRepository repository;

    @Autowired
    private UserService userService;

    @Override
    public void processBeforeSave(Playlist item, Authentication authentication) throws Exception {

    }

    @Override
    public MongoRepository<Playlist, String> getRepository() {
        return repository;
    }

    @Override
    public void checkRight(Playlist element, Authentication authentication) {
        User user = userService.getUser(authentication);
        if (user.getId().equals(element.getUserId())) {
            throw new OperationNotPermited();
        }
    }

    @Override
    public List<Playlist> getAll(Authentication authentication) {
        User user = userService.getUser(authentication);
        return repository.findByUserId(user.getId());
    }
}
