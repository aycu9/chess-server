package com.github.aycu9.data;

/**
 * Created by Libra on 2018-05-26.
 */
public class Team {
    private final int teamNumber;

    public Team (int teamNumber){
        this.teamNumber = teamNumber;
    }

    public Team getOppositeTeam (){
        if(teamNumber == 1){
            return new Team(2);
        }
        return new Team (1);
    }
}
