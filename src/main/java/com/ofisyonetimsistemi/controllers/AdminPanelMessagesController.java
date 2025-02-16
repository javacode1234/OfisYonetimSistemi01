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
import com.ofisyonetimsistemi.security.model.MyUser;
import com.ofisyonetimsistemi.security.model.MyUserDetails;
import com.ofisyonetimsistemi.security.service.MyUserService;
import com.ofisyonetimsistemi.services.SmmmOfisMessageService;
import com.ofisyonetimsistemi.services.SmmmOfisNotificationService;
import com.ofisyonetimsistemi.services.SmmmOfisService;

@Controller
@RequestMapping("/cp/")
public class AdminPanelMessagesController {
	
	@Autowired private SmmmOfisService smmmOfisService;
	@Autowired private MyUserService myUserService;
	@Autowired private SmmmOfisMessageService messageService;
	@Autowired private SmmmOfisNotificationService notificationService;
		
	@GetMapping("/get-un-read-messages")
	public String getUnReadMessages(@AuthenticationPrincipal MyUserDetails loggedUser, Model model) {
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		model.addAttribute("smmmOfis", smmmOfis.get());
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("selectedMessage", new SmmmOfisMessage());
		
		model.addAttribute("allMessages", messageService.getAllUnReadMessages());
		
		loadRequaredCommenItems(model);
		
		return "adminpanel/messages";
	}
	
	@GetMapping("/get-read-messages")
	public String getReadMessages(@AuthenticationPrincipal MyUserDetails loggedUser, Model model) {
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		model.addAttribute("smmmOfis", smmmOfis.get());
		model.addAttribute("currentUser", currentUser);
		
		model.addAttribute("allMessages", messageService.getAllReadMessages());
		
		loadRequaredCommenItems(model);
		
		return "adminpanel/messages";
	}
	
	@GetMapping("/get-all-messages")
	public String getAllMessages(@AuthenticationPrincipal MyUserDetails loggedUser, Model model) {
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		model.addAttribute("smmmOfis", smmmOfis.get());
		model.addAttribute("currentUser", currentUser);
		
		model.addAttribute("allMessages", messageService.getAll());
		
		loadRequaredCommenItems(model);
		
		return "adminpanel/messages";
	}
	
	@GetMapping("/get-message/{id}")
	@ResponseBody
	public Optional<SmmmOfisMessage> viewSelectedMessage(@PathVariable("id") Integer id) {
		return messageService.getById(id);
	}
	
	@RequestMapping(value="/delete-message", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delById(@RequestParam("id") Integer id) {
		messageService.deleteById(id);
		return "redirect:/cp/get-un-read-messages";
	}
	
	@PostMapping("/add-message")
	public String addMessage(
			
									 @RequestParam("name")String name,
						             @RequestParam("email")String email,
						             @RequestParam("subject")String subject,
						             @RequestParam("message")String message
			                        
									) {
		SmmmOfis smmmOfis = smmmOfisService.getFirstSmmmOfis().get();
		
		SmmmOfisMessage newMessage = SmmmOfisMessage.builder()
				.name(name)
				.email(email)
				.subject(subject)
				.message(message)
				.date(LocalDateTime.now().withNano(0))
				.smmmofis_id(smmmOfis.getId())
				.build();

		messageService.saveMessage(newMessage);
		return "redirect:/cp/get-un-read-messages";
	}
	
	@PostMapping("/update-message")
	public String updateMessage(
									@RequestParam("id")Integer id,
			                        @RequestParam("name")String name,
			                        @RequestParam("email")String email,
			                        @RequestParam("subject")String subject,
			                        @RequestParam("message")String message,
			                        @RequestParam(value="okundu", required = false)boolean okundu,
			                        @RequestParam("smmmofis_id")Integer smmmofis_id
			                        
									) {
		SmmmOfisMessage selectedMessage = messageService.getById(id).get();
		SmmmOfisMessage newMessage = new SmmmOfisMessage();
		if(okundu==false) {
			newMessage = SmmmOfisMessage.builder()
					.id(id)
					.name(name)
					.email(email)
					.subject(subject)
					.message(message)
					.date(selectedMessage.getDate().withNano(0))
					.smmmofis_id(smmmofis_id)
					.build();
		}else if(okundu==true) {
			newMessage = SmmmOfisMessage.builder()
					.id(id)
					.name(name)
					.email(email)
					.subject(subject)
					.message(message)
					.date(selectedMessage.getDate().withNano(0))
					.dateofread(LocalDateTime.now().withNano(0))
					.okundu(okundu)
					.smmmofis_id(smmmofis_id)
					.build();
		}
		
		
		messageService.saveMessage(newMessage);
		
		return "redirect:/cp/get-un-read-messages";
		
	}
	
	public void loadRequaredCommenItems(Model model) {
		
		model.addAttribute("countOfUnReadMessages", messageService.countOfUnReadMessages(false));
		model.addAttribute("listOfUnreadMessages", messageService.getAllUnReadMessages());
		
		model.addAttribute("countOfUnReadNotifications", notificationService.countOfUnReadNotifications(false));
		model.addAttribute("listOfUnreadNotifications", notificationService.getAllUnReadNotifications());
		
}

}
