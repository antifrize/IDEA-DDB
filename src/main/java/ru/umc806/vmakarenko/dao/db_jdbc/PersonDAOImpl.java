package ru.umc806.vmakarenko.dao.db_jdbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.PersonDAO;
import ru.umc806.vmakarenko.dao.db_jdbc.CommonDAOImpl;
import ru.umc806.vmakarenko.domain.Person;
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
    public List<Person> list() {
        return null;
    }

    @Override
    public List<Person> list(Filter filter) {
        return null;
    }
}

