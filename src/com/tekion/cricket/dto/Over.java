package com.tekion.cricket.dto;

import com.tekion.cricket.enums.BallOutcome;
import com.tekion.cricket.utils.Constants;


public class Over {
    private Scorecard team1Scorecard;
    private Scorecard team2Scorecard;
    Integer currentOver = 0;

    public void throwOver() {
        if (team1Scorecard.getBatting() == true) {
            team1Innings();
        } else {
            team2Innings();
        }

    }

    private static void ballOutcomeActions(Ball currentBall, BallOutcome ballOutcome, Scorecard teamScorecard) {
        if (ballOutcome == BallOutcome.One || ballOutcome == BallOutcome.Three) {
            teamScorecard.swapStriker();
        }
        if (ballOutcome == BallOutcome.Four) {
            teamScorecard.getStriker().incrementFours();
        }
        if (ballOutcome == BallOutcome.Six) {
            teamScorecard.getStriker().incrementSixes();
        }
        teamScorecard.getStriker().setRuns(currentBall.getRun());
        teamScorecard.getStriker().incrementBallsfaced();
        teamScorecard.setTotalRuns(teamScorecard.getTotalRuns() + currentBall.getRun());
        if (currentBall.getBallOutcome() == BallOutcome.Wicket) {
            teamScorecard.setTotalWickets(teamScorecard.getTotalWickets() + 1);
            System.out.println("\n\t" + teamScorecard.getStriker().getName() + " goes to pavilion..");
            teamScorecard.changeBatman();
            if (teamScorecard.getTotalWickets() == 10) {
                System.out.println("\tALL OUT...");
            } else {
                System.out.println("\t" + teamScorecard.getStriker().getName() + " comes to bat..");
            }
        }

    }

    private void team1Innings() {
        for (int i = 0; i < 6 && team1Scorecard.getTotalWickets() < 10; i++) {
            Ball currentBall = new Ball(team1Scorecard.getStriker(), team2Scorecard.getBowler());
            BallOutcome outcome = currentBall.getBallOutcome();
            ballOutcomeActions(currentBall, outcome, team1Scorecard);
        }

    }

    private void team2Innings() {
        for (int i = 0; i < 6 && (team2Scorecard.getTotalWickets() < 10) && (team2Scorecard.getTotalRuns() <= team1Scorecard.getTotalRuns()); i++) {
            Ball currentBall = new Ball(team2Scorecard.getStriker(), team1Scorecard.getBowler());
            BallOutcome outcome = currentBall.getBallOutcome();
            ballOutcomeActions(currentBall, outcome, team2Scorecard);
        }
    }

    public Over(Scorecard team1Scorecard, Scorecard team2Scorecard) {
        this.team1Scorecard = team1Scorecard;
        this.team2Scorecard = team2Scorecard;
    }
}
