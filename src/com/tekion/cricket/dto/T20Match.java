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

    private void displayResult(Team team1, Team team2) {
        System.out.println("\nTeam: " + team1.getName() + matchScorecard.getTeam1Scorecard() + "\n");
        List<Player> players1 = matchScorecard.getTeam1Scorecard().getPlayers();
        System.out.println("Name\t\tRuns/Balls\t\t4s\t\t6s\t\tStrike rate");
        for (int i = 0; i < players1.size(); i++) {
            System.out.format("%s\t\t%d/%d\t\t%d\t\t%d\t\t%.2f\n", players1.get(i).getName(), players1.get(i).getRuns(), players1.get(i).getBallsfaced(), players1.get(i).getFours(), players1.get(i).getSixes(), ((float) players1.get(i).getRuns() / players1.get(i).getBallsfaced()) * 100);
        }

        List<Player> bowlers = matchScorecard.getTeam1Scorecard().getBowlers();
        System.out.println("-----------------------------------");
        System.out.println("Name\t\tOvers\t\tRuns\t\tWickets");
        for (int i = 0; i < bowlers.size(); i++) {
            Bowler b = ((Bowler) bowlers.get(i));
            System.out.println(b.getName() + "\t\t" + (b.getBallsDone() / 6) + "." + (b.getBallsDone() % 6) + "\t\t" + b.getRunsGiven() + "\t\t" + b.getWickets());
        }
        System.out.println(ANSI_RED + "\nTeam: " + team2.getName() + matchScorecard.getTeam2Scorecard() + "\n");
        List<Player> players2 = matchScorecard.getTeam2Scorecard().getPlayers();
        System.out.println("Name\t\tRuns/Balls\t\t4s\t\t6s\t\tStrike rate");

        for (int i = 0; i < players2.size(); i++) {
            System.out.format("%s\t\t%d/%d\t\t%d\t\t%d\t\t%.2f\n", players2.get(i).getName(), players2.get(i).getRuns(), players2.get(i).getBallsfaced(), players2.get(i).getFours(), players2.get(i).getSixes(), ((float) players2.get(i).getRuns() / players2.get(i).getBallsfaced()) * 100);
        }
        bowlers = matchScorecard.getTeam2Scorecard().getBowlers();
        System.out.println("-----------------------------------");
        System.out.println("Name\t\tOvers\t\tRuns\t\tWickets");
        for (int i = 0; i < bowlers.size(); i++) {
            Bowler b = ((Bowler) bowlers.get(i));
            System.out.println(b.getName() + "\t\t" + (b.getBallsDone() / 6) + "." + (b.getBallsDone() % 6) + "\t\t" + b.getRunsGiven() + "\t\t" + b.getWickets());
        }
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
