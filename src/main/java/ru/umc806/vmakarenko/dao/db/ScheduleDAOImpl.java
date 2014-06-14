package ru.umc806.vmakarenko.dao.db;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.umc806.vmakarenko.dao.ScheduleDAO;
import ru.umc806.vmakarenko.domain.*;
import ru.umc806.vmakarenko.util.Filter;

import java.util.List;

public class ScheduleDAOImpl implements ScheduleDAO {    @Autowired
private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    Logger LOG = LoggerFactory.getLogger(ScheduleDAOImpl.class);

    @Override
    public List<Schedule> getSchedulesList() {
        return getSchedulesList(new Filter());
    }

    @Override
    public List<Schedule> getSchedulesList(Filter filter) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Schedule.class,"schedule");
        LOG.debug("gonna get schedules");
        if(filter.getSchedule()!=null){
            LOG.debug("filter by schedule");
            Schedule schedule = filter.getSchedule();
            if(schedule.getId()!=null){
                criteria.add(Restrictions.eq("id", schedule.getId()));
            }
            if(schedule.getFrom()!=null){
                criteria.add(Restrictions.ge("from", schedule.getFrom()));
            }
            if(schedule.getTo()!=null){
                criteria.add(Restrictions.le("to", schedule.getTo()));
            }
        }
        if(filter.getInstructor()!=null){
            LOG.debug("filter by instructor");
            criteria.createAlias("schedule.instructor","instructor");
            Instructor instructor = filter.getInstructor();
            if(instructor.getId()!=null){
                criteria.add(Restrictions.eq("instructor.id", instructor.getId()));
            }
            if(instructor.getLicense()!=null){
                criteria.add(Restrictions.eq("instructor.license", instructor.getLicense()));
            }
            if(instructor.getPerson()!=null){
                criteria.createAlias("instructor.person","person");
                Person person = instructor.getPerson();
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
                    criteria.add(Restrictions.eq("person.lastname",person.getLastname()));
                }
                if(person.getLogin()!=null){
                    criteria.add(Restrictions.eq("person.login",person.getLogin()));
                }
            }
        }
        if(filter.getStudent()!=null){
            LOG.debug("filter by student");
            criteria.createAlias("schedule.student","student");
            Student student = filter.getStudent();
            if(student.getId()!=null){
                criteria.add(Restrictions.eq("student.id",student.getId()));
            }
            if(student.getPerson()!=null){
                criteria.createAlias("student.person","person");
                Person person = student.getPerson();
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
                    criteria.add(Restrictions.eq("person.lastname",person.getLastname()));
                }
                if(person.getLogin()!=null){
                    criteria.add(Restrictions.eq("person.login",person.getLogin()));
                }
            }
        }
        LOG.debug("returning schedule list");

        return criteria.list();
    }

    @Override
    public Schedule getSchedule(Schedule schedule) {
        return null;
    }

    @Override
    public boolean add(Schedule schedule) {
        sessionFactory.getCurrentSession().save(schedule);
        return true;
    }
    @Override
    public boolean delete(Filter filter) {
        List<Schedule> scheduleList = getSchedulesList(filter);
        if(scheduleList.size()<1){
            return false;
        }
        sessionFactory.getCurrentSession().delete(scheduleList.get(0));
        return true;
    }

}
