package ru.umc806.vmakarenko.domain;

import javax.persistence.*;

@Entity
@Table(name="administrator")
public class Administrator {
    @Id
    @Column(name="admin_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "admin_seq_gen")
    @SequenceGenerator(name="admin_seq_gen", sequenceName = "admin_seq")
 	private Integer id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="person_id")
    private Person person;

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

}
