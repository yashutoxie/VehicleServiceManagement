package com.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.ServiceRecord;
import com.project.entity.ServiceRepresentative;
import com.project.entity.Vehicle;
import com.project.repository.ServiceRecordRepository;
import com.project.repository.ServiceRepresentativeRepository;
import com.project.repository.VehicleRepository;
import com.project.service.ServiceRecordService;

import java.util.List;

@Service
public class ServiceRecordServiceImpl implements ServiceRecordService {

    @Autowired
    private ServiceRecordRepository recordRepository;

    @Autowired
    private ServiceRepresentativeRepository representativeRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public ServiceRecord scheduleService(ServiceRecord record) {
        // Fetch and set managed vehicle entity
        if (record.getVehicle() != null && record.getVehicle().getId() != null) {
            Vehicle vehicle = vehicleRepository.findById(record.getVehicle().getId())
                    .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + record.getVehicle().getId()));
            record.setVehicle(vehicle);
        }

        // Fetch and set managed service advisor entity
        if (record.getServiceAdvisor() != null && record.getServiceAdvisor().getId() != null) {
            ServiceRepresentative advisor = representativeRepository.findById(record.getServiceAdvisor().getId())
                    .orElseThrow(() -> new RuntimeException("Service Advisor not found with ID: " + record.getServiceAdvisor().getId()));
            record.setServiceAdvisor(advisor);
        }

        return recordRepository.save(record);
    }

    @Override
    public List<ServiceRecord> getAllRecords() {
        return recordRepository.findAll();
    }

    @Override
    public ServiceRecord getRecordById(Long id) {
        return recordRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteRecord(Long id) {
        recordRepository.deleteById(id);
    }

    @Override
    public List<ServiceRecord> getByStatus(String status) {
        return recordRepository.findByStatus(status);
    }
}
