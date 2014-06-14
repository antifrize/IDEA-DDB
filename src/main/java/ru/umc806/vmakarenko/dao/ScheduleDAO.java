package ru.umc806.vmakarenko.dao;

import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.util.Filter;

import java.util.List;

public interface ScheduleDAO {
	public List<Schedule> getSchedulesList();
    public List<Schedule> getSchedulesList(Filter filter);
    public Schedule getSchedule(Schedule schedule);
    public boolean add(Schedule schedule);
    public boolean delete(Filter filter);
}
