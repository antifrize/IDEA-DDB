package ru.umc806.vmakarenko.dao;

import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.util.Filter;

import java.util.List;

public interface PersonDAO {
	public List<Person> getPersonsList();
    public List<Person> getPersonsList(Filter filter);
    public Person getPerson(Person person);
}
