package ru.umc806.vmakarenko.service;

import ru.umc806.vmakarenko.dao.InstructorDAO;
import ru.umc806.vmakarenko.dao.ScheduleDAO;
import ru.umc806.vmakarenko.dao.StudentDAO;
import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.exceptions.CannotAddException;
import ru.umc806.vmakarenko.util.Filter;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by VMakarenko on 5/16/14.
 */
public interface InstructorService {
    public List<Instructor> getAll();
    public List<Instructor> getAllBlacklistedInstructors(Instructor instructor);
    public List<Student> getBlacklistedStudents(Instructor instructor);
    public List<Student> getStudents(Instructor instructor);
    public List<Schedule> getSchedules(Instructor instructor);
    public List<Schedule> getSchedulesByStudent(Instructor instructor, Student student);
    public List<Instructor> getInstructors(Filter filter);
    public List<Instructor> getUnapproved();
    public boolean approve(String id);
    public void add(Instructor instructor) throws CannotAddException;
}
