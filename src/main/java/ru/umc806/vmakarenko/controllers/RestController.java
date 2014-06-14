package ru.umc806.vmakarenko.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Plane;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.propertyEditor.InstructorPropertyEditor;
import ru.umc806.vmakarenko.propertyEditor.PlanePropertyEditor;
import ru.umc806.vmakarenko.propertyEditor.StudentPropertyEditor;
import ru.umc806.vmakarenko.service.MenuService;
import ru.umc806.vmakarenko.service.PlaneService;
import ru.umc806.vmakarenko.service.ScheduleService;
import ru.umc806.vmakarenko.service.StudentService;
import ru.umc806.vmakarenko.service.mock.InstructrorServiceImpl;
import ru.umc806.vmakarenko.util.CabinetRequestObject;
import ru.umc806.vmakarenko.util.Filter;
import ru.umc806.vmakarenko.util.RoleService;
import ru.umc806.vmakarenko.util.ScheduleException;
import ru.umc806.vmakarenko.util.rest.Result;
import ru.umc806.vmakarenko.util.schedule.ScheduleComponent;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("rest/")
public class RestController {
    private Logger LOG = LoggerFactory.getLogger(RestController.class);
    @Autowired
    private ScheduleService scheduleService;
    @RequestMapping(value = "lesson",method = RequestMethod.DELETE)
    public @ResponseBody Result cancelLesson(@RequestParam(value = "id",required = true)String id){
        LOG.debug(String.format("rest lesson delete with id = %s",id));
        Result result = new Result();
        Schedule schedule = new Schedule();
        schedule.setId(Integer.parseInt(id));
        result.setSuccess(scheduleService.delete(new Filter().setSchedule(schedule)));
        result.setResult("ХУЙ");
        return result;
    }

}