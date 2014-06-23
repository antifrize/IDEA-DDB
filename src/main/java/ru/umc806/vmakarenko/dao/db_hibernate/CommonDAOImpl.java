package ru.umc806.vmakarenko.dao.db_hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import ru.umc806.vmakarenko.dao.CommonDAO;
import ru.umc806.vmakarenko.exceptions.CannotAddException;
import ru.umc806.vmakarenko.util.Filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VMakarenko on 6/16/14.
 */
public abstract class CommonDAOImpl<E> implements CommonDAO<E>{
    private Class currentClass;

    @Autowired
    SessionFactory sessionFactory;

    CommonDAOImpl(Class currentClass){
        this.currentClass = currentClass;
    }

    @Override
    public Integer insert(E entity){
        Serializable serializable = getSession().save(entity);
        if(serializable instanceof Integer){
            return (Integer)serializable;
        }else{
            return null;
        }
    }

    @Override
    public void update(E entity) {
        getSession().merge(entity);
    }

    @Override
    public void delete(E entity) {
        getSession().delete(entity);
    }

    @Override
    public void delete(int id) {
        E entity = (E)getSession().load(currentClass,id);
        getSession().delete(entity);
    }

    @Override
    public E get(int id){
        return (E)getSession().load(currentClass,id);
    }

    @Override
    public List<E> list() {
        return new ArrayList<>();
    }

    @Override
    public List<E> list(Filter filter) {
        return new ArrayList<>();
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
}
