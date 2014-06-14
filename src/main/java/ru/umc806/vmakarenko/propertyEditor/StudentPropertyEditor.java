package ru.umc806.vmakarenko.propertyEditor;

import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.InstructorDAO;
import ru.umc806.vmakarenko.dao.StudentDAO;
import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.service.StudentService;
import ru.umc806.vmakarenko.util.Filter;

import javax.transaction.Transactional;
import java.beans.PropertyEditorSupport;

/**
 * Created by VMakarenko on 6/8/14.
 */
public class StudentPropertyEditor extends PropertyEditorSupport {
    @Autowired
    StudentService studentService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Student student = new Student();
        Person person = new Person();
        student.setPerson(person);
        if("any".equals(text)){
            setValue(new Student());
        }else{
            student.getPerson().setName(text.split(" ")[0]);
            student.getPerson().setSurname(text.split(" ")[1]);
            setValue(studentService.getStudent(new Filter().setStudent(student)).get(0));
        }
    }
}
