package com.ubs.opsit.interviews.constant;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * Class to declare constant variables used by other classes.
 */
public class ClockConstants {
    public final static int NO_OF_LAMPS_IN_FIRST_ROW = 4;
    public final static int NO_OF_LAMPS_IN_SECOND_ROW = 4;
    public final static int NO_OF_LAMPS_IN_THIRD_ROW = 11;
    public final static int NO_OF_LAMPS_IN_FOURTH_ROW = 4;
    public final static Pattern TIME_PATTERN = Pattern.compile("([01]?[0-9]|2[0-4]):([0-5][0-9]):([0-5][0-9])");
    public final static SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
    public final static SimpleDateFormat SECOND_FORMAT = new SimpleDateFormat("ss");
    public final static String STATUS_QUEUE = "/topic/clockstatus";
    public final static String TIME_QUEUE = "/topic/clocktime";
}
