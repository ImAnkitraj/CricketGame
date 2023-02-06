package com.tekion.cricket.dto;

import com.tekion.cricket.enums.BallOutcome;
import com.tekion.cricket.utils.Constants;

import java.util.ArrayList;
import java.util.List;


public class Over {
    private List<Ball> balls = new ArrayList<>();
    private Scorecard team1Scorecard;
    private Scorecard team2Scorecard;
    private Integer runs;
    private Integer wickets;
    private Double currentOver = 0.0;

    public void throwOver() {
        if (team1Scorecard.getBatting() == true) {
            team1Innings();
        } else {
            team2Innings();
        } System.out.println("\n\n" + Constants.ANSI_RED + "Runs in over : " + runs);
        System.out.println("Wickets in over : " + wickets);
    }

    private void team1Innings() {
        for (int i = 0; i < 6 && team1Scorecard.getTotalWickets() < 10; i++) {
            Ball currentBall = new Ball(team1Scorecard.getStriker(), team2Scorecard.getBowler());
            BallOutcome outcome = currentBall.getBallOutcome();
            if (outcome == BallOutcome.One || outcome == BallOutcome.Three) {
                team1Scorecard.swapStriker();
            }
            if(outcome == BallOutcome.Four) {
                team1Scorecard.getStriker().incrementFours();
            }
            if(outcome == BallOutcome.Four) {
                team1Scorecard.getStriker().incrementSixes();
            }
            runs += currentBall.getRun();
            team1Scorecard.getStriker().setRuns(currentBall.getRun());
            team1Scorecard.getStriker().incrementBallsfaced();
            team1Scorecard.setTotalRuns(team1Scorecard.getTotalRuns() + currentBall.getRun());
            if (currentBall.getBallOutcome() == BallOutcome.Wicket) {
                wickets++;
                team1Scorecard.setTotalWickets(team1Scorecard.getTotalWickets() + 1);
                System.out.println("\n\t" + team1Scorecard.getStriker().getName() + " goes to pavilion..");
                team1Scorecard.changeBatman();

                if (team1Scorecard.getTotalWickets() == 10) {
                    System.out.println("\tALL OUT...");
                } else {
                    System.out.println("\t" + team1Scorecard.getStriker().getName() + " comes to bat..");
                }
            }
        }
    }

    private void team2Innings() {
        for (int i = 0; i < 6 && (team2Scorecard.getTotalWickets() < 10) && (team2Scorecard.getTotalRuns() <= team1Scorecard.getTotalRuns()); i++) {
            Ball currentBall = new Ball(team2Scorecard.getStriker(), team1Scorecard.getBowler());
            BallOutcome outcome = currentBall.getBallOutcome();
            if (outcome == BallOutcome.One || outcome == BallOutcome.Three) {
                team2Scorecard.swapStriker();
            }
            if(outcome == BallOutcome.Four) {
                team2Scorecard.getStriker().incrementFours();
            }
            if(outcome == BallOutcome.Four) {
                team2Scorecard.getStriker().incrementSixes();
            }
            runs += currentBall.getRun();
            team2Scorecard.getStriker().setRuns(currentBall.getRun());
            team2Scorecard.getStriker().incrementBallsfaced();
            team2Scorecard.setTotalRuns(team2Scorecard.getTotalRuns() + currentBall.getRun());
            if (currentBall.getBallOutcome() == BallOutcome.Wicket) {
                wickets++;
                team2Scorecard.setTotalWickets(team2Scorecard.getTotalWickets() + 1);
                System.out.println("\n\t" + team2Scorecard.getStriker().getName() + " goes to pavilion..");
                team2Scorecard.changeBatman();
                if (team2Scorecard.getTotalWickets() == 10) {
                    System.out.println("\n\tALL OUT...");
                } else {
                    System.out.println("\t" + team2Scorecard.getStriker().getName() + " comes to bat...");
                }
            }
        }
    }

    public Integer getRuns() {
        return runs;
    }

    public Over(Scorecard team1Scorecard, Scorecard team2Scorecard) {
        this.team1Scorecard = team1Scorecard;
        this.team2Scorecard = team2Scorecard;
        this.runs = new Integer(0);
        this.wickets = new Integer(0);
    }
}
