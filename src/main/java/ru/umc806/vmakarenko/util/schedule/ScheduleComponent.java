package ru.umc806.vmakarenko.util.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.umc806.vmakarenko.dao.PersonDAO;
import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.service.InstructorService;
import ru.umc806.vmakarenko.service.PersonService;
import ru.umc806.vmakarenko.service.ScheduleService;
import ru.umc806.vmakarenko.service.mock.ScheduleServiceImpl;
import ru.umc806.vmakarenko.util.Filter;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by VMakarenko on 5/21/14.
 */
public class ScheduleComponent {
    private static final int DAYS = 7;

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private InstructorService instructorService;
    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private PersonService personService;

    Student student;
    public void setStudent(Student student){
        this.student = student;
    }

    @Transactional
    public List<ScheduleHeaderItem> getHeaderItems() {
        List<ScheduleHeaderItem> headerItems = new ArrayList<>();
        Calendar from = Calendar.getInstance();
        Calendar to;
        from.set(Calendar.HOUR,0);
        from.set(Calendar.MINUTE,0);
        from.set(Calendar.SECOND,0);
        from.set(Calendar.MILLISECOND,0);
        to = (Calendar)from.clone();
        to.add(Calendar.DAY_OF_MONTH,1);

        //Calendar to = (Calendar)from.clone();
        //to.add(Calendar.DAY_OF_MONTH, DAYS);
        Filter filter = new Filter();
        Schedule schedule = new Schedule();
        filter.setSchedule(schedule);
        for(int i=0;i<DAYS;i++){
            schedule.setFrom(from);
            schedule.setTo(to);
            if(student!=null){
                schedule.setStudent(student);
            }

            ScheduleHeaderItem shi = new ScheduleHeaderItem(from.get(Calendar.DAY_OF_MONTH),from.get(Calendar.MONTH));
            shi.add(scheduleService.getSchedules(filter));
            headerItems.add(shi);
            from.add(Calendar.DAY_OF_MONTH,1);
            to.add(Calendar.DAY_OF_MONTH,1);
        }
        return headerItems;
    }

}
