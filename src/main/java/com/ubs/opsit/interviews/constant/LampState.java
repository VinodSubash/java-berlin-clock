package com.ubs.opsit.interviews.constant;

/**
 * Created by x071039 on 11/3/2015.
 */
public enum LampState {
    ON("Y")
    ,OFF("O");

    private final String lampState;

    LampState(String lampState){
        this.lampState=lampState;
    }

    public String getValue(){
        return lampState;
    }
}
