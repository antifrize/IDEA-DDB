package ru.umc806.vmakarenko.service.mock;

import ru.umc806.vmakarenko.dao.BlacklistDAO;
import ru.umc806.vmakarenko.dao.InstructorDAO;
import ru.umc806.vmakarenko.dao.PersonDAO;
import ru.umc806.vmakarenko.dao.StudentDAO;
import ru.umc806.vmakarenko.domain.*;
import ru.umc806.vmakarenko.exceptions.CannotAddException;
import ru.umc806.vmakarenko.service.BlacklistService;
import ru.umc806.vmakarenko.service.StudentService;
import ru.umc806.vmakarenko.util.Filter;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VMakarenko on 5/16/14.
 */
@Transactional
public class StudentServiceImpl implements StudentService {
    @Resource(name = "studentDAO")
    private StudentDAO studentDAO;
    @Resource(name = "personDAO")
    private PersonDAO personDAO;
    @Resource(name = "blacklistDAO")
    private BlacklistDAO blacklistDAO;

    @Override
    public List<Student> getAll(){
        return studentDAO.list();
    }

    @Override
    public Instructor getPreferredInstructor(Student student){
        return null;
    }

    @Override
    public List<Student> getBlacklisted() {
        return studentDAO.list(new Filter().setBlacklisted(true));
    }

    @Override
    public List<Student> getBlacklisted(Person person) {
        return getBlacklisted(person,true);
    }

    @Override
    public List<Student> getBlacklisted(Person person, boolean blacklisted) {
        List<Student> resultList = new ArrayList<>();
        if(blacklisted){
            List<Blacklist> blacklistList = blacklistDAO.list(person, null);
            for(Blacklist blacklist: blacklistList){
                // FIXME shitty performance here
                resultList.addAll(studentDAO.list(new Filter().setPerson(blacklist.getBlacklisted())));
            }
        }else{
            List<Person> personList = personDAO.list(new Filter().setBlacklisted(false));
            for(Person currentPerson:personList){
                // FIXME here too
                resultList.addAll(studentDAO.list(new Filter().setPerson(currentPerson)));
            }

        }
        return resultList;

    }


    @Override
    public Instructor getInstructorBySchedule(Schedule schedule){
        return null;
    }

    @Override
    public List<Schedule> getPreferredSchedules(){
        return null;
    }

    @Override
    public List<Student> getStudent(Filter filter){
        return studentDAO.list(filter);
    }

    @Override
    public List<Student> getUnapproved() {
        Student student = new Student();
        student.setApproved(false);
        return studentDAO.list(new Filter().setStudent(student));
    }

    @Override
    public boolean approve(String id) {
        Student student = studentDAO.list(
                new Filter().setStudent(
                        new Student().setId(Integer.parseInt(id)))).get(0);
        student.setApproved(true);
        studentDAO.update(student);
        return true;
    }

    @Override
    public void add(Student student) throws CannotAddException{
        if(studentDAO.list(
                new Filter().setPerson(
                        new Person().setId(
                                student.getPerson().getId()
                        )
                )
        ).isEmpty()) {
            studentDAO.insert(student);
        }else{
            throw new CannotAddException("cannot add student");
        }
    }
}

