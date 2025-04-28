package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.entity.ServiceRepresentative;
import com.project.service.ServiceRepresentativeService;

import java.util.List;

@RestController
@RequestMapping("/api/representatives")
@CrossOrigin(origins = "*")
public class ServiceRepresentativeController {

    @Autowired
    private ServiceRepresentativeService repService;

    @PostMapping
    public ServiceRepresentative addRepresentative(@RequestBody ServiceRepresentative rep) {
        return repService.saveRep(rep);
    }

    @GetMapping
    public List<ServiceRepresentative> getAllRepresentatives() {
        return repService.getAllReps();
    }

    @GetMapping("/{id}")
    public ServiceRepresentative getRepresentativeById(@PathVariable Long id) {
        return repService.getRepById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRepresentative(@PathVariable Long id) {
        repService.deleteRep(id);
    }
}
