package ru.umc806.vmakarenko.service.mock;

import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.PersonDAO;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.service.LoginService;
import ru.umc806.vmakarenko.util.Filter;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by VMakarenko on 5/22/14.
 */
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    private PersonDAO personDAO;
    @Override
    public Person checkLogin(String login, char[] password) {
        Person person = new Person();
        person.setLogin(login);
        List<Person> list =  personDAO.list(new Filter().setPerson(person));
        return list.isEmpty()?null:list.get(0);
    }
}
