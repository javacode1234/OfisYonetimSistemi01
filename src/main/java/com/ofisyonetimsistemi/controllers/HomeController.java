package com.ofisyonetimsistemi.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.services.HomePagePortfolioCompanyService;
import com.ofisyonetimsistemi.services.SmmmOfisBusinesSectorService;
import com.ofisyonetimsistemi.services.SmmmOfisHomePageServicesService;
import com.ofisyonetimsistemi.services.SmmmOfisPricingService;
import com.ofisyonetimsistemi.services.SmmmOfisService;



@Controller
public class HomeController {
	
	@Autowired private SmmmOfisService smmmOfisHomePageService;
	@Autowired private SmmmOfisBusinesSectorService businesSectorService;
	@Autowired private HomePagePortfolioCompanyService companyService;
	@Autowired private SmmmOfisHomePageServicesService homepageServicesServis;
	@Autowired private SmmmOfisPricingService pricingService;
	//@Autowired private SmmmOfisAskedQuestionsService hpFaqService;
		
	@GetMapping("/")
	public String getHomePage(Model model) {		
		
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		
		if(smmmOfis.isPresent()) {
			model.addAttribute("smmmOfisHomePage", smmmOfis.get());
			model.addAttribute("sectors", businesSectorService.getAllSector());
			model.addAttribute("companies", companyService.getAll());
			model.addAttribute("pricingList", pricingService.getAll());
		}else if(!smmmOfis.isPresent()){
			model.addAttribute("smmmOfisHomePage", new SmmmOfis());
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
	
	
}
