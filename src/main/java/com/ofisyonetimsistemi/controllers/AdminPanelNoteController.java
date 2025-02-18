package com.ofisyonetimsistemi.controllers;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.models.SmmmOfisMessage;
import com.ofisyonetimsistemi.models.SmmmOfisNotes;
import com.ofisyonetimsistemi.security.model.MyUser;
import com.ofisyonetimsistemi.security.model.MyUserDetails;
import com.ofisyonetimsistemi.security.service.MyUserService;
import com.ofisyonetimsistemi.services.SmmmOfisMessageService;
import com.ofisyonetimsistemi.services.SmmmOfisNoteService;
import com.ofisyonetimsistemi.services.SmmmOfisNotificationService;
import com.ofisyonetimsistemi.services.SmmmOfisService;

@Controller
@RequestMapping("/cp/")
public class AdminPanelNoteController {
	
	@Autowired private SmmmOfisService smmmOfisService;
	@Autowired private MyUserService myUserService;
	@Autowired private SmmmOfisMessageService messageService;
	@Autowired private SmmmOfisNotificationService notificationService;
	@Autowired private SmmmOfisNoteService noteService;
	
	@GetMapping("/get-un-read-notes")
	public String getUnReadNotes(@AuthenticationPrincipal MyUserDetails loggedUser, Model model) {
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		model.addAttribute("smmmOfis", smmmOfis.get());
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("selectedMessage", new SmmmOfisMessage());
		
		model.addAttribute("allNotes", noteService.getAllUnreadNotes());
		
		loadRequaredCommenItems(model);
		
		return "adminpanel/notes";
	}
	
	@GetMapping("/get-read-notes")
	public String getReadMessages(@AuthenticationPrincipal MyUserDetails loggedUser, Model model) {
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		model.addAttribute("smmmOfis", smmmOfis.get());
		model.addAttribute("currentUser", currentUser);
		
		model.addAttribute("allNotes", noteService.getAllReadNotes());
		
		loadRequaredCommenItems(model);
		
		return "adminpanel/notes";
	}
	
	@GetMapping("/get-all-notes")
	public String getAllMessages(@AuthenticationPrincipal MyUserDetails loggedUser, Model model) {
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		model.addAttribute("smmmOfis", smmmOfis.get());
		model.addAttribute("currentUser", currentUser);
		
		model.addAttribute("allNotes", noteService.getAllNotes());
		
		loadRequaredCommenItems(model);
		
		return "adminpanel/notes";
	}
	
	@GetMapping("/get-note/{id}")
	@ResponseBody
	public Optional<SmmmOfisNotes> viewSelectedMessage(@PathVariable("id") Integer id) {
		return noteService.getNoteById(id);
	}
	
	@RequestMapping(value="/delete-note", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delById(@RequestParam("id") Integer id) {
		noteService.deleteNoteById(id);
		return "redirect:/cp/get-un-read-notes";
	}
	
	@PostMapping("/add-note")
	public String addMessage(
			
									 @RequestParam("subject")String subject,
						             @RequestParam("noteText")String noteText
			                        
									) {
		SmmmOfis smmmOfis = smmmOfisService.getFirstSmmmOfis().get();
		
		SmmmOfisNotes newNote = SmmmOfisNotes.builder()
				.subject(subject)
				.noteText(noteText)
				.createDate(LocalDateTime.now().withNano(0))
				.smmmofis_id(smmmOfis.getId())
				.build();

		noteService.saveNote(newNote);
		
		return "redirect:/cp/get-un-read-notes";
	}
	
	@PostMapping("/update-note")
	public String updateMessage(
									@RequestParam("id")Integer id,
									@RequestParam("subject")String subject,
						            @RequestParam("noteText")String noteText,
			                        @RequestParam(value="okundu", required = false)boolean okundu,
			                        @RequestParam("smmmofis_id")Integer smmmofis_id
			                        
									) {
		SmmmOfisNotes selectedNote = noteService.getNoteById(id).get();
		SmmmOfisNotes newNote = new SmmmOfisNotes();
		if(okundu==false) {
			newNote = SmmmOfisNotes.builder()
					.id(id)
					.subject(subject)
					.noteText(noteText)
					.createDate(selectedNote.getCreateDate().withNano(0))
					.okundu(okundu)
					.smmmofis_id(smmmofis_id)
					.build();
		}else if(okundu==true) {
			newNote = SmmmOfisNotes.builder()
					.id(id)
					.subject(subject)
					.noteText(noteText)
					.createDate(selectedNote.getCreateDate().withNano(0))
					.readDate(LocalDateTime.now().withNano(0))
					.okundu(okundu)
					.smmmofis_id(smmmofis_id)
					.build();
		}
		
		
		noteService.saveNote(newNote);
		
		return "redirect:/cp/get-un-read-notes";
		
	}
	
	public void loadRequaredCommenItems(Model model) {
		
		model.addAttribute("countOfUnReadMessages", messageService.countOfUnReadMessages(false));
		model.addAttribute("listOfUnreadMessages", messageService.getAllUnReadMessages());
		
		model.addAttribute("countOfUnReadNotifications", notificationService.countOfUnReadNotifications(false));
		model.addAttribute("listOfUnreadNotifications", notificationService.getAllUnReadNotifications());
		
		model.addAttribute("countOfUnReadNotes", noteService.countOfUnReadNotes());
		model.addAttribute("listOfUnReadNotes", noteService.getAllUnreadNotes());
		
	}

}
