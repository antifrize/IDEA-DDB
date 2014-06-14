package ru.umc806.vmakarenko.propertyEditor;

import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.InstructorDAO;
import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.service.InstructrorService;
import ru.umc806.vmakarenko.util.Filter;

import javax.transaction.Transactional;
import java.beans.PropertyEditorSupport;
import java.util.List;

/**
 * Created by VMakarenko on 6/8/14.
 */
public class InstructorPropertyEditor extends PropertyEditorSupport {
    @Autowired
    InstructrorService instructorService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Instructor instructor = new Instructor();
        Person person = new Person();
        instructor.setPerson(person);
        if("any".equals(text)){
            setValue(null);
        }else{
            instructor.getPerson().setName(text.split(" ")[0]);
            instructor.getPerson().setSurname(text.split(" ")[1]);
            List<Instructor> list = instructorService.getInstructors(new Filter().setInstructor(instructor));
            setValue(list.get(0));
        }
    }
}