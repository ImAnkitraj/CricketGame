package com.tekion.cricket.dto;

import com.tekion.cricket.services.Match;

import java.util.Date;
import java.util.List;

import static com.tekion.cricket.utils.Constants.*;
import static com.tekion.cricket.utils.Constants.ANSI_BLUE;

public class TestMatch extends Match {
    private Integer overs;
    private Integer overLimitPerBowler;

    public TestMatch(Date date, String venue) {
        super(date, venue);
        this.overs = 90;
        this.overLimitPerBowler = 30;
        this.matchScorecard.setTeam1Inning1Scorecard(new Scorecard(matchScorecard.getTeams().get(0).getPlayers()));
        this.matchScorecard.setTeam1Inning2Scorecard(new Scorecard(matchScorecard.getTeams().get(0).getPlayers()));
        this.matchScorecard.setTeam2Inning1Scorecard(new Scorecard(matchScorecard.getTeams().get(1).getPlayers()));
        this.matchScorecard.setTeam2Inning2Scorecard(new Scorecard(matchScorecard.getTeams().get(1).getPlayers()));
        matchScorecard.getTeam1Inning1Scorecard().setBatting(true);
        matchScorecard.getTeam2Inning1Scorecard().setBatting(false);
    }

    @Override
    public void play() {
        Team team1 = matchScorecard.getTeams().get(0);
        Team team2 = matchScorecard.getTeams().get(1);
        //team1 inning 1
        for (int overNumber = 1; (overNumber <= this.overs) && matchScorecard.getTeam1Inning1Scorecard().getTotalWickets() < 10; overNumber++) {
            Over over = new Over(matchScorecard.getTeam1Inning1Scorecard(), matchScorecard.getTeam2Inning1Scorecard());
            System.out.println(ANSI_CYAN + "\nOver: " + overNumber);
            over.throwOver();
            matchScorecard.getTeam1Inning1Scorecard().swapStriker();
            matchScorecard.getTeam2Inning1Scorecard().changeBowler();
        }

        matchScorecard.getTeam1Inning1Scorecard().setBatting(false);
        matchScorecard.getTeam2Inning1Scorecard().setBatting(true);
        //team2 inning 1
        for (int overNumber = 1; (overNumber <= this.overs) && matchScorecard.getTeam2Inning1Scorecard().getTotalWickets() < 10 ; overNumber++) {
            Over over = new Over(matchScorecard.getTeam1Inning1Scorecard(), matchScorecard.getTeam2Inning1Scorecard());
            System.out.println(ANSI_GREEN + "\nOver: " + overNumber);
            over.throwOver();
            matchScorecard.getTeam2Inning1Scorecard().swapStriker();
            matchScorecard.getTeam1Inning1Scorecard().changeBowler();
        }

        matchScorecard.getTeam1Inning2Scorecard().setBatting(true);
        matchScorecard.getTeam2Inning2Scorecard().setBatting(false);
        //team1 inning 2
        for (int overNumber = 1; (overNumber <= this.overs) && matchScorecard.getTeam1Inning2Scorecard().getTotalWickets() < 10; overNumber++) {
            Over over = new Over(matchScorecard.getTeam1Inning2Scorecard(), matchScorecard.getTeam2Inning2Scorecard());
            System.out.println(ANSI_CYAN + "\nOver: " + overNumber);
            over.throwOver();
            matchScorecard.getTeam1Inning2Scorecard().swapStriker();
            matchScorecard.getTeam2Inning2Scorecard().changeBowler();
        }

        matchScorecard.getTeam1Inning2Scorecard().setBatting(false);
        matchScorecard.getTeam2Inning2Scorecard().setBatting(true);

        for (int overNumber = 1; (overNumber <= this.overs) && (matchScorecard.getTeam2Inning2Scorecard().getTotalWickets() < 10) && (matchScorecard.getTeam2Inning2Scorecard().getTotalRuns() < (matchScorecard.getTeam1Inning1Scorecard().getTotalRuns() + matchScorecard.getTeam1Inning2Scorecard().getTotalRuns() - matchScorecard.getTeam2Inning1Scorecard().getTotalRuns())) ; overNumber++) {
            Over over = new Over(matchScorecard.getTeam1Inning2Scorecard(), matchScorecard.getTeam2Inning2Scorecard());
            System.out.println(ANSI_GREEN + "\nOver: " + overNumber);
            over.throwOver();
            matchScorecard.getTeam2Inning2Scorecard().swapStriker();
            matchScorecard.getTeam1Inning2Scorecard().changeBowler();
        }

        displayResult(team1, team2);
    }
    @Override
    public void displayResult(Team team1, Team team2) {
        System.out.println(ANSI_RED + "\nTeam: " + team1.getName() +"\nInnings 1: "+ matchScorecard.getTeam1Inning1Scorecard() + "\n");
        List<Player> players1 = matchScorecard.getTeam1Inning1Scorecard().getPlayers();
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-20s | %-4s | %-4s | %-4s | %-4s | %-8s | \n", "Name", "Runs", "Balls", "4s", "6s", "Srike Rate");
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < players1.size(); i++) {
            System.out.format("| %-20s | %-4d | %-4d  | %-4d | %-4d | %-9.2f  | \n", players1.get(i).getName(), players1.get(i).getRuns(), players1.get(i).getBallsfaced(), players1.get(i).getFours(), players1.get(i).getSixes(), ((float) players1.get(i).getRuns() / players1.get(i).getBallsfaced()) * 100);
        }
        List<Player> bowlers = matchScorecard.getTeam2Inning1Scorecard().getBowlers();
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-20s | %-5s | %-5s | %-8s |\n", "Name", "Overs", "Runs", "Wickets");

