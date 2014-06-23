package ru.umc806.vmakarenko.dao.db_jdbc;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.BlacklistDAO;
import ru.umc806.vmakarenko.dao.db_jdbc.CommonDAOImpl;
import ru.umc806.vmakarenko.domain.Blacklist;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.util.Filter;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by VMakarenko on 6/15/14.
 */
public class BlacklistDAOImpl  extends CommonDAOImpl<Blacklist> implements BlacklistDAO{
    BlacklistDAOImpl() {
        super(Blacklist.class);
    }

    @Override
    public Blacklist get(String id) {
        return null;
    }

    @Override
    public List<Blacklist> list(Person blocker, Person blocked) {
        return null;
    }

    @Override
    public void insert(Person blocker, Person blocked) {

    }

    @Override
    public List<Blacklist> list() {
        return null;
    }

    @Override
    public List<Blacklist> list(Filter filter) {
        return null;
    }

}
