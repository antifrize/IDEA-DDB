package ru.umc806.vmakarenko.domain;

import ru.umc806.vmakarenko.util.schedule.ScheduleConverter;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "schedule")
public class Schedule implements Lockable{
    @Id
    @Column(name="schedule_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "schedule_seq_gen")
    @SequenceGenerator(name = "schedule_seq_gen",sequenceName = "schedule_seq")
    private Integer id;
    @Column(name="approved")
    private String approved="0";
    @Column(name="sched_from")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar from;
    @Column(name="sched_to")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar to;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="student_id",referencedColumnName = "student_id")
    private Student student;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="instructor_id",referencedColumnName = "instr_id")
    private Instructor instructor;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="plane_id",referencedColumnName = "plane_id")
    private Plane plane;

    @Column(name="lock_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lockTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="locker",referencedColumnName = "person_id")
    private Person locker;

    public Calendar getLockTime() {
        return lockTime;
    }

    public void setLockTime(Calendar lockTime) {
        this.lockTime = lockTime;
    }

    public Person getLocker() {
        return locker;
    }

    @Override
    public void removeLock() {
        locker = null;
        lockTime = null;
    }

    public void setLocker(Person locker) {
        this.locker = locker;
    }

    public boolean isApproved() {
        return "1".equals(approved);
    }

    public void setApproved(boolean approved) {
        this.approved = approved?"1":"0";
    }



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Calendar getFrom() {
		return from;
	}
	public void setFrom(Calendar from) {
		this.from = from;
	}
	public Calendar getTo() {
		return to;
	}
	public void setTo(Calendar to) {
		this.to = to;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	public Plane getPlane() {
		return plane;
	}
	public void setPlane(Plane plane) {
		this.plane = plane;
	}
	public ScheduleConverter getConverter(){
        return new ScheduleConverter(from,to);
    }


}
