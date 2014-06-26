package ru.umc806.vmakarenko.dao.db_jdbc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.CommonDAO;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.util.Filter;
import sun.reflect.Reflection;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VMakarenko on 6/16/14.
 */
public abstract class CommonDAOImpl<E> implements CommonDAO<E>{
    private Class<E> currentClass;
    private static Logger LOG = LoggerFactory.getLogger(CommonDAOImpl.class);

    CommonDAOImpl(Class currentClass){
        this.currentClass = currentClass;
    }

    @Override
    public Integer insert(E entity) {
        try{
            String fieldsString = "";
            String valuesString = "";
            for(Field field: currentClass.getDeclaredFields()){
                field.setAccessible(true);
                if(field.getAnnotation(Column.class)!=null){
                    fieldsString +=(field.getAnnotation(Column.class).name()+",");
                    Object value = field.get(entity);
                    if(value==null) continue;
                    valuesString += field.getType().equals(String.class)?"'"+value.toString()+"',":(value.toString()+",");
                }
                if(field.getAnnotation(ManyToOne.class)!=null || field.getAnnotation(OneToOne.class)!=null || field.getAnnotation(OneToMany.class)!=null){
                    fieldsString += (field.getAnnotation(JoinColumn.class).name()+",");
                    CommonDAO dao = (CommonDAO)Class.forName("ru.umc806.vmakarenko.dao.db_jdbc."+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1)+"DAOImpl").getConstructor().newInstance();
                    valuesString += (dao.insert(field.get(entity))+",");
                }
            }
            fieldsString = fieldsString.substring(0,fieldsString.length()-1);
            valuesString = valuesString.substring(0,valuesString.length()-1);
            LOG.debug(String.format("finally: fieldsString = %s, valuesString = %s", fieldsString, valuesString));
            PreparedStatement ps = getConnection().prepareStatement(String.format("INSERT INTO %s (%s) VALUES (%s)",getTableName(),fieldsString,valuesString));
           // ps.setString(1,valuesString);
            ps.executeQuery();
        }catch(Exception e){
            LOG.debug(e.getMessage());
        }
        return 0;
    }

    @Override
    public void update(E entity) {
    }

    @Override
    public void delete(E entity) {
        try{
            Field idField = entity.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            String idColumn = "";
            for(Field field: currentClass.getDeclaredFields()){
                field.setAccessible(true);
                if(field.getAnnotation(Id.class)!=null){
                    idColumn = field.getAnnotation(Column.class).name();
                }
            }
            PreparedStatement ps = getConnection().prepareStatement(String.format("DELETE FROM %s WHERE %s = %s",getTableName(),idColumn,idField.get(entity).toString()));
            ps.executeQuery();
            for(Field field: currentClass.getDeclaredFields()){
                field.setAccessible(true);
                if(field.getAnnotation(ManyToOne.class)!=null || field.getAnnotation(OneToOne.class)!=null || field.getAnnotation(OneToMany.class)!=null){
                    CommonDAO dao = (CommonDAO)Class.forName("ru.umc806.vmakarenko.dao.db_jdbc."+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1)+"DAOImpl").getConstructor().newInstance();
                    dao.delete(field.getInt(entity));
                }
            }


        }catch(Exception e){
            LOG.error(e.getMessage());
            LOG.error("Illegal entity: no ID!");
        }
    }

    @Override
    public void delete(int id) {
        try{
            String idColumn = "";
            for(Field field: currentClass.getDeclaredFields()){
                field.setAccessible(true);
                if(field.getAnnotation(Id.class)!=null){
                    idColumn = field.getAnnotation(Column.class).name();
                }
            }
            PreparedStatement ps = getConnection().prepareStatement(String.format("DELETE FROM %s WHERE %s = %s", getTableName(),idColumn,id));
            CommonDAO currentDao = (CommonDAO)Class.forName("ru.umc806.vmakarenko.dao.db_jdbc."+currentClass.getSimpleName().substring(0,1).toUpperCase()+currentClass.getSimpleName().toLowerCase().substring(1)+"DAOImpl").getConstructor().newInstance();
            E entity = (E)currentDao.get(id);
            for(Field field: currentClass.getDeclaredFields()){
                field.setAccessible(true);
                if(field.getAnnotation(ManyToOne.class)!=null || field.getAnnotation(OneToOne.class)!=null || field.getAnnotation(OneToMany.class)!=null){
                    CommonDAO dao = (CommonDAO)Class.forName("ru.umc806.vmakarenko.dao.db_jdbc."+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1)+"DAOImpl").getConstructor().newInstance();
                    dao.delete(field.getInt(entity));
                }
            }
            ResultSet rs = ps.executeQuery();
        }catch(Exception e){
            LOG.error(e.getMessage());
        }
    }

    @Override
    public E get(int id){
        try{
            Connection connection = getConnection();
            String idColumn = "";
            for(Field field: currentClass.getDeclaredFields()){
                field.setAccessible(true);
                if(field.getAnnotation(Id.class)!=null){
                    idColumn = field.getAnnotation(Column.class).name();
                }
            }
            PreparedStatement ps = connection.prepareStatement(String.format("SELECT * FROM %s WHERE %s = %s",getTableName(),idColumn,id));
            ResultSet rs = ps.executeQuery();
            rs.next();
            E entity = currentClass.newInstance();
            for(Field field: currentClass.getDeclaredFields()){
                field.setAccessible(true);
                if(field.getAnnotation(Column.class)!=null){
                    String columnName = field.getAnnotation(Column.class).name();
                    field.set(entity, field.getType().equals(String.class) ? rs.getString(columnName) : rs.getInt(columnName));
                }
                if(field.getAnnotation(ManyToOne.class)!=null || field.getAnnotation(OneToOne.class)!=null || field.getAnnotation(OneToMany.class)!=null){
                    CommonDAO dao = (CommonDAO)Class.forName("ru.umc806.vmakarenko.dao.db_jdbc."+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1)+"DAOImpl").getConstructor().newInstance();
                    field.set(entity, dao.get(rs.getInt(field.getAnnotation(JoinColumn.class).name())));
                }
            }
            return entity;
        }catch(Exception e){
            LOG.error(e.getMessage());
        }
        return null;
    }

    public List<E> list(){
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(String.format("SELECT * FROM %s",getTableName()));
            ResultSet rs = ps.executeQuery();
            List<E> list = new ArrayList<>();
            while(rs.next()){
                E entity = currentClass.newInstance();
                for(Field field: currentClass.getDeclaredFields()){
                    field.setAccessible(true);
                    if(field.getAnnotation(Column.class)!=null){
                        String columnName = field.getAnnotation(Column.class).name();
                        field.set(entity, field.getType().equals(String.class) ? rs.getString(columnName) : rs.getInt(columnName));
                    }
                    if(field.getAnnotation(ManyToOne.class)!=null || field.getAnnotation(OneToOne.class)!=null || field.getAnnotation(OneToMany.class)!=null){
                        CommonDAO dao = (CommonDAO)Class.forName("ru.umc806.vmakarenko.dao.db_jdbc."+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1)+"DAOImpl").getConstructor().newInstance();
                        field.set(entity, dao.get(rs.getInt(field.getAnnotation(JoinColumn.class).name())));
                    }
                }
                list.add(entity);
            }
            return list;
        }catch(Exception e){

        }
        return null;
    }


    private String getTableName() {
        return currentClass.getAnnotation(Table.class).name();
    }

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        try{
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","TAILWHEELS","123456");
        }catch(Exception e){
            LOG.error(e.getMessage());
        }
        return null;
    }


}