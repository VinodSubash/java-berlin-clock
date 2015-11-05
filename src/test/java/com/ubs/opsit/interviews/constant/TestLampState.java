package com.ubs.opsit.interviews.constant;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by x071039 on 11/4/2015.
 */
public class TestLampState {

    @Test
    public void testLampStateValue(){
        assertEquals("Lamp State Value is O","O",LampState.OFF.getValue());
        assertEquals("Lamp State Value is Y","Y",LampState.ON.getValue());
    }



}
