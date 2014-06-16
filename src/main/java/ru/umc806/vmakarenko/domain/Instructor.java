package ru.umc806.vmakarenko.domain;

import javax.persistence.*;

@Entity
@Table(name = "instructor")
public class Instructor {
    @Id
    @Column(name="instr_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "instr_seq_gen")
    @SequenceGenerator(name = "instr_seq_gen",sequenceName = "instr_seq")
    private Integer id;
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "person_id",referencedColumnName = "person_id")
    private Person person;
    @Column(name = "approved")
    private String approved;

    @Column(name="instr_license")
    private String license;
	
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
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
    public boolean isApproved() {
        return "Y".equalsIgnoreCase(approved);
    }

    public void setApproved(boolean approved) {
        this.approved = approved?"Y":"N";
    }


}
