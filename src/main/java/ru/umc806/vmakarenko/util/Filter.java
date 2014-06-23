package ru.umc806.vmakarenko.util;

import ru.umc806.vmakarenko.domain.*;

/**
 * Created by VMakarenko on 5/20/14.
 */
public class Filter {
    private boolean blacklisted;
    private Instructor instructor;
    private Student student;
    private Schedule schedule;
    private Person person;
    private Administrator administrator;

    public Administrator getAdministrator() {
        return administrator;
    }

    public Filter setAdministrator(Administrator administrator) {
        this.administrator = administrator;
        return this;
    }

    public Plane getPlane() {
        return plane;
    }

    public Filter setPlane(Plane plane) {
        this.plane = plane; return this;
    }

    private Plane plane;

    public Person getPerson() {
        return person;
    }

    public Filter setPerson(Person person) {
        this.person = person;
        return this;
    }

    public Filter(){}

    public Instructor getInstructor() {
        return instructor;
    }

    public Filter setInstructor(Instructor instructor) {
        this.instructor = instructor;
        return this;
    }

    public Student getStudent() {
        return student;
    }

    public Filter setStudent(Student student) {
        this.student = student;
        return this;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Filter setSchedule(Schedule schedule) {
        this.schedule = schedule;
        return this;
    }
    public boolean isBlacklisted() {
        return blacklisted;
    }

    public Filter setBlacklisted(boolean blacklisted) {
        this.blacklisted = blacklisted;
        return this;
    }

}
