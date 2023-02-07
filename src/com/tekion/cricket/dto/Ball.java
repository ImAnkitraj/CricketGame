package com.tekion.cricket.dto;

import com.tekion.cricket.enums.BallOutcome;
import com.tekion.cricket.utils.Constants;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
        this.ballOutcome = ballOutcomes.get(random.nextInt(ballOutcomes.size()));

        if (batsman instanceof Batsman) {
            if (random.nextInt(10) > 3) {
                List<BallOutcome> o = new ArrayList<>(List.of(BallOutcome.One, BallOutcome.Two, BallOutcome.Three, BallOutcome.Four, BallOutcome.Six, BallOutcome.Three));
                this.ballOutcome = o.get(random.nextInt(o.size()));
            }
        } else if (batsman instanceof Bowler) {
            if (random.nextInt(10) > 3) {
                List<BallOutcome> o = new ArrayList<>(List.of(BallOutcome.One, BallOutcome.Two, BallOutcome.Three, BallOutcome.Wicket));
                this.ballOutcome = o.get(random.nextInt(o.size()));
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
