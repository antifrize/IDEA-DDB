package ru.umc806.vmakarenko.dao.db_hibernate;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.CommonDAO;
import ru.umc806.vmakarenko.domain.Lockable;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.exceptions.CannotLockException;
import ru.umc806.vmakarenko.util.Filter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static ru.umc806.vmakarenko.util.Consts.*;

/**
 * Created by VMakarenko on 6/16/14.
 */
public abstract class CommonDAOImpl<E> implements CommonDAO<E>{

    private Class<E> currentClass;

    private boolean tryLock(E entity, Person person){
        Query query = getSession()
                .createSQLQuery(
                        UPDATE_SQL_QUERY.replace(":tableName", getTableName())
                                .replace(":locker", person.getId().toString())
                                .replace(":locker", person.getId().toString())
                                .replace(":idColumn", getIdColumn())
                                .replace(":idValue", getIdValue(entity))).setTimestamp("lockTime",new Date());
        query.executeUpdate();
        E newEntity = get(Integer.parseInt(getIdValue(entity)));
        return ((Lockable)newEntity).getLocker().equals(person);
    }

    private void unlock(E entity){
        if(entity instanceof Lockable){
            Lockable lockable = (Lockable)entity;
            lockable.removeLock();
            update(entity);
        }
    }

    @Override
    public void safeUpdate(E entity,Person person) throws CannotLockException {
        if(tryLock(entity,person)){
            update(entity);
            unlock(entity);
        }else{
            throw new CannotLockException();
        }
    }

    @Override
    public void safeDelete(E entity,Person person) throws CannotLockException {
        if(entity instanceof  Lockable){
            if(tryLock(entity,person)){
                delete(entity);
                unlock(entity);
            }else{
                throw new CannotLockException();
            }
        }else{
            delete(entity);
        }
    }


    @Override
    public void safeDelete(int id,Person person) throws CannotLockException {
        E entity = get(id);
        if(entity instanceof Lockable){
            if(tryLock(entity, person)){
                delete(entity);
                unlock(entity);
            }else{
                throw new CannotLockException();
            }
        }else{
            delete(entity);
        }
    }

    @Autowired
    SessionFactory sessionFactory;

    CommonDAOImpl(Class currentClass)  {
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

    private String getTableName() {
        return currentClass.getAnnotation(Table.class).name();
    }

    private String getIdColumn()  {
        for(Field field: currentClass.getDeclaredFields()){
            field.setAccessible(true);
            if(field.getAnnotation(Id.class)!=null){
                return field.getAnnotation(Column.class).name();
            }
        }
        return null;
    }
    private String getIdValue(E entity)  {
        for(Field field: currentClass.getDeclaredFields()){
            field.setAccessible(true);
            if(field.getAnnotation(Id.class)!=null){
                try{
                    return String.valueOf((Integer)field.get(entity));
                }catch(Exception e){
                    return null;
                }
            }
        }
        return null;
    }

}
