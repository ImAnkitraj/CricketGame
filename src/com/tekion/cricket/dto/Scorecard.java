package com.tekion.cricket.dto;

import com.tekion.cricket.utils.Constants;

import java.util.*;

public class Scorecard {
    // match // team // currOver
    private Integer totalWickets;
    private Integer totalRuns;
    private List<Player> players;
    private Player striker;
    private Player nonStriker;
    private Player bowler;
    private Boolean isBatting;
    Set<Player> played;
    Map<Player, Integer> bowled;
    List<Player> bowlers = new ArrayList<>();

    public Scorecard(List<Player> players, Boolean isBatting) {
        this.totalWickets = 0;
        this.totalRuns = 0;
        this.players = players;
        this.striker = players.get(0);
        this.nonStriker = players.get(1);
        this.isBatting = isBatting;
        this.played = new HashSet<>();
        this.bowled = new HashMap<>();

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) instanceof Bowler) {
                bowled.put(players.get(i), 0);
                if (this.bowler == null) {
                    this.bowler = players.get(i);
                }
                bowlers.add(players.get(i));
            }
        }
    }

    public Boolean getBatting() {
        return isBatting;
    }

    public void changeBatman() {
        for (int i = 0; i < players.size(); i++) {
            if (!played.contains(players.get(i)) && players.get(i) != striker && players.get(i) != nonStriker) {
                played.add(striker);
                setStriker(players.get(i));
                break;
            }
        }
    }

    public void changeBowler() {
        Random random = new Random();
        Player b = bowlers.get(random.nextInt(bowlers.size()));
        if (b.getId().equals(bowler.getId())) {
            changeBowler();
        } else {
            bowler = b;
        }
    }

    public Integer getTotalWickets() {
        return totalWickets;
    }

    public void setTotalWickets(Integer totalWickets) {
        this.totalWickets = totalWickets;
    }

    public Integer getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(Integer totalRuns) {
        this.totalRuns = totalRuns;
    }

    public void setBatting(Boolean batting) {
        isBatting = batting;
    }

    public Player getStriker() {
        return striker;
    }

    public void setStriker(Player striker) {
        this.striker = striker;
    }

    public Player getBowler() {
        return bowler;
    }

    public void setBowler(Player bowler) {
        this.bowler = bowler;
    }

    public Player getNonStriker() {
        return nonStriker;
    }

    public void setNonStriker(Player nonStriker) {
        this.nonStriker = nonStriker;
    }

    public void swapStriker() {
        Player tempPlayer = this.striker;
        this.striker = this.nonStriker;
        this.nonStriker = tempPlayer;
    }

    public String toString() {
        return "\n" + Constants.ANSI_PURPLE + "Scorecard: \n" + "\tRuns: " + totalRuns + "\n\tWickets: " + totalWickets;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
