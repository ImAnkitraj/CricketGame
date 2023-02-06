package com.tekion.cricket.dto;

import java.util.ArrayList;
import java.util.List;

public class IPLTeams {
    public static List<Team> iplTeams = new ArrayList<>();
    protected static List<Player> initializeCSKPlayers() {
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

    protected static List<Player> initializeMIPlayers() {
        List<Player> team2Players = new ArrayList<>();
        team2Players.add(new Batsman("Rohit Sharma"));
        team2Players.add(new Batsman("Tilak Verma"));
        team2Players.add(new Batsman("Suryakumar Yadav"));
        team2Players.add(new Batsman("Dewald Brewis"));
        team2Players.add(new Batsman("Cameron Green"));
        team2Players.add(new Batsman("Tim David"));
        team2Players.add(new Bowler("Duan Jansen"));
        team2Players.add(new Bowler("Jofra Archer"));
        team2Players.add(new Bowler("Jason Behrendorff"));
        team2Players.add(new Bowler("Jasprit Bumrah"));
        team2Players.add(new Bowler("Piyush Chawla"));
        return team2Players;
    }

    protected static List<Player> initializeRCBPlayers() {
        List<Player> team2Players = new ArrayList<>();
        team2Players.add(new Batsman("Virat Kohli"));
        team2Players.add(new Batsman("Faf Du Plesis"));
        team2Players.add(new Batsman("Rajat Patidar"));
        team2Players.add(new Batsman("Glenn Maxwell"));
        team2Players.add(new Batsman("Dinesh Karthik"));
        team2Players.add(new Batsman("Mahipal Lomror"));
        team2Players.add(new Bowler(" Shahbaz Ahmed"));
        team2Players.add(new Bowler("Wanindu Hasaranga"));
        team2Players.add(new Bowler("Harshal Patel"));
        team2Players.add(new Bowler("Josh Hazlewood"));
        team2Players.add(new Bowler("Mohammad Siraj"));

        return team2Players;
    }
    public static void initIplTeams() {
        iplTeams.add(new Team("Chennai Super Kings", initializeCSKPlayers(), true));
        iplTeams.add(new Team("Mumbai Indians", initializeMIPlayers(), true));
        iplTeams.add(new Team("Royal Challengs Bangalore", initializeRCBPlayers(), true));
    }
}
