package ru.umc806.vmakarenko.service.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.InstructorDAO;
import ru.umc806.vmakarenko.dao.StudentDAO;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.service.PersonService;
import ru.umc806.vmakarenko.util.Filter;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * Created by VMakarenko on 5/22/14.
 */
@Transactional
public class PersonServiceImpl implements PersonService {


    Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class);
    @Autowired
    StudentDAO studentDAO;
    @Autowired
    InstructorDAO instructorDAO;

    @Override
    public boolean isInstructor(Person person) {
        return instructorDAO.getInstructorsList(new Filter().setPerson(person)).size()>0;
    }

    @Override
    public boolean isStudent(Person person) {
        return studentDAO.getStudentsList(new Filter().setPerson(person)).size()>0;
    }

    @Override
    public boolean isAdmin(Person person) {
        return false;
    }

    @Override
    public boolean isInstructor(String username) {
        LOG.debug("isInstructor?");
        Person person = new Person();
        person.setLogin(username);
        return instructorDAO.getInstructorsList(new Filter().setPerson(person)).size()>0;
    }

    @Override
    public boolean isStudent(String username) {
        LOG.debug("isStudent?");
        Person person = new Person();
        person.setLogin(username);
        return studentDAO.getStudentsList(new Filter().setPerson(person)).size()>0;
    }

    @Override
    public boolean isAdmin(String username) {
        LOG.debug("isAdmin?");
        Person person = new Person();
        person.setLogin(username);
        return false;
    }

    public StudentDAO getStudentDAO() {
        return studentDAO;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public InstructorDAO getInstructorDAO() {
        return instructorDAO;
    }

    public void setInstructorDAO(InstructorDAO instructorDAO) {
        this.instructorDAO = instructorDAO;
    }
}
