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

    /**
     * Service delegator to convert time to clock representation.
     * @param strTime
     * @return str
     */
    @Override
    public String convertTime(final String strTime) {
       return berlinClock.convertTime(strTime);
    }

}