        for (int i = 0; i < bowlers.size(); i++) {
            Bowler b = ((Bowler) bowlers.get(i));
            System.out.printf("| %-20s | %-5s | %-5s | %-8s |\n", b.getName(), (b.getBallsDone() / 6) + "." + (b.getBallsDone() % 6), b.getRunsGiven(), b.getWickets());
        }
        System.out.println(ANSI_RED + "\nTeam: " + team1.getName() +"\nInning 2: "+ matchScorecard.getTeam1Inning2Scorecard() + "\n");
        players1 = matchScorecard.getTeam1Inning2Scorecard().getPlayers();
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-20s | %-4s | %-4s | %-4s | %-4s | %-8s | \n", "Name", "Runs", "Balls", "4s", "6s", "Srike Rate");
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < players1.size(); i++) {
            System.out.format("| %-20s | %-4d | %-4d  | %-4d | %-4d | %-9.2f  | \n", players1.get(i).getName(), players1.get(i).getRuns(), players1.get(i).getBallsfaced(), players1.get(i).getFours(), players1.get(i).getSixes(), ((float) players1.get(i).getRuns() / players1.get(i).getBallsfaced()) * 100);
        }

        bowlers = matchScorecard.getTeam2Inning2Scorecard().getBowlers();
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-20s | %-5s | %-5s | %-8s |\n", "Name", "Overs", "Runs", "Wickets");

        for (int i = 0; i < bowlers.size(); i++) {
            Bowler b = ((Bowler) bowlers.get(i));
            System.out.printf("| %-20s | %-5s | %-5s | %-8s |\n", b.getName(), (b.getBallsDone() / 6) + "." + (b.getBallsDone() % 6), b.getRunsGiven(), b.getWickets());
        }


        System.out.println(ANSI_RED + "\nTeam: " + team2.getName() +"\n Inning 1: " + matchScorecard.getTeam2Inning1Scorecard() + "\n");
        List<Player> players2 = matchScorecard.getTeam2Inning1Scorecard().getPlayers();
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-20s | %-4s | %-4s | %-4s | %-4s | %-8s | \n", "Name", "Runs", "Balls", "4s", "6s", "Srike Rate");
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < players2.size(); i++) {
            System.out.format("| %-20s | %-4d | %-4d  | %-4d | %-4d | %-9.2f  | \n", players2.get(i).getName(), players2.get(i).getRuns(), players2.get(i).getBallsfaced(), players2.get(i).getFours(), players2.get(i).getSixes(), ((float) players2.get(i).getRuns() / players2.get(i).getBallsfaced()) * 100);
        }
        bowlers = matchScorecard.getTeam1Inning1Scorecard().getBowlers();
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-20s | %-5s | %-5s | %-8s |\n", "Name", "Overs", "Runs", "Wickets");
        for (int i = 0; i < bowlers.size(); i++) {
            Bowler b = ((Bowler) bowlers.get(i));
            System.out.printf("| %-20s | %-5s | %-5s | %-8s |\n", b.getName(), (b.getBallsDone() / 6) + "." + (b.getBallsDone() % 6), b.getRunsGiven(), b.getWickets());
        }
        System.out.println(ANSI_RED + "\nTeam: " + team2.getName() +"\n Inning 2: " + matchScorecard.getTeam2Inning2Scorecard() + "\n");
        players2 = matchScorecard.getTeam2Inning2Scorecard().getPlayers();
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-20s | %-4s | %-4s | %-4s | %-4s | %-8s | \n", "Name", "Runs", "Balls", "4s", "6s", "Srike Rate");
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < players2.size(); i++) {
            System.out.format("| %-20s | %-4d | %-4d  | %-4d | %-4d | %-9.2f  | \n", players2.get(i).getName(), players2.get(i).getRuns(), players2.get(i).getBallsfaced(), players2.get(i).getFours(), players2.get(i).getSixes(), ((float) players2.get(i).getRuns() / players2.get(i).getBallsfaced()) * 100);
        }
        bowlers = matchScorecard.getTeam1Inning2Scorecard().getBowlers();
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-20s | %-5s | %-5s | %-8s |\n", "Name", "Overs", "Runs", "Wickets");
        for (int i = 0; i < bowlers.size(); i++) {
            Bowler b = ((Bowler) bowlers.get(i));
            System.out.printf("| %-20s | %-5s | %-5s | %-8s |\n", b.getName(), (b.getBallsDone() / 6) + "." + (b.getBallsDone() % 6), b.getRunsGiven(), b.getWickets());
        }

        System.out.print(ANSI_BLUE);
        if (matchScorecard.getTeam1Inning1Scorecard().getTotalRuns() + matchScorecard.getTeam1Inning2Scorecard().getTotalRuns()  > matchScorecard.getTeam2Inning1Scorecard().getTotalRuns() + matchScorecard.getTeam2Inning2Scorecard().getTotalRuns()) {
            System.out.println(team1.getName() + " wins by " + ((matchScorecard.getTeam1Inning1Scorecard().getTotalRuns() + matchScorecard.getTeam1Inning2Scorecard().getTotalRuns())  - (matchScorecard.getTeam2Inning1Scorecard().getTotalRuns() + matchScorecard.getTeam2Inning2Scorecard().getTotalRuns())) + " runs");
        } else if (matchScorecard.getTeam1Inning1Scorecard().getTotalRuns() + matchScorecard.getTeam1Inning2Scorecard().getTotalRuns()  < matchScorecard.getTeam2Inning1Scorecard().getTotalRuns() + matchScorecard.getTeam2Inning2Scorecard().getTotalRuns()) {
            System.out.println(team2.getName() + " wins by " + (10 - matchScorecard.getTeam2Inning2Scorecard().getTotalWickets()) + " wickets");
        } else {
            System.out.println("Match drawn");
        }
    }
}
