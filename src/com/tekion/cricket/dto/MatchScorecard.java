package com.tekion.cricket.dto;

import java.util.List;

public class MatchScorecard {
    private Scorecard team1Scorecard;
    List<Team> teams;
    private Scorecard team2Scorecard;

    public MatchScorecard(List<Team> teams) {
        this.team1Scorecard = teams.get(0).getScorecard();
        this.team2Scorecard = teams.get(1).getScorecard();
        this.teams = teams;
    }

    public Scorecard getTeam1Scorecard() {
        return team1Scorecard;
    }

    public Scorecard getTeam2Scorecard() {
        return team2Scorecard;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeam1Scorecard(Scorecard team1Scorecard) {
        this.team1Scorecard = team1Scorecard;
    }

    public void setTeam2Scorecard(Scorecard team2Scorecard) {
        this.team2Scorecard = team2Scorecard;
    }
}
