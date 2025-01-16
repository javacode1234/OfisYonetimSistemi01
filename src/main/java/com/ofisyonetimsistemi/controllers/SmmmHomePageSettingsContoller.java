package com.ofisyonetimsistemi.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.security.model.MyUser;
import com.ofisyonetimsistemi.security.service.MyUserService;
import com.ofisyonetimsistemi.services.SmmmOfisService;



@Controller
@RequestMapping("/api/v1/")
public class SmmmHomePageSettingsContoller {

	@Autowired
	private SmmmOfisService smmmOfisService;
	
	@Autowired 
	private MyUserService myUserService;
	
	private static MyUser myUser;

	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/smmm-homepage-settings")
	public String getHomePageSettingsPage( Model model, Principal principal) {
		  myUser = myUserService.getMyUserByUsername(principal.getName());
		  Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		  
		  if(!smmmOfis.isEmpty()) {
			  model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan()+" "+smmmOfis.get().getFullName());
			  model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			  model.addAttribute("fullusername", smmmOfis.get().getUserName());
			  model.addAttribute("gorev", smmmOfis.get().getUnvan());
			  
			  model.addAttribute("updateBtnActive", true);
			  model.addAttribute("smmmOfisId", smmmOfis.get().getId());
			  model.addAttribute("smmmOfis", smmmOfis.get());
			  
			  model.addAttribute("currentUser", myUser);
			  
			  return "adminpanel/homepagesettings/homepage-settings";
			  
		   }else{
		  
			  model.addAttribute("dashboardtitle", "SMMM Muammer UZUN");
			  model.addAttribute("smmmisim", "Muammer UZUN");
			  model.addAttribute("fullusername", "Muammer UZUN");
			  model.addAttribute("gorev", "SMMM");
			  model.addAttribute("updateBtnActive", false);
			  model.addAttribute("smmmOfis", new SmmmOfis());
			  
			  model.addAttribute("currentUser", myUser);
		  }
		  
		  return "adminpanel/homepagesettings/homepage-settings";			
		
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
	
	@PostMapping("/save-smmm-homepage-settings-services-info")
	public String saveHomePageSettingsServicesPageInfo(
												@RequestParam("serviceMainHeader")String serviceMainHeader,
												@RequestParam("serviceHeader")String serviceHeader																								
												) {
		
		smmmOfisService.saveHomePageSettingServicesPageInfo( serviceMainHeader, serviceHeader);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}

	@PostMapping("/update-smmm-homepage-settings-services-info/{id}")
	public String updateHomePageSettingsServicesPageInfo(
												@PathVariable("id")Integer id,
												@RequestParam("serviceMainHeader")String serviceMainHeader,
												@RequestParam("serviceHeader")String serviceHeader
												) {
		
		smmmOfisService.updateHomePageSettingServicesPageInfo(id, serviceMainHeader, serviceHeader);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}

	@PostMapping("/save-smmm-homepage-settings-mevzuat-info")
	public String saveHomePageSettingsMevzuatPageInfo(
												@RequestParam("callToActionHeader")String callToActionHeader,
												@RequestParam("callToActionText")String callToActionText																								
												) {
		
		smmmOfisService.saveHomePageSettingMevzuatPageInfo( callToActionHeader, callToActionText);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}

	@PostMapping("/update-smmm-homepage-settings-mevzuat-info/{id}")
	public String updateHomePageSettingsMevzuatPageInfo(
												@PathVariable("id")Integer id,
												@RequestParam("callToActionHeader")String callToActionHeader,
												@RequestParam("callToActionText")String callToActionText
												) {
		
		smmmOfisService.updateHomePageSettingMevzuatPageInfo(id, callToActionHeader, callToActionText);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}
	
	@PostMapping("/save-smmm-homepage-settings-portfoy-info")
	public String saveHomePageSettingsPortfoyPageInfo(
												@RequestParam("portfolioHeader")String portfolioHeader,
												@RequestParam("portfolioText")String portfolioText																								
												) {
		
		smmmOfisService.saveHomePageSettingPortfoyPageInfo( portfolioHeader, portfolioText);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}

	@PostMapping("/update-smmm-homepage-settings-portfoy-info/{id}")
	public String updateHomePageSettingsPortfoyPageInfo(
												@PathVariable("id")Integer id,
												@RequestParam("portfolioHeader")String portfolioHeader,
												@RequestParam("portfolioText")String portfolioText
												) {
		
		smmmOfisService.updateHomePageSettingPortfoyPageInfo(id, portfolioHeader, portfolioText);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}
	
	@PostMapping("/save-smmm-homepage-settings-team-info")
	public String saveHomePageSettingsTeamPageInfo(
												@RequestParam("teammainheader")String teammainheader,
												@RequestParam("teamheader")String teamheader																								
												) {
		
		smmmOfisService.saveHomePageSettingTeamPageInfo( teammainheader, teamheader);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}

