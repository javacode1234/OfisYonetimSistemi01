package com.ofisyonetimsistemi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/")
public class AdminPanelController {
	
	@GetMapping("/admin-panel")
	public String getAdminPanel(Model model) {
		model.addAttribute("smmmisim","Muammer UZUN");
		return "adminpanel/index";
	}
	
	@GetMapping("/components-alerts")
	public String getAlerts(Model model) {
		
		return "adminpanel/components-alerts";
	}

}
