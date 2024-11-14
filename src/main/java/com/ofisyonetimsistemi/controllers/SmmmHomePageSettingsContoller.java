package com.ofisyonetimsistemi.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
			  model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan()+" "+smmmOfis.get().getFullName());
			  model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			  model.addAttribute("fullusername", smmmOfis.get().getUserName());
			  model.addAttribute("gorev", smmmOfis.get().getUnvan());
			  
			  model.addAttribute("updateBtnActive", true);
			  model.addAttribute("smmmOfisId", smmmOfis.get().getId());
			  model.addAttribute("smmmOfis", smmmOfis.get());
			  
			  return "adminpanel/homepage-settings";
			  
		   }else{
		  
			  model.addAttribute("dashboardtitle", "SMMM Muammer UZUN");
			  model.addAttribute("smmmisim", "Muammer UZUN");
			  model.addAttribute("fullusername", "Muammer UZUN");
			  model.addAttribute("gorev", "SMMM");
			  model.addAttribute("updateBtnActive", false);
			  model.addAttribute("smmmOfis", new SmmmOfis());
		  }
		  
		  return "adminpanel/homepage-settings";			
		
	}

	@PostMapping("/save-smmm-homepage-settings-personal-info")
	public String saveHomePageSettingsPersonalInfo(
										
										@RequestParam("logo") MultipartFile file,
										@RequestParam("unvan") String unvan,
										@RequestParam("firstName")String firstName,
										@RequestParam("lastName")String lastName,
										@RequestParam("fullName")String fullName,
										@RequestParam("email")String email,
										@RequestParam("userName")String userName,
										@RequestParam("password")String password
										
										) throws IOException {	
		
		
		smmmOfisService.saveHomePageSettingsPersonalInfo( file, unvan, firstName, lastName,fullName, email, userName, password);
		return "redirect:/api/v1/smmm-homepage-settings";
	}
	
	@PostMapping("/update-smmm-homepage-settings-personal-info/{id}")
	public String updateHomePageSettingsPersonalInfo( 
														@PathVariable("id")Integer id,
														@RequestParam("logo") MultipartFile file,
														@RequestParam("unvan") String unvan,
														@RequestParam("firstName")String firstName,
														@RequestParam("lastName")String lastName,
														@RequestParam("fullName")String fullName,
														@RequestParam("email")String email,
														@RequestParam("userName")String userName,
														@RequestParam("password")String password
														
													) throws IOException{
		
		smmmOfisService.updateHomePageSettingsPersonalInfo(id, file, unvan, firstName, lastName, fullName, email, userName, password);
		
		return "redirect:/api/v1/smmm-homepage-settings";
		
	}	
	
	@PostMapping("/save-smmm-homepage-settings-homepage-info")
	public String saveHomePageSettingsHomeInfo(
												@RequestParam("mainpageTitle")String mainpageTitle,
												@RequestParam("headerTitle")String headerTitle,
												@RequestParam("heroTitle")String heroTitle,
												@RequestParam("heroParagraf")String heroParagraf,
												@RequestParam("videoLink")String videoLink
												
												) {
		
		smmmOfisService.saveHomePageSettingsHomeInfo( mainpageTitle, headerTitle, heroTitle, heroParagraf, videoLink);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}
	
	@PostMapping("/update-smmm-homepage-settings-homepage-info/{id}")
	public String updateHomePageSettingsHomeInfo(
												@PathVariable("id")Integer id,
												@RequestParam("mainpageTitle")String mainpageTitle,
												@RequestParam("headerTitle")String headerTitle,
												@RequestParam("heroTitle")String heroTitle,
												@RequestParam("heroParagraf")String heroParagraf,
												@RequestParam("videoLink")String videoLink
												
												) {
		
		smmmOfisService.updateHomePageSettingsHomeInfo(id, mainpageTitle, headerTitle, heroTitle, heroParagraf, videoLink);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}
	
	@PostMapping("/save-smmm-homepage-settings-aboutus-info")
	public String saveHomePageSettingsAboutUsInfo(
												@RequestParam("aboutusmainheader")String aboutusmainheader,
												@RequestParam("aboutUsColumnOneHeader")String aboutUsColumnOneHeader,
												@RequestParam("aboutUsColumnTwoParagraf")String aboutUsColumnTwoParagraf
																								
												) {
		
		smmmOfisService.saveHomePageSettingsAboutUsInfo( aboutusmainheader, aboutUsColumnOneHeader, aboutUsColumnTwoParagraf);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}
	
	@PostMapping("/update-smmm-homepage-settings-aboutus-info/{id}")
	public String updateHomePageSettingsAboutUsInfo(
												@PathVariable("id")Integer id,
												@RequestParam("aboutusmainheader")String aboutusmainheader,
												@RequestParam("aboutUsColumnOneHeader")String aboutUsColumnOneHeader,
												@RequestParam("aboutUsColumnTwoParagraf")String aboutUsColumnTwoParagraf
																								
												) {
		
		smmmOfisService.updateHomePageSettingsAboutUsInfo(id, aboutusmainheader, aboutUsColumnOneHeader, aboutUsColumnTwoParagraf);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}
		
	@PostMapping("/save-smmm-homepage-settings-whyus-info")
	public String saveHomePageSettingsWhyUsInfo(
												@RequestParam("whyusMainHeader")String whyusMainHeader,
												@RequestParam("whyusHeader")String whyusHeader,
												@RequestParam("whyusParagraf")String whyusParagraf
																								
												) {
		
		smmmOfisService.saveHomePageSettingsWhyUsInfo( whyusMainHeader, whyusHeader, whyusParagraf);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}

	@PostMapping("/update-smmm-homepage-settings-whyus-info/{id}")
	public String updateHomePageSettingsWhyUsInfo(
												@PathVariable("id")Integer id,
												@RequestParam("whyusMainHeader")String whyusMainHeader,
												@RequestParam("whyusHeader")String whyusHeader,
												@RequestParam("whyusParagraf")String whyusParagraf
																								
												) {
		
		smmmOfisService.updateHomePageSettingsWhyUsInfo(id, whyusMainHeader, whyusHeader, whyusParagraf);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}
	
	@PostMapping("/save-smmm-homepage-settings-skillspage-info")
	public String saveHomePageSettingsSkillsPageInfo(
												@RequestParam("skillsMainHeader")String skillsMainHeader,
												@RequestParam("skillsHeader")String skillsHeader,
												@RequestParam("skillsParagraf")String skillsParagraf
																								
												) {
		
		smmmOfisService.saveHomePageSettingsskillsPageInfo( skillsMainHeader, skillsHeader, skillsParagraf);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}

	@PostMapping("/update-smmm-homepage-settings-skillspage-info/{id}")
	public String updateHomePageSettingsSkillsPageInfo(
												@PathVariable("id")Integer id,
												@RequestParam("skillsMainHeader")String skillsMainHeader,
												@RequestParam("skillsHeader")String skillsHeader,
												@RequestParam("skillsParagraf")String skillsParagraf
																								
												) {
		
		smmmOfisService.updateHomePageSettingsSkillsPageInfo(id, skillsMainHeader, skillsHeader, skillsParagraf);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}


}
