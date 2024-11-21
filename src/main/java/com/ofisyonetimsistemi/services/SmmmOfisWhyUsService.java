package com.ofisyonetimsistemi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofisyonetimsistemi.models.SmmmOfisWhyUs;
import com.ofisyonetimsistemi.repositories.SmmmOfisWhyUsRepository;

@Service
public class SmmmOfisWhyUsService {

	@Autowired private SmmmOfisWhyUsRepository repo;
	
	public void saveWhyUs(SmmmOfisWhyUs whyus) {
		repo.save(whyus);
		
	}

	public List<SmmmOfisWhyUs> getAllWhyUs() {
		return repo.findAll();
	}

	public Optional<SmmmOfisWhyUs> getWhyUsById(Integer id) {
		return repo.findById(id);
	}

	public void deleteWhyUsById(Integer id) {
		repo.deleteById(id);		
	}
}
