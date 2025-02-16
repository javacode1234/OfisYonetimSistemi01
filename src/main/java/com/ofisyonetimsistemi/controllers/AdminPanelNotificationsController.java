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
import com.ofisyonetimsistemi.models.SmmmOfisNotification;
import com.ofisyonetimsistemi.security.model.MyUser;
import com.ofisyonetimsistemi.security.model.MyUserDetails;
import com.ofisyonetimsistemi.security.service.MyUserService;
import com.ofisyonetimsistemi.services.SmmmOfisMessageService;
import com.ofisyonetimsistemi.services.SmmmOfisNotificationService;
import com.ofisyonetimsistemi.services.SmmmOfisService;


@Controller
@RequestMapping("/cp/")
public class AdminPanelNotificationsController {
	
	@Autowired private SmmmOfisService smmmOfisService;
	@Autowired private MyUserService myUserService;
	@Autowired private SmmmOfisMessageService messageService;
	@Autowired private SmmmOfisNotificationService notificationService;
		
	@GetMapping("/get-un-read-notifications")
	public String getUnReadNotifications(@AuthenticationPrincipal MyUserDetails loggedUser, Model model) {
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		model.addAttribute("smmmOfis", smmmOfis.get());
		model.addAttribute("currentUser", currentUser);
		
		model.addAttribute("allNotifications", notificationService.getAllUnReadNotifications());
		
		loadRequaredCommenItems(model);
		
		return "adminpanel/notifications";
	}
	
	@GetMapping("/get-read-notifications")
	public String getReadNotifications(@AuthenticationPrincipal MyUserDetails loggedUser, Model model) {
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		model.addAttribute("smmmOfis", smmmOfis.get());
		model.addAttribute("currentUser", currentUser);
		
		model.addAttribute("allNotifications", notificationService.getAllReadNotifications());
		
		loadRequaredCommenItems(model);
		
		return "adminpanel/notifications";
	}
	
	@GetMapping("/get-all-notifications")
	public String getAllNotifications(@AuthenticationPrincipal MyUserDetails loggedUser, Model model) {
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		model.addAttribute("smmmOfis", smmmOfis.get());
		model.addAttribute("currentUser", currentUser);
		
		model.addAttribute("allNotifications", notificationService.getAll());
		
		loadRequaredCommenItems(model);
		
		return "adminpanel/notifications";
	}
	
	@GetMapping("/get-notification/{id}")
	@ResponseBody
	public Optional<SmmmOfisNotification> viewSelectedNotification(@PathVariable("id") Integer id) {
		return notificationService.getById(id);
	}
		
	@RequestMapping(value="/delete-notification", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delById(@RequestParam("id") Integer id) {
		notificationService.deleteById(id);
		return "redirect:/cp/get-un-read-notifications";
	}
	
	@PostMapping("/add-notification")
	public String addNotification(
			                        @RequestParam("header")String header,
			                        @RequestParam("notification")String notification,
			                        @RequestParam("information")String information
			                        
									) {
		SmmmOfis smmmOfis = smmmOfisService.getFirstSmmmOfis().get();
		
		SmmmOfisNotification newNotification = SmmmOfisNotification.builder()
				.header(header)
				.notification(notification)
				.information(information)
				.dateofpublish(LocalDateTime.now().withNano(0))
				.smmmofis_id(smmmOfis.getId())
				.build();
		
		notificationService.saveNotification(newNotification);
		return "redirect:/cp/get-un-read-notifications";
	}
	
	@PostMapping("/update-notification")
	public String updateNotification(
									@RequestParam("id")Integer id,
			                        @RequestParam("header")String header,
			                        @RequestParam("notification")String notification,
			                        @RequestParam("information")String information,
			                        @RequestParam(value="okundu", required = false)boolean okundu,
			                        @RequestParam("smmmofis_id")Integer smmmofis_id
			                        
									) {
		SmmmOfisNotification selectedNotification = notificationService.getById(id).get();
		SmmmOfisNotification newNotification = new SmmmOfisNotification();
		if(okundu==false) {
			newNotification = SmmmOfisNotification.builder()
					.id(id)
					.header(header)
					.notification(notification)
					.information(information)
					.dateofpublish(selectedNotification.getDateofpublish().withNano(0))
					.okundu(okundu)
					.smmmofis_id(smmmofis_id)
					.build();
		}else if(okundu==true) {
			newNotification = SmmmOfisNotification.builder()
					.id(id)
					.header(header)
					.notification(notification)
					.information(information)
					.dateofpublish(selectedNotification.getDateofpublish().withNano(0))
					.dateofread(LocalDateTime.now().withNano(0))
					.okundu(okundu)
					.smmmofis_id(smmmofis_id)
					.build();
		}
		
		
		notificationService.saveNotification(newNotification);
		
		return "redirect:/cp/get-un-read-notifications";
		
	}
	
	public void loadRequaredCommenItems(Model model) {
			
			model.addAttribute("countOfUnReadMessages", messageService.countOfUnReadMessages(false));
			model.addAttribute("listOfUnreadMessages", messageService.getAllUnReadMessages());
			
			model.addAttribute("countOfUnReadNotifications", notificationService.countOfUnReadNotifications(false));
			model.addAttribute("listOfUnreadNotifications", notificationService.getAllUnReadNotifications());
			
	}

}
