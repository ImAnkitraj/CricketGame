package com.tekion.cricket.dto;

import com.tekion.cricket.dto.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name, List<Player> players) {
        this.name = name;
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        List<Player> playersList = new ArrayList<>(players);
        return playersList;
    }

}
