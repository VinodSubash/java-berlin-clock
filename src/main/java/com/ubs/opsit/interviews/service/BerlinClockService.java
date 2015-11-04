package com.ubs.opsit.interviews.service;

import com.ubs.opsit.interviews.domain.BerlinClock;

/**
 * Service call to implement TimeConverter interface and call berlinClock method for representation
 */
public class BerlinClockService implements TimeConverter {
    BerlinClock berlinClock;
    public BerlinClockService(){
        berlinClock = new BerlinClock();
    }

    @Override
    public String convertTime(final String strTime) {
       return berlinClock.convertTime(strTime);
    }

}
