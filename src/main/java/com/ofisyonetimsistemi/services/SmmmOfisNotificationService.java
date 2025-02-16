package com.ofisyonetimsistemi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofisyonetimsistemi.models.SmmmOfisNotification;
import com.ofisyonetimsistemi.repositories.SmmmOfisNotificationRepository;

@Service
public class SmmmOfisNotificationService {
	
	@Autowired private SmmmOfisNotificationRepository repo;
	
	public void saveNotification(SmmmOfisNotification notification) {
		repo.save(notification);
	}

	public Optional<SmmmOfisNotification> getById(Integer id){
		return repo.findById(id);
	}
	
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
	
	public List<SmmmOfisNotification> getAll(){
		return repo.findAll();
	}

	public void updateSelectedNotification(SmmmOfisNotification selectedNotification) {
		repo.save(selectedNotification);
	}
	
	public Long countOfRecord() {
		return repo.count();
	}
	
	public Long countOfUnReadNotifications(boolean okundu) {
		return repo.countOfUnReadNotifications(okundu);
	}

	public List<SmmmOfisNotification> getAllUnReadNotifications() {
		List<SmmmOfisNotification> listOfUnreadNotifications = repo.findAll();
		List<SmmmOfisNotification> newListOfUnreadNotifications = new ArrayList<>();
		for(SmmmOfisNotification notification : listOfUnreadNotifications) {
			if(notification.isOkundu()==false) {
				newListOfUnreadNotifications.add(notification);
			}
		}
		return newListOfUnreadNotifications;
	}

	public List<SmmmOfisNotification> getAllReadNotifications() {
		return repo.getAllReadNotifications(true);
	}

	public List<SmmmOfisNotification> getAllNotifications() {
		return repo.findAll();
	}
}
