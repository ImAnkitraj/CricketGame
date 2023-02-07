package com.tekion.cricket.dto;

import com.tekion.cricket.services.Match;

import java.util.Date;

import static com.tekion.cricket.utils.Constants.*;

public class OdiMatch extends Match {
    private Integer overs;
    private Integer overLimitPerBowler;

    public OdiMatch(Date date, String venue) {
        super(date, venue);
        this.overs = 50;
        this.overLimitPerBowler = 10;
    }

    @Override
    public void play() {
        Team team1 = matchScorecard.getTeams().get(0);
        Team team2 = matchScorecard.getTeams().get(1);
        //team1 innings
        for (int overNumber = 1; (overNumber <= this.overs) && matchScorecard.getTeam1Inning1Scorecard().getTotalWickets() < 10; overNumber++) {
            Over over = new Over(matchScorecard.getTeam1Inning1Scorecard(), matchScorecard.getTeam2Inning1Scorecard());
            System.out.println(ANSI_CYAN + "\nOver: " + overNumber);
            over.throwOver();
            matchScorecard.getTeam1Inning1Scorecard().swapStriker();
            matchScorecard.getTeam2Inning1Scorecard().changeBowler();
        }

        matchScorecard.getTeam1Inning1Scorecard().setBatting(false);
        matchScorecard.getTeam2Inning1Scorecard().setBatting(true);
        //team2 innings
        for (int overNumber = 1; (overNumber <= this.overs) && matchScorecard.getTeam2Inning1Scorecard().getTotalWickets() < 10 && matchScorecard.getTeam2Inning1Scorecard().getTotalRuns() < matchScorecard.getTeam1Inning1Scorecard().getTotalRuns(); overNumber++) {
            Over over = new Over(matchScorecard.getTeam1Inning1Scorecard(), matchScorecard.getTeam2Inning1Scorecard());
            System.out.println(ANSI_GREEN + "\nOver: " + overNumber);
            over.throwOver();
            matchScorecard.getTeam2Inning1Scorecard().swapStriker();
            matchScorecard.getTeam1Inning1Scorecard().changeBowler();
        }

        displayResult(team1, team2);
    }
}
