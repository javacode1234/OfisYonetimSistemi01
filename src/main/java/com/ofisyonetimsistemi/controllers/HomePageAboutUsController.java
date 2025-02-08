package com.ofisyonetimsistemi.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.models.SmmmOfisAboutUsColumnOne;
import com.ofisyonetimsistemi.security.model.MyUser;
import com.ofisyonetimsistemi.security.service.MyUserService;
import com.ofisyonetimsistemi.services.SmmmOfisAboutUsColumnOneService;
import com.ofisyonetimsistemi.services.SmmmOfisMessageService;
import com.ofisyonetimsistemi.services.SmmmOfisService;

@Controller
@RequestMapping("/api/v1/")
public class HomePageAboutUsController {
	
	@Autowired SmmmOfisService smmmOfisHomePageService;
	@Autowired SmmmOfisAboutUsColumnOneService smmmOfisHomePageAboutUsColOneService;
	@Autowired private MyUserService myUserService;
	@Autowired private SmmmOfisMessageService messageService;

	@GetMapping("/smmm-homepage-aboutus-settings")
	public String getMethodName(Model model, Principal principal) {
		MyUser myUser = myUserService.getMyUserByUsername(principal.getName());
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		  if(!smmmOfis.isEmpty()) {
			  model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan()+" "+smmmOfis.get().getFullName());
			  model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			  model.addAttribute("fullusername", smmmOfis.get().getUserName());
			  model.addAttribute("gorev", smmmOfis.get().getUnvan());
			  
			  model.addAttribute("smmmOfis", smmmOfis.get());
			  model.addAttribute("${hpAboutUsColOneItem}", new SmmmOfisAboutUsColumnOne());
			  model.addAttribute("aboutUsColOneItems", smmmOfisHomePageAboutUsColOneService.getAllAboutUscolOneItems());
			  
			  model.addAttribute("currentUser", myUser);
			  model.addAttribute("messageCount", messageService.countOfRecord());
			  model.addAttribute("countOfUnReadMessages", messageService.countOfUnReadMessages(false));
			  
			  return "adminpanel/homepagesettings/homepage-aboutus-settings";
			  
		   }else {
		  
			  model.addAttribute("dashboardtitle", "SMMM Muammer UZUN");
			  model.addAttribute("smmmisim", "Muammer UZUN");
			  model.addAttribute("fullusername", "Muammer UZUN");
			  model.addAttribute("gorev", "SMMM");
			  model.addAttribute("smmmOfis", new SmmmOfis());
			  
			  model.addAttribute("currentUser", myUser);
			  model.addAttribute("messageCount", messageService.countOfRecord());
			  model.addAttribute("countOfUnReadMessages", messageService.countOfUnReadMessages(false));
		   }
		  
		return "adminpanel/homepagesettings/homepage-aboutus-settings";
	}
	
	@GetMapping("/get-homepage-aboutus-col-one-item/{id}")
	@ResponseBody
	public Optional<SmmmOfisAboutUsColumnOne> getMethodName(@PathVariable("id")Integer id) {
			return smmmOfisHomePageAboutUsColOneService.getAboutUsColOneById(id);
	}
	
	
	@PostMapping("/save-homepage-aboutus-col-one-item")
	public String saveSmmmOfisHomePageAboutUsColOne(@ModelAttribute("hpAboutUsColOne") SmmmOfisAboutUsColumnOne aboutUsColOne) {
		
		aboutUsColOne.setSmmmofis_id(smmmOfisHomePageService.getFirstSmmmOfis().get().getId());
		smmmOfisHomePageAboutUsColOneService.saveAboutUsColOneItem(aboutUsColOne);
		
		return "redirect:/api/v1/smmm-homepage-aboutus-settings";
	}
	
	@PostMapping("/update-homepage-aboutus-col-one-item")
	public String updateSmmmOfisHomePageAboutUsColOne(@ModelAttribute("hpAboutUsColOne") SmmmOfisAboutUsColumnOne aboutUsColOne) {
		
		aboutUsColOne.setSmmmofis_id(smmmOfisHomePageService.getFirstSmmmOfis().get().getId());
		smmmOfisHomePageAboutUsColOneService.saveAboutUsColOneItem(aboutUsColOne);
		
		return "redirect:/api/v1/smmm-homepage-aboutus-settings";
	}
	
	@RequestMapping(value="/delete-homepage-aboutus-col-one-item/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delSmmmOfisHomePageAboutUsColOneById(@PathVariable("id") Integer id) {
		smmmOfisHomePageAboutUsColOneService.deleteAboutUsById(id);
		return "redirect:/api/v1/smmm-homepage-aboutus-settings";
	}
	
	

}
