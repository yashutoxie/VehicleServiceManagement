package com.project.VehicleServiceManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.VehicleServiceManagement.entity.Service_Record;

public interface ServiceRecordRepository extends JpaRepository<Service_Record, Long> {

}
