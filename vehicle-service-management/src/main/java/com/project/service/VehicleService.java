package com.project.service;

import java.util.List;

import com.project.entity.Vehicle;

public interface VehicleService {
    Vehicle saveVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(Long id);
    void deleteVehicle(Long id);
}
