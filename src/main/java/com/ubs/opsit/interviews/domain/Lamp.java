package com.ubs.opsit.interviews.domain;

import com.ubs.opsit.interviews.constant.LampColor;
import com.ubs.opsit.interviews.constant.LampState;

/**
 * Class to encapsulate Lamp property and behaviour.
 * It uses FluentInterface to build the object
 */
public class Lamp {

    /**
     * Property to hold Lamp state.
     * Default state is OFF.
     */
    private LampState lampState= LampState.OFF;
    //Red or Yellow
    private LampColor lampColor;
    //Row number
    private int rowNo;

    public Lamp withLampState(LampState state){
        this.lampState = state;
        return this;
    }

    public Lamp withLampColor(LampColor color){
        this.lampColor = color;
        return this;
    }

    public Lamp withRowNo(int rowNo){
        this.rowNo = rowNo;
        return this;
    }

    public String getLampState(){ return lampState.getValue(); }

    public String getLampColor() { return lampColor.getValue(); }

    public int getRowNo() { return rowNo; }


}
