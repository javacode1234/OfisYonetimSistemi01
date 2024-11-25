package com.ofisyonetimsistemi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofisyonetimsistemi.models.SmmmOfisBusinesSector;
import com.ofisyonetimsistemi.repositories.SmmmOfisBusinesSectorRepository;

@Service
public class SmmmOfisBusinesSectorService {
	
	@Autowired private SmmmOfisBusinesSectorRepository sectorRepo;
	
	public void saveSector(SmmmOfisBusinesSector sector) {
		sectorRepo.save(sector);
		
	}

	public List<SmmmOfisBusinesSector> getAllSector() {
		return sectorRepo.findAll();
	}

	public Optional<SmmmOfisBusinesSector> getSectorById(Integer id) {
		return sectorRepo.findById(id);
	}

	public void deleteSectorById(Integer id) {
		sectorRepo.deleteById(id);		
	}

}
