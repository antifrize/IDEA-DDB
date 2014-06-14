package ru.umc806.vmakarenko.service;

import ru.umc806.vmakarenko.domain.Person;

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
}
