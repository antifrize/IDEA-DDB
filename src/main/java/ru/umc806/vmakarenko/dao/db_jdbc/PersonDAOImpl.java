package ru.umc806.vmakarenko.dao.db_jdbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.PersonDAO;
import ru.umc806.vmakarenko.dao.db_jdbc.CommonDAOImpl;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.exceptions.CannotLockException;
import ru.umc806.vmakarenko.util.Filter;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by VMakarenko on 5/25/14.
 */
public class PersonDAOImpl  extends CommonDAOImpl<Person> implements PersonDAO {
    public PersonDAOImpl(){
        super(Person.class);
    }


    @Override
    public List<Person> list(Filter filter) {
        return null;
    }

    @Override
    public void safeUpdate(Person entity, Person person) throws CannotLockException {

    }

    @Override
    public void safeDelete(Person entity, Person person) throws CannotLockException {

    }

    @Override
    public void safeDelete(int id, Person person) throws CannotLockException {

    }
}

