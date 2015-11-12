package com.ubs.opsit.interviews.domain;

import com.ubs.opsit.interviews.constant.ClockConstants;
import com.ubs.opsit.interviews.constant.LampColor;
import com.ubs.opsit.interviews.constant.LampState;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.stream.Stream;
import java.util.Arrays;


/**
 * A domain class to translate digital time to Berlin clock format.
 * This class holds the representation of berlin and implement business logic to convert time.
 * Two rows of lamp indicates hours of the day.
 * Next two rows of lamp indicates minutes of the day.
 * On top of the clock yellow light that blinks on/off for every 2 seconds
 *
 */

public class BerlinClock implements Serializable {

    private Lamp[][] clockArr = new Lamp[4][11];
    private String clockOnOffStatus="";
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(BerlinClock.class);

    /**
     * Initialize Lamp with default color and state for each row
     */
    public BerlinClock() {
        initiateHourLamp(0, ClockConstants.NO_OF_LAMPS_IN_FIRST_ROW);
        initiateHourLamp(1, ClockConstants.NO_OF_LAMPS_IN_SECOND_ROW);
        initiateMinuteLamp(2, ClockConstants.NO_OF_LAMPS_IN_THIRD_ROW, true);
        initiateMinuteLamp(3, ClockConstants.NO_OF_LAMPS_IN_FOURTH_ROW, false);
        LOG.debug("Initialized the berlin clock");
    }

    /**
     * Initialize Hour Lamp with default property
     * @param rowNo
     * @param columnCount
     */
    private void initiateHourLamp(final int rowNo, final int columnCount) {
        for(int columnNo = 0; columnNo < columnCount ; columnNo++) {
            clockArr[rowNo][columnNo] = new Lamp().withLampColor(LampColor.RED).withRowNo(rowNo);
        }
    }

    /**
     * Initialize Minute Lamp with default value.
     * First row of minute lamp 3rd,6th and 9th lamp is red
     * @param rowNo
     * @param columnCount
     * @param colorChange
     */
    private void initiateMinuteLamp(final int rowNo, final int columnCount,final boolean colorChange) {
        int columnCountForRedLamp = 1;
        LampColor lampColor;
        for(int columnNo = 0; columnNo < columnCount ; columnNo++) {
            lampColor = LampColor.YELLOW;
            if(colorChange) {
                if(columnCountForRedLamp % 3 == 0) {
                    lampColor = LampColor.RED;
                }
                columnCountForRedLamp++;
            }
            clockArr[rowNo][columnNo] = new Lamp().withLampColor(lampColor).withRowNo(rowNo);
        }
    }

    /**
     * Reset the lamp to default state and changed the state of Lamp based on hours
     * @param hours
     */
    private void setLampStatusForHours(final int hours) {
        resetLampState(0,4);
        resetLampState(1,4);
        setOnOffStatus(0,(hours - (hours%5))/5);
        setOnOffStatus(1,hours%5);
    }

    /**
     * Reset the lamp to default state and changed the state of Lamp based on minutes
     * @param minutes
     */
    private void setLampStatusForMinutes(int minutes) {
        resetLampState(2,11);
        resetLampState(3,4);
        setOnOffStatus(2,(minutes - (minutes % 5)) / 5);
        setOnOffStatus(3,minutes % 5);
    }

    /**
     * Logic to change the state of Lamp
     * @param rowNo
     * @param columnCount
     */
    private void setOnOffStatus(final int rowNo,final int columnCount){
        for(int columnNo = 0;columnNo<columnCount;columnNo++){
            clockArr[rowNo][columnNo].withLampState(LampState.ON);
        }
    }

    /**
     * Method to reset the Lamp state to default state
     * @param rowNo
     * @param columnCount
     */
    private void resetLampState(final int rowNo,final int columnCount){
        for(int columnNo = 0;columnNo<columnCount;columnNo++){
            clockArr[rowNo][columnNo].withLampState(LampState.OFF);
        }
    }

    /**
     * Get the blinking status for the clock - every 2 seconds
     * @param seconds
     * @return
     */
    public String getClockOnOffStatus(){
        return clockOnOffStatus;
    }

    /**
     * Getter method to return Multi dimensional array
     * @return clockArr
     */
    public Lamp[][] getClockArr(){
        return clockArr;
    }

    /**
     * Builder method to convert time to berlin clock representation format.
     * @param strTime
     * @return str
     */
    public String convertTime(final String strTime){
            setTime(strTime);
            StringBuilder builder = new StringBuilder();
            builder.append(getClockOnOffStatus());
            Arrays.stream(clockArr).forEach(clockSubArr -> iterate(clockSubArr, builder));
            return builder.toString();
    }

    public void setTime(final String strTime){
        if(StringUtils.isNotBlank(strTime) && isValidTimePattern(strTime)) {
            int[] arrTime = Stream.of(strTime.split(":")).mapToInt(Integer::parseInt).toArray();
            setLampStatusForHours(arrTime[0]);
            setLampStatusForMinutes(arrTime[1]);
            setClockOnOffStatus(arrTime[2]);
        }else{
            LOG.error("Invalid time given :{}",strTime);
            throw new IllegalArgumentException("Invalid time given " + strTime +".Please provide valid format/shouldn't be blank");
        }
    }

    public void setClockOnOffStatus(final int seconds) {
        clockOnOffStatus = seconds % 2 == 0 ?LampState.ON.getValue():LampState.OFF.getValue();
    }


    /**
     * Validate the time format hh:mm:ss
     * @param strTime
     * @return
     */
    private boolean isValidTimePattern(String strTime){
        return ClockConstants.TIME_PATTERN.matcher(strTime).matches();
    }

    /**
     * Iterator to iterate multi dimensional array and create a new line for each row.
     * @param arr
     * @param builder
     * @param <T>
     */
    private static <T> void iterate(T[] arr,StringBuilder builder) {
        builder.append("\r\n");
        Arrays.stream(arr).forEach(elem -> iterateAction(elem,builder));
    }

    /**
     * Iterate multi dimensional array and build the lamp status
     * @param elem
     * @param builder
     * @param <T>
     */
    private static <T> void iterateAction(T elem,StringBuilder builder) {
        if(null!=elem){
            Lamp lamp =(Lamp)elem;
            String lampState= lamp.getLampState();
            if("Y".equals(lampState)){
                lampState = lamp.getLampColor();
            }
            builder.append(lampState);
        }
    }

}
