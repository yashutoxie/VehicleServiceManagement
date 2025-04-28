package com.project.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ServiceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate serviceDate;
    private String status;  // e.g., Scheduled, In Progress, Completed

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private ServiceRepresentative serviceAdvisor;

	public ServiceRecord() {
		
	}

	public ServiceRecord(LocalDate serviceDate, String status, Vehicle vehicle, ServiceRepresentative serviceAdvisor) {
		super();
		this.serviceDate = serviceDate;
		this.status = status;
		this.vehicle = vehicle;
		this.serviceAdvisor = serviceAdvisor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(LocalDate serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public ServiceRepresentative getServiceAdvisor() {
		return serviceAdvisor;
	}   

	public void setServiceAdvisor(ServiceRepresentative serviceAdvisor) {
		this.serviceAdvisor = serviceAdvisor;
	}
 
    
}
