package com.ofisyonetimsistemi.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.services.SmmmOfisService;

@Controller
@RequestMapping("/api/v1/")
public class AdminPanelController {
	
	@Autowired
	private SmmmOfisService smmmOfisService;
	
	
	
	@GetMapping("/admin-panel")
	public String getAdminPanel(Model model) {
		 Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		  if(!smmmOfis.isEmpty()) {
			  model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan()+" "+smmmOfis.get().getFullName());
			  model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			  model.addAttribute("fullusername", smmmOfis.get().getUserName());
			  model.addAttribute("gorev", smmmOfis.get().getUnvan());
			  
			  model.addAttribute("updateBtnActive", true);
			  model.addAttribute("smmmOfisId", smmmOfis.get().getId());
			  model.addAttribute("smmmOfis", smmmOfis.get());
			  
			  
			  return "adminpanel/index";
			  
		   }else {
		  
			  model.addAttribute("dashboardtitle", "SMMM Ofis Yönetim Sistemi");
			  model.addAttribute("smmmisim", "Smmm İsim");
			  model.addAttribute("fullusername", "Smmm AdSoyad");
			  model.addAttribute("gorev", "SMMM");
			  model.addAttribute("updateBtnActive", false);
			  model.addAttribute("smmmOfis", new SmmmOfis());
		   }
		  
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
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		  if(smmmOfis.isPresent()) {
			  model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan()+" "+smmmOfis.get().getFullName());
			  model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			  model.addAttribute("fullusername", smmmOfis.get().getUserName());
			  model.addAttribute("gorev", smmmOfis.get().getUnvan());
			  
			  model.addAttribute("tabpane","view");
			  model.addAttribute("smmmOfis", smmmOfis.get());
			  
			  return "adminpanel/users-profile";
			  
		   }else {
				model.addAttribute("dashboardtitle","SMMM Muammer UZUN");
				model.addAttribute("smmmisim","Muammer UZUN");
				model.addAttribute("fullusername","Muammer UZUN");
				model.addAttribute("gorev","SMMM");
				model.addAttribute("tabpane","view");
				
				model.addAttribute("smmmOfis", new SmmmOfis());
		   }
		return "adminpanel/users-profile";
	}
	
	@GetMapping("/user-profile-view")
	public String getUserProfileWiew(Model model){
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		  if(smmmOfis.isPresent()) {
			  model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan()+" "+smmmOfis.get().getFullName());
			  model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			  model.addAttribute("fullusername", smmmOfis.get().getUserName());
			  model.addAttribute("gorev", smmmOfis.get().getUnvan());
			  
			  model.addAttribute("tabpane","view");
			  model.addAttribute("smmmOfis", smmmOfis.get());
			  
			  return "adminpanel/users-profile";
			  
		   }else {
				model.addAttribute("dashboardtitle","SMMM Muammer UZUN");
				model.addAttribute("smmmisim","Muammer UZUN");
				model.addAttribute("fullusername","Muammer UZUN");
				model.addAttribute("gorev","SMMM");
				model.addAttribute("tabpane","view");
				
				model.addAttribute("smmmOfis", new SmmmOfis());
		   }
		return "adminpanel/users-profile";
	}
	
	@GetMapping("/user-profile-edit")
	public String getUserProfileEdit(Model model){
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		  if(smmmOfis.isPresent()) {
			  model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan()+" "+smmmOfis.get().getFullName());
			  model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			  model.addAttribute("fullusername", smmmOfis.get().getUserName());
			  model.addAttribute("gorev", smmmOfis.get().getUnvan());
			  
			  model.addAttribute("tabpane","edit");
			  model.addAttribute("smmmOfis", smmmOfis.get());
			  
			  return "adminpanel/users-profile";
			  
		   }else {
				model.addAttribute("dashboardtitle","SMMM Muammer UZUN");
				model.addAttribute("smmmisim","Muammer UZUN");
				model.addAttribute("fullusername","Muammer UZUN");
				model.addAttribute("gorev","SMMM");
				model.addAttribute("tabpane","edit");
				
				model.addAttribute("smmmOfis", new SmmmOfis());
		   }
		return "adminpanel/users-profile";
	}
	
	@GetMapping("/user-profile-settings")
	public String getUserProfileSettings(Model model){
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		  if(smmmOfis.isPresent()) {
			  model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan()+" "+smmmOfis.get().getFullName());
			  model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			  model.addAttribute("fullusername", smmmOfis.get().getUserName());
			  model.addAttribute("gorev", smmmOfis.get().getUnvan());
			  
			  model.addAttribute("tabpane","settings");
			  model.addAttribute("smmmOfis", smmmOfis.get());
			  
			  return "adminpanel/users-profile";
			  
		   }else {
				model.addAttribute("dashboardtitle","SMMM Muammer UZUN");
				model.addAttribute("smmmisim","Muammer UZUN");
				model.addAttribute("fullusername","Muammer UZUN");
				model.addAttribute("gorev","SMMM");
				model.addAttribute("tabpane","settings");
				
				model.addAttribute("smmmOfis", new SmmmOfis());
		   }
		return "adminpanel/users-profile";
	}
	
	@GetMapping("/user-profile-changepwd")
	public String getUserProfileChangepwd(Model model){
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		  if(smmmOfis.isPresent()) {
			  model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan()+" "+smmmOfis.get().getFullName());
			  model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			  model.addAttribute("fullusername", smmmOfis.get().getUserName());
			  model.addAttribute("gorev", smmmOfis.get().getUnvan());
			  
			  model.addAttribute("tabpane","changepwd");
			  model.addAttribute("smmmOfis", smmmOfis.get());
			  
			  return "adminpanel/users-profile";
			  
		   }else {
				model.addAttribute("dashboardtitle","SMMM Muammer UZUN");
				model.addAttribute("smmmisim","Muammer UZUN");
				model.addAttribute("fullusername","Muammer UZUN");
				model.addAttribute("gorev","SMMM");
				model.addAttribute("tabpane","changepwd");
				
				model.addAttribute("smmmOfis", new SmmmOfis());
		   }
		return "adminpanel/users-profile";
	}

}
