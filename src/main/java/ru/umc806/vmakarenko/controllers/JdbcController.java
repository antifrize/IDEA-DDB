package ru.umc806.vmakarenko.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.umc806.vmakarenko.dao.InstructorDAO;
import ru.umc806.vmakarenko.dao.PlaneDAO;
import ru.umc806.vmakarenko.dao.ScheduleDAO;
import ru.umc806.vmakarenko.dao.StudentDAO;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.service.InstructorService;
import ru.umc806.vmakarenko.service.ScheduleService;
import ru.umc806.vmakarenko.util.CabinetRequestObject;
import ru.umc806.vmakarenko.util.Filter;

import java.util.Calendar;

/**
 * Created by VMakarenko on 6/26/14.
 */

@Controller
@RequestMapping(value="jdbc/")
public class JdbcController {
    @Autowired
    @Qualifier(value = "jdbc")
    InstructorDAO instructorDAO;
    @Autowired
    @Qualifier(value = "jdbc")
    ScheduleDAO scheduleDAO;
    @Autowired
    @Qualifier(value = "jdbc")
    PlaneDAO planeDAO;
    @Autowired
    @Qualifier(value = "jdbc")
    StudentDAO studentDAO;



    @RequestMapping(method = RequestMethod.GET, value="main")
    public ModelAndView showMain(){
        ModelAndView mav = new ModelAndView("jdbc-main");
        CabinetRequestObject cro = new CabinetRequestObject();
        mav.addObject("cabinetRequest",cro);
        mav.addObject("instructors",instructorDAO.list());
        mav.addObject("planes",planeDAO.list());
        mav.addObject("duration","10000");
        return mav;

    }
    @RequestMapping(method = RequestMethod.POST, value="tryDeleteInstructor")
    public String tryDeleteInstructor(){


        return "jdbc-main";

    }
    @RequestMapping(method = RequestMethod.POST, value="tryPausedScheduleAdd")
    public String tryPausedScheduleAdd(@ModelAttribute(value = "cabinetRequest") CabinetRequestObject cro,@ModelAttribute(value = "duration") Integer duration) throws InterruptedException {
        if(cro!=null){
            Schedule schedule = cro.getSchedule();
            Student student = studentDAO.get(1);
            schedule.setStudent(student);
            schedule.setLocker(student.getPerson());
            schedule.setLockTime(Calendar.getInstance());
            scheduleDAO.insert(schedule);
            Thread.sleep(duration);
            schedule.removeLock();
            scheduleDAO.update(schedule);
        }
        return "redirect:main";

    }
}
