package com.ofisyonetimsistemi.controllers;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.models.SmmmOfisMessage;
import com.ofisyonetimsistemi.security.model.MyUser;
import com.ofisyonetimsistemi.security.model.MyUserDetails;
import com.ofisyonetimsistemi.security.service.MyUserService;
import com.ofisyonetimsistemi.services.SmmmOfisMessageService;
import com.ofisyonetimsistemi.services.SmmmOfisService;

@Controller
@RequestMapping("/api/v1/")
public class AdminPanelMessagesController {
	
	@Autowired private SmmmOfisService smmmOfisService;
	@Autowired private MyUserService myUserService;
	@Autowired private SmmmOfisMessageService messageService;
	
	@GetMapping("/get-selected-message")
	public String getSelectedMessage(@AuthenticationPrincipal MyUserDetails loggedUser, Model model,
										@RequestParam("id")Integer id) {
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		SmmmOfisMessage selectedMessage = messageService.getById(id).get();
		model.addAttribute("smmmOfis", smmmOfis.get());
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("selectedMessage", selectedMessage);
		model.addAttribute("messageCount", messageService.countOfRecord());
		model.addAttribute("countOfNonReadMessages", messageService.countOfRecordReaded(false));
		model.addAttribute("allMessages", messageService.getAllUnReadMessages());
		
		return "adminpanel/messages";
	}
	
	@PostMapping("/update-selected-message")
	public String updateSelectedMessage(@ModelAttribute("selectedMessage")SmmmOfisMessage selectedMessage, Model model) {
		if(selectedMessage.isOkundu()==true) {
			selectedMessage.setDateofread(LocalDateTime.now().withNano(0));
		}
		messageService.updateSelectedMessage(selectedMessage);
		
		return "redirect:/api/v1/get-selected-message?id="+selectedMessage.getId();
	}
	
	@GetMapping("/get-un-read-messages")
	public String getUnReadMessages(@AuthenticationPrincipal MyUserDetails loggedUser, Model model) {
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		model.addAttribute("smmmOfis", smmmOfis.get());
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("selectedMessage", new SmmmOfisMessage());
		model.addAttribute("messageCount", messageService.countOfRecord());
		model.addAttribute("countOfNonReadMessages", messageService.countOfRecordReaded(false));
		model.addAttribute("allMessages", messageService.getAllUnReadMessages());
		
		return "adminpanel/messages";
	}
	
	@GetMapping("/get-read-messages")
	public String getReadMessages(@AuthenticationPrincipal MyUserDetails loggedUser, Model model) {
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		model.addAttribute("smmmOfis", smmmOfis.get());
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("selectedMessage", new SmmmOfisMessage());
		model.addAttribute("messageCount", messageService.countOfRecord());
		model.addAttribute("countOfNonReadMessages", messageService.countOfRecordReaded(false));
		model.addAttribute("allMessages", messageService.getAllReadMessages());
		
		return "adminpanel/messages";
	}
	
	@GetMapping("/get-all-messages")
	public String getAllMessages(@AuthenticationPrincipal MyUserDetails loggedUser, Model model) {
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		model.addAttribute("smmmOfis", smmmOfis.get());
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("selectedMessage", new SmmmOfisMessage());
		model.addAttribute("messageCount", messageService.countOfRecord());
		model.addAttribute("countOfNonReadMessages", messageService.countOfRecordReaded(false));
		model.addAttribute("allMessages", messageService.getAllMessages());
		
		return "adminpanel/messages";
	}
	
	@GetMapping("/view-selected-message/{id}")
	@ResponseBody
	public Optional<SmmmOfisMessage> viewSelectedMessage(@PathVariable("id") Integer id) {
		return messageService.getById(id);
	}
	
	@RequestMapping(value="/delete-selected-message", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delById(@RequestParam("id") Integer id) {
		messageService.deleteById(id);
		return "redirect:/api/v1/get-read-messages";
	}
	

}
