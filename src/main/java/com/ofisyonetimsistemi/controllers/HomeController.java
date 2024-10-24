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
	
	@Autowired SmmmOfisService smmmOfisHomePageService;
		
	@GetMapping("/")
	public String getHomePage(Model model) {		
		
		try {			
			Optional<SmmmOfis> homePage = smmmOfisHomePageService.getFirstSmmmOfis();
			model.addAttribute("smmmOfisHomePage", homePage.get());
				
			
		} catch (Exception e) {
			System.out.println("Eception : " + e.getMessage());			
		}		
		
		return "index";		
		
	}
	
	@GetMapping("/portfolio-details")
	public String portfolioDetails(Model model) {
		Optional<SmmmOfis> smmmOfisHomePage = smmmOfisHomePageService.getFirstSmmmOfis();
		
		if( smmmOfisHomePage.isPresent() ) {
			model.addAttribute("smmmOfisHomePage", smmmOfisHomePage);
			return "index";			
		}
		
		SmmmOfis smmmOfis = new SmmmOfis();
		
		smmmOfis.setMainpageTitle("SMMM Muammer UZUN");
		smmmOfis.setHeaderTitle("MMOYS");
		
		model.addAttribute("smmmOfisHomePage", smmmOfis);
		return "portfolio-details";
	}
	
	@GetMapping("/service-details")
	public String serviceDetails(Model model) {
		Optional<SmmmOfis> smmmOfisHomePage = smmmOfisHomePageService.getFirstSmmmOfis();
		
		if( smmmOfisHomePage.isPresent() ) {
			model.addAttribute("smmmOfisHomePage", smmmOfisHomePage);
			return "index";			
		}
		
		SmmmOfis smmmOfis = new SmmmOfis();
		
		smmmOfis.setMainpageTitle("SMMM Muammer UZUN");
		smmmOfis.setHeaderTitle("MMOYS");
		
		model.addAttribute("smmmOfisHomePage", smmmOfis);
		return "service-details";
	}
	
	
}
