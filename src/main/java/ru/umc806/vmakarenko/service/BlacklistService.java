package ru.umc806.vmakarenko.service;

import ru.umc806.vmakarenko.domain.Person;

/**
 * Created by VMakarenko on 6/16/14.
 */
public interface BlacklistService {
    public boolean isBlacklisted(Person person);
    public void setBlacklisted(Person person);
    public void setBlacklisted(Person person,Person blocker);
    public void setBlacklisted(int id);
    public boolean isBlacklisted(int id);
}
