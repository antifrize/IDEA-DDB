package ru.umc806.vmakarenko.dao;

import java.util.List;

import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.util.Filter;

public interface InstructorDAO {
	public List<Instructor> getInstructorsList();
    public List<Instructor> getInstructorsList(Filter filter);
    public Instructor getInstructor(Student student);
}
