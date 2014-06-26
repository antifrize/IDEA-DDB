package ru.umc806.vmakarenko.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.exceptions.CannotAddException;
import ru.umc806.vmakarenko.exceptions.NoSuchPersonException;
import ru.umc806.vmakarenko.exceptions.RestException;
import ru.umc806.vmakarenko.service.*;
import ru.umc806.vmakarenko.util.Filter;
import ru.umc806.vmakarenko.util.rest.Result;

import java.util.List;

@Controller
@RequestMapping("rest/")
public class RestController {
    private Logger LOG = LoggerFactory.getLogger(RestController.class);
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private PersonService personService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private InstructorService instructorService;
    @Autowired
    private BlacklistService blacklistService;

    private final String TYPE_STUDENT = "STUDENT";
    private final String TYPE_INSTRUCTOR = "INSTRUCTOR";
    @RequestMapping(value = "lesson",method = RequestMethod.DELETE)
    public @ResponseBody Result cancelLesson(@RequestParam(value ="id",required = true)String id){
        LOG.debug(String.format("rest lesson delete with id = %s",id));
        Result result = new Result();
        Schedule schedule = new Schedule();
        schedule.setId(Integer.parseInt(id));
        result.setSuccess(scheduleService.delete(new Filter().setSchedule(schedule)));
        result.setResult("");
        return result;
    }
    @RequestMapping(value = "approve",method = RequestMethod.POST)
    public @ResponseBody boolean approvePerson(@RequestParam(value = "type",required = true)String type,@RequestParam(value = "id",required = true)Integer id) throws RestException {
        LOG.debug(String.format("rest approve type = %s id = %s", type, id));
        try{
            if(TYPE_STUDENT.equalsIgnoreCase(type)){
                List<Student> list = studentService.getStudent(new Filter().setPerson(new Person().setId(id)));
                return studentService.approve(list.get(0).getId().toString());
            }
            if(TYPE_INSTRUCTOR.equalsIgnoreCase(type)){
                List<Instructor> list = instructorService.getInstructors(new Filter().setPerson(new Person().setId(id)));
                return instructorService.approve(list.get(0).getId().toString());
            }
        }catch(Exception e){
            throw new RestException(e);
        }
        return false;

    }
    @RequestMapping(value = "blacklist",method = RequestMethod.POST)
    public @ResponseBody boolean blacklistPerson(@RequestParam(value = "type",required = true)String type,@RequestParam(value = "id",required = true)String id) throws RestException {
        LOG.debug(String.format("rest blacklist type = %s  id = %s",type, id));
        try{
            blacklistService.setBlacklisted(Integer.parseInt(id));
        }catch(Exception e){
            throw new RestException(e);
        }
        return true;
    }
    @RequestMapping(value = "instructor",method = RequestMethod.PUT)
    public @ResponseBody boolean addInstructor(@RequestParam(value = "login",required = true)String login,@RequestParam(value = "license",required = true)String license) throws RestException {
        LOG.debug(String.format("rest add instructor login = %s", login));
        try{
            instructorService.add(
                    new Instructor().setLicense(license).setPerson(
                            personService.getOne(
                                    new Filter().setPerson(
                                            new Person().setLogin(login)
                                    )
                            )
                    ).setApproved(false)
            );
        }catch(Exception e){
            throw new RestException(e);
        }
        return true;
    }
    @RequestMapping(value = "student",method = RequestMethod.PUT)
    public @ResponseBody boolean addStudent(@RequestParam(value = "login",required = true)String login) throws RestException {
        LOG.debug(String.format("add student login = %s", login));
        try{
        studentService.add(
                new Student().setApproved(false).setGraduated(false).setPerson(
                        personService.getOne(new Filter().setPerson(
                                new Person().setLogin(login)
                        ))
                )
        );
        }catch(Exception e){
            throw new RestException(e);
        }
        return true;
    }

}