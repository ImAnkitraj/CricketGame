package com.tekion.cricket.dto;

public class Bowler extends Batsman {

    private Integer ballsDone = 0;
    private Integer wickets = 0;
    private Integer runsGiven = 0;

    public Bowler(String name) {
        super(name);
    }

    public Integer getBallsDone() {
        return ballsDone;
    }

    public void incrementBallsDone() {
        this.ballsDone++;
    }

    public Integer getWickets() {
        return wickets;
    }

    public void incrementWickets() {
        this.wickets++;
    }

    public Integer getRunsGiven() {
        return runsGiven;
    }

    public void setRunsGiven(Integer runs) {
        this.runsGiven += runs;
    }

    @Override
    public void reset() {
        super.reset();
        this.ballsDone = 0;
        this.wickets = 0;
        this.runsGiven = 0;
    }
}
