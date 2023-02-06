package com.tekion.cricket.dto;

public abstract class Player {
    private String name;
    private String id;
    private Integer runs = 0;
    private Integer ballsfaced = 0;
    private Integer sixes = 0;
    private Integer fours = 0;

    public String getId() {
        return id;
    }

    private static int idCount = 0;

    public Player(String name) {
        this.id = new String(String.format("%d", idCount));
        this.name = name;
        idCount++;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        String objId = ((Player) obj).getId();
        return this.id.equals(objId);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 37;
    }

    public Integer getRuns() {
        return runs;
    }

    public void setRuns(Integer runs) {
        this.runs += runs;
    }

    public Integer getBallsfaced() {
        return ballsfaced;
    }

    public void incrementBallsfaced() {
        this.ballsfaced++;
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
}




