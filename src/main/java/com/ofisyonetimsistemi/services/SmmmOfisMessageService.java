package com.ofisyonetimsistemi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofisyonetimsistemi.models.SmmmOfisMessage;
import com.ofisyonetimsistemi.repositories.SmmmOfisMessageRepository;

@Service
public class SmmmOfisMessageService {
	
	@Autowired private SmmmOfisMessageRepository repo;
	
	public void saveMessage(SmmmOfisMessage message) {
		repo.save(message);
	}

	public Optional<SmmmOfisMessage> getById(Integer id){
		return repo.findById(id);
	}
	
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
	
	public List<SmmmOfisMessage> getAll(){
		return repo.findAll();
	}

	public void updateSelectedMessage(SmmmOfisMessage selectedMessage) {
		repo.save(selectedMessage);
	}
	
	public Long countOfRecord() {
		return repo.count();
	}
	
	public Long countOfRecordReaded(boolean okundu) {
		return repo.countOfNonReadMessage(okundu);
	}

	public List<SmmmOfisMessage> getAllUnReadMessages() {
		return repo.getAllUnReadMessages(false);
	}

	public List<SmmmOfisMessage> getAllReadMessages() {
		return repo.getAllReadMessages(true);
	}

	public List<SmmmOfisMessage> getAllMessages() {
		return repo.findAll();
	}
}
