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
import ru.umc806.vmakarenko.exceptions.CannotAddException;
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
public class GuestController {
    private Logger LOG = LoggerFactory.getLogger(GuestController.class);

    @Autowired
    private InstructorService instructorService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private PersonService personService;
    @Autowired
    private MenuService menu;
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


	@RequestMapping(method = RequestMethod.GET,value="hello")
	public String printWelcome(ModelMap model) {
        ModelAndView mav = new ModelAndView("hello");
        mav.addObject("menuList", menu.getMenuList());
		return "hello";
	}
    @RequestMapping(method = RequestMethod.GET,value="login")
     public String login(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "login";
    }
    @RequestMapping(method = RequestMethod.GET,value="register")
    public String register(ModelMap model) {
        model.addAttribute("registerBean", new Person());
        return "cabinet-register";
    }
    @RequestMapping(method = RequestMethod.POST,value="register")
    public String registerPost(@ModelAttribute(value = "registerBean") Person person, ModelMap model) throws CannotAddException{
        personService.addPerson(person);
        return "cabinet-register-success";
    }

    @RequestMapping(method = RequestMethod.GET,value="instructors")
    public ModelAndView getInstructorsList(ModelMap model) {
        LOG.debug("list instructorService list");
        ModelAndView mav = new ModelAndView("instructors-list");
        mav.addObject("instructors", instructorService.getAll());
        return mav;
    }
    @RequestMapping(method = RequestMethod.GET,value="students")
    public ModelAndView getStudentsList(ModelMap model) {
        LOG.debug("list students list");
        ModelAndView mav = new ModelAndView("students-list");
        mav.addObject("students", studentService.getAll());
        return mav;
    }
    @RequestMapping(method = RequestMethod.GET,value="planes")
    public ModelAndView getPlanesList(ModelMap model) {
        LOG.debug("list planes list");
        ModelAndView mav = new ModelAndView("planes-list");
        mav.addObject("planes", planeService.getAll());
        return mav;
    }


}