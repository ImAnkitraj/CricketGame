package com.tekion.cricket.services;

import com.tekion.cricket.dto.*;

import java.util.*;

import static com.tekion.cricket.utils.Constants.*;

public abstract class Match {

    protected Date date;
    protected MatchScorecard matchScorecard;
    protected String venue;

    public Match(Date date, String venue, Integer overs) {
        this.date = date;
        List<Team> teams = selectTeams();
        this.matchScorecard = new MatchScorecard(teams, overs);
        matchScorecard.getTeam1Inning1Scorecard().setBatting(true);
        matchScorecard.getTeam2Inning1Scorecard().setBatting(false);
        this.venue = venue;
    }

    public void play() {
        Team team1 = matchScorecard.getTeams().get(0);
        Team team2 = matchScorecard.getTeams().get(1);

        //team1 innings
        for (int overNumber = 1; (overNumber <= matchScorecard.getOvers()) &&
                                 matchScorecard.getTeam1Inning1Scorecard().getTotalWickets() < 10; overNumber++) {
            Over over = new Over(matchScorecard.getTeam1Inning1Scorecard(), matchScorecard.getTeam2Inning1Scorecard());
            System.out.println(ANSI_CYAN + "\nOver: " + overNumber);
            over.throwOver();
            matchScorecard.getTeam1Inning1Scorecard().swapStriker();
            matchScorecard.getTeam2Inning1Scorecard().changeBowler();
        }

        matchScorecard.getTeam1Inning1Scorecard().setBatting(false);
        matchScorecard.getTeam2Inning1Scorecard().setBatting(true);

        //team2 innings
        for (int overNumber = 1; (overNumber <= matchScorecard.getOvers()) &&
                                 (matchScorecard.getTeam2Inning1Scorecard().getTotalWickets() < 10) &&
                                 (matchScorecard.getTeam2Inning1Scorecard().getTotalRuns() <=
                                  matchScorecard.getTeam1Inning1Scorecard().getTotalRuns()); overNumber++) {
            Over over = new Over(matchScorecard.getTeam1Inning1Scorecard(), matchScorecard.getTeam2Inning1Scorecard());
            System.out.println(ANSI_GREEN + "\nOver: " + overNumber);
            over.throwOver();
            matchScorecard.getTeam2Inning1Scorecard().swapStriker();
            matchScorecard.getTeam1Inning1Scorecard().changeBowler();
        }

        ScoreboardDisplayer.displayResult(team1, team2, matchScorecard);
    }

    private static void teamSelectPrompt(List<Team> teams) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Select " + teams.size() + 1 + "...");

        for (int i = 0; i < IPLTeams.iplTeams.size(); i++) {
            System.out.println(i + 1 + ". " + IPLTeams.iplTeams.get(i).getName());
        }

        System.out.println("Other keys to Exit");

        try {
            int selectedTeam = sc.nextInt();
            teams.add(IPLTeams.iplTeams.remove(selectedTeam - 1));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exiting ....");
            System.exit(0);
        }
    }

    protected static List<Team> selectTeams() {

        List<Team> teams = new ArrayList<>();
        IPLTeams.initIplTeams();

        //team1
        teamSelectPrompt(teams);

        //team2
        teamSelectPrompt(teams);

        return teams;
    }

    private void swapScorecard() {
        Scorecard temp = matchScorecard.getTeam1Inning1Scorecard();
        matchScorecard.setTeam1Inning1Scorecard(matchScorecard.getTeam2Inning1Scorecard());
        matchScorecard.setTeam2Inning1Scorecard(temp);
        matchScorecard.getTeam2Inning1Scorecard().setBatting(false);
        matchScorecard.getTeam1Inning1Scorecard().setBatting(true);
        Collections.swap(matchScorecard.getTeams(), 0, 1);
    }

    public void toss() {
        Random random = new Random();
        int toss = random.nextInt(2);
        if (toss == 0) {
            int choice = random.nextInt(2);
            if (choice == 1) {
                swapScorecard();
                System.out.println(
                        matchScorecard.getTeams().get(1).getName() + " won the toss and elected to bowl first.");
            } else {
                System.out.println(
                        matchScorecard.getTeams().get(0).getName() + " won the toss and elected to bat first.");
            }
        } else {
            int choice = random.nextInt(2);
            if (choice == 0) {
                swapScorecard();
                System.out.println(
                        matchScorecard.getTeams().get(0).getName() + " won the toss and elected to bat first.");
            } else {
                System.out.println(
                        matchScorecard.getTeams().get(1).getName() + " won the toss and elected to bowl first.");
            }
        }
    }

    public static Match selectMatchFormat() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Select format of match :");
        System.out.println("\t1. T20 Match");
        System.out.println("\t2. ODI Match");

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
                    default: {
                        match = null;
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Exiting...");
                System.exit(0);
            }

        }
        return match;
    }

}
