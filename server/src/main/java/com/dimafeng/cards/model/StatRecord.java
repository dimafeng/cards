package com.dimafeng.cards.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Document
public class StatRecord implements Model {
    @Id
    private String id;

    private String playlistId;
    private Date date;
    private String userId;

    private Map<String, Integer> statistic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStatistic(Map<String, Integer> statistic) {
        this.statistic = statistic;
    }

    public Map<String, Integer> getStatistic() {
        return statistic;
    }
}
