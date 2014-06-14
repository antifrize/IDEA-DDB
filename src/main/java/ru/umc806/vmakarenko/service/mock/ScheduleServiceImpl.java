package ru.umc806.vmakarenko.service.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.umc806.vmakarenko.dao.ScheduleDAO;
import ru.umc806.vmakarenko.dao.mock.NamingFactory;
import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.service.ScheduleService;
import ru.umc806.vmakarenko.util.Filter;
import ru.umc806.vmakarenko.util.RoleService;
import ru.umc806.vmakarenko.util.ScheduleException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by VMakarenko on 5/21/14.
 */
@javax.transaction.Transactional
public class ScheduleServiceImpl implements ScheduleService {
    Logger LOG = LoggerFactory.getLogger(ScheduleService.class);
    @Autowired
    private RoleService roleService;
    @Autowired
    private ScheduleDAO scheduleDAO;


    @Override
    public List<Schedule> getSchedules() {
        return this.getSchedules(new Filter()) ;
    }

    @Override
    public List<Schedule> getSchedules(Filter filter) {
        return scheduleDAO.getSchedulesList(filter);
    }

    @Override
    public boolean addIfPossible(Schedule schedule) throws ScheduleException {
        // TODO theoritically -- performance is bad
        LOG.debug("adding schedule if possible");
        // check if student have no lessons
        Filter filter = new Filter();
        filter.setStudent(schedule.getStudent());
        List<Schedule> studentLessons = scheduleDAO.getSchedulesList(filter);
        for(Schedule lesson: studentLessons){
            if(schedule.getFrom()!=null && schedule.getFrom().before(lesson.getFrom())){
                if(schedule.getTo()!=null && schedule.getTo().after(lesson.getTo())){
                    throw new ScheduleException("ERR_STUD","Student planning error");
                }else
                if(schedule.getTo()!=null && schedule.getTo().after(lesson.getFrom())){
                    throw new ScheduleException("ERR_STUD","Student planning error");
                }
            }else{
                if(schedule.getFrom()!=null && schedule.getFrom().before(lesson.getTo())){
                    throw new ScheduleException("ERR_STUD","Student planning error");
                }
            }
        }
        LOG.debug("no problems with student");
        // check if instructor is free
        filter = new Filter();
        filter.setInstructor(schedule.getInstructor());
        List<Schedule> instructorLessons = scheduleDAO.getSchedulesList(filter);
        for(Schedule lesson: instructorLessons){
            if(schedule.getFrom()!=null && schedule.getFrom().before(lesson.getFrom())){
                if(schedule.getTo()!=null && schedule.getTo().after(lesson.getTo())){
                    throw new ScheduleException("ERR_INST","Instructor planning error");
                }else
                if(schedule.getTo()!=null && schedule.getTo().after(lesson.getFrom())){
                    throw new ScheduleException("ERR_INST","Instructor planning error");
                }
            }else{
                if(schedule.getFrom()!=null && schedule.getFrom().before(lesson.getTo())){
                    throw new ScheduleException("ERR_INST","Instructor planning error");
                }
            }
        }
        LOG.debug("no problems with instructor");
        // check if plane is free
        filter = new Filter();
        filter.setPlane(schedule.getPlane());
        List<Schedule> planeLessons = scheduleDAO.getSchedulesList(filter);
        for(Schedule lesson: studentLessons){
            if(schedule.getFrom()!=null && schedule.getFrom().before(lesson.getFrom())){
                if(schedule.getTo()!=null && schedule.getTo().after(lesson.getTo())){
                    throw new ScheduleException("ERR_PLNE","Plane planning error");
                }else
                if(schedule.getTo()!=null && schedule.getTo().after(lesson.getFrom())){
                    throw new ScheduleException("ERR_PLNE","Plane planning error");
                }
            }else{
                if(schedule.getFrom()!=null && schedule.getFrom().before(lesson.getTo())){
                    throw new ScheduleException("ERR_PLNE","Plane planning error");
                }
            }
        }
        LOG.debug("no problems with plane");
        return scheduleDAO.add(schedule);
    }
    @Transactional
    @Override
    public boolean delete(Filter filter) {
        return scheduleDAO.delete(filter);
    }

}