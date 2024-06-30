package com.ofisyonetimsistemi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/")
public class SmmmHomePageSettingsContoller {
	
	@GetMapping("/home-page-settings")
	public String homePageSettings(Model model) {
		model.addAttribute("dashboardtitle","SMMM Muammer UZUN");
		model.addAttribute("smmmisim","Muammer UZUN");
		model.addAttribute("fullusername","Muammer UZUN");
		model.addAttribute("gorev","SMMM");
		return "adminpanel/home-page-settings";
	}

}
