package com.ofisyonetimsistemi.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.services.SmmmOfisService;



@Controller
@RequestMapping("/api/v1/")
public class SmmmHomePageSettingsContoller {

	@Autowired
	private SmmmOfisService smmmOfisService;

	@GetMapping("/smmm-homepage-settings")
	public String getHomePageSettingsPage( Model model) {
		  
		  Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		  
		  if(!smmmOfis.isEmpty()) {
			  model.addAttribute("updateBtnActive", true);
			  model.addAttribute("smmmOfisId", smmmOfis.get().getId());
			  model.addAttribute("smmmOfis", smmmOfis);
			  return "adminpanel/homepage-settings";
		  }	 
		  
		  model.addAttribute("dashboardtitle", "SMMM Muammer UZUN");
		  model.addAttribute("smmmisim", "Muammer UZUN");
		  model.addAttribute("fullusername", "Muammer UZUN");
		  model.addAttribute("gorev", "SMMM");
		  model.addAttribute("updateBtnActive", false);
		  model.addAttribute("smmmOfis", new SmmmOfis());
		  return "adminpanel/homepage-settings";
			
		
	}

	@PostMapping("/save-smmm-homepage-settings")
	public String saveHomePageSettings(@ModelAttribute SmmmOfis smmmOfis) {
		smmmOfisService.saveHomePageSettings(smmmOfis);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}
	
	@PostMapping("/update-smmm-homepage-settings")
	public String updateSmmmHomePageSettings(@ModelAttribute SmmmOfis smmmOfis, Model model) {
		smmmOfisService.saveHomePageSettings(smmmOfis);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}
	
//	@PutMapping("/update-smmm-homepage-settings/{id}")
//	public String updateSmmmHomePageSettings(@PathVariable("id") Integer id, 
//											 @ModelAttribute("smmmOfis") SmmmOfis smmmOfis, Model model) {
//		smmmOfisService.updateSmmmHomePageSettingsById(id);
//		return "redirect:/api/v1/smmm-homepage-settings";
//	}
	
	

}
