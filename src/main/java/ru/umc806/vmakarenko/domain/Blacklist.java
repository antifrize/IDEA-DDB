package ru.umc806.vmakarenko.domain;

import javax.persistence.*;

@Entity
@Table(name="blacklist")
public class Blacklist {
    @Id
    @Column(name="bl_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "time_seq_gen")
    @SequenceGenerator(name="time_seq_gen", sequenceName = "time_seq")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="blocker_id")
    private Person blacklisted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="blocked_id")
    private Person blacklister;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Person getBlacklisted() {
		return blacklisted;
	}
	public void setBlacklisted(Person blacklisted) {
		this.blacklisted = blacklisted;
	}
	public Person getBlacklister() {
		return blacklister;
	}
	public void setBlacklister(Person blacklister) {
		this.blacklister = blacklister;
	}
}
