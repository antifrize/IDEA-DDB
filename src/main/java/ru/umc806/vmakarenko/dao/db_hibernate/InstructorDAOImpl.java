package ru.umc806.vmakarenko.dao.db_hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.InstructorDAO;
import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.exceptions.CannotAddException;
import ru.umc806.vmakarenko.util.Filter;

import java.io.Serializable;
import java.util.List;

public class InstructorDAOImpl  extends CommonDAOImpl<Instructor> implements InstructorDAO {
    Logger LOG = LoggerFactory.getLogger(InstructorDAOImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    public InstructorDAOImpl(){
        super(Instructor.class);
    }

    public List<Instructor> list() {
        return list(new Filter());
    }


    @Override
    public List<Instructor> list(Filter filter) {
        LOG.debug("gonna list hibernate session");
        Session session = sessionFactory.getCurrentSession();
        LOG.debug("got session from hibernate");

        Criteria criteria = session.createCriteria(Instructor.class,"instructor");
        if(filter.getInstructor()!=null){
            LOG.debug("filtering by instructor");
            Instructor instructor = filter.getInstructor();
            if(instructor.getId()!=null){
                criteria.add(Restrictions.eq("person_id",instructor.getId()));
            }
            if(instructor.getLicense()!=null){
                criteria.add(Restrictions.eq("instr_license",instructor.getLicense()));
            }
        }
        if(filter.getPerson()!=null){
            LOG.debug("filtering by person");
            criteria.createAlias("instructor.person","person");
            Person person = filter.getPerson();
            if(person.getId()!=null){
                criteria.add(Restrictions.eq("person.id",person.getId()));
            }
            if(person.getName()!=null){
                criteria.add(Restrictions.eq("person.name",person.getName()));
            }
            if(person.getSurname()!=null){
                criteria.add(Restrictions.eq("person.surname",person.getSurname()));
            }
            if(person.getLastname()!=null){
                criteria.add(Restrictions.eq("person.last_name",person.getLastname()));
            }
            if(person.getLogin()!=null){
                criteria.add(Restrictions.eq("person.login",person.getLogin()));
            }
        }
        return criteria.list();
    }


    @Override
    public Instructor get(int id) {
        Instructor instructor = new Instructor();
        instructor.setId(id);
        List<Instructor> list = list(new Filter().setInstructor(instructor));
        if(list.isEmpty()){
            return null;
        }else{
            return list.get(0);
        }
    }

}
