package ru.umc806.vmakarenko.dao;

import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.util.Filter;

import java.util.List;

public interface PersonDAO {
	public List<Person> list();
    public List<Person> list(Filter filter);
}
