package com.tekion.cricket;

import com.tekion.cricket.services.Match;

public class Main {
    public static void main(String[] args) {
        Match match = Match.selectMatchFormat();
        match.toss();
        match.play();
    }
}
