package ru.umc806.vmakarenko.service.mock;

import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.BlacklistDAO;
import ru.umc806.vmakarenko.dao.PersonDAO;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.service.BlacklistService;

/**
 * Created by VMakarenko on 6/16/14.
 */
public class BlacklistServiceImpl implements BlacklistService {
    @Autowired
    BlacklistDAO blacklistDAO;
    @Autowired
    PersonDAO personDAO;

    @Override
    public boolean isBlacklisted(Person person) {
        return !blacklistDAO.list(null, person).isEmpty();
    }

    @Override
    public void setBlacklisted(Person person) {
        blacklistDAO.insert(null,person);
    }

    @Override
    public void setBlacklisted(Person person, Person blocker) {
        blacklistDAO.insert(blocker,person);
    }

    @Override
    public void setBlacklisted(int id) {
        setBlacklisted(personDAO.get(id));
    }

    @Override
    public boolean isBlacklisted(int id) {
        return isBlacklisted(personDAO.get(id));;
    }
}
