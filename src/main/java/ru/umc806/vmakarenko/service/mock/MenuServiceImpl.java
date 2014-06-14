package ru.umc806.vmakarenko.service.mock;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.service.MenuService;
import ru.umc806.vmakarenko.util.Menu;
import ru.umc806.vmakarenko.util.RoleService;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by VMakarenko on 5/20/14.
 */
public class MenuServiceImpl implements MenuService {
    Logger LOG = org.slf4j.LoggerFactory.getLogger(MenuServiceImpl.class);
    @Autowired
    private RoleService roleService;
    public List<Menu> getMenuList(){
        LOG.debug("getMenuList()");
        List<Menu> menuList = new ArrayList<>();
        if(roleService.isLogged()){
        menuList.add(new Menu("Schedule","schedule"));
        if(roleService.isStudent()){
            menuList.add(new Menu("Instructors","instructors"));
        }
        if(roleService.isInstructor()){
            menuList.add(new Menu("My students","students"));
            menuList.add(new Menu("My time","time_select"));
        }
        if(roleService.isAdministrator()){
            menuList.add(new Menu("Statistics","statistics"));
        }
        menuList.add(new Menu("Logout", "logout"));
        }else{
            menuList.add(new Menu("Our students","students"));
            menuList.add(new Menu("Our instructors","instructors"));
            menuList.add(new Menu("Our planes","planes"));
        }
        LOG.debug("returning: "+menuList);

        return menuList;
    }
}