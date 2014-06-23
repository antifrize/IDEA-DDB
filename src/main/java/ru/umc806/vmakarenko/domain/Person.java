package ru.umc806.vmakarenko.domain;

import javax.persistence.*;

@Entity
@Table(name="person")
public class Person {
    @Id
    @Column(name="person_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "person_seq_gen")
    @SequenceGenerator(name = "person_seq_gen",sequenceName = "person_seq")
    private Integer id;
    @Column(name="passport")
    private String passport;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name="last_name")
    private String lastname;
    @Column(name="login")
    private String login;
    @Column(name="pass_hash")
    private String pass_hash;
	public String getLogin() {
		return login;
	}
	public Person setLogin(String login) {
		this.login = login;
        return this;
	}
	public String getPass_hash() {
		return pass_hash;
	}
	public Person setPass_hash(String pass_hash) {
		this.pass_hash = pass_hash;
        return this;
	}
	public Integer getId() {
		return id;
	}
	public Person setId(Integer id) {
		this.id = id;
        return this;
	}
	public String getPassport() {
		return passport;
	}
	public Person setPassport(String passport) {
		this.passport = passport;
        return this;
	}
	public String getName() {
		return name;
	}
	public Person setName(String name) {
		this.name = name;
        return this;
	}
	public String getSurname() {
		return surname;
	}
	public Person setSurname(String surname) {
		this.surname = surname;
        return this;
	}
	public String getLastname() {
		return lastname;
	}
	public Person setLastname(String lastname) {
		this.lastname = lastname;
        return this;
	}
   }
