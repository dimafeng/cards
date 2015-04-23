package com.dimafeng.cards.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Playlist implements Model {

    @Id
    private String id;

    private String name;
    private List<String> cardIds;

    @Indexed
    private String userId;

    private boolean active;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getCardIds() {
        return cardIds;
    }

    public void setCardIds(List<String> cardIds) {
        this.cardIds = cardIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
