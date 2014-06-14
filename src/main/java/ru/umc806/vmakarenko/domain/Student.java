package ru.umc806.vmakarenko.domain;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name="student_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "student_seq_gen")
    @SequenceGenerator(name = "student_seq_gen",sequenceName = "student_seq")
    private Integer id;
    @OneToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person person;
    //TODO @Column(name = "graduated")
    @Transient
    private boolean graduated;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public boolean isGraduated() {
		return graduated;
	}
	public void setGraduated(boolean graduated) {
		this.graduated = graduated;
	}
	
}
