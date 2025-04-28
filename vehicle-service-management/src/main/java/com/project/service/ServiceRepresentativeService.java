package com.project.service;

import java.util.List;

import com.project.entity.ServiceRepresentative;

public interface ServiceRepresentativeService {
    ServiceRepresentative saveRep(ServiceRepresentative rep);
    List<ServiceRepresentative> getAllReps();
    ServiceRepresentative getRepById(Long id);
    void deleteRep(Long id);
}
