package ru.umc806.vmakarenko.dao;

import ru.umc806.vmakarenko.domain.Blacklist;
import ru.umc806.vmakarenko.domain.Person;

import java.util.List;

/**
 * Created by VMakarenko on 6/15/14.
 */
public interface BlacklistDAO extends CommonDAO<Blacklist> {
    public Blacklist get(String id);
    public List<Blacklist> list(Person blocker, Person blocked);
    public void insert(Person blocker,Person blocked);
}
