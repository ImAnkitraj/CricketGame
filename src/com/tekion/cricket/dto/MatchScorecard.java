package com.tekion.cricket.dto;

import java.util.List;

public class MatchScorecard {
    private Scorecard team1Inning1Scorecard = null;
    private Scorecard team1Inning2Scorecard = null;

    List<Team> teams;
    private Scorecard team2Inning1Scorecard = null;
    private Scorecard team2Inning2Scorecard = null;


    public MatchScorecard(List<Team> teams ) {
        this.team1Inning1Scorecard = new Scorecard(teams.get(0).getPlayers());
        this.team2Inning1Scorecard = new Scorecard(teams.get(1).getPlayers());
        this.teams = teams;
    }

    public Scorecard getTeam1Inning1Scorecard() {
        return team1Inning1Scorecard;
    }

    public Scorecard getTeam2Inning1Scorecard() {
        return team2Inning1Scorecard;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeam1Inning1Scorecard(Scorecard team1Inning1Scorecard) {
        this.team1Inning1Scorecard = team1Inning1Scorecard;
    }

    public Scorecard getTeam1Inning2Scorecard() {
        return team1Inning2Scorecard;
    }

    public void setTeam1Inning2Scorecard(Scorecard team1Inning2Scorecard) {
        this.team1Inning2Scorecard = team1Inning2Scorecard;
    }

    public Scorecard getTeam2Inning2Scorecard() {
        return team2Inning2Scorecard;
    }

    public void setTeam2Inning2Scorecard(Scorecard team2Inning2Scorecard) {
        this.team2Inning2Scorecard = team2Inning2Scorecard;
    }

    public void setTeam2Inning1Scorecard(Scorecard team2Inning1Scorecard) {
        this.team2Inning1Scorecard = team2Inning1Scorecard;
    }
}
