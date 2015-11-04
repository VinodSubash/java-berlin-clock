package com.ubs.opsit.interviews.domain;

import com.ubs.opsit.interviews.constant.LampColor;
import com.ubs.opsit.interviews.constant.LampState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for Lamp domain model
 *
 */
public class TestLamp {

    @Test
    public void testLampObjectCreation(){
        Lamp lamp = new Lamp().withLampColor(LampColor.RED).withLampState(LampState.ON).withRowNo(1);
        assertEquals("Y",lamp.getLampState());
        assertEquals("R",lamp.getLampColor());
        assertEquals(1,lamp.getRowNo());
    }
}
