package com.ofisyonetimsistemi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofisyonetimsistemi.models.SmmmOfisPricing;
import com.ofisyonetimsistemi.repositories.SmmmOfisPricingRepository;

@Service
public class SmmmOfisPricingService {
	
	@Autowired private SmmmOfisPricingRepository repo;
	
	public void save(SmmmOfisPricing pr) {
		repo.save(pr);
	}
	
	public void update(SmmmOfisPricing pr) {
		repo.save(pr);
	}
	
	public Optional<SmmmOfisPricing> getById(Integer id){
		return repo.findById(id);
	}
	
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
	
	public List<SmmmOfisPricing> getAll(){
		return repo.findAll();
	}

}
