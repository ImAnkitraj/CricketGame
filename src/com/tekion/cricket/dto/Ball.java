package com.tekion.cricket.dto;

import com.tekion.cricket.enums.BallOutcome;
import com.tekion.cricket.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ball {

    private Player batsman;
    private Player bowler;
    private Integer run = 0;
    private BallOutcome ballOutcome;

    private void calcBallOutcome() {
        Random random = new Random();
        List<BallOutcome> ballOutcomes = new ArrayList<>(List.of(BallOutcome.values()));

        if (batsman instanceof Bowler) {
            if (random.nextInt(10) > 3) {
                List<BallOutcome> o = new ArrayList<>(List.of(BallOutcome.One, BallOutcome.Two, BallOutcome.Wicket));
                this.ballOutcome = o.get(random.nextInt(o.size()));
            } else {
                this.ballOutcome = ballOutcomes.get(random.nextInt(ballOutcomes.size() - 1));
            }
        } else {
            int probability = random.nextInt(10);
            if (probability < 3) {
                this.ballOutcome = ballOutcomes.get(random.nextInt(ballOutcomes.size()));
            } else if (probability > 5) {
                this.ballOutcome = ballOutcomes.get(random.nextInt(ballOutcomes.size() - 3));
            } else {
                this.ballOutcome = ballOutcomes.get(random.nextInt(ballOutcomes.size() - 1));
            }
        }

    }

    public Ball(Player batsman, Player bowler) {
        this.batsman = batsman;
        this.bowler = bowler;
        calcBallOutcome();
        this.run = ballOutcome.getValue();
        ((Bowler) this.bowler).setRunsGiven(this.run);
        ((Bowler) this.bowler).incrementBallsDone();
        if (ballOutcome == BallOutcome.Wicket) {
            ((Bowler) this.bowler).incrementWickets();
        }
        printBallResult();
    }

    private void printBallResult() {
        System.out.println();
        System.out.println(Constants.ANSI_GREEN + batsman.getName() + " faced the ball by " + bowler.getName());
        System.out.print(Constants.ANSI_PURPLE + "\tResult: " + ballOutcome);
    }

    public BallOutcome getBallOutcome() {
        return ballOutcome;
    }

    public Integer getRun() {
        return run;
    }
}
