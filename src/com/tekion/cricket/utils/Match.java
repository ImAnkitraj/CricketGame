package com.tekion.cricket.utils;

import com.tekion.cricket.dto.*;

import java.util.*;

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

        int selectedTeam = sc.nextInt();
        if (selectedTeam <= 0 || selectedTeam > IPLTeams.iplTeams.size()) {
            System.out.println("Exiting ....");
            System.exit(0);
        }
        teams.add(IPLTeams.iplTeams.remove(selectedTeam - 1));
        System.out.println("Select Team2 ...");
        for (int i = 0; i < IPLTeams.iplTeams.size(); i++) {
            System.out.println(i + 1 + ". " + IPLTeams.iplTeams.get(i).getName());
        }
        System.out.println("Other keys to Exit");

        selectedTeam = sc.nextInt();
        if (selectedTeam <= 0 || selectedTeam > IPLTeams.iplTeams.size()) {
            System.out.println("Exiting ....");
            System.exit(0);
        }
        teams.add(IPLTeams.iplTeams.get(selectedTeam - 1));
        return teams;
    }

    protected static List<Player> initializeTeam1Players() {
        List<Player> team1Players = new ArrayList<>();
        team1Players.add(new Batsman("Ruturaj Gaikwad"));
        team1Players.add(new Batsman("Devon Convoy"));
        team1Players.add(new Batsman("Moen Ali"));
        team1Players.add(new Batsman("Ambati Rayudu"));
        team1Players.add(new Batsman("MS Dhoni"));
        team1Players.add(new Batsman("Ravindra Jadeja"));
        team1Players.add(new Bowler("Ben Stokes"));
        team1Players.add(new Bowler("Deepak Chahar"));
        team1Players.add(new Bowler("Mukesh Choudhary"));
        team1Players.add(new Bowler("Maheesh Theekshana"));
        team1Players.add(new Bowler("Tushar Deshpande"));
        return team1Players;
    }

    protected static List<Player> initializeTeam2Players() {
        List<Player> team2Players = new ArrayList<>();
        team2Players.add(new Batsman("Rohit Sharma"));
        team2Players.add(new Batsman("Tilak Verma"));
        team2Players.add(new Batsman("Suryakumar Yadav"));
        team2Players.add(new Batsman("Dewald Brewis"));
        team2Players.add(new Batsman("Cameron Green"));
        team2Players.add(new Bowler("Duan Jansen"));
        team2Players.add(new Bowler("Jofra Archer"));
        team2Players.add(new Bowler("Jason Behrendorff"));
        team2Players.add(new Bowler("Jasprit Bumrah"));
        team2Players.add(new Bowler("Piyush Chawla"));
        return team2Players;
    }

    protected void toss() {
        Random random = new Random();
        int toss = random.nextInt(2);
        if (toss == 0) {
            int choice = random.nextInt(2);
            if (choice == 1) {
                Scorecard temp = matchScorecard.getTeam1Scorecard();
                matchScorecard.setTeam1Scorecard(matchScorecard.getTeam2Scorecard());
                matchScorecard.setTeam2Scorecard(temp);
                matchScorecard.getTeam2Scorecard().setBatting(false);
                matchScorecard.getTeam1Scorecard().setBatting(true);
                Collections.swap(matchScorecard.getTeams(), 0, 1);
                System.out.println(matchScorecard.getTeams().get(1).getName() + " won the toss and elected to bowl first.");
            } else {
                System.out.println(matchScorecard.getTeams().get(0).getName() + " won the toss and elected to bat first.");
            }
        } else {
            int choice = 0;
            if (choice == random.nextInt(2)) {
                Scorecard temp = matchScorecard.getTeam1Scorecard();
                matchScorecard.setTeam1Scorecard(matchScorecard.getTeam2Scorecard());
                matchScorecard.setTeam2Scorecard(temp);
                matchScorecard.getTeam2Scorecard().setBatting(false);
                matchScorecard.getTeam1Scorecard().setBatting(true);
                Collections.swap(matchScorecard.getTeams(), 0, 1);
                System.out.println(matchScorecard.getTeams().get(0).getName() + " won the toss and elected to bat first.");
            } else {
                System.out.println(matchScorecard.getTeams().get(1).getName() + " won the toss and elected to bowl first.");
            }
        }
    }

    private static Match selectMatchFormat() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Select format of match :");
        System.out.println("\t1. T20 Match");
        System.out.println("\t2. ODI Match");
        System.out.println("\t3. Test Match");

        Match match = null;
        while (match == null) {
            System.out.println("Press 1, 2, or 3 to select...\n");
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
                case 3: {
                    System.out.println("Enter Venue: ");
                    String venue = sc.next();
                    match = new TestMatch(new Date(), venue);
                    break;
                }
                default: {
                    match = null;
                    break;
                }
            }
        }
        return match;
    }

    protected void displayResult(Team team1, Team team2) {
        System.out.println("\nTeam: " + team1.getName() + matchScorecard.getTeam1Scorecard() + "\n");
        List<Player> players1 = matchScorecard.getTeam1Scorecard().getPlayers();
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-20s | %-4s | %-4s | %-4s | %-4s | %-8s | \n", "Name", "Runs", "Balls", "4s", "6s", "Srike Rate");
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < players1.size(); i++) {
            System.out.format("| %-20s | %-4d | %-4d  | %-4d | %-4d | %-9.2f  | \n", players1.get(i).getName(), players1.get(i).getRuns(), players1.get(i).getBallsfaced(), players1.get(i).getFours(), players1.get(i).getSixes(), ((float) players1.get(i).getRuns() / players1.get(i).getBallsfaced()) * 100);
        }
        List<Player> bowlers = matchScorecard.getTeam2Scorecard().getBowlers();
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-20s | %-5s | %-5s | %-8s |\n", "Name", "Overs", "Runs", "Wickets");

        for (int i = 0; i < bowlers.size(); i++) {
            Bowler b = ((Bowler) bowlers.get(i));
            System.out.printf("| %-20s | %-5s | %-5s | %-8s |\n", b.getName(), (b.getBallsDone() / 6) + "." + (b.getBallsDone() % 6), b.getRunsGiven(), b.getWickets());
        }

        System.out.println(ANSI_RED + "\nTeam: " + team2.getName() + matchScorecard.getTeam2Scorecard() + "\n");
        List<Player> players2 = matchScorecard.getTeam2Scorecard().getPlayers();
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-20s | %-4s | %-4s | %-4s | %-4s | %-8s | \n", "Name", "Runs", "Balls", "4s", "6s", "Srike Rate");
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < players2.size(); i++) {
            System.out.format("| %-20s | %-4d | %-4d  | %-4d | %-4d | %-9.2f  | \n", players2.get(i).getName(), players2.get(i).getRuns(), players2.get(i).getBallsfaced(), players2.get(i).getFours(), players2.get(i).getSixes(), ((float) players2.get(i).getRuns() / players2.get(i).getBallsfaced()) * 100);
        }
        bowlers = matchScorecard.getTeam1Scorecard().getBowlers();
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-20s | %-5s | %-5s | %-8s |\n", "Name", "Overs", "Runs", "Wickets");
        for (int i = 0; i < bowlers.size(); i++) {
            Bowler b = ((Bowler) bowlers.get(i));
            System.out.printf("| %-20s | %-5s | %-5s | %-8s |\n", b.getName(), (b.getBallsDone() / 6) + "." + (b.getBallsDone() % 6), b.getRunsGiven(), b.getWickets());
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

    public static void main(String[] args) {
        Match match = selectMatchFormat();
        match.toss();
        match.play();
    }

}
