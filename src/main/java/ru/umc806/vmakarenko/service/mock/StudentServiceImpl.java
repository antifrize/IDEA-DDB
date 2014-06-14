package ru.umc806.vmakarenko.service.mock;

import ru.umc806.vmakarenko.dao.InstructorDAO;
import ru.umc806.vmakarenko.dao.StudentDAO;
import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.service.StudentService;
import ru.umc806.vmakarenko.util.Filter;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by VMakarenko on 5/16/14.
 */
@Transactional
public class StudentServiceImpl implements StudentService {
    @Resource(name = "studentDAO")
    private StudentDAO studentDAO;
    @Resource(name = "instructorDAO")
    private InstructorDAO instructorDAO;

    public List<Student> getAll(){
        return studentDAO.getStudentsList();
    }

    public Instructor getPreferredInstructor(Student student){
        return null;
    }

    public List<Instructor> getBlacklisted(Student student){
        return null;
    }

    public Instructor getInstructorBySchedule(Schedule schedule){
        return null;
    }

    public List<Schedule> getPreferredSchedules(){
        return null;
    }

    public List<Student> getStudent(Filter filter){
        return studentDAO.getStudentsList(filter);
    }
}

