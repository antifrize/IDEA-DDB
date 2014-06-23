package ru.umc806.vmakarenko.dao.db_hibernate;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.BlacklistDAO;
import ru.umc806.vmakarenko.domain.Blacklist;
import ru.umc806.vmakarenko.domain.Person;

import java.util.List;

/**
 * Created by VMakarenko on 6/15/14.
 */
public class BlacklistDAOImpl  extends CommonDAOImpl<Blacklist> implements BlacklistDAO{
    Logger LOG = LoggerFactory.getLogger(BlacklistDAOImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    public BlacklistDAOImpl(){
        super(Blacklist.class);
    }

    @Override
    public Blacklist get(String id) {
        List<Blacklist> blacklistList = sessionFactory.getCurrentSession().createCriteria(Blacklist.class,"blacklist").add(Restrictions.eq("blacklist.id",id)).list();
        return blacklistList.isEmpty()?null:blacklistList.get(0);
    }

    @Override
    public List<Blacklist> list(Person blocker, Person blocked) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Blacklist.class,"blacklist");
        if(blocker!=null){
            criteria.add(Restrictions.eq("blacklist.blocker",blocker));
        }
        if(blocked!=null){
            criteria.add(Restrictions.eq("blacklist.blocked",blocked));
        }
        return criteria.list();
    }

    @Override
    public void insert(Person blocker, Person blocked) {
        Blacklist blacklist = new Blacklist();
        blacklist.setBlacklister(blocker);
        blacklist.setBlacklisted(blocked);
        sessionFactory.getCurrentSession().save(blacklist);
    }
}
