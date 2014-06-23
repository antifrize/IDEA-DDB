package ru.umc806.vmakarenko.service;

import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.exceptions.CannotAddException;
import ru.umc806.vmakarenko.util.Filter;

import java.util.List;

/**
 * Created by VMakarenko on 5/21/14.
 */
public interface StudentService {
    public List<Student> getAll();
    public Instructor getPreferredInstructor(Student student);
    public List<Student> getBlacklisted();
    public List<Student> getBlacklisted(Person person);
    public List<Student> getBlacklisted(Person person, boolean blacklisted);
    public Instructor getInstructorBySchedule(Schedule schedule);
    public List<Schedule> getPreferredSchedules();
    public List<Student> getStudent(Filter filter);
    public List<Student> getUnapproved();
    public boolean approve(String id);
    public void add(Student student) throws CannotAddException;
}

