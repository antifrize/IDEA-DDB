package ru.umc806.vmakarenko.dao;

import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.util.Filter;

import java.util.List;

public interface StudentDAO {
	public List<Student> getStudentsList();
    public List<Student> getStudentsList(Filter filter);
    public Student getStudent(int id);
    public boolean deleteStudent(int id);
    public boolean addStudent(Student student);
}
