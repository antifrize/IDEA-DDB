package ru.umc806.vmakarenko.propertyEditor;

import org.springframework.beans.factory.annotation.Autowired;
import ru.umc806.vmakarenko.dao.InstructorDAO;
import ru.umc806.vmakarenko.dao.PlaneDAO;
import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.domain.Plane;
import ru.umc806.vmakarenko.service.PlaneService;
import ru.umc806.vmakarenko.util.Filter;

import javax.transaction.Transactional;
import java.beans.PropertyEditorSupport;

/**
 * Created by VMakarenko on 6/8/14.
 */
public class PlanePropertyEditor extends PropertyEditorSupport {
    @Autowired
    PlaneService planeService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Plane plane = new Plane();
        Person person = new Person();
        if("any".equals(text)){
            setValue(null);
        }else{
            plane.setManufacturer(text.split(" ")[0]);
            plane.setModel(text.split(" ")[1]);
            setValue(planeService.getPlanes(new Filter().setPlane(plane)).get(0));
        }
    }
}