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
		model.addAttribute("dashboardtitle","SMMM Muammer UZUN");
		model.addAttribute("smmmisim","Muammer UZUN");
		model.addAttribute("fullusername","Muammer UZUN");
		model.addAttribute("gorev","SMMM");
		return "adminpanel/index";
	}
	
	@GetMapping("/components-alerts")
	public String getAlerts(Model model) {
		model.addAttribute("dashboardtitle","SMMM Muammer UZUN");
		model.addAttribute("smmmisim","Muammer UZUN");
		model.addAttribute("fullusername","Muammer UZUN");
		model.addAttribute("gorev","SMMM");
		return "adminpanel/components-alerts";
	}
	
	@GetMapping("/user-profile")
	public String getUserProfile(Model model) {
		model.addAttribute("dashboardtitle","SMMM Muammer UZUN");
		model.addAttribute("smmmisim","Muammer UZUN");
		model.addAttribute("fullusername","Muammer UZUN");
		model.addAttribute("gorev","SMMM");
		model.addAttribute("tabpane","view");
		return "adminpanel/users-profile";
	}
	
	@GetMapping("/user-profile-view")
	public String getUserProfileWiew(Model model){
		model.addAttribute("dashboardtitle","SMMM Muammer UZUN");
		model.addAttribute("smmmisim","Muammer UZUN");
		model.addAttribute("fullusername","Muammer UZUN");
		model.addAttribute("gorev","SMMM");
		model.addAttribute("tabpane","view");
		return "adminpanel/users-profile";
	}
	
	@GetMapping("/user-profile-edit")
	public String getUserProfileEdit(Model model){
		model.addAttribute("dashboardtitle","SMMM Muammer UZUN");
		model.addAttribute("smmmisim","Muammer UZUN");
		model.addAttribute("fullusername","Muammer UZUN");
		model.addAttribute("gorev","SMMM");
		model.addAttribute("tabpane","edit");
		return "adminpanel/users-profile";
	}
	
	@GetMapping("/user-profile-settings")
	public String getUserProfileSettings(Model model){
		model.addAttribute("dashboardtitle","SMMM Muammer UZUN");
		model.addAttribute("smmmisim","Muammer UZUN");
		model.addAttribute("fullusername","Muammer UZUN");
		model.addAttribute("gorev","SMMM");
		model.addAttribute("tabpane","settings");
		return "adminpanel/users-profile";
	}
	
	@GetMapping("/user-profile-changepwd")
	public String getUserProfileChangepwd(Model model){
		model.addAttribute("dashboardtitle","SMMM Muammer UZUN");
		model.addAttribute("smmmisim","Muammer UZUN");
		model.addAttribute("fullusername","Muammer UZUN");
		model.addAttribute("gorev","SMMM");
		model.addAttribute("tabpane","changepwd");
		return "adminpanel/users-profile";
	}

}
