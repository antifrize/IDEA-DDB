package ru.umc806.vmakarenko.service;

import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.util.Filter;
import ru.umc806.vmakarenko.util.ScheduleException;

import java.util.Calendar;
import java.util.List;

/**
 * Created by VMakarenko on 5/20/14.
 */
public interface ScheduleService {
    public List<Schedule> getSchedules();
    public List<Schedule> getSchedules(Filter filter);
    public boolean addIfPossible(Schedule schedule) throws ScheduleException;
    public boolean delete(Filter filter);


    // TODO add/mantain methods
}
