package com.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private String registrationNumber;
    private LocalDate purchaseDate;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

	public Vehicle() {
		
	}

	public Vehicle(String model, String registrationNumber, LocalDate purchaseDate, Customer customer) {
		super();
		this.model = model;
		this.registrationNumber = registrationNumber;
		this.purchaseDate = purchaseDate;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
    
}
