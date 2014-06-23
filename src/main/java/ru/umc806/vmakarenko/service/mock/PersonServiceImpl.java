package ru.umc806.vmakarenko.service.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.AdministratorDAO;
import ru.umc806.vmakarenko.dao.InstructorDAO;
import ru.umc806.vmakarenko.dao.PersonDAO;
import ru.umc806.vmakarenko.dao.StudentDAO;
import ru.umc806.vmakarenko.domain.Administrator;
import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.exceptions.CannotAddException;
import ru.umc806.vmakarenko.exceptions.NoSuchPersonException;
import ru.umc806.vmakarenko.service.PersonService;
import ru.umc806.vmakarenko.util.Filter;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by VMakarenko on 5/22/14.
 */
@Transactional
public class PersonServiceImpl implements PersonService {


    Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class);
    @Autowired
    InstructorDAO instructorDAO;
    @Autowired
    StudentDAO studentDAO;
    @Autowired
    PersonDAO personDAO;
    @Autowired
    AdministratorDAO administratorDAO;

    @Override
    public boolean isInstructor(Person person) {
        return instructorDAO.list(new Filter().setPerson(person)).size()>0;
    }

    @Override
    public boolean isStudent(Person person) {
        return studentDAO.list(new Filter().setPerson(person)).size()>0;
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
        List<Instructor> list = instructorDAO.list(new Filter().setPerson(person));
        return !list.isEmpty() && list.get(0).isApproved();
    }

    @Override
    public boolean isStudent(String username) {
        LOG.debug("isStudent?");
        Person person = new Person();
        person.setLogin(username);
        List<Student> list =  studentDAO.list(new Filter().setPerson(person));
        return !list.isEmpty() && list.get(0).isApproved();
    }

    @Override
    public boolean isAdmin(String username) {
        LOG.debug("isAdmin?");
        Person person = new Person();
        person.setLogin(username);
        List<Administrator> list =  administratorDAO.list(new Filter().setPerson(person));
        return !list.isEmpty();
    }

    @Override
    public void addPerson(Person person) throws CannotAddException {
        if(personDAO.insert(person)==null){
            throw new CannotAddException("Error adding person!");
        }
    }

    @Override
    public Person getOne(Filter filter) throws NoSuchPersonException {
        List<Person> personList = personDAO.list(filter);
        if(!personList.isEmpty()){
            return personList.get(0);
        }else{
            throw new NoSuchPersonException();
        }
    }

    @Override
    public List<Person> get(Filter filter) throws NoSuchPersonException {
        List<Person> personList = personDAO.list(filter);
        if(!personList.isEmpty()){
            return personList;
        }else{
            throw new NoSuchPersonException();
        }
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
