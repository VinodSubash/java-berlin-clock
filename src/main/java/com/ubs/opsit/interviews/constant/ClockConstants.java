package com.ubs.opsit.interviews.constant;

import java.util.regex.Pattern;

/**
 * Created by x071039 on 11/3/2015.
 */
public class ClockConstants {
    public final static int NO_OF_LAMPS_IN_FIRST_ROW = 4;
    public final static int NO_OF_LAMPS_IN_SECOND_ROW = 4;
    public final static int NO_OF_LAMPS_IN_THIRD_ROW = 11;
    public final static int NO_OF_LAMPS_IN_FOURTH_ROW = 4;
    public final static Pattern TIME_FORMAT = Pattern.compile("([0-2][0-4]):([0-5][0-9]):([0-5][0-9])");

}
