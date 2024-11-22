package com.ofisyonetimsistemi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofisyonetimsistemi.models.commonmodels.BoxIcons;
import com.ofisyonetimsistemi.repositories.BoxIconsRepository;

@Service
public class BoxIconsService {
	
	@Autowired private BoxIconsRepository repo;
	
	public void saveBoxIcon(BoxIcons icon) {
		repo.save(icon);
	}
	
	public Optional<BoxIcons> getBoxIconById(Integer id) {
		return repo.findById(id);
		
	}
	
	public List<BoxIcons> getAllBoxIcons(){
		return repo.findAll();
	}

}
