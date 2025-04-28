package com.project.service;

import java.util.List;

import com.project.entity.BillOfMaterial;

public interface BillOfMaterialService {
	BillOfMaterial addItem(BillOfMaterial bom);

	List<BillOfMaterial> getAllItems();

	List<BillOfMaterial> getItemsByServiceRecordId(Long serviceRecordId);

	void deleteItem(Long id);
}
