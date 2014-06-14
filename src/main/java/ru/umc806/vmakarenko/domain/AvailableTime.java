package ru.umc806.vmakarenko.domain;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name="week_timing")
public class AvailableTime {
    @Id
    @Column(name="time_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "time_seq_gen")
    @SequenceGenerator(name="time_seq_gen", sequenceName = "time_seq")
	private int id;
    @Column(name="from")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar from;
    @Column(name="to")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar to;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Calendar getFrom() {
		return from;
	}
	public void setFrom(Calendar from) {
		this.from = from;
	}
	public Calendar getTo() {
		// TODO Return LocalTIme instance 
		return to;
	}
	public void setTo(Calendar to) {
		this.to = to;
	}

}
