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

	private static Integer id = 1;

	@GetMapping("/smmm-homepage-settings")
	public String getHomePageSettingsPage(Model model) {
		
		  model.addAttribute("dashboardtitle", "SMMM Muammer UZUN");
		  model.addAttribute("smmmisim", "Muammer UZUN");
		  model.addAttribute("fullusername", "Muammer UZUN");
		  model.addAttribute("gorev", "SMMM");
		  
		  Optional<SmmmOfis> smmmOfis = smmmOfisService.getById(id);
		  
		  if(!smmmOfis.isEmpty()) {
			  model.addAttribute("smmmOfis", smmmOfis);
			  return "adminpanel/homepage-settings";
		  }	 
		  
		
			model.addAttribute("smmmOfis", new SmmmOfis());
			return "adminpanel/homepage-settings";
		
	}

	@PostMapping("/save-smmm-homepage-settings")
	public String saveHomePageSettings(@ModelAttribute("smmmOfis") SmmmOfis smmmOfis) {
		smmmOfisService.saveHomePageSettings(smmmOfis);

		return "redirect:/api/v1/smmm-homepage-settings";
	}

}
