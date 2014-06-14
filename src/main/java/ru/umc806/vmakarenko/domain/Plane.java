package ru.umc806.vmakarenko.domain;

import javax.persistence.*;

@Entity
@Table(name = "plane")
public class Plane {
    @Id
    @Column(name="plane_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "plane_seq_gen")
    @SequenceGenerator(name = "plane_seq_gen",sequenceName = "plane_seq")
    private Integer id;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "model")
    private String model;
    @Column(name = "sn")
    private String sn;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
}
