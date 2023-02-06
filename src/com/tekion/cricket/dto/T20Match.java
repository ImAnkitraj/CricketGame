package com.tekion.cricket.dto;

import com.tekion.cricket.utils.Match;

import java.util.*;

import static com.tekion.cricket.utils.Constants.*;

public class T20Match extends Match {

    private Integer overs;
    private Integer overLimitPerBowler;

    public T20Match(Date date, String venue) {
        this.date = date;

        List<Team> teams = selectTeams();

        teams.get(0).getScorecard().setBatting(true);
        teams.get(1).getScorecard().setBatting(false);

        this.matchScorecard = new MatchScorecard(teams);
        this.venue = venue;
        this.overs = 20;
        this.overLimitPerBowler = 4;
    }


    @Override
    public void play() {
        Team team1 = matchScorecard.getTeams().get(0);
        Team team2 = matchScorecard.getTeams().get(1);
        //team1 innings
        for (int overNumber = 1; (overNumber <= this.overs) && matchScorecard.getTeam1Scorecard().getTotalWickets() < 10; overNumber++) {
            Over over = new Over(matchScorecard.getTeam1Scorecard(), matchScorecard.getTeam2Scorecard());
            System.out.println(ANSI_CYAN + "\nOver: " + overNumber);
            over.throwOver();
            matchScorecard.getTeam1Scorecard().swapStriker();
            matchScorecard.getTeam2Scorecard().changeBowler();
        }

        matchScorecard.getTeam1Scorecard().setBatting(false);
    matchScorecard.getTeam2Scorecard().setBatting(true);
        //team2 innings
        for (int overNumber = 1; (overNumber <= this.overs) && matchScorecard.getTeam2Scorecard().getTotalWickets() < 10 && matchScorecard.getTeam2Scorecard().getTotalRuns() <= matchScorecard.getTeam1Scorecard().getTotalRuns(); overNumber++) {
            Over over = new Over(matchScorecard.getTeam1Scorecard(), matchScorecard.getTeam2Scorecard());
            System.out.println(ANSI_GREEN + "\nOver: " + overNumber);
            over.throwOver();
            matchScorecard.getTeam2Scorecard().swapStriker();
            matchScorecard.getTeam1Scorecard().changeBowler();
        }
        displayResult(team1, team2);
    }
}
