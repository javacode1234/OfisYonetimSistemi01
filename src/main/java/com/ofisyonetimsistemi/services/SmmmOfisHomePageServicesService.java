package com.ofisyonetimsistemi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofisyonetimsistemi.models.SmmmOfisServices;
import com.ofisyonetimsistemi.repositories.SmmmOfisServicesRepository;

@Service
public class SmmmOfisHomePageServicesService {

	@Autowired private SmmmOfisServicesRepository repo;
	
	public void saveService(SmmmOfisServices service) {
		repo.save(service);
		
	}

	public List<SmmmOfisServices> getAllServices() {
		return repo.findAll();
	}

	public Optional<SmmmOfisServices> getServiceById(Integer id) {
		return repo.findById(id);
	}

	public void deleteServiceById(Integer id) {
		repo.deleteById(id);		
	}
}
