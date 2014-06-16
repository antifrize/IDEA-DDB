package ru.umc806.vmakarenko.service;

import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.util.Filter;

import java.util.List;

/**
 * Created by VMakarenko on 5/21/14.
 */
public interface StudentService {
    public List<Student> getAll();
    public Instructor getPreferredInstructor(Student student);
    public List<Instructor> getBlacklisted(Student student);
    public Instructor getInstructorBySchedule(Schedule schedule);
    public List<Schedule> getPreferredSchedules();
    public List<Student> getStudent(Filter filter);
    public List<Student> getUnapproved();
    public boolean approve(String id);
}

