package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.entity.BillOfMaterial;
import com.project.service.BillOfMaterialService;

import java.util.List;

@RestController
@RequestMapping("/api/bom")
@CrossOrigin(origins = "*")
public class BillOfMaterialController {

    @Autowired
    private BillOfMaterialService bomService;

    @PostMapping
    public BillOfMaterial addItem(@RequestBody BillOfMaterial bom) {
        return bomService.addItem(bom);
    }

    @GetMapping
    public List<BillOfMaterial> getAllItems() {
        return bomService.getAllItems();
    }

    @GetMapping("/record/{recordId}")
    public List<BillOfMaterial> getItemsByServiceRecord(@PathVariable Long recordId) {
        return bomService.getItemsByServiceRecordId(recordId);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        bomService.deleteItem(id);
    }
}
