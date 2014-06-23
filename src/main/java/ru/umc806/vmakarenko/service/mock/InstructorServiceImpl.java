package ru.umc806.vmakarenko.service.mock;

import org.springframework.transaction.annotation.Transactional;
import ru.umc806.vmakarenko.dao.InstructorDAO;
import ru.umc806.vmakarenko.dao.PersonDAO;
import ru.umc806.vmakarenko.dao.ScheduleDAO;
import ru.umc806.vmakarenko.dao.StudentDAO;
import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.exceptions.CannotAddException;
import ru.umc806.vmakarenko.service.InstructorService;
import ru.umc806.vmakarenko.util.Filter;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by VMakarenko on 5/16/14.
 */
@javax.transaction.Transactional
public class InstructorServiceImpl implements InstructorService {
    @Resource(name = "instructorDAO")
    private InstructorDAO instructorDAO;
    @Resource(name="studentDAO")
    private StudentDAO studentDAO;
    @Resource(name="scheduleDAO")
    private ScheduleDAO scheduleDAO;
    @Resource(name="personDAO")
    private PersonDAO personDAO;
    @Transactional
    public List<Instructor> getAll(){
        return instructorDAO.list();
    }


    @Override
    public List<Instructor> getAllBlacklistedInstructors(Instructor instructor){
        return instructorDAO.list(new Filter().setBlacklisted(true));
    }

    @Override
    public List<Student> getBlacklistedStudents(Instructor instructor){
        return studentDAO.list(new Filter().setBlacklisted(true).setInstructor(instructor));
    }

    @Override
    public List<Student> getStudents(Instructor instructor){
        return studentDAO.list(new Filter().setInstructor(instructor));
    }

    @Override
    public List<Schedule> getSchedules(Instructor instructor){
        return scheduleDAO.list(new Filter().setInstructor(instructor));
    }

    @Override
    public List<Schedule> getSchedulesByStudent(Instructor instructor, Student student){
        return scheduleDAO.list(new Filter().setInstructor(instructor).setStudent(student));
    }

    @Override
    public List<Instructor> getInstructors(Filter filter) {
        return instructorDAO.list(filter);
    }

    @Override
    public List<Instructor> getUnapproved() {
        Instructor instructor = new Instructor();
        instructor.setApproved(true);
        return instructorDAO.list(new Filter().setInstructor(instructor));
    }


    @Override
    public boolean approve(String id) {
        Instructor instructor = instructorDAO.get(Integer.parseInt(id));
        instructor.setApproved(true);
        instructorDAO.update(instructor);
        return true;
    }

    @Override
    public void add(Instructor instructor) throws CannotAddException{
        if(instructorDAO.list(
                new Filter().setPerson(
                        new Person().setId(
                                instructor.getPerson().getId()
                        )
                )
        ).isEmpty()) {
            instructorDAO.insert(instructor);
        }else{
            throw new CannotAddException("cannot add instructor");
        }
    }
}
