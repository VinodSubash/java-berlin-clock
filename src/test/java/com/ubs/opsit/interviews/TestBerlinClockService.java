package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.service.BerlinClockService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for Berlin Clock Service
 */
public class TestBerlinClockService {

    @Test
    public void testConvertTime(){
        BerlinClockService service = new BerlinClockService();
        assertEquals("Y\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO",service.convertTime("00:00:00"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorForInvalidTimeFormat(){
        BerlinClockService service = new BerlinClockService();
        service.convertTime("EE:00:00");
    }
}
