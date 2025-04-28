package com.project.entity;

import jakarta.persistence.*;

@Entity
public class ServiceRepresentative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private
    String expertise;
	public ServiceRepresentative() {
		
	}
	public ServiceRepresentative(String name, String phone, String expertise) {
		super();
		this.name = name;
		this.phone = phone;
		this.expertise = expertise;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
    
    
}
