package com.tekion.cricket.dto;

import com.tekion.cricket.dto.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;
    private Scorecard scorecard;

    public Team(String name, List<Player> players, boolean isBatting) {
        this.name = name;
        this.players = players;
        this.scorecard = new Scorecard(players, isBatting);
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        List<Player> playersList = new ArrayList<>(players);
        return playersList;
    }

    public Scorecard getScorecard() {
        return scorecard;
    }
}
