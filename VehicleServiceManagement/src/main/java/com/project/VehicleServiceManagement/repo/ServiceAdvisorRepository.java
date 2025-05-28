package com.project.VehicleServiceManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.VehicleServiceManagement.entity.ServiceAdvisor;

public interface ServiceAdvisorRepository extends JpaRepository<ServiceAdvisor, Long> {

}
