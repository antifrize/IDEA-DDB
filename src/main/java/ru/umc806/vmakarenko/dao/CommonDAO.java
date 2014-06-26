package ru.umc806.vmakarenko.dao;

import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.exceptions.CannotAddException;
import ru.umc806.vmakarenko.exceptions.CannotLockException;
import ru.umc806.vmakarenko.util.Filter;

import java.util.List;

/**
 * Created by VMakarenko on 6/16/14.
 */
public interface CommonDAO<E> {
    Integer insert (E entity);
    void update (E entity);
    void delete (E entity);
    void delete (int id);
    List<E> list();
    List<E> list(Filter filter);
    public E get(int id);

    public void safeUpdate(E entity,Person person)throws CannotLockException;
    public void safeDelete(E entity, Person person) throws CannotLockException;
    public void safeDelete(int id, Person person)throws CannotLockException;
}
