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

	public Optional<SmmmOfis> getFirstSmmmOfis() {
		return smmmOfisRepo.findAll().stream().findFirst();
	}

	public void updateSmmmHomePageSettingsById(Integer id) {
		SmmmOfis smmmOfis = smmmOfisRepo.findById(id).get();
		smmmOfisRepo.save(smmmOfis);
	}

	public void updatdateSmmmOfis(SmmmOfis smmmOfis) {
		smmmOfisRepo.save(smmmOfis);
	}
	
	

}
