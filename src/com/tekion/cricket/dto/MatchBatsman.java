package com.tekion.cricket.dto;

public class MatchBatsman extends Batsman {
    private Integer currentMatchRun = 0;
    private Integer getCurrentMatchSixes = 0;
    private Integer getCurrentMatchFours = 0;
    private Integer getCurrentMatchBallsFaced = 0;

    public MatchBatsman(String name) {
        super(name);
    }

    public Integer getCurrentMatchRun() {
        return currentMatchRun;
    }

    public void setCurrentMatchRun(Integer runs) {
        this.currentMatchRun += runs;
    }

    public Integer getGetCurrentMatchSixes() {
        return getCurrentMatchSixes;
    }

    public void incrementCurrentMatchSixes() {
        this.getCurrentMatchSixes++;
    }

    public Integer getGetCurrentMatchFours() {
        return getCurrentMatchFours;
    }

    public void incrementCurrentMatchFours() {
        this.getCurrentMatchFours++;
    }

    public Integer getGetCurrentMatchBallsFaced() {
        return getCurrentMatchBallsFaced;
    }

    public void incrementCurrentMatchBallsFaced() {
        this.getCurrentMatchBallsFaced++;
    }
}
