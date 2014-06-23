package ru.umc8.vmakarenko.jdbc_test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.umc806.vmakarenko.dao.InstructorDAO;
import ru.umc806.vmakarenko.dao.db_jdbc.InstructorDAOImpl;
import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Person;

import javax.persistence.Table;
import java.io.Console;

/**
 * Created by VMakarenko on 6/18/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet-test.xml")
public class Test {
    private Logger LOG = LoggerFactory.getLogger(Test.class);

    public void testSelect(){
        Instructor instructor = new InstructorDAOImpl().get(1);
        System.out.println(String.format("instructor license = %s, person name = %s %s", instructor.getLicense(), instructor.getPerson().getName(), instructor.getPerson().getSurname()));
    }

    @org.junit.Test
    public void test1() throws Exception{
        Instructor instructor = new Instructor();
        Person person = new Person();
        person.setId(100);
        person.setLogin("login");
        person.setPass_hash("pass");
        person.setName("person");
        person.setSurname("person");
        person.setLastname("person");
        person.setPassport("123456");
        instructor.setPerson(person);
        instructor.setId(100);
        instructor.setApproved(true);
        instructor.setLicense("AHQFFR");
        new InstructorDAOImpl().delete(100);
        new InstructorDAOImpl().insert(instructor);
        Thread.sleep(10000);
        new InstructorDAOImpl().delete(100);


        //
        System.exit(0);
    }
    @org.junit.Test
    public void test2() throws Exception{
    //    Instructor instructor = new InstructorDAOImpl().get(1);
    //    LOG.debug(instructor.getPerson().getName());
    }
}
