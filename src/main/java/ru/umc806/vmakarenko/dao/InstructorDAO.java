package ru.umc806.vmakarenko.dao;

import java.util.List;

import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.exceptions.CannotAddException;
import ru.umc806.vmakarenko.util.Filter;

public interface InstructorDAO  extends CommonDAO<Instructor>{
	public List<Instructor> list();
    public List<Instructor> list(Filter filter);

    public Instructor get(int id);
}
