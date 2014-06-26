package ru.umc806.vmakarenko.service.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import ru.umc806.vmakarenko.dao.ScheduleDAO;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.exceptions.CannotLockException;
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
public class ScheduleServiceImpl implements ScheduleService {
    Logger LOG = LoggerFactory.getLogger(ScheduleService.class);
    @Autowired
    private RoleService roleService;
    @Autowired
    private ScheduleDAO scheduleDAO;
    @Autowired
    private TransactionTemplate transactionTemplate;


    @Transactional
    @Override
    public List<Schedule> getSchedules() {
        return this.getSchedules(new Filter()) ;
    }

    @Transactional
    @Override
    public List<Schedule> getSchedules(Filter filter) {
        return scheduleDAO.list(filter);
    }

    @Override
    public boolean addIfPossible(final Schedule schedule) throws ScheduleException {
        // TODO theoritically -- performance is bad
        LOG.debug("adding schedule if possible");
        try{
            transactionTemplate.execute(
                    new TransactionCallback<Void>() {
                        @Override
                        public Void doInTransaction(TransactionStatus transactionStatus) {
                            schedule.setLocker(schedule.getStudent().getPerson());
                            schedule.setLockTime(Calendar.getInstance());
                            scheduleDAO.insert(schedule);
                            return null;
                        }
                    });

            // check if student have no lessons
            final List<Schedule> studentLessons = new ArrayList<>();
            transactionTemplate.execute(
                    new TransactionCallback<Void>() {
                        @Override
                        public Void doInTransaction(TransactionStatus transactionStatus) {
                            Filter filter = new Filter();
                            filter.setStudent(schedule.getStudent());
                            studentLessons.addAll(scheduleDAO.list(filter));
                            return null;
                        }
                    }
            );
            for(Schedule lesson: studentLessons){
                if(lesson.getId().equals(schedule.getId())){
                    continue;
                }
                if(schedule.getFrom()!=null && !schedule.getFrom().after(lesson.getFrom())){
                    if(schedule.getTo()!=null && !schedule.getTo().before(lesson.getTo())){
                        throw new ScheduleException("ERR_STUD","Student planning error");
                    }else
                    if(schedule.getTo()!=null && !schedule.getTo().before(lesson.getFrom())){
                        throw new ScheduleException("ERR_STUD","Student planning error");
                    }
                }else{
                    if(schedule.getFrom()!=null && !schedule.getFrom().after(lesson.getTo())){
                        throw new ScheduleException("ERR_STUD","Student planning error");
                    }
                }
            }
            LOG.debug("no problems with student");
            // check if instructor is free
            final List<Schedule> instructorLessons = new ArrayList<>();
            transactionTemplate.execute(
                    new TransactionCallback<Void>() {
                        @Override
                        public Void doInTransaction(TransactionStatus transactionStatus) {
                            Filter filter = new Filter();
                            filter.setInstructor(schedule.getInstructor());
                            instructorLessons.addAll(scheduleDAO.list(filter));
                            return null;
                        }
                    }
            );

            for(Schedule lesson: instructorLessons){
                if(lesson.getId().equals(schedule.getId())){
                    continue;
                }
                if(schedule.getFrom()!=null && !schedule.getFrom().after(lesson.getFrom())){
                    if(schedule.getTo()!=null && schedule.getTo().after(lesson.getTo())){
                        throw new ScheduleException("ERR_INST","Instructor planning error");
                    }else
                    if(schedule.getTo()!=null && !schedule.getTo().before(lesson.getFrom())){
                        throw new ScheduleException("ERR_INST","Instructor planning error");
                    }
                }else{
                    if(schedule.getFrom()!=null && !schedule.getFrom().after(lesson.getTo())){
                        throw new ScheduleException("ERR_INST","Instructor planning error");
                    }
                }
            }
            LOG.debug("no problems with instructor");
            // check if plane is free
            final List<Schedule> planeLessons = new ArrayList<>();
            transactionTemplate.execute(
                    new TransactionCallback<Void>() {
                        @Override
                        public Void doInTransaction(TransactionStatus transactionStatus) {
                            Filter filter = new Filter();
                            filter.setPlane(schedule.getPlane());
                            planeLessons.addAll(scheduleDAO.list(filter));
                            return null;
                        }
                    }
            );
            for(Schedule lesson: studentLessons){
                if(lesson.getId().equals(schedule.getId())){
                    continue;
                }
                if(schedule.getFrom()!=null && !schedule.getFrom().after(lesson.getFrom())){
                    if(schedule.getTo()!=null && schedule.getTo().after(lesson.getTo())){
                        throw new ScheduleException("ERR_PLNE","Plane planning error");
                    }else
                    if(schedule.getTo()!=null && !schedule.getTo().before(lesson.getFrom())){
                        throw new ScheduleException("ERR_PLNE","Plane planning error");
                    }
                }else{
                    if(schedule.getFrom()!=null && !schedule.getFrom().after(lesson.getTo())){
                        throw new ScheduleException("ERR_PLNE","Plane planning error");
                    }
                }
            }
            schedule.removeLock();
            CannotLockException e =
            transactionTemplate.execute(
                    new TransactionCallback<CannotLockException>() {
                        @Override
                        public CannotLockException doInTransaction(TransactionStatus transactionStatus) {
                            try{
                                scheduleDAO.safeUpdate(schedule,schedule.getStudent().getPerson());
                            }catch(CannotLockException e){
                                return e;
                            }
                            return null;
                        }
                    }
            );
            if(e!=null){
                throw e;
            }
            LOG.debug("no problems with plane");
        } catch (ScheduleException e){
            transactionTemplate.execute(
                    new TransactionCallback<Void>() {
                        @Override
                        public Void doInTransaction(TransactionStatus transactionStatus) {
                            scheduleDAO.delete(schedule.getId());
                            return null;
                        }
                    }
            );
            throw e;
        } catch (CannotLockException e){
        }
        return true;
    }

    @Transactional
    @Override
    public boolean delete(Filter filter) {
        // FIXME
        //return scheduleDAO.delete(filter);
        return true;
    }

}
