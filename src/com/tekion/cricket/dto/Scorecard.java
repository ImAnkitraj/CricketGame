package com.tekion.cricket.dto;

import com.tekion.cricket.utils.Constants;

import java.util.*;

public class Scorecard {

    private Integer totalWickets = 0;
    private Integer totalRuns = 0;
    private List<Player> players;
    private Player striker;
    private Player nonStriker;
    private Player bowler;
    private Boolean isBatting = true;
    private ArrayList<Player> played = new ArrayList<>();
    private Integer totalBallsFaced = 0;
    private List<Player> bowlers = new ArrayList<>();

    public Scorecard(List<Player> players) {

        this.players = players;
        this.striker = this.players.get(0);
        this.nonStriker = this.players.get(1);

        for (int i = 0; i < this.players.size(); i++) {
            if (this.players.get(i) instanceof Bowler) {
                if (this.bowler == null) {
                    this.bowler = this.players.get(i);
                }
                bowlers.add(this.players.get(i));
            }
        }

        this.played.add(this.striker);
        this.played.add(this.nonStriker);

    }

    public Boolean getBatting() {
        return isBatting;
    }

    public void changeBatman() {
        for (int i = 0; i < this.players.size(); i++) {
            if (!played.contains(this.players.get(i)) && this.players.get(i) != striker &&
                this.players.get(i) != nonStriker) {
                setStriker(this.players.get(i));
                played.add((Batsman) striker);
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

    public void swapStriker() {
        Player tempPlayer = this.striker;
        this.striker = this.nonStriker;
        this.nonStriker = tempPlayer;
    }

    public String toString() {
        return "\n" + Constants.ANSI_PURPLE + "Scorecard: \n" + "\tRuns: " + totalRuns + "\n\tWickets: " +
               totalWickets + "\n\tOvers: " + totalBallsFaced / 6 + "." + totalBallsFaced % 6;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public List<Player> getBowlers() {
        return this.bowlers;
    }

    public ArrayList<Player> getPlayed() {
        return played;
    }

    public Integer getTotalBallsFaced() {
        return totalBallsFaced;
    }

    public void incrementTotalBallsBowled() {
        this.totalBallsFaced++;
    }
}
