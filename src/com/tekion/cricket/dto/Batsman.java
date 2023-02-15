package com.tekion.cricket.dto;

public class Batsman extends Player {

    private Integer runs = 0;
    private Integer ballsFaced = 0;
    private Integer sixes = 0;
    private Integer fours = 0;

    public Batsman(String name) {
        super(name);
    }

    public Integer getRuns() {
        return runs;
    }

    public void setRuns(Integer runs) {
        this.runs += runs;
    }

    public Integer getBallsFaced() {
        return ballsFaced;
    }

    public void incrementBallsFaced() {
        this.ballsFaced++;
    }

    public Integer getSixes() {
        return sixes;
    }

    public Integer getFours() {
        return fours;
    }

    public void incrementSixes() {
        this.sixes++;
    }

    public void incrementFours() {
        this.fours++;
    }

    protected void reset() {
        this.ballsFaced = 0;
        this.runs = 0;
        this.fours = 0;
        this.sixes = 0;
    }
}
