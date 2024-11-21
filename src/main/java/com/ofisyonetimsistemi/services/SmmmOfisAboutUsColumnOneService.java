package com.ofisyonetimsistemi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofisyonetimsistemi.models.SmmmOfisAboutUsColumnOne;
import com.ofisyonetimsistemi.repositories.SmmmOfisAboutUsColumnOneRepo;

@Service
public class SmmmOfisAboutUsColumnOneService {
	
	@Autowired SmmmOfisAboutUsColumnOneRepo repo;

	public void saveAboutUsColOneItem(SmmmOfisAboutUsColumnOne aboutUsColOne) {
		repo.save(aboutUsColOne);
		
	}

	public List<SmmmOfisAboutUsColumnOne> getAllAboutUscolOneItems() {
		return repo.findAll();
	}

	public Optional<SmmmOfisAboutUsColumnOne> getAboutUsColOneById(Integer id) {
		return repo.findById(id);
	}

	public void deleteAboutUsById(Integer id) {
		repo.deleteById(id);		
	}

}
