package ru.umc806.vmakarenko.service.mock;

import org.springframework.transaction.annotation.Transactional;
import ru.umc806.vmakarenko.dao.InstructorDAO;
import ru.umc806.vmakarenko.dao.ScheduleDAO;
import ru.umc806.vmakarenko.dao.StudentDAO;
import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.service.InstructrorService;
import ru.umc806.vmakarenko.util.Filter;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by VMakarenko on 5/16/14.
 */
@javax.transaction.Transactional
public class InstructrorServiceImpl implements InstructrorService {
    @Resource(name = "instructorDAO")
    private InstructorDAO instructorDAO;
    @Resource(name="studentDAO")
    private StudentDAO studentDAO;
    @Resource(name="scheduleDAO")
    private ScheduleDAO scheduleDAO;
    @Transactional
    public List<Instructor> getAll(){
        return instructorDAO.getInstructorsList();
    }


    @Override
    public List<Instructor> getAllBlacklistedInstructors(Instructor instructor){
        return instructorDAO.getInstructorsList(new Filter().setBlacklisted(true));
    }

    @Override
    public List<Student> getBlacklistedStudents(Instructor instructor){
        return studentDAO.getStudentsList(new Filter().setBlacklisted(true).setInstructor(instructor));
    }

    @Override
    public List<Student> getStudents(Instructor instructor){
        return studentDAO.getStudentsList(new Filter().setInstructor(instructor));
    }

    @Override
    public List<Schedule> getSchedules(Instructor instructor){
        return scheduleDAO.getSchedulesList(new Filter().setInstructor(instructor));
    }

    @Override
    public List<Schedule> getSchedulesByStudent(Instructor instructor, Student student){
        return scheduleDAO.getSchedulesList(new Filter().setInstructor(instructor).setStudent(student));
    }

    @Override
    public List<Instructor> getInstructors(Filter filter) {
        return instructorDAO.getInstructorsList(filter);
    }


}
