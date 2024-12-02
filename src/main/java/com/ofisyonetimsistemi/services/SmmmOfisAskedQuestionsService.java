package com.ofisyonetimsistemi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofisyonetimsistemi.models.SmmmOfisAskedQuestions;
import com.ofisyonetimsistemi.repositories.SmmmOfisAskedQuestionsRepository;

@Service
public class SmmmOfisAskedQuestionsService {
	
	@Autowired private SmmmOfisAskedQuestionsRepository repo;
	
	public void save(SmmmOfisAskedQuestions askedQuestion) {
		repo.save(askedQuestion);
	}
	
	public void update(SmmmOfisAskedQuestions askedQuestion) {
		repo.save(askedQuestion);
	}
	
	public Optional<SmmmOfisAskedQuestions> getById(Integer id){
		return repo.findById(id);
	}
	
	public List<SmmmOfisAskedQuestions> getAll(){
		return repo.findAll();
	}
	
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

}
