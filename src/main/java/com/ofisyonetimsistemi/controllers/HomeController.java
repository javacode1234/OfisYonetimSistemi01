package com.ofisyonetimsistemi.controllers;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.models.SmmmOfisMessage;
import com.ofisyonetimsistemi.services.HomePagePortfolioCompanyService;
import com.ofisyonetimsistemi.services.SmmmOfisBusinesSectorService;
import com.ofisyonetimsistemi.services.SmmmOfisHomePageServicesService;
import com.ofisyonetimsistemi.services.SmmmOfisMessageService;
import com.ofisyonetimsistemi.services.SmmmOfisPricingService;
import com.ofisyonetimsistemi.services.SmmmOfisService;

@Controller
public class HomeController {
	
	@Autowired private SmmmOfisService smmmOfisHomePageService;
	@Autowired private SmmmOfisBusinesSectorService businesSectorService;
	@Autowired private HomePagePortfolioCompanyService companyService;
	@Autowired private SmmmOfisHomePageServicesService homepageServicesServis;
	@Autowired private SmmmOfisPricingService pricingService;
	@Autowired private SmmmOfisMessageService messageService;
		
	@GetMapping("/")
	public String getHomePage(Model model) {		
		
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		
		if(smmmOfis.isPresent()) {
			model.addAttribute("smmmOfisHomePage", smmmOfis.get());
			model.addAttribute("sectors", businesSectorService.getAllSector());
			model.addAttribute("companies", companyService.getAll());
			model.addAttribute("pricingList", pricingService.getAll());
			model.addAttribute("smmmOfisMessage", new SmmmOfisMessage());
		}else if(!smmmOfis.isPresent()){
			model.addAttribute("smmmOfisHomePage", new SmmmOfis());
			model.addAttribute("smmmOfisMessage", new SmmmOfisMessage());
		}		
		
		return "index";		
		
	}
	
	@GetMapping("/portfolio-details")
	public String portfolioDetails(@RequestParam("id")Integer id, Model model ) { 
		
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		
		if(smmmOfis.isPresent()) {
			model.addAttribute("smmmOfisHomePage", smmmOfis.get());
			model.addAttribute("company", companyService.getById(id).get());
		}else if(!smmmOfis.isPresent()){
			model.addAttribute("smmmOfisHomePage", new SmmmOfis());
		}		
		return "portfolio-details";
	}
		
	@GetMapping("/service-details")
	public String serviceDetails(@RequestParam("id")Integer id, Model model) {
		
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		
		if(smmmOfis.isPresent()) {
			model.addAttribute("smmmOfisHomePage", smmmOfis.get());
			model.addAttribute("homepageService", homepageServicesServis.getServiceById(id).get());
		}else if(!smmmOfis.isPresent()){
			model.addAttribute("smmmOfisHomePage", new SmmmOfis());
		}		
		return "service-details";
	}
	
	@PostMapping("/send-message")
	public String sendMessage(
									@RequestParam("name")String name,
									@RequestParam("email")String email,
									@RequestParam("subject")String subject,
									@RequestParam("message")String message,
									Model model
							  ) {
		
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		LocalDateTime localDateTime =LocalDateTime.now();
		SmmmOfisMessage newMessage = SmmmOfisMessage.builder()
				.name(name)
				.email(email)
				.subject(subject)
				.message(message)
				.date(localDateTime)
				.smmmofis_id(smmmOfis.get().getId())
				.build();
		
		messageService.saveMessage(newMessage);	
		
		return "redirect:/#contact";
	}
	
}
