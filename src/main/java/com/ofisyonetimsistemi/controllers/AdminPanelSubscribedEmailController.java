package com.ofisyonetimsistemi.controllers;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.models.SmmmOfisMessage;
import com.ofisyonetimsistemi.models.SmmmOfisSubscribedEmail;
import com.ofisyonetimsistemi.security.model.MyUser;
import com.ofisyonetimsistemi.security.model.MyUserDetails;
import com.ofisyonetimsistemi.security.service.MyUserService;
import com.ofisyonetimsistemi.services.SmmmOfisMessageService;
import com.ofisyonetimsistemi.services.SmmmOfisNoteService;
import com.ofisyonetimsistemi.services.SmmmOfisNotificationService;
import com.ofisyonetimsistemi.services.SmmmOfisService;
import com.ofisyonetimsistemi.services.SmmmOfisSubsCribedEmailService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cp/")
public class AdminPanelSubscribedEmailController {
	
	@Autowired private SmmmOfisService smmmOfisService;
	@Autowired private MyUserService myUserService;
	@Autowired private SmmmOfisMessageService messageService;
	@Autowired private SmmmOfisNotificationService notificationService;
	@Autowired private SmmmOfisNoteService noteService;
	@Autowired private SmmmOfisSubsCribedEmailService subscribedEmailService;
		
	@GetMapping("/get-in-active-subscribed-emails")
	public String getInActiveSubscribedEmail(@AuthenticationPrincipal MyUserDetails loggedUser, Model model) {
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		model.addAttribute("smmmOfis", smmmOfis.get());
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("selectedMessage", new SmmmOfisMessage());
		
		model.addAttribute("emailList", subscribedEmailService.getInActiveSubscribedEmailList());
		
		loadRequaredCommenItems(model);
		
		return "adminpanel/subscribed-emails";
	}
	
	@GetMapping("/get-active-subscribed-emails")
	public String getActiveSubscribedEmail(@AuthenticationPrincipal MyUserDetails loggedUser, Model model) {
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		model.addAttribute("smmmOfis", smmmOfis.get());
		model.addAttribute("currentUser", currentUser);
		
		model.addAttribute("emailList", subscribedEmailService.getActiveSubscribedEmailList());
		
		loadRequaredCommenItems(model);
		
		return "adminpanel/subscribed-emails";
	}
	
	@GetMapping("/get-all-subscribed-emails")
	public String getAllSubscribedEmail(@AuthenticationPrincipal MyUserDetails loggedUser, Model model) {
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		model.addAttribute("smmmOfis", smmmOfis.get());
		model.addAttribute("currentUser", currentUser);
		
		model.addAttribute("emailList", subscribedEmailService.getSubscribedEmailList());
		
		loadRequaredCommenItems(model);
		
		return "adminpanel/subscribed-emails";
	}
	
	@GetMapping("/get-subscribed-email/{id}")
	@ResponseBody
	public Optional<SmmmOfisSubscribedEmail> viewSubscribedEmailById(@PathVariable("id") Integer id) {
		return subscribedEmailService.getSubscribedEmailById(id);
	}
	
	@RequestMapping(value="/delete-subscribed-email", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String deleteSubscribedEmailById(@RequestParam("id") Integer id) {
		subscribedEmailService.deleteSubscribedEmailById(id);
		return "redirect:/cp/get-in-active-subscribed-emails";
	}
	
	@PostMapping("/add-subscribed-email")
	public String addSubscribedEmail(
			
									 @Valid @RequestParam("email")String email,
						             BindingResult result
			                        
									) {
		
		if(result.hasErrors()) {
			
			return "redirect:/";
		}
		SmmmOfis smmmOfis = smmmOfisService.getFirstSmmmOfis().get();
		
		SmmmOfisSubscribedEmail newSubscribedEmail = SmmmOfisSubscribedEmail.builder()
				.email(email)
				.date(LocalDateTime.now().withNano(0))
				.smmmofis_id(smmmOfis.getId())
				.build();

		subscribedEmailService.save(newSubscribedEmail);
		return "redirect:/";
	}
	
	@PostMapping("/update-subscribed-email")
	public String updateMessage(
									@RequestParam("id")Integer id,
			                        @RequestParam("email")String email,
			                        @RequestParam(value="active", required = false)boolean active,
			                        @RequestParam("smmmofis_id")Integer smmmofis_id
			                        
									) {
		SmmmOfisMessage selectedMessage = messageService.getById(id).get();
		SmmmOfisSubscribedEmail newSubscribedEmail = new SmmmOfisSubscribedEmail();
		if(active==false) {
			newSubscribedEmail = SmmmOfisSubscribedEmail.builder()
					.id(id)
					.email(email)
					.date(selectedMessage.getDate().withNano(0))
					.smmmofis_id(smmmofis_id)
					.build();
			subscribedEmailService.save(newSubscribedEmail);
			return "redirect:/cp/get-in-active-subscribed-emails";
			
		}else if(active==true) {
			newSubscribedEmail = SmmmOfisSubscribedEmail.builder()
					.id(id)
					.email(email)
					.date(selectedMessage.getDate().withNano(0))
					.active(active)
					.smmmofis_id(smmmofis_id)
					.build();
		}
		
		
		subscribedEmailService.save(newSubscribedEmail);
		
		return "redirect:/cp/get-active-subscribed-emails";
		
	}
	
	public void loadRequaredCommenItems(Model model) {
		
		model.addAttribute("countOfUnReadMessages", messageService.countOfUnReadMessages());
		model.addAttribute("listOfUnreadMessages", messageService.getAllUnReadMessages());
		
		model.addAttribute("countOfUnReadNotifications", notificationService.countOfUnReadNotifications());
		model.addAttribute("listOfUnreadNotifications", notificationService.getAllUnReadNotifications());
		
		model.addAttribute("countOfUnReadNotes", noteService.countOfUnReadNotes());
		model.addAttribute("listOfUnReadNotes", noteService.getAllUnreadNotes());
		
	}

}
