package ru.umc806.vmakarenko.util.schedule;

import ru.umc806.vmakarenko.domain.Schedule;

/**
 * Created by VMakarenko on 5/22/14.
 */
public class ScheduleComponentWrapper {
    private Schedule schedule;

    public Schedule getSchedule() {
        return schedule;
    }

    public String getStatus(){
        if(schedule.getInstructor()==null){
            return "new";
        }else{
            return "my";
        }
    }

    public ScheduleComponentWrapper(Schedule schedule){
        this.schedule = schedule;
    }
}
