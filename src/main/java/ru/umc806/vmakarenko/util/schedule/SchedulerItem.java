package ru.umc806.vmakarenko.util.schedule;

import ru.umc806.vmakarenko.domain.Schedule;

/**
 * Created by VMakarenko on 5/21/14.
 */
public class SchedulerItem {
    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    private boolean empty;
    private Schedule schedule;
}
