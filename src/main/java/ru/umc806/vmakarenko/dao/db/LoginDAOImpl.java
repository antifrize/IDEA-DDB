package ru.umc806.vmakarenko.dao.db;

import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.LoginDAO;
import ru.umc806.vmakarenko.dao.PersonDAO;
import ru.umc806.vmakarenko.domain.Person;

/**
 * Created by VMakarenko on 5/22/14.
 */
public class LoginDAOImpl implements LoginDAO {
    @Autowired
    PersonDAO personDAO;
    @Override
    public Person checkLogin(String login, char[] password) {
        Person person = new Person();
        person.setLogin(login);
        person.setPass_hash(String.valueOf(password));
        return person;
    }
}
