package com.ofisyonetimsistemi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.repositories.SmmmOfisRepository;

@Service
public class SmmmOfisService {
	
	@Autowired SmmmOfisRepository smmmOfisRepo;
	
	public void saveHomePageSettings(SmmmOfis smmmOfis) {
		smmmOfisRepo.save(smmmOfis);
	}
	
	public Optional<SmmmOfis> getById(Integer id) {
		return smmmOfisRepo.findById(id);
	}

}
