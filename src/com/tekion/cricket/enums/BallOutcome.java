package com.tekion.cricket.enums;

public enum BallOutcome {
     NoRun(0), One(1), Two(2), Three(3), Four(4), Six(6), Wicket(0);
    private final int value;

    BallOutcome(int intValue) {
        this.value = intValue;
    }

    public int getValue() {
        return value;
    }
}
