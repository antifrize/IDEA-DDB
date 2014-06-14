package ru.umc806.vmakarenko.util.schedule;

import java.util.Calendar;

/**
 * Created by VMakarenko on 6/10/14.
 */
public class ScheduleConverter {

    private Calendar from,to;
    public ScheduleConverter(Calendar from, Calendar to){
        this.from = from;
        this.to = to;
    }

    public ScheduleTime getFrom(){
        return new ScheduleTime(from);
    }

    public ScheduleTime getTo(){
        return new ScheduleTime(to);
    }

}
