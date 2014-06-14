package ru.umc806.vmakarenko.service.mock;

import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.LoginDAO;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.service.LoginService;

import javax.transaction.Transactional;

/**
 * Created by VMakarenko on 5/22/14.
 */
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDAO loginDAO;
    @Override
    public Person checkLogin(String login, char[] password) {
        return loginDAO.checkLogin(login,password);
    }
}
