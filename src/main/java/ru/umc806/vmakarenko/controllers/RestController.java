package ru.umc806.vmakarenko.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.service.*;
import ru.umc806.vmakarenko.util.Filter;
import ru.umc806.vmakarenko.util.rest.Result;

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
    @RequestMapping(value = "approvePerson",method = RequestMethod.DELETE)
    public @ResponseBody boolean approvePerson(@RequestParam(value = "type",required = true)String type,@RequestParam(value = "id",required = true)String id){
        LOG.debug(String.format("rest approve type = %s id = %s", type, id));
        if(TYPE_STUDENT.equalsIgnoreCase(type)){
            return studentService.approve(id);
        }
        if(TYPE_INSTRUCTOR.equalsIgnoreCase(type)){
            return instructorService.approve(id);
        }
    }
    @RequestMapping(value = "blacklistPerson",method = RequestMethod.DELETE)
    public @ResponseBody boolean blacklistPerson(@RequestParam(value = "type",required = true)String type,@RequestParam(value = "id",required = true)String id){
        LOG.debug(String.format("rest blacklist type = %s  id = %s",type, id));
        blacklistService.setBlacklisted(Integer.parseInt(id));
        return true;
    }

}