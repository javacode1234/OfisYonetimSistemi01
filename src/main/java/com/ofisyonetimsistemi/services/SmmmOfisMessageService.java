package com.ofisyonetimsistemi.services;

import java.util.ArrayList;
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
		
	public Long countOfUnReadMessages() {
		return repo.countOfUnReadOrReadMessage(false);
	}

	public List<SmmmOfisMessage> getAllUnReadMessages() {
		List<SmmmOfisMessage> listOfUnreadMessages = repo.findAll();
		List<SmmmOfisMessage> newListOfUnreadMessages = new ArrayList<>();
		for(SmmmOfisMessage notification : listOfUnreadMessages) {
			if(notification.isOkundu()==false) {
				newListOfUnreadMessages.add(notification);
			}
		}
		return newListOfUnreadMessages;
	}

	public List<SmmmOfisMessage> getAllReadMessages() {
		return repo.getAllReadMessages(true);
	}

	public List<SmmmOfisMessage> getAllMessages() {
		return repo.findAll();
	}
}
