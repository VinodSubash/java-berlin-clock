package com.ubs.opsit.interviews.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * Test class for Berlin Clock
 */
public class TestBerlinClock {

   @Test
    public void testYellowLampShouldBlinkForEveryTwoSeconds(){
        BerlinClock clock = new BerlinClock();
        clock.setClockOnOffStatus(00);
        assertEquals("Yellow light should click","Y",clock.getClockOnOffStatus());
        clock.setClockOnOffStatus(01);
        assertEquals("Light should be Off","O",clock.getClockOnOffStatus());
       clock.setClockOnOffStatus(59);
        assertEquals("Light should be Off","O",clock.getClockOnOffStatus());
    }

    @Test
    public void testInitialStateAndColorOfBerlinClock(){
        BerlinClock clock = new BerlinClock();
        Lamp[][] lampArr = clock.getClockArr();
        assertEquals("No. of Rows in Berlin clock",4,lampArr.length);
        for(int columnCount=0;columnCount<4;columnCount++){
            assertEquals("Initial state for hour lamp","O",lampArr[0][columnCount].getLampState());
            assertEquals("Initial state for hour lamp","R",lampArr[0][columnCount].getLampColor());
        }
        assertNull(lampArr[0][4]);
        assertNull(lampArr[0][10]);
        for(int columnCount=0;columnCount<4;columnCount++){
            assertEquals("Initial state for hour lamp","O",lampArr[1][columnCount].getLampState());
            assertEquals("Initial state for hour lamp","R",lampArr[1][columnCount].getLampColor());
        }
        for(int columnCount=0;columnCount<11;columnCount++){
            assertEquals("Initial state for hour lamp","O",lampArr[2][columnCount].getLampState());
        }
        assertEquals("Initial state for hour lamp","R",lampArr[2][2].getLampColor());
        assertEquals("Initial state for hour lamp","R",lampArr[2][5].getLampColor());
        assertEquals("Initial state for hour lamp","R",lampArr[2][8].getLampColor());
        assertEquals("Initial state for hour lamp","Y",lampArr[2][3].getLampColor());
        assertNotNull(lampArr[2][10]);
        for(int columnCount=0;columnCount<4;columnCount++){
            assertEquals("Initial state for hour lamp","O",lampArr[3][columnCount].getLampState());
            assertEquals("Initial state for hour lamp","Y",lampArr[3][columnCount].getLampColor());
        }
    }

    @Test
    public void testBerlinClockRepresentation(){
        BerlinClock clock = new BerlinClock();
        String berlinRep = clock.convertTime("13:17:01");
        assertEquals("O\r\nRROO\r\nRRRO\r\nYYROOOOOOOO\r\nYYOO",berlinRep);
        berlinRep = clock.convertTime("24:00:00");
        assertEquals("Y\r\nRRRR\r\nRRRR\r\nOOOOOOOOOOO\r\nOOOO",berlinRep);
        berlinRep = clock.convertTime("00:09:00");
        assertEquals("Y\r\nOOOO\r\nOOOO\r\nYOOOOOOOOOO\r\nYYYY",berlinRep);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDateFormat(){
        BerlinClock clock = new BerlinClock();
        String berlinRep = clock.convertTime("013:er:34");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBlankDateFormat(){
        BerlinClock clock = new BerlinClock();
        String berlinRep = clock.convertTime("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidHour(){
        BerlinClock clock = new BerlinClock();
        String berlinRep = clock.convertTime("25:46:00");
    }

}
