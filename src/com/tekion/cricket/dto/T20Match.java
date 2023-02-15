package com.tekion.cricket.dto;

import com.tekion.cricket.services.Match;

import java.util.Date;

import static com.tekion.cricket.utils.Constants.*;

public class T20Match extends Match {

    public T20Match(Date date, String venue) {
        super(date, venue, t20Overs);
    }
}
