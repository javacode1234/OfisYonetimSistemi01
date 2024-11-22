package com.ofisyonetimsistemi.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.services.SmmmOfisService;



@Controller
public class HomeController {
	
	@Autowired private SmmmOfisService smmmOfisHomePageService;
		
	@GetMapping("/")
	public String getHomePage(Model model) {		
		
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		
		if(smmmOfis.isPresent()) {
			model.addAttribute("smmmOfisHomePage", smmmOfis.get());
		}else if(!smmmOfis.isPresent()){
			model.addAttribute("smmmOfisHomePage", new SmmmOfis());
		}		
		
		return "index";		
		
	}
	
	@GetMapping("/portfolio-details")
	public String portfolioDetails(Model model) {
		
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		
		if(smmmOfis.isPresent()) {
			model.addAttribute("smmmOfisHomePage", smmmOfis.get());
		}else if(!smmmOfis.isPresent()){
			model.addAttribute("smmmOfisHomePage", new SmmmOfis());
		}		
		return "portfolio-details";
	}
	
	@GetMapping("/service-details")
	public String serviceDetails(Model model) {
		
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		
		if(smmmOfis.isPresent()) {
			model.addAttribute("smmmOfisHomePage", smmmOfis.get());
		}else if(!smmmOfis.isPresent()){
			model.addAttribute("smmmOfisHomePage", new SmmmOfis());
		}		
		return "service-details";
	}
	
	
}
