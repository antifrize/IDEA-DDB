package ru.umc806.vmakarenko.util.schedule;

import java.util.Calendar;

/**
 * Created by VMakarenko on 6/10/14.
 */
public class ScheduleTime {

        private Calendar c;
        public ScheduleTime(Calendar calendar){
            c = calendar;
        }
        public String getMinutes(){
            int minutes = c.get(Calendar.MINUTE);
            return minutes>9?String.valueOf(minutes):"0"+String.valueOf(minutes);
        }
        public String getHours(){
            int hours = c.get(Calendar.HOUR);
            return hours>9?String.valueOf(hours):"0"+String.valueOf(hours);
        }
}
