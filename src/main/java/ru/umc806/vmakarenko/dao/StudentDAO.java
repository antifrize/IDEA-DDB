package ru.umc806.vmakarenko.dao;

import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.exceptions.CannotAddException;
import ru.umc806.vmakarenko.util.Filter;

import java.util.List;

public interface StudentDAO  extends CommonDAO<Student>{
	public List<Student> list();
    public List<Student> list(Filter filter);
    public Student get(int id);
}
