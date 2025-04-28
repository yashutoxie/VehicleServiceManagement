package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.BillOfMaterial;
import com.project.entity.ServiceRecord;

import java.util.List;

public interface BillOfMaterialRepository extends JpaRepository<BillOfMaterial, Long> {
    List<BillOfMaterial> findByServiceRecord(ServiceRecord serviceRecord);
}