	@PostMapping("/update-smmm-homepage-settings-team-info/{id}")
	public String updateHomePageSettingsTeamPageInfo(
												@PathVariable("id")Integer id,
												@RequestParam("teammainheader")String teammainheader,
												@RequestParam("teamheader")String teamheader
												) {
		
		smmmOfisService.updateHomePageSettingTeamPageInfo(id, teammainheader, teamheader);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}

	@PostMapping("/save-smmm-homepage-settings-pricing-info")
	public String saveHomePageSettingsPricingPageInfo(
												@RequestParam("pricingMainHeader")String pricingMainHeader,
												@RequestParam("pricingHeader")String pricingHeader																								
												) {
		
		smmmOfisService.saveHomePageSettingPricingPageInfo( pricingMainHeader, pricingHeader);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}

	@PostMapping("/update-smmm-homepage-settings-pricing-info/{id}")
	public String updateHomePageSettingsPricingPageInfo(
												@PathVariable("id")Integer id,
												@RequestParam("pricingMainHeader")String pricingMainHeader,
												@RequestParam("pricingHeader")String pricingHeader
												) {
		
		smmmOfisService.updateHomePageSettingPricingPageInfo(id, pricingMainHeader, pricingHeader);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}
	
	@PostMapping("/save-smmm-homepage-settings-referans-info")
	public String saveHomePageSettingsReferansPageInfo(
												@RequestParam("testimonialsMainHeader")String testimonialsMainHeader,
												@RequestParam("testimonialsHeader")String testimonialsHeader																								
												) {
		
		smmmOfisService.saveHomePageSettingReferansPageInfo( testimonialsMainHeader, testimonialsHeader);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}

	@PostMapping("/update-smmm-homepage-settings-referans-info/{id}")
	public String updateHomePageSettingsReferansPageInfo(
												@PathVariable("id")Integer id,
												@RequestParam("testimonialsMainHeader")String testimonialsMainHeader,
												@RequestParam("testimonialsHeader")String testimonialsHeader
												) {
		
		smmmOfisService.updateHomePageSettingReferansPageInfo(id, testimonialsMainHeader, testimonialsHeader);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}
	
	@PostMapping("/save-smmm-homepage-settings-questions-info")
	public String saveHomePageSettingsQuestionsPageInfo(
												@RequestParam("smmmofisFrequantlyAskedQuestionsMainHeader")String smmmofisFrequantlyAskedQuestionsMainHeader,
												@RequestParam("smmmofisFrequantlyAskedQuestionsHeader")String smmmofisFrequantlyAskedQuestionsHeader																								
												) {
		
		smmmOfisService.saveHomePageSettingQuestionsPageInfo( smmmofisFrequantlyAskedQuestionsMainHeader, smmmofisFrequantlyAskedQuestionsHeader);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}

	@PostMapping("/update-smmm-homepage-settings-questions-info/{id}")
	public String updateHomePageSettingsQuestionsPageInfo(
												@PathVariable("id")Integer id,
												@RequestParam("smmmofisFrequantlyAskedQuestionsMainHeader")String smmmofisFrequantlyAskedQuestionsMainHeader,
												@RequestParam("smmmofisFrequantlyAskedQuestionsHeader")String smmmofisFrequantlyAskedQuestionsHeader
												) {
		
		smmmOfisService.updateHomePageSettingQuestionsPageInfo(id, smmmofisFrequantlyAskedQuestionsMainHeader, smmmofisFrequantlyAskedQuestionsHeader);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}

	@PostMapping("/save-smmm-homepage-settings-contact-info")
	public String saveHomePageSettingsContactPageInfo(
												@RequestParam("contactMainHeader")String contactMainHeader,
												@RequestParam("contactHeader")String contactHeader,
												@RequestParam("contactAddress")String contactAddress,
												@RequestParam("contactTelephone")String contactTelephone,
												@RequestParam("contactEmail")String contactEmail,
												@RequestParam("googleHarita")String googleHarita
												) {
		
		smmmOfisService.saveHomePageSettingContactPageInfo( contactMainHeader, contactHeader, contactAddress, contactTelephone, contactEmail, googleHarita);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}

	@PostMapping("/update-smmm-homepage-settings-contact-info/{id}")
	public String updateHomePageSettingsContactPageInfo(
												@PathVariable("id")Integer id,
												@RequestParam("contactMainHeader")String contactMainHeader,
												@RequestParam("contactHeader")String contactHeader,
												@RequestParam("contactAddress")String contactAddress,
												@RequestParam("contactTelephone")String contactTelephone,
												@RequestParam("contactEmail")String contactEmail,
												@RequestParam("googleHarita")String googleHarita
												) {
		
		smmmOfisService.updateHomePageSettingContactPageInfo(id, contactMainHeader, contactHeader, contactAddress, contactTelephone, contactEmail, googleHarita);
		
		return "redirect:/api/v1/smmm-homepage-settings";
	}


	



}
