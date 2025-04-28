package com.project.service;

import java.util.List;

import com.project.entity.ServiceRecord;

public interface ServiceRecordService {
    ServiceRecord scheduleService(ServiceRecord record);
    List<ServiceRecord> getAllRecords();
    ServiceRecord getRecordById(Long id);
    void deleteRecord(Long id);
    List<ServiceRecord> getByStatus(String status);
}
