package com.ofisyonetimsistemi.controllers;

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
import com.ofisyonetimsistemi.models.SmmmOfisWhyUs;
import com.ofisyonetimsistemi.services.SmmmOfisService;
import com.ofisyonetimsistemi.services.SmmmOfisWhyUsService;

@Controller
@RequestMapping("/api/v1/")
public class HomePageWhyUsController {
	
	@Autowired SmmmOfisService smmmOfisHomePageService;
	@Autowired SmmmOfisWhyUsService whyUsService;

	@GetMapping("/smmm-homepage-whyus-settings")
	public String getWhyUsSettingPage(Model model) {
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		  if(!smmmOfis.isEmpty()) {
			  model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan()+" "+smmmOfis.get().getFullName());
			  model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			  model.addAttribute("fullusername", smmmOfis.get().getUserName());
			  model.addAttribute("gorev", smmmOfis.get().getUnvan());
			  
			  model.addAttribute("smmmOfis", smmmOfis.get());
			  model.addAttribute("hpWhyUs", new SmmmOfisWhyUs());
			  model.addAttribute("whyUsList", whyUsService.getAllWhyUs());
			  
			  return "adminpanel/homepage-whyus-settings";
			  
		   }else {
		  
			  model.addAttribute("dashboardtitle", "SMMM Muammer UZUN");
			  model.addAttribute("smmmisim", "Muammer UZUN");
			  model.addAttribute("fullusername", "Muammer UZUN");
			  model.addAttribute("gorev", "SMMM");
			  model.addAttribute("smmmOfis", new SmmmOfis());
		   }
		  
		return "adminpanel/homepage-whyus-settings";
	}
	
	@GetMapping("/get-homepage-whyus/{id}")
	@ResponseBody
	public Optional<SmmmOfisWhyUs> getWhyUsById(@PathVariable("id")Integer id) {
			return whyUsService.getWhyUsById(id);
	}
	
	
	@PostMapping("/save-homepage-whyus")
	public String saveWhyUs(@ModelAttribute("hpWhyUs") SmmmOfisWhyUs whyus) {
		
		whyus.setSmmmofis_id(smmmOfisHomePageService.getFirstSmmmOfis().get().getId());
		whyUsService.saveWhyUs(whyus);
		
		return "redirect:/api/v1/smmm-homepage-whyus-settings";
	}
	
	@PostMapping("/update-homepage-whyus")
	public String updateWhyUs(@ModelAttribute("hpWhyUs") SmmmOfisWhyUs hpWhyUs) {
		
		hpWhyUs.setSmmmofis_id(smmmOfisHomePageService.getFirstSmmmOfis().get().getId());
		whyUsService.saveWhyUs(hpWhyUs);
		
		return "redirect:/api/v1/smmm-homepage-whyus-settings";
	}
	
	@RequestMapping(value="/delete-homepage-whyus/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delWhyUsById(@PathVariable("id") Integer id) {
		whyUsService.deleteWhyUsById(id);
		return "redirect:/api/v1/smmm-homepage-whyus-settings";
	}
	
	

}

