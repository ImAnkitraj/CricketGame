package com.tekion.cricket.dto;

public class RecordPlayer {
    private String name;
    private String id;
    private Integer careerRuns = 0;
    private Integer careerWickets = 0;
    private Integer careerBallsFaced = 0;
    private Integer careerSixes = 0;
    private Integer careerFours = 0;
    private Integer careerBallsDone = 0;
    private Integer careerRunsGiven = 0;
    private static int idCount = 0;
    public RecordPlayer(String name) {
        this.id = new String(String.format("%d", idCount));
        this.name = name;
        idCount++;
    }
    public String getId() {
        return id;
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
        return this.id.hashCode() + 37;
    }

    public Integer getCareerRuns() {
        return careerRuns;
    }

    public void setCareerRuns(Integer careerRuns) {
        this.careerRuns = careerRuns;
    }

    public Integer getCareerWickets() {
        return careerWickets;
    }

    public void setCareerWickets(Integer careerWickets) {
        this.careerWickets = careerWickets;
    }

    public Integer getCareerBallsFaced() {
        return careerBallsFaced;
    }

    public void setCareerBallsFaced(Integer careerBallsFaced) {
        this.careerBallsFaced = careerBallsFaced;
    }

    public Integer getCareerSixes() {
        return careerSixes;
    }

    public void setCareerSixes(Integer careerSixes) {
        this.careerSixes = careerSixes;
    }

    public Integer getCareerFours() {
        return careerFours;
    }

    public void setCareerFours(Integer careerFours) {
        this.careerFours = careerFours;
    }

    public Integer getCareerBallsDone() {
        return careerBallsDone;
    }

    public void setCareerBallsDone(Integer careerBallsDone) {
        this.careerBallsDone = careerBallsDone;
    }

    public Integer getCareerRunsGiven() {
        return careerRunsGiven;
    }

    public void setCareerRunsGiven(Integer careerRunsGiven) {
        this.careerRunsGiven = careerRunsGiven;
    }
}
