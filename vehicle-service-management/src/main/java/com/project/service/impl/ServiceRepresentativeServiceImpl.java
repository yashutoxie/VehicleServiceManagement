package com.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.ServiceRepresentative;
import com.project.repository.ServiceRepresentativeRepository;
import com.project.service.ServiceRepresentativeService;

import java.util.List;

@Service
public class ServiceRepresentativeServiceImpl implements ServiceRepresentativeService {

    @Autowired
    private ServiceRepresentativeRepository repRepository;

    public ServiceRepresentative saveRep(ServiceRepresentative rep) {
        return repRepository.save(rep);
    }

    public List<ServiceRepresentative> getAllReps() {
        return repRepository.findAll();
    }

    public ServiceRepresentative getRepById(Long id) {
        return repRepository.findById(id).orElse(null);
    }

    public void deleteRep(Long id) {
        repRepository.deleteById(id);
    }
}
