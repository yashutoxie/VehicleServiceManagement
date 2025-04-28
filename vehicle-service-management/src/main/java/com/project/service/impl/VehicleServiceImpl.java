package com.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Customer;
import com.project.entity.Vehicle;
import com.project.repository.CustomerRepository;
import com.project.repository.VehicleRepository;
import com.project.service.VehicleService;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        if (vehicle.getCustomer() != null && vehicle.getCustomer().getId() != null) {
            Customer customer = customerRepository.findById(vehicle.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + vehicle.getCustomer().getId()));
            vehicle.setCustomer(customer); // Set the full customer object
        }
        return vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}
