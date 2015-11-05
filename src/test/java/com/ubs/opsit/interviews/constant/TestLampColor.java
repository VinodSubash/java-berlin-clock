package com.ubs.opsit.interviews.constant;


import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Created by x071039 on 11/3/2015.
 */
public class TestLampColor {

    @Test
    public void lampColorValue(){
        assertEquals("Lamp Color Value is R","R",LampColor.RED.getValue());
        assertEquals("Lamp Color Value is Y","Y",LampColor.YELLOW.getValue());
    }


}
