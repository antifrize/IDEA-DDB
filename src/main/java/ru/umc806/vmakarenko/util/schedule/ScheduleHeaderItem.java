package ru.umc806.vmakarenko.util.schedule;

import ru.umc806.vmakarenko.domain.Schedule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VMakarenko on 5/21/14.
 */
public class ScheduleHeaderItem {
    private int day;
    private int month;

    public List<ScheduleComponentWrapper> getScheduleList() {
        return scheduleList;
    }

    private List<ScheduleComponentWrapper> scheduleList = new ArrayList<>();

    public ScheduleHeaderItem(int day, int month){
        this.day = day;
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {

        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void add(Schedule schedule){
        scheduleList.add(new ScheduleComponentWrapper(schedule));
    }


    public void add(List<Schedule> schedules){
        List<ScheduleComponentWrapper> wrappers = new ArrayList<>();
        for(Schedule schedule:schedules){
            wrappers.add(new ScheduleComponentWrapper(schedule));
        }
        scheduleList.addAll(wrappers);
    }


    @Override
    public String toString(){
        return ""+(day<10?"0"+day:day)+"."+(month<10?"0"+month:month);
    }
}
