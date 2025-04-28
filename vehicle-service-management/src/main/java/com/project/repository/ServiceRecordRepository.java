package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.ServiceRecord;

import java.time.LocalDate;
import java.util.List;

public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {
    List<ServiceRecord> findByStatus(String status);
    List<ServiceRecord> findByServiceDateBetween(LocalDate start, LocalDate end);
}
