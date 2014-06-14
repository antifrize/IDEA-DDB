package ru.umc806.vmakarenko.util.schedule;

import org.hibernate.Hibernate;
import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Student;

import java.util.List;

/**
 * Created by VMakarenko on 6/13/14.
 */
public class HibernateUtils{
    public static <E> List<E> init(List<E> list){
        for(E item: list){
            if(item instanceof Instructor){
                Instructor instr = (Instructor)item;
                Hibernate.initialize(instr.getPerson());
            }
            if(item instanceof Student){
                Student stud = (Student)item;
                Hibernate.initialize(stud.getPerson());
            }
        }
        return list;
    }
}
