package ru.umc806.vmakarenko.service;

import ru.umc806.vmakarenko.domain.Person;

/**
 * Created by VMakarenko on 5/22/14.
 */
public interface LoginService {
    public Person checkLogin(String login, char[] password);
}
