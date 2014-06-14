package ru.umc806.vmakarenko.util;
import ru.umc806.vmakarenko.domain.Schedule;

import ru.umc806.vmakarenko.domain.Instructor;
import ru.umc806.vmakarenko.domain.Plane;
import ru.umc806.vmakarenko.domain.Schedule;
import ru.umc806.vmakarenko.domain.Student;
import ru.umc806.vmakarenko.util.CalendarUtils;

import javax.lang.model.util.SimpleAnnotationValueVisitor6;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by VMakarenko on 5/29/14.
 */
public class CabinetRequestObject {
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    private Schedule schedule = new Schedule();

    public Schedule getSchedule(){
        return schedule;
    }

    public CabinetRequestObject setInstructor(Instructor instructor){
        schedule.setInstructor(instructor);
        return this;
    }

    public void setPlane(Plane plane){
        schedule.setPlane(plane);
//        return this;
    }
    public void setStudent(Student student){
        schedule.setStudent(student);
//        return this;
    }

    public String getTime_from(){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        if(schedule == null || schedule.getFrom()==null){
            Calendar c = CalendarUtils.getInstance();
            c.add(Calendar.HOUR_OF_DAY,2);
            return sdf.format(c.getTime());
        }
        return sdf.format(schedule.getFrom().getTime());
    }
    public String getTime_to(){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        if(schedule == null || schedule.getTo()==null){
            Calendar c = CalendarUtils.getInstance();
            c.add(Calendar.HOUR_OF_DAY,4);
            return sdf.format(c.getTime());
        }
        return sdf.format(schedule.getTo().getTime());
    }

    public String getDate_from(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
        if(schedule == null || schedule.getFrom()==null){
            Calendar c = CalendarUtils.getInstance();
            c.add(Calendar.HOUR_OF_DAY,2);
            return sdf.format(c.getTime());
        }
        return sdf.format(schedule.getFrom().getTime());
    }
    public String getDate_to(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
        if(schedule == null || schedule.getTo()==null){
            Calendar c = CalendarUtils.getInstance();
            c.add(Calendar.HOUR_OF_DAY,4);
            return sdf.format(c.getTime());
        }
        return sdf.format(schedule.getTo().getTime());
    }
    public CabinetRequestObject setTime_from(String time) throws Exception{
        String hours = time.substring(0, time.indexOf(":"));
        String minutes = time.substring(time.indexOf(":") + 1);
        if(schedule.getFrom()==null){
            schedule.setFrom(CalendarUtils.getInstance());
        }
        schedule.getFrom().set(Calendar.HOUR_OF_DAY,Integer.parseInt(hours));
        schedule.getFrom().set(Calendar.MINUTE,Integer.parseInt(minutes));
        return this;
    }

    public CabinetRequestObject setTime_to(String time){
        String hours = time.substring(0,time.indexOf(":"));
        String minutes = time.substring(time.indexOf(":")+1);
        if(schedule.getTo()==null){
            schedule.setTo(CalendarUtils.getInstance());
        }
        schedule.getTo().set(Calendar.HOUR_OF_DAY,Integer.parseInt(hours));
        schedule.getTo().set(Calendar.MINUTE,Integer.parseInt(minutes));
        return this;
    }

    public CabinetRequestObject setDate_from(String date){
        String day = date.substring(0,date.indexOf("."));
        String month = date.substring(date.indexOf(".")+1,date.indexOf(".",date.indexOf(".")));
        String year = date.substring(date.indexOf(".",date.indexOf("."))+1);
        if(schedule.getFrom()==null){
            schedule.setFrom(CalendarUtils.getInstance());
        }
        schedule.getFrom().set(Calendar.DAY_OF_YEAR,Integer.parseInt(day));
        schedule.getFrom().set(Calendar.MONTH,Integer.parseInt(month));
        schedule.getFrom().set(Calendar.YEAR,Integer.parseInt(year));
        return this;
    }

    public CabinetRequestObject setDate_to(String date){
        String day = date.substring(0,date.indexOf("."));
        String month = date.substring(date.indexOf(".")+1,date.indexOf(".",date.indexOf(".")));
        String year = date.substring(date.indexOf(".",date.indexOf("."))+1);
        if(schedule.getTo()==null){
            schedule.setTo(CalendarUtils.getInstance());
        }
        schedule.getTo().set(Calendar.DAY_OF_YEAR,Integer.parseInt(day));
        schedule.getTo().set(Calendar.MONTH,Integer.parseInt(month));
        schedule.getTo().set(Calendar.YEAR,Integer.parseInt(year));
        return this;
    }

    public Instructor getInstructor(){
        return schedule==null?new Instructor():schedule.getInstructor();
    }
    public Plane getPlane(){
        return schedule==null?new Plane():schedule.getPlane();
    }
}
