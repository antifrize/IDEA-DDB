package ru.umc806.vmakarenko.dao.db_jdbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.InstructorDAO;
import ru.umc806.vmakarenko.dao.db_jdbc.CommonDAOImpl;
import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.exceptions.CannotAddException;
import ru.umc806.vmakarenko.exceptions.CannotLockException;
import ru.umc806.vmakarenko.util.Filter;

import java.sql.ResultSet;
import java.util.List;

public class InstructorDAOImpl  extends CommonDAOImpl<Instructor> implements InstructorDAO {
    Logger LOG = LoggerFactory.getLogger(InstructorDAOImpl.class);

    public InstructorDAOImpl(){
        super(Instructor.class);
    }


    @Override
    public List<Instructor> list(Filter filter) {
        return null;
    }

    @Override
    public void safeUpdate(Instructor entity, Person person) throws CannotLockException {

    }

    @Override
    public void safeDelete(Instructor entity, Person person) throws CannotLockException {

    }

    @Override
    public void safeDelete(int id, Person person) throws CannotLockException {

    }

}
