package ru.umc806.vmakarenko.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import ru.umc806.vmakarenko.dao.PersonDAO;
import ru.umc806.vmakarenko.domain.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VMakarenko on 5/21/14.
 */
@Transactional
public class RoleService {
    List<GrantedAuthority> authList = new ArrayList<>();
    @Autowired
    PersonDAO personDAO;

    public boolean isLogged(){
        for(GrantedAuthority auth: authList){
            if("ROLE_LOGGED".equals(auth.getAuthority())){
                return true;
            }
        }
        return false;
    }

    public boolean isInstructor(){
        for(GrantedAuthority auth: authList){
            if("ROLE_INSTRUCTOR".equals(auth.getAuthority())){
                return true;
            }
        }
        return false;
    }
    public boolean isStudent(){
        for(GrantedAuthority auth: authList){
            if("ROLE_STUDENT".equals(auth.getAuthority())){
                return true;
            }
        }
        return false;
    }

    public boolean isAdministrator(){
        for(GrantedAuthority auth: authList){
            if("ROLE_ADMIN".equals(auth.getAuthority())){
                return true;
            }
        }
        return false;
    }

    public Person getPerson(String username){
        Person person = new Person();
        person.setLogin(username);
        List<Person> list = personDAO.list(new Filter().setPerson(person));
        if(list!=null){
            return list.get(0);
        }else{
            return null;
        }
    }
}
