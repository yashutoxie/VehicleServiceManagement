package com.project.VehicleServiceManagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.VehicleServiceManagement.entity.BillOfMaterial;
import com.project.VehicleServiceManagement.entity.TotalServices;

public interface BillOfMaterialRepository extends JpaRepository<BillOfMaterial, Long> {

	@Query(value = "select * from bill_of_material", nativeQuery = true)
	List<BillOfMaterial> getAllBills();

	@Query(value = "select sum(quantity) from bill_of_materail where service_id=:id", nativeQuery = true)
	Integer findTotalUsedQuantityByServiceId(Long id);
}
