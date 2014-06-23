package ru.umc806.vmakarenko.dao.db_hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.umc806.vmakarenko.dao.AdministratorDAO;
import ru.umc806.vmakarenko.domain.Administrator;
import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.util.Filter;

import java.util.List;

public class AdministratorDAOImpl extends ru.umc806.vmakarenko.dao.db_hibernate.CommonDAOImpl<Administrator> implements AdministratorDAO {
    Logger LOG = LoggerFactory.getLogger(AdministratorDAOImpl.class);

    public AdministratorDAOImpl(){
        super(Administrator.class);
    }


    @Override
    public List<Administrator> list() {
        return list(new Filter());
    }

    @Override
    public List<Administrator> list(Filter filter) {
        LOG.debug("gonna list hibernate session");
        Session session = sessionFactory.getCurrentSession();
        LOG.debug("got session from hibernate");

        Criteria criteria = session.createCriteria(Administrator.class,"administrator");
        if(filter.getInstructor()!=null){
            LOG.debug("filtering by admin");
            Administrator administrator = filter.getAdministrator();
            if(administrator.getId()!=null){
                criteria.add(Restrictions.eq("admin_id", administrator.getId()));
            }
        }
        if(filter.getPerson()!=null){
            LOG.debug("filtering by person");
            criteria.createAlias("administrator.person","person");
            Person person = filter.getPerson();
            if(person.getId()!=null){
                criteria.add(Restrictions.eq("person.person_id",person.getId()));
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

}
