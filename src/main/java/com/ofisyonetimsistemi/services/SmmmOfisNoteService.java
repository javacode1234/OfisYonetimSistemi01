package com.ofisyonetimsistemi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofisyonetimsistemi.models.SmmmOfisNotes;
import com.ofisyonetimsistemi.repositories.SmmmOfisNoteRepository;

@Service
public class SmmmOfisNoteService {
	
	@Autowired private SmmmOfisNoteRepository noteRepo;
	
	public void saveNote(SmmmOfisNotes note) {
		noteRepo.save(note);
	}

	public Optional<SmmmOfisNotes> getNoteById(Integer id) {
		return noteRepo.findById(id);
	}
	
	public List<SmmmOfisNotes> getAllNotes(){
		return noteRepo.findAll();
	}
	
	public void deleteNoteById(Integer id) {
		noteRepo.deleteById(id);
	}

	public List<SmmmOfisNotes> getAllUnreadNotes() {
		return noteRepo.getAllReadOrUnReadNotes(false);
	}
	
	public List<SmmmOfisNotes> getAllReadNotes() {
		return noteRepo.getAllReadOrUnReadNotes(true);
	}

	public Long countOfUnReadNotes() {
		return noteRepo.countOfUnReadOrReadNotes(false);
	}
	
}
