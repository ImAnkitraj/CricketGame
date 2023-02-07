package com.tekion.cricket.services;

import com.tekion.cricket.dto.*;

import java.util.*;
import java.util.concurrent.ExecutionException;

import static com.tekion.cricket.utils.Constants.ANSI_BLUE;
import static com.tekion.cricket.utils.Constants.ANSI_RED;

public abstract class Match {
    protected Date date;
    protected MatchScorecard matchScorecard;
    protected String venue;

    abstract public void play();

    protected static List<Team> selectTeams() {

        List<Team> teams = new ArrayList<>();

        IPLTeams.initIplTeams();
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Team1 ...");

        for (int i = 0; i < IPLTeams.iplTeams.size(); i++) {
            System.out.println(i + 1 + ". " + IPLTeams.iplTeams.get(i).getName());
        }

        System.out.println("Other keys to Exit");

        try {
            int selectedTeam = sc.nextInt();
            teams.add(IPLTeams.iplTeams.remove(selectedTeam - 1));

        } catch (Exception e) {
            System.out.println("Exiting ....");
            System.exit(0);
        }

        System.out.println("Select Team2 ...");
        for (int i = 0; i < IPLTeams.iplTeams.size(); i++) {
            System.out.println(i + 1 + ". " + IPLTeams.iplTeams.get(i).getName());
        }
        System.out.println("Other keys to Exit");

        try {
            int selectedTeam = sc.nextInt();
            teams.add(IPLTeams.iplTeams.get(selectedTeam - 1));
        } catch (Exception e) {
            System.out.println("Exiting ....");
            System.exit(0);
        }

        return teams;
    }

    public void toss() {
        Random random = new Random();
        int toss = random.nextInt(2);
        if (toss == 0) {
            int choice = random.nextInt(2);
            if (choice == 1) {
                Scorecard temp = matchScorecard.getTeam1Inning1Scorecard();
                matchScorecard.setTeam1Inning1Scorecard(matchScorecard.getTeam2Inning1Scorecard());
                matchScorecard.setTeam2Inning1Scorecard(temp);
                matchScorecard.getTeam2Inning1Scorecard().setBatting(false);
                matchScorecard.getTeam1Inning1Scorecard().setBatting(true);
                Collections.swap(matchScorecard.getTeams(), 0, 1);
                System.out.println(matchScorecard.getTeams().get(1).getName() + " won the toss and elected to bowl first.");
            } else {
                System.out.println(matchScorecard.getTeams().get(0).getName() + " won the toss and elected to bat first.");
            }
        } else {
            int choice = 0;
            if (choice == random.nextInt(2)) {
                Scorecard temp = matchScorecard.getTeam1Inning1Scorecard();
                matchScorecard.setTeam1Inning1Scorecard(matchScorecard.getTeam2Inning1Scorecard());
                matchScorecard.setTeam2Inning1Scorecard(temp);
                matchScorecard.getTeam2Inning1Scorecard().setBatting(false);
                matchScorecard.getTeam1Inning1Scorecard().setBatting(true);
                Collections.swap(matchScorecard.getTeams(), 0, 1);
                System.out.println(matchScorecard.getTeams().get(0).getName() + " won the toss and elected to bat first.");
            } else {
                System.out.println(matchScorecard.getTeams().get(1).getName() + " won the toss and elected to bowl first.");
            }
        }
    }

    public static Match selectMatchFormat() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Select format of match :");
        System.out.println("\t1. T20 Match");
        System.out.println("\t2. ODI Match");
//        System.out.println("\t3. Test Match");

        Match match = null;
        while (match == null) {
            System.out.println("Press 1 or 2 to select...\n");
            try {
                Integer matchFormat = sc.nextInt();
                switch (matchFormat) {
                    case 1: {
                        System.out.println("Enter Venue: ");
                        String venue = sc.next();
                        match = new T20Match(new Date(), venue);
                        break;
                    }
                    case 2: {
                        System.out.println("Enter Venue: ");
                        String venue = sc.next();
                        match = new OdiMatch(new Date(), venue);
                        break;
                    }
//                case 3: {
//                    System.out.println("Enter Venue: ");
//                    String venue = sc.next();
//                    match = new TestMatch(new Date(), venue);
//                    break;
//                }
                    default: {
                        match = null;
                        break;
                    }
                }
            }
            catch (Exception e) {
                System.out.println("Exiting...");
                System.exit(0);
            }

        }
        return match;
    }

    protected void displayResult(Team team1, Team team2) {

        System.out.println(ANSI_RED + "\nTeam: " + team1.getName() + matchScorecard.getTeam1Inning1Scorecard() + "\n");
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

        System.out.println(ANSI_RED + "\nTeam: " + team2.getName() + matchScorecard.getTeam2Inning1Scorecard() + "\n");
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
        System.out.print(ANSI_BLUE);
        if (matchScorecard.getTeam1Inning1Scorecard().getTotalRuns() > matchScorecard.getTeam2Inning1Scorecard().getTotalRuns()) {
            System.out.println(team1.getName() + " wins by " + (matchScorecard.getTeam1Inning1Scorecard().getTotalRuns() - matchScorecard.getTeam2Inning1Scorecard().getTotalRuns()) + " runs");
        } else if (matchScorecard.getTeam1Inning1Scorecard().getTotalRuns() < matchScorecard.getTeam2Inning1Scorecard().getTotalRuns()) {
            System.out.println(team2.getName() + " wins by " + (10 - matchScorecard.getTeam2Inning1Scorecard().getTotalWickets()) + " wickets");
        } else {
            System.out.println("Match drawn");
        }
    }

    public Match(Date date, String venue) {
        this.date = date;
        List<Team> teams = selectTeams();
        this.matchScorecard = new MatchScorecard(teams);
        matchScorecard.getTeam1Inning1Scorecard().setBatting(true);
        matchScorecard.getTeam2Inning1Scorecard().setBatting(false);
        this.venue = venue;
    }
}
