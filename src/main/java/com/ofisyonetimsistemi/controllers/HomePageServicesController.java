package com.ofisyonetimsistemi.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.models.SmmmOfisServices;
import com.ofisyonetimsistemi.security.model.MyUser;
import com.ofisyonetimsistemi.security.service.MyUserService;
import com.ofisyonetimsistemi.services.BoxIconsService;
import com.ofisyonetimsistemi.services.SmmmOfisHomePageServicesService;
import com.ofisyonetimsistemi.services.SmmmOfisMessageService;
import com.ofisyonetimsistemi.services.SmmmOfisNotificationService;
import com.ofisyonetimsistemi.services.SmmmOfisService;

@Controller
@RequestMapping("/cp/")
public class HomePageServicesController {

	@Autowired private SmmmOfisService smmmOfisHomePageService;
	@Autowired private SmmmOfisHomePageServicesService homePageServices;
	@Autowired private BoxIconsService boxiconService;
	@Autowired private SmmmOfisMessageService messageService;
	@Autowired private SmmmOfisNotificationService notificationService;
	
	@Autowired private MyUserService myUserService;
	
	
	@GetMapping("/smmm-homepage-services-settings")
	public String getMethodName(Model model, Principal principal) {
		MyUser myUser = myUserService.getMyUserByUsername(principal.getName());
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		if (!smmmOfis.isEmpty()) {
			model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan() + " " + smmmOfis.get().getFullName());
			model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			model.addAttribute("fullusername", smmmOfis.get().getUserName());
			model.addAttribute("gorev", smmmOfis.get().getUnvan());

			model.addAttribute("smmmOfis", smmmOfis.get());
			model.addAttribute("homePageService", new SmmmOfisServices());
			model.addAttribute("homepageServices", homePageServices.getAllServices());
			model.addAttribute("boxicons", boxiconService.getAllBoxIcons());
			
			model.addAttribute("currentUser", myUser);
			model.addAttribute("messageCount", messageService.countOfRecord());
			model.addAttribute("countOfUnReadMessages", messageService.countOfUnReadMessages(false));
			
			model.addAttribute("notificationCount", notificationService.countOfRecord());
			model.addAttribute("countOfUnReadNotifications", notificationService.countOfUnReadNotifications(false));
			model.addAttribute("allNotifications", notificationService.getAllNotifications());
			
			model.addAttribute("listOfUnreadMessages", messageService.getAllUnReadMessages());
			model.addAttribute("listOfUnreadNotifications", notificationService.getAllUnReadNotifications());

			return "adminpanel/homepagesettings/homepage-services-settings";

		} else {

			model.addAttribute("dashboardtitle", "SMMM Muammer UZUN");
			model.addAttribute("smmmisim", "Muammer UZUN");
			model.addAttribute("fullusername", "Muammer UZUN");
			model.addAttribute("gorev", "SMMM");
			model.addAttribute("smmmOfis", new SmmmOfis());
			
			model.addAttribute("currentUser", myUser);
			model.addAttribute("messageCount", messageService.countOfRecord());
			model.addAttribute("countOfUnReadMessages", messageService.countOfUnReadMessages(false));
			
			model.addAttribute("notificationCount", notificationService.countOfRecord());
			model.addAttribute("countOfUnReadNotifications", notificationService.countOfUnReadNotifications(false));
			model.addAttribute("allNotifications", notificationService.getAllNotifications());
			
			model.addAttribute("listOfUnreadMessages", messageService.getAllUnReadMessages());
			model.addAttribute("listOfUnreadNotifications", notificationService.getAllUnReadNotifications());
		}

		return "adminpanel/homepagesettings/homepage-services-settings";
	}

	@GetMapping("/get-homepage-service/{id}")
	@ResponseBody
	public Optional<SmmmOfisServices> getServiceById(@PathVariable("id") Integer id) {
		return homePageServices.getServiceById(id);
	}

	@PostMapping("/save-homepage-service")
	public String saveWhyUs(@ModelAttribute("homePageService") SmmmOfisServices homepageService) {

		homepageService.setSmmmofis_id(smmmOfisHomePageService.getFirstSmmmOfis().get().getId());
		homePageServices.saveService(homepageService);

		return "redirect:/cp/smmm-homepage-services-settings";
	}

	@PostMapping("/update-homepage-services")
	public String updateWhyUs(@ModelAttribute("homePageService") SmmmOfisServices homepageService) {

		homepageService.setSmmmofis_id(smmmOfisHomePageService.getFirstSmmmOfis().get().getId());
		homePageServices.saveService(homepageService);

		return "redirect:/cp/smmm-homepage-services-settings";
	}

	@RequestMapping(value = "/delete-homepage-services/{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
	public String delWhyUsById(@PathVariable("id") Integer id) {
		homePageServices.deleteServiceById(id);
		return "redirect:/cp/smmm-homepage-services-settings";
	}

}
