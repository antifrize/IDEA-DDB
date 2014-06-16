package ru.umc806.vmakarenko.dao.mock;

import ru.umc806.vmakarenko.dao.ScheduleDAO;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.util.Filter;

import java.util.List;

public class ScheduleDAOImpl implements ScheduleDAO {
    @Override
    public List<Schedule> list() {
        return null;
    }

    @Override
    public List<Schedule> list(Filter filter) {
        return null;
    }

    @Override
    public boolean add(Schedule schedule) {
        return false;
    }

}
