package ru.umc806.vmakarenko.util;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import ru.umc806.vmakarenko.dao.StudentDAO;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.service.PersonService;
import ru.umc806.vmakarenko.service.mock.PersonServiceImpl;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VMakarenko on 5/24/14.
 */
public class MyJdbcDAOImpl extends JdbcDaoImpl {
    private String usersByUsernameQuery;
    Logger LOG = LoggerFactory.getLogger(MyJdbcDAOImpl.class);

    @Resource(name="personService")
    private PersonService personService;
    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public StudentDAO getStudentDAO() {
        return studentDAO;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    protected List<GrantedAuthority> loadUserAuthorities(String username) {
        LOG.debug("Get user authorities");
        List<GrantedAuthority> list = new ArrayList<>();
        try{
            if(personService.isAdmin(username)){
                list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                LOG.debug("admin");
            }
            if(personService.isInstructor(username)){
                list.add(new SimpleGrantedAuthority("ROLE_INSTRUCTOR"));
                LOG.debug("instructor");
            }
            if(personService.isStudent(username)){
                list.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
                LOG.debug("student");
            }
            if(personService.isUnapprovedInstructor(username)){
                list.add(new SimpleGrantedAuthority("ROLE_INSTRUCTOR_UNAPPROVED"));
                LOG.debug("instructor");
            }
            if(personService.isUnapprovedStudent(username)){
                list.add(new SimpleGrantedAuthority("ROLE_STUDENT_UNAPPROVED"));
                LOG.debug("student");
            }
            if(!sessionFactory.openSession().createCriteria(Person.class).add(Restrictions.eq("login",username)).list().isEmpty()){
                list.add(new SimpleGrantedAuthority("ROLE_LOGGED"));
            }
        }catch (Exception e){
            LOG.debug("exception: "+e);
            throw e;
        }
        LOG.debug("list = "+list);

        return list;
    }

    @Override
    protected List<UserDetails> loadUsersByUsername(String username) {
        List<Person> persons = sessionFactory.openSession().createCriteria(Person.class).add(Restrictions.eq("login",username)).list();
        List<UserDetails> result = new ArrayList<>();
        for(Person person: persons){
            result.add(new User(persons.get(0).getLogin(), persons.get(0).getPass_hash(), true, true, true, true, AuthorityUtils.NO_AUTHORITIES));
        }
        return result;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
    public String getUsersByUsernameQuery() {
        return usersByUsernameQuery;
    }
    public void setUsersByUsernameQuery(String usersByUsernameQuery) {
        this.usersByUsernameQuery = usersByUsernameQuery;
    }


}
