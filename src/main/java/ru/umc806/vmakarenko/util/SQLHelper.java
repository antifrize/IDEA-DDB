package ru.umc806.vmakarenko.util;

import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.domain.Student;

/**
 * Created by VMakarenko on 5/24/14.

public class SQLHelper {
    public static String getSQLQuery(String invoker, Filter filter){
        String query =String.format("select x from %s x ",invoker);
        String criteria = "where ";
        if(filter.getPerson()!=null){
            query+=", person";
            Person person = filter.getPerson();
            if(person.getId()!=null){
                criteria+=String.format("person.person_id=%s and",person.getId());
            }
            if(person.getName()!=null){
                criteria+=String.format("person.name=%s and",person.getName());
            }
            if(person.getSurname()!=null){
                criteria+=String.format("person.surname=%s and"+person.getSurname());
            }
            if(person.getLastname()!=null){
                criteria+=String.format("person.last_name=%s and"+person.getLastname());
            }
            if(person.getLogin()!=null){
                criteria+=String.format("person.login=%s and"+person.getLogin());
            }
            criteria+=String.format("person.person_id = %s.%s_id and",invoker,invoker);
        }
        if(filter.getStudent()!=null){
            query+=", student";
            Student student = filter.getStudent();
            if(student.getId()!=null){
                criteria+=String.format("student.student_id=%s and",student.getId());
            }
            criteria+=String.format("student.student_id = %s.%student_id and",invoker,invoker);
        }
        if(filter.getInstructor()!=null){
            query+=", instructor";
            Instructor instructor = filter.getInstructor();
            if(instructor.getId()!=null){
                criteria+=String.format("instructor.instructor_id=%s and",instructor.getId());
            }
            if(instructor.getLicense()!=null){
                criteria+=String.format("intructor.instr_license=%s and",instructor.getLicense());
            }
            criteria+=String.format("instructor.instructor_id = %s.%instructor_id and",invoker,invoker);
        }
        if(filter.getSchedule()!=null){
            query+=", schedule";
            Schedule schedule = filter.getSchedule();
            if(schedule.getId()!=null){
                criteria+=String.format("schedule.schedule_id=%s and",schedule.getId());
            }
            if(schedule.get()!=null){
                criteria+=String.format("schedule.schedule_id=%s and",schedule.getId());ye n
            }
            if(schedule.getId()!=null){
                criteria+=String.format("schedule.schedule_id=%s and",schedule.getId());
            }
            if(schedule.getId()!=null){
                criteria+=String.format("schedule.schedule_id=%s and",schedule.getId());
            }


        }
        return query+criteria;
    }
}
*/