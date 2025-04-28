package com.project.entity;

import jakarta.persistence.*;

@Entity
public class BillOfMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private int quantity;
    private double unitCost;

    @ManyToOne
    @JoinColumn(name = "service_record_id")
    private ServiceRecord serviceRecord;

    // Getters and Setters
    

    public double getTotalCost() {
        return quantity * unitCost;
    }

	public BillOfMaterial() {
		
	}

	public BillOfMaterial(String itemName, int quantity, double unitCost, ServiceRecord serviceRecord) {
		super();
		this.itemName = itemName;
		this.quantity = quantity;
		this.unitCost = unitCost;
		this.serviceRecord = serviceRecord;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	public ServiceRecord getServiceRecord() {
		return serviceRecord;
	}

	public void setServiceRecord(ServiceRecord serviceRecord) {
		this.serviceRecord = serviceRecord;
	}
	
}
