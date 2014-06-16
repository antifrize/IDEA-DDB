package ru.umc806.vmakarenko.dao.db_hibernate;

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

public class StudentDAOImpl extends CommonDAOImpl implements StudentDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Student> list() {
        return list(new Filter());
    }

    @Override
    public List<Student> list(Filter filter) {
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
    public Student get(int id) {
        Student student = new Student();
        student.setId(id);
        List<Student> list = list(new Filter().setStudent(student));
        if(list.isEmpty()){
            return null;
        }else{
            return list.get(0);
        }
    }
}
