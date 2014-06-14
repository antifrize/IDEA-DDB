package ru.umc806.vmakarenko.dao.mock;

import ru.umc806.vmakarenko.dao.ScheduleDAO;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.util.Filter;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDAOImpl implements ScheduleDAO {
    @Override
    public List<Schedule> getSchedulesList() {
        return null;
    }

    @Override
    public List<Schedule> getSchedulesList(Filter filter) {
        return null;
    }

    @Override
    public Schedule getSchedule(Schedule schedule) {
        return null;
    }

    @Override
    public boolean add(Schedule schedule) {
        return false;
    }

    @Override
    public boolean delete(Filter filter) {
        return false;
    }

}
