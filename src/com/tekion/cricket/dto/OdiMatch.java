package com.tekion.cricket.dto;

import com.tekion.cricket.services.Match;

import java.util.Date;

public class OdiMatch extends Match {
    public OdiMatch(Date date, String venue) {
        super(date, venue, 50);
    }
}
