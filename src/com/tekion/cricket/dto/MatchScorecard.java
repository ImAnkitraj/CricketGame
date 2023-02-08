package com.tekion.cricket.dto;

import java.util.List;

public class MatchScorecard {
    private Scorecard team1Inning1Scorecard;

    private List<Team> teams;
    private Integer overs;

    private Scorecard team2Inning1Scorecard;


    public MatchScorecard(List<Team> teams, Integer overs) {
        this.team1Inning1Scorecard = new Scorecard(teams.get(0).getPlayers());
        this.team2Inning1Scorecard = new Scorecard(teams.get(1).getPlayers());
        this.teams = teams;
        this.overs = overs;
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

    public void setTeam2Inning1Scorecard(Scorecard team2Inning1Scorecard) {
        this.team2Inning1Scorecard = team2Inning1Scorecard;
    }

    public Integer getOvers() {
        return overs;
    }
}
