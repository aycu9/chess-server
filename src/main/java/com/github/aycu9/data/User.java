package com.github.aycu9.data;

import java.util.UUID;

/**
 * Created by Libra on 2018-05-26.
 */
public class User {
    private final String uuid;
    private final String name;
    private Team team;
    private String opponent;
    private UserState userState;

    public User(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }


    public User(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public boolean isHosting() {
        return this.getTeam() != null && this.getOpponent() == null;
    }

    public String getUuid() {
        return uuid;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public UserState getUserState() {
        return userState;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }
}
