package com.dimafeng.cards.repository;

import com.dimafeng.cards.model.Playlist;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface PlaylistRepository extends MongoRepository<Playlist, String> {
    List<Playlist> findByUserId(String id);
}
