package ru.umc806.vmakarenko.dao.db_hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.ScheduleDAO;
import ru.umc806.vmakarenko.domain.*;
import ru.umc806.vmakarenko.util.Filter;

import java.util.List;

public class ScheduleDAOImpl  extends CommonDAOImpl<Schedule> implements ScheduleDAO {    @Autowired
    private SessionFactory sessionFactory;
    Logger LOG = LoggerFactory.getLogger(ScheduleDAOImpl.class);

    public ScheduleDAOImpl(){
        super(Schedule.class);
    }
    @Override
    public List<Schedule> list() {
        return list(new Filter());
    }

    @Override
    public List<Schedule> list(Filter filter) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Schedule.class,"schedule");
        LOG.debug("gonna list schedules");
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

}
