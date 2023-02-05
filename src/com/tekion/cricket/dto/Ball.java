package com.tekion.cricket.dto;

import com.tekion.cricket.enums.BallOutcome;
import com.tekion.cricket.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Ball {
    private Player batsman;
    private Player bowler;
    private Integer run = 0;
    private BallOutcome ballOutcome;

    public Ball(Player batsman, Player bowler) {
        this.batsman = batsman;
        this.bowler = bowler;
        this.run = 0;
        Random random = new Random();
        List<BallOutcome> ballOutcomes = Arrays.asList(BallOutcome.values());
        this.ballOutcome = ballOutcomes.get(random.nextInt(ballOutcomes.size()));
        this.run = ballOutcome.getValue();
        System.out.println();
        System.out.println(Constants.ANSI_GREEN+batsman.getName() + " faced the ball by " + bowler.getName());
        System.out.print(Constants.ANSI_PURPLE+"\tResult: " + ballOutcome);
    }

    public String toString() {
        return batsman.getName() + " played the ball, bowled by "
                + bowler.getName() + " Result : " +
                (ballOutcome == BallOutcome.Wicket ? "Wicket" : ballOutcome.getValue());
    }

    public Player getBatsman() {
        return batsman;
    }

    public Player getBowler() {
        return bowler;
    }

    public BallOutcome getBallOutcome() {
        return ballOutcome;
    }

    public Integer getRun() {
        return run;
    }
}
