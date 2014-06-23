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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.umc806.vmakarenko.domain.*;
import ru.umc806.vmakarenko.exceptions.NoSuchPersonException;
import ru.umc806.vmakarenko.propertyEditor.InstructorPropertyEditor;
import ru.umc806.vmakarenko.propertyEditor.PlanePropertyEditor;
import ru.umc806.vmakarenko.propertyEditor.StudentPropertyEditor;
import ru.umc806.vmakarenko.service.*;
import ru.umc806.vmakarenko.util.CabinetRequestObject;
import ru.umc806.vmakarenko.util.Filter;
import ru.umc806.vmakarenko.util.RoleService;
import ru.umc806.vmakarenko.util.ScheduleException;
import ru.umc806.vmakarenko.util.schedule.ScheduleComponent;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "cabinet/")
public class LoggedController {
    private Logger LOG = LoggerFactory.getLogger(LoggedController.class);

    @Autowired
    private InstructorService instructorService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private PersonService personService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private PlaneService planeService;
    @Autowired
    RoleService roleService;
    @Autowired
    private ScheduleComponent scheduleComponent;

    public InstructorPropertyEditor getInstructorPropertyEditor() {
        return instructorPropertyEditor;
    }

    public void setInstructorPropertyEditor(InstructorPropertyEditor instructorPropertyEditor) {
        this.instructorPropertyEditor = instructorPropertyEditor;
    }

    public StudentPropertyEditor getStudentPropertyEditor() {
        return studentPropertyEditor;
    }

    public void setStudentPropertyEditor(StudentPropertyEditor studentPropertyEditor) {
        this.studentPropertyEditor = studentPropertyEditor;
    }

    public PlanePropertyEditor getPlanePropertyEditor() {
        return planePropertyEditor;
    }

    public void setPlanePropertyEditor(PlanePropertyEditor planePropertyEditor) {
        this.planePropertyEditor = planePropertyEditor;
    }

    @Autowired
    private InstructorPropertyEditor instructorPropertyEditor;
    @Autowired
    private StudentPropertyEditor studentPropertyEditor;
    @Autowired
    private PlanePropertyEditor planePropertyEditor;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        dataBinder.registerCustomEditor(Instructor.class, instructorPropertyEditor);
        dataBinder.registerCustomEditor(Plane.class, planePropertyEditor);
        dataBinder.registerCustomEditor(Student.class, studentPropertyEditor);
    }



    @RequestMapping(method = RequestMethod.GET,value="cabinet")
    public ModelAndView cabinetMain(Model model, Principal principal) throws NoSuchPersonException {
        LOG.debug("open cabinet");
        String username = principal.getName();
        ModelAndView mav = new ModelAndView("cabinet-main");
        Person person = personService.getOne(new Filter().setPerson(new Person().setLogin(username)));
        mav.addObject("person",person);
        return mav;
    }


    @RequestMapping(method = RequestMethod.GET,value="schedule")
    public ModelAndView schedule(Model model, HttpServletRequest request) {
        LOG.debug("open schedule");
        ModelAndView mav = new ModelAndView("cabinet-schedule");
        mav.addObject("scheduler", scheduleComponent);
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET,value="students")
    public ModelAndView students(Principal principal) {
        LOG.debug("open students");
        ModelAndView mav = new ModelAndView("cabinet-mystudents");
        mav.addObject("students", studentService.getBlacklisted(roleService.getPerson(principal.getName()),false));
        mav.addObject("studentsBlacklisted", studentService.getBlacklisted(roleService.getPerson(principal.getName()), true));
        return mav;
    }

    @RequestMapping(value = "request", method = RequestMethod.GET)
    public ModelAndView request( ) {
        LOG.debug("create request");
        ModelAndView mav = new ModelAndView("cabinet-request");
        mav.addObject("cabinetRequest",new CabinetRequestObject());
        mav.addObject("instructors",instructorService.getAll());
        mav.addObject("planes",planeService.getAll());
        return mav;
    }

    @RequestMapping(value = "request",method = RequestMethod.POST)
    public ModelAndView requestAdd(@ModelAttribute(value = "cabinetRequest") CabinetRequestObject cro, BindingResult result, Principal principal) {
        LOG.debug("process request (submitted)");
        if (result.hasErrors()) {
            List<String> fieldErrors = new ArrayList<String>();
            for (FieldError f : result.getFieldErrors()) {
                fieldErrors.add(f.getField() + "=" + f.getRejectedValue().toString());
                LOG.debug("BindingFailure: {} = {}", new Object[]{f.getField(), f.getRejectedValue()});
            }
                // deal with the invalid input
        }
        String resultString = "";
        try{
            if(cro!=null){
                Schedule schedule = cro.getSchedule();
                schedule.setStudent(studentService.getStudent(new Filter().setPerson(roleService.getPerson("sergey"))).get(0));
                scheduleService.addIfPossible(schedule);
                resultString = "You successfully requested a lesson.";
            }
        }catch(ScheduleException e){
            switch(e.getCode()){
                case "ERR_STUD":
                    resultString = "Error: you are intersecting with planned lesson!";
                    break;
                case "ERR_INST":
                    resultString ="Error: this instructor is busy that time!";
                    break;
                case "ERR_PLNE":
                    resultString ="Error: this plane is busy that time!";
                        break;
            }
        }
        ModelAndView mav = new ModelAndView("cabinet-request");
        mav.addObject("instructors",instructorService.getAll());
        mav.addObject("planes",planeService.getAll());
        mav.addObject("result",resultString);
        return mav;
    }

    @RequestMapping(value = "stats",method = RequestMethod.POST)
    public ModelAndView statistics() {
        ModelAndView mav = new ModelAndView("cabinet-stats");
        return mav;
    }

    @RequestMapping(value = "approve",method = RequestMethod.GET)
    public ModelAndView approvance() {
        ModelAndView mav = new ModelAndView("cabinet-approve");
        mav.addObject("students",studentService.getUnapproved());
        mav.addObject("instructors",instructorService.getUnapproved());
        return mav;
    }
}