package ru.umc806.vmakarenko.util;

import java.util.Calendar;

/**
 * Created by VMakarenko on 6/10/14.
 */
public class CalendarUtils {
    public static Calendar getInstance(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        return c;
    }
}
