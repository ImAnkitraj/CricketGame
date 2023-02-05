package com.tekion.cricket.utils;

import com.tekion.cricket.dto.*;

import java.util.*;

import static com.tekion.cricket.utils.Constants.MATCH_DATE;

public abstract class Match {
    protected Date date;
    protected MatchScorecard matchScorecard;
    protected String venue;


    abstract public void play();

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

    public static void main(String[] args) {
        Match match = new T20Match(new Date(MATCH_DATE), "Eden Gardens");
        match.toss();
        match.play();
    }

}
