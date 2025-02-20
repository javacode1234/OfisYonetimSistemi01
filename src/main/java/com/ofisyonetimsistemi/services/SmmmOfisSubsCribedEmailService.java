package com.ofisyonetimsistemi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofisyonetimsistemi.models.SmmmOfisSubscribedEmail;
import com.ofisyonetimsistemi.repositories.SmmmOfisSubcribedEmailRepository;

@Service
public class SmmmOfisSubsCribedEmailService {

	@Autowired private SmmmOfisSubcribedEmailRepository repo;
	
	public void save(SmmmOfisSubscribedEmail email) {
		repo.save(email);
	}
	
	public Optional<SmmmOfisSubscribedEmail> getSubscribedEmailById(Integer id){
		return repo.findById(id);
	}
	
	public List<SmmmOfisSubscribedEmail> getActiveSubscribedEmailList(){
		return repo.activeAndInActiveSubscribedEmailList(true);
	}
	
	public List<SmmmOfisSubscribedEmail> getInActiveSubscribedEmailList(){
		return repo.activeAndInActiveSubscribedEmailList(false);
	}
	
	public List<SmmmOfisSubscribedEmail> getSubscribedEmailList(){
		return repo.findAll();
	}
	
	public void deleteSubscribedEmailById(Integer id) {
		repo.deleteById(id);
	}

	public boolean emailExist(String email) {
		List<SmmmOfisSubscribedEmail> emailList=repo.findAll();
		for(SmmmOfisSubscribedEmail emailObject:emailList) {
			if(emailObject.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
	
	
}
