package com.project.VehicleServiceManagement.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.VehicleServiceManagement.entity.BillOfMaterial;
import com.project.VehicleServiceManagement.entity.ProvidedServices;
import com.project.VehicleServiceManagement.entity.ServiceAdvisor;
import com.project.VehicleServiceManagement.entity.Service_Record;
import com.project.VehicleServiceManagement.entity.TotalServices;
import com.project.VehicleServiceManagement.repo.BillOfMaterialRepository;
import com.project.VehicleServiceManagement.repo.ProvidedServicesRepository;
import com.project.VehicleServiceManagement.repo.ServiceAdvisorRepository;
import com.project.VehicleServiceManagement.repo.ServiceRecordRepository;
import com.project.VehicleServiceManagement.repo.TotalServicesRepository;

@RestController
@RequestMapping("/ServiceAdvisor")
public class ServiceAdvisorsController {
	@Autowired
	ProvidedServicesRepository psr;
	
	@Autowired
	TotalServicesRepository tsr;
	
	@Autowired
	ServiceRecordRepository srr;
	
	@Autowired
	BillOfMaterialRepository bom;
	
	@Autowired
    ServiceAdvisorRepository sar;
	
	
	/*@PostMapping("/bill/{id}/{quantity}")
	 public Service_Record addingintoservicerecord(@PathVariable Long id, @PathVariable int quantity) {
		 TotalServices service=tsr.findByStatus("ONGOING");
		 Service_Record new_record=new Service_Record();
		 new_record.setService(service); //setting the service to record
		 srr.save(new_record);
		 BillOfMaterial bill=createbill(id,quantity,new_record); //id says what are the  providedservices taken. 
		 new_record.setTotalcost(bill.getTotalcost());
		 LocalDate completiondate=service.getScheduled_date().plusDays(bill.getService().getDaystocomplete());
		 new_record.setCompletiondate(completiondate);
		 service.setStatus("COMPLETED");
		 srr.save(new_record);
		 return new_record; 
	 } // if only one service is there */
	
	// POST to add ServiceAdvisor
    @PostMapping("/addServiceAdvisor")
    public ServiceAdvisor addServiceAdvisor(@RequestBody ServiceAdvisor advisor) {
        return sar.save(advisor);
    }
	
 // POST to add TotalServices
    @PostMapping("/addTotalServices")
    public TotalServices addTotalServices(@RequestBody TotalServices totalServices) {
        return tsr.save(totalServices);
    }
    
    
 // POST to add ProvidedServices
    @PostMapping("/addProvidedServices")
    public ProvidedServices addProvidedServices(@RequestBody ProvidedServices providedServices) {
        return psr.save(providedServices);
    }
    
 // POST to add Service_Record
    @PostMapping("/addServiceRecord")
    public Service_Record addServiceRecord(@RequestBody Service_Record serviceRecord) {
        return srr.save(serviceRecord);
    }
 // POST to add BillOfMaterial
    @PostMapping("/addBillOfMaterial")
    public BillOfMaterial addBillOfMaterial(@RequestBody BillOfMaterial billOfMaterial) {
        return bom.save(billOfMaterial);
    }
    
	@GetMapping("/getvehicles/{id}")
	public TotalServices getvehicles(@PathVariable Long id) {
		TotalServices vehicle=null;
		TotalServices service=tsr.findByServiceAdvisorId(id); // passes the service advisor id , and finds if the advisor is present in the totalservices table , and if presents and checks the status is ongoing or not
		if(service!=null) {
			if ("ONGOING".equals(service.getStatus())) {
			vehicle=service;
		}
		}
		else {
			vehicle=null;
		}
      return vehicle;
		
	}
	
	
	@PostMapping("/bill")
	 public Service_Record addingintoservicerecord(@RequestParam Long service_id,@RequestParam List<Long> items_id,@RequestParam List<Integer> quantity) {
		 Optional<TotalServices> serviceOptional=tsr.findById(service_id); //id is the service id whose service record need to be updated
		 TotalServices service=serviceOptional.get();
		 Service_Record new_record=new Service_Record();
		 new_record.setService(service); //setting the service to record
		 srr.save(new_record);
		 
		 double totalcost=0;
		 int maxDays=0;
		 for(int i=0;i<items_id.size();i++) {  //items_id is the list of ids(items) used by the vehicle
			 
		  Long itemsId=items_id.get(i);    //taking one from list
		  int qty=quantity.get(i);
		 BillOfMaterial bill=createbill(itemsId,qty,new_record); //id says what are the  providedservices taken. 
		  totalcost+=bill.getTotalcost();
		  int daystocomplete=bill.getService().getDaystocomplete();
		  //System.out.println(daystocomplete);
		  if(daystocomplete>maxDays) {
			  maxDays=daystocomplete;
		  }
		 }
		 new_record.setTotalcost(totalcost);
		 LocalDate completiondate=service.getScheduled_date().plusDays(maxDays);
		 
		 new_record.setCompletiondate(completiondate); // setting the completion date
		 service.setStatus("COMPLETED");              // marking the service as completed 
		 ServiceAdvisor advisor=service.getService_advisor();
		 advisor.setStatus("FREE");                 // marking the advisor as free
		 
		 srr.save(new_record);
		 return new_record; 
	 }
	
	
	public BillOfMaterial createbill(Long id,int quantity,Service_Record new_record) {
		BillOfMaterial bill=new BillOfMaterial();
		Optional<ProvidedServices> ps=psr.findById(id);
		bill.setService(ps.get());
		bill.setRecord(new_record);
		bill.setQuantity(quantity);
		bill.setTotalcost(quantity*ps.get().getPrice());
		bom.save(bill);
		return bill;
	}
	
	

}
