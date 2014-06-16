package ru.umc806.vmakarenko.domain;

import javax.persistence.*;

@Entity
@Table(name="person")
public class Person {
    @Id
    @Column(name=";")
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
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPass_hash() {
		return pass_hash;
	}
	public void setPass_hash(String pass_hash) {
		this.pass_hash = pass_hash;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
   }
