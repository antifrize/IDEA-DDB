package ru.umc806.vmakarenko.service;

import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.exceptions.CannotAddException;
import ru.umc806.vmakarenko.exceptions.NoSuchPersonException;
import ru.umc806.vmakarenko.util.Filter;

import java.util.List;

/**
 * Created by VMakarenko on 5/22/14.
 */
public interface PersonService {
    public boolean isInstructor(Person person);
    public boolean isStudent(Person person);
    public boolean isAdmin(Person person);
    public boolean isInstructor(String person);
    public boolean isStudent(String person);
    public boolean isAdmin(String person);
    public boolean isUnapprovedInstructor(String person);
    public boolean isUnapprovedStudent(String person);

    public void addPerson(Person person) throws CannotAddException;
    public Person getOne(Filter filter) throws NoSuchPersonException;
    public List<Person> get(Filter filter) throws NoSuchPersonException;
}
