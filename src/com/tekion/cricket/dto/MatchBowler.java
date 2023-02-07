package com.tekion.cricket.dto;

public class MatchBowler extends Bowler{
    private Integer curreentMatchBallsDone = 0;
    private Integer currentMatchWickets = 0;
    private Integer currentMatchRunsGiven = 0;
    public MatchBowler(String name) {
        super(name);
    }

    public Integer getCurreentMatchBallsDone() {
        return curreentMatchBallsDone;
    }

    public void incrementCurreentMatchBallsDone() {
        this.curreentMatchBallsDone++;
    }

    public Integer getCurrentMatchWickets() {
        return currentMatchWickets;
    }

    public void incrementCurrentMatchWickets() {
        this.currentMatchWickets++;
    }

    public Integer getCurrentMatchRunsGiven() {
        return currentMatchRunsGiven;
    }

    public void setCurrentMatchRunsGiven(Integer currentMatchRunsGiven) {
        this.currentMatchRunsGiven += currentMatchRunsGiven;
    }


}
