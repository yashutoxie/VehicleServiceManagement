package com.project.VehicleServiceManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.VehicleServiceManagement.entity.ProvidedServices;
import com.project.VehicleServiceManagement.entity.TotalServices;

public interface ProvidedServicesRepository extends JpaRepository<ProvidedServices, Long> {
	@Query(value = "update provided_services set quantity=:qty where id=:id", nativeQuery = true)
	void UpdateQuantity(int qty, Long id);

}
