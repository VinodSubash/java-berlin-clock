package com.ubs.opsit.interviews.constant;

/**
 * Created by x071039 on 11/3/2015.
 */
public enum LampColor {
    RED("R"),
    YELLOW("Y");

    private final String lampColor;

    LampColor(String lampColor){
        this.lampColor=lampColor;
    }

    public String getValue(){
        return lampColor;
    }

}
