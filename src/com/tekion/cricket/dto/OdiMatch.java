package com.tekion.cricket.dto;

import com.tekion.cricket.services.Match;

import java.util.Date;

import static com.tekion.cricket.utils.Constants.odiOvers;

public class OdiMatch extends Match {
    public OdiMatch(Date date, String venue) {
        super(date, venue, odiOvers);
    }
}
