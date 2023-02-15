package com.tekion.cricket.services;

import com.tekion.cricket.dto.*;

import java.util.List;

import static com.tekion.cricket.utils.Constants.ANSI_BLUE;
import static com.tekion.cricket.utils.Constants.ANSI_RED;

public class ScoreboardDisplayer {

    private static void battingDisplayFormatter(List<Player> players) {
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-20s | %-4s | %-4s | %-4s | %-4s | %-8s | \n", "Id", "Name", "Runs", "Balls", "4s",
                "6s", "Srike Rate");
        System.out.println("--------------------------------------------------------------------------");
        for (int i = 0; i < players.size(); i++) {
            Batsman batsman = (Batsman) players.get(i);
            System.out.format("| %-5s | %-20s | %-4d | %-4d  | %-4d | %-4d | %-9.2f  | \n", batsman.getId(),
                    batsman.getName(), batsman.getRuns(), batsman.getBallsFaced(), batsman.getFours(),
                    batsman.getSixes(), (batsman.getBallsFaced() == 0 ? 0.00 :
                                         ((float) batsman.getRuns() / batsman.getBallsFaced()) * 100));
        }
        System.out.println("--------------------------------------------------------------------------\n");

    }

    private static void bowlingDisplayFormatter(List<Player> bowlers) {

        System.out.println("---------------------------------------------------------------------");
        System.out.printf("| %-5s | %-20s | %-5s | %-5s | %-8s | %-7s |\n", "Id", "Name", "Overs", "Runs", "Wickets",
                "Economy");
        System.out.println("---------------------------------------------------------------------");


        for (int i = 0; i < bowlers.size(); i++) {
            Bowler b = ((Bowler) bowlers.get(i));
            System.out.printf("| %-5s | %-20s | %-5s | %-5s | %-8s | %-7.2f |\n", b.getId(), b.getName(),
                    (b.getBallsDone() / 6) + "." + (b.getBallsDone() % 6), b.getRunsGiven(), b.getWickets(),
                    ((float) b.getRunsGiven() / ((float) b.getBallsDone() / 6 + (float) (b.getBallsDone() % 6) / 6)));
        }
        System.out.println("---------------------------------------------------------------------");


    }

    private static void displayFinalResult(Team team1, Team team2, MatchScorecard matchScorecard) {
        System.out.print(ANSI_BLUE);
        if (matchScorecard.getTeam1Inning1Scorecard().getTotalRuns() >
            matchScorecard.getTeam2Inning1Scorecard().getTotalRuns()) {
            System.out.println(team1.getName() + " wins by " +
                               (matchScorecard.getTeam1Inning1Scorecard().getTotalRuns() -
                                matchScorecard.getTeam2Inning1Scorecard().getTotalRuns()) + " runs");
        } else if (matchScorecard.getTeam1Inning1Scorecard().getTotalRuns() <
                   matchScorecard.getTeam2Inning1Scorecard().getTotalRuns()) {
            System.out.println(
                    team2.getName() + " wins by " + (10 - matchScorecard.getTeam2Inning1Scorecard().getTotalWickets()) +
                    " wickets");
        } else {
            System.out.println("Match drawn");
        }
    }

    public static void displayResult(Team team1, Team team2, MatchScorecard matchScorecard) {

        System.out.println(ANSI_RED + "\nTeam: " + team1.getName() + matchScorecard.getTeam1Inning1Scorecard() + "\n");
        battingDisplayFormatter(matchScorecard.getTeam1Inning1Scorecard().getPlayed());
        bowlingDisplayFormatter(matchScorecard.getTeam2Inning1Scorecard().getBowlers());

        System.out.println(ANSI_RED + "\nTeam: " + team2.getName() + matchScorecard.getTeam2Inning1Scorecard() + "\n");
        battingDisplayFormatter(matchScorecard.getTeam2Inning1Scorecard().getPlayed());
        bowlingDisplayFormatter(matchScorecard.getTeam1Inning1Scorecard().getBowlers());

        displayFinalResult(team1, team2, matchScorecard);
    }
}
