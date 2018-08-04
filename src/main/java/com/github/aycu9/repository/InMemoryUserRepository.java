package com.github.aycu9.repository;

import com.github.aycu9.data.Team;
import com.github.aycu9.data.User;
import com.github.aycu9.data.UserState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Libra on 2018-05-19.
 */
public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> userMap;

    public InMemoryUserRepository() {
        userMap = new HashMap<>();
    }

    @Override
    public void hostGame(String uuid, Team team) {
        userMap.get(uuid).setTeam(team);
        printCurrentState();
    }

    @Override
    public User getUser(String uuid) {
        return userMap.get(uuid);
    }

    @Override
    public void setState(String uuid, UserState userState) {
        userMap.get(uuid).setUserState(userState);
        printCurrentState();
    }

    @Override
    public List<User> getListOfHosts() {
        List<User> hostList = new ArrayList<>();
        for (User user : userMap.values()) {
            if (user.isHosting()) {
                hostList.add(user);
            }
        }
        return hostList;
    }

    @Override
    public String assignUUID(String name) {
        User user = new User(name);
        String uuid = user.getUuid();
        userMap.put(uuid, user);
        printCurrentState();
        return uuid;
    }

    @Override
    public void startGame(String hostUuid, String otherUuid) {
        User hostUser = userMap.get(hostUuid);
        User otherUser = userMap.get(otherUuid);
        hostUser.setOpponent(otherUuid);
        otherUser.setTeam(hostUser.getTeam().getOppositeTeam());
        otherUser.setOpponent(hostUuid);
        printCurrentState();
    }

    @Override
    public void quitUser(String uuid) {
        userMap.remove(uuid);
    }

    @Override
    public void gameOver(String uuid) {
        User user1 = userMap.get(uuid);
        User user2 = userMap.get(user1.getOpponent());
        user1.setTeam(null);
        user1.setOpponent(null);
        user1.setUserState(null);
        user2.setTeam(null);
        user2.setOpponent(null);
        user2.setUserState(null);
    }

    public void printCurrentState() {
        for (User user : userMap.values()) {
            System.out.println(user);
        }
    }
}
