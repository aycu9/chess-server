package com.github.aycu9.repository;

import com.github.aycu9.data.Team;
import com.github.aycu9.data.User;
import com.github.aycu9.data.UserState;

import java.util.List;
import java.util.Map;

/**
 * Created by Libra on 2018-05-19.
 */
public interface UserRepository {
    void hostGame(String uuid, Team team);

    UserState getState(String uuid);

    void setState(String uuid, UserState userState);

    List<User> getListOfHosts();

    /**
     * Registers a new User and returns the UUID of the newly created User.
     *
     * @param name public name of the user
     * @return the UUID of the new user
     */
    String assignUUID(String name);

    void startGame(String hostUuid, String otherUuid);

    void quitUser(String uuid);

    void gameOver(String uuid);
}
