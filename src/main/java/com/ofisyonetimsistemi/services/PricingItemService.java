package com.ofisyonetimsistemi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofisyonetimsistemi.models.PricingItem;
import com.ofisyonetimsistemi.repositories.PricingItemRepository;

@Service
public class PricingItemService {

@Autowired private PricingItemRepository repo;
	
	public PricingItem save(PricingItem pr) {
		return repo.save(pr);
	}
	
	public void update(PricingItem pr) {
		repo.save(pr);
	}
	
	public Optional<PricingItem> getById(Integer id){
		return repo.findById(id);
	}
	
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
	
	public List<PricingItem> getAll(){
		return repo.findAll();
	}

	public List<PricingItem> getPricingItemByPricingId(Integer pricingId) {
		return repo.findByPricingId(pricingId);
	}
	
}
