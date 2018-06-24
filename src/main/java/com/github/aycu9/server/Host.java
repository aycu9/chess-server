package com.github.aycu9.server;

import com.github.aycu9.data.Team;
import com.github.aycu9.data.User;

/**
 * Created by Libra on 2018-06-23.
 */
public class Host {
    public String uuid;
    public String name;
    public Team team;
    public Host (User user){
        uuid = user.getUuid();
        name = user.getName();
        team = user.getTeam();
    }
}
