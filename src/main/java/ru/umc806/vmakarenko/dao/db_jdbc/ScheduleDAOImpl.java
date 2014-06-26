package ru.umc806.vmakarenko.dao.db_jdbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.ScheduleDAO;
import ru.umc806.vmakarenko.dao.db_jdbc.CommonDAOImpl;
import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.exceptions.CannotLockException;
import ru.umc806.vmakarenko.util.Filter;

import java.sql.ResultSet;
import java.util.List;

public class ScheduleDAOImpl  extends CommonDAOImpl<Schedule> implements ScheduleDAO {
    public ScheduleDAOImpl(){
        super(Schedule.class);
    }

    @Override
    public List<Schedule> list() {
        return null;
    }

    @Override
    public List<Schedule> list(Filter filter) {
        return null;
    }

    @Override
    public void safeUpdate(Schedule entity, Person person) throws CannotLockException {

    }

    @Override
    public void safeDelete(Schedule entity, Person person) throws CannotLockException {

    }

    @Override
    public void safeDelete(int id, Person person) throws CannotLockException {

    }

}
