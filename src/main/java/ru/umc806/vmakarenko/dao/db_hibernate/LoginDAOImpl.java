package ru.umc806.vmakarenko.dao.db_hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.LoginDAO;
import ru.umc806.vmakarenko.dao.PersonDAO;
import ru.umc806.vmakarenko.domain.Person;

/**
 * Created by VMakarenko on 5/22/14.
 */

// TODO what is the reason for that class???
public class LoginDAOImpl  extends CommonDAOImpl implements LoginDAO {
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
