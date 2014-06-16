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

    @Override
    public List<Student> getAll(){
        return studentDAO.list();
    }

    @Override
    public Instructor getPreferredInstructor(Student student){
        return null;
    }

    @Override
    public List<Instructor> getBlacklisted(Student student){
        return null;
    }

    @Override
    public Instructor getInstructorBySchedule(Schedule schedule){
        return null;
    }

    @Override
    public List<Schedule> getPreferredSchedules(){
        return null;
    }

    @Override
    public List<Student> getStudent(Filter filter){
        return studentDAO.list(filter);
    }

    @Override
    public List<Student> getUnapproved() {
        Student student = new Student();
        student.setApproved(true);
        return studentDAO.list(new Filter().setStudent(student));
    }

    @Override
    public boolean approve(String id) {
        Student student = studentDAO.get(Integer.parseInt(id));
        student.setApproved(true);
        studentDAO.update(student);
        return true;
    }
}

