package ru.umc806.vmakarenko.dao.db_hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.PersonDAO;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.util.Filter;

import java.util.List;

/**
 * Created by VMakarenko on 5/25/14.
 */
public class PersonDAOImpl  extends CommonDAOImpl implements PersonDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Person> list() {
        return list(new Filter());
    }

    @Override
    public List<Person> list(Filter filter) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Person.class,"person");
        if(filter.getPerson()!=null){
            Person person = filter.getPerson();
            if(person.getId()!=null){
                criteria.add(Restrictions.eq("id",person.getId()));
            }
            if(person.getName()!=null){
                criteria.add(Restrictions.eq("name",person.getName()));
            }
            if(person.getSurname()!=null){
                criteria.add(Restrictions.eq("surname",person.getSurname()));
            }
            if(person.getLastname()!=null){
                criteria.add(Restrictions.eq("last_name",person.getLastname()));
            }
            if(person.getLogin()!=null){
                criteria.add(Restrictions.eq("login",person.getLogin()));
            }
        }
        return criteria.list();
    }

}
