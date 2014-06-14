package ru.umc806.vmakarenko.dao.db;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.StudentDAO;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.util.Filter;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public List<Student> getStudentsList() {
        return getStudentsList(new Filter());
    }

    @Override
    public List<Student> getStudentsList(Filter filter) {
        Session session =sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Student.class,"student");
        if(filter.getStudent()!=null){
            Student student = filter.getStudent();
            if(student.getId()!=null){
                criteria.add(Restrictions.eq("student_id", student.getId()));
            }
        }
        if(filter.getPerson()!=null){
            criteria.createAlias("student.person","person");
            Person person = filter.getPerson();
            if(person.getId()!=null){
                criteria.add(Restrictions.eq("person.id",person.getId()));
            }
            if(person.getName()!=null){
                criteria.add(Restrictions.eq("person.name",person.getName()));
            }
            if(person.getSurname()!=null){
                criteria.add(Restrictions.eq("person.surname",person.getSurname()));
            }
            if(person.getLastname()!=null){
                criteria.add(Restrictions.eq("person.lastname",person.getLastname()));
            }
            if(person.getLogin()!=null){
                criteria.add(Restrictions.eq("person.login",person.getLogin()));
            }
        }
        return criteria.list();
    }

    @Override
    public Student getStudent(int id) {
        return null;
    }

    @Override
    public boolean deleteStudent(int id) {
        return false;
    }

    @Override
    public boolean addStudent(Student student) {
        return false;
    }
}
