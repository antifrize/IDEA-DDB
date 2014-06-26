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
    @Column(name="approved")
    private String approved;
    //TODO @Column(name = "graduated")
    @Transient
    private boolean graduated;
	public Integer getId() {
		return id;
	}
	public Student setId(Integer id) {
		this.id = id;
        return this;
	}
	public Person getPerson() {
		return person;
	}
	public Student setPerson(Person person) {
		this.person = person;
        return this;
	}
	public boolean isGraduated() {
		return graduated;
	}
	public Student setGraduated(boolean graduated) {
		this.graduated = graduated;
        return this;
	}
    public Boolean isApproved() {
        return "Y".equalsIgnoreCase(approved);
    }

    public Student setApproved(Boolean approved) {
        this.approved = approved?"Y":"N";
        return this;
    }

}
