package com.ofisyonetimsistemi.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.models.SmmmOfisAboutUsColumnOne;
import com.ofisyonetimsistemi.services.SmmmOfisAboutUsColumnOneService;
import com.ofisyonetimsistemi.services.SmmmOfisService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/")
public class HomePageAboutUsController {
	
	@Autowired SmmmOfisService smmmOfisHomePageService;
	@Autowired SmmmOfisAboutUsColumnOneService smmmOfisHomePageAboutUsColOneService;

	@GetMapping("/smmm-homepage-aboutus-settings")
	public String getMethodName(Model model) {
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		  if(!smmmOfis.isEmpty()) {
			  model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan()+" "+smmmOfis.get().getFullName());
			  model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			  model.addAttribute("fullusername", smmmOfis.get().getUserName());
			  model.addAttribute("gorev", smmmOfis.get().getUnvan());
			  
			  model.addAttribute("smmmOfis", smmmOfis.get());
			  model.addAttribute("${hpAboutUsColOneItem}", new SmmmOfisAboutUsColumnOne());
			  model.addAttribute("aboutUsColOneItems", smmmOfisHomePageAboutUsColOneService.getAllAboutUscolOneItems());
			  
			  return "adminpanel/homepage-aboutus-settings";
			  
		   }else {
		  
			  model.addAttribute("dashboardtitle", "SMMM Muammer UZUN");
			  model.addAttribute("smmmisim", "Muammer UZUN");
			  model.addAttribute("fullusername", "Muammer UZUN");
			  model.addAttribute("gorev", "SMMM");
			  model.addAttribute("smmmOfis", new SmmmOfis());
		   }
		  
		return "adminpanel/homepage-aboutus-settings";
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
