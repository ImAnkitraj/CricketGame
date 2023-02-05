package com.tekion.cricket.dto;

import com.tekion.cricket.utils.Match;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.tekion.cricket.utils.Constants.*;
import static com.tekion.cricket.utils.Constants.ANSI_BLUE;

public class OdiMatch extends Match {
    private Integer overs;
    private Integer overLimitPerBowler;

    public OdiMatch(Date date, String venue) {
        this.date = date;
        List<Player> team1Players = initializeTeam1Players();
        List<Player> team2Players = initializeTeam2Players();
        List<Team> teams = new ArrayList<>();
        teams.add(new Team("Chennai Super Kings", team1Players, true));
        teams.add(new Team("Mumbai Indians", team2Players, false));
        this.matchScorecard = new MatchScorecard(teams);
        this.venue = venue;
        this.overs = 50;
        this.overLimitPerBowler = 10;
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
        for (int overNumber = 1; (overNumber <= this.overs) && matchScorecard.getTeam2Scorecard().getTotalWickets() < 10 && matchScorecard.getTeam2Scorecard().getTotalRuns() < matchScorecard.getTeam1Scorecard().getTotalRuns(); overNumber++) {
            Over over = new Over(matchScorecard.getTeam1Scorecard(), matchScorecard.getTeam2Scorecard());
            System.out.println(ANSI_GREEN + "\nOver: " + overNumber);
            over.throwOver();
            matchScorecard.getTeam2Scorecard().swapStriker();
            matchScorecard.getTeam1Scorecard().changeBowler();
        }

        System.out.println("\nTeam: " + team1.getName() + matchScorecard.getTeam1Scorecard());
        System.out.println(ANSI_RED + "\nTeam: " + team2.getName() + matchScorecard.getTeam2Scorecard());

        System.out.print(ANSI_BLUE);
        if (matchScorecard.getTeam1Scorecard().getTotalRuns() > matchScorecard.getTeam2Scorecard().getTotalRuns()) {
            System.out.println(team1.getName() + " wins by " + (matchScorecard.getTeam1Scorecard().getTotalRuns() - matchScorecard.getTeam2Scorecard().getTotalRuns()) + " runs");
        } else if (matchScorecard.getTeam1Scorecard().getTotalRuns() < matchScorecard.getTeam2Scorecard().getTotalRuns()) {
            System.out.println(team2.getName() + " wins by " + (10 - matchScorecard.getTeam2Scorecard().getTotalWickets()) + " wickets");
        } else {
            System.out.println("Match drawn");
        }
    }
}
