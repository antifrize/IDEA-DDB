package ru.umc806.vmakarenko.dao.mock;

import ru.umc806.vmakarenko.dao.LoginDAO;
import ru.umc806.vmakarenko.domain.Person;

/**
 * Created by VMakarenko on 5/22/14.
 */
public class LoginDAOImpl implements LoginDAO {
    @Override
    public Person checkLogin(String login, char[] password) {
        return NamingFactory.getPerson();
    }
}
