package com.ofisyonetimsistemi.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.models.SmmmOfisHomePageClient;
import com.ofisyonetimsistemi.services.SmmmOfisHomePageClientService;
import com.ofisyonetimsistemi.services.SmmmOfisService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/api/v1/")
public class HomePageClientController {

	@Autowired
	private SmmmOfisService smmmOfisService;
	
	@Autowired
	private SmmmOfisHomePageClientService hpcService;
	
	@GetMapping("/smmm-homepage-clients")
	public String getHomePageClientsSettingsPage(Model model) {
		
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		  if(!smmmOfis.isEmpty()) {
			  model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan()+" "+smmmOfis.get().getFullName());
			  model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			  model.addAttribute("fullusername", smmmOfis.get().getUserName());
			  model.addAttribute("gorev", smmmOfis.get().getUnvan());
			  
			  model.addAttribute("smmmOfis", smmmOfis.get());
			  model.addAttribute("hpClient", new SmmmOfisHomePageClient());
			  model.addAttribute("hpClients", hpcService.getAllHomePageClients());
			  
			  return "adminpanel/homepage-clients-settings";
			  
		   }else {
		  
			  model.addAttribute("dashboardtitle", "SMMM Muammer UZUN");
			  model.addAttribute("smmmisim", "Muammer UZUN");
			  model.addAttribute("fullusername", "Muammer UZUN");
			  model.addAttribute("gorev", "SMMM");
			  model.addAttribute("smmmOfis", new SmmmOfis());
		   }
		  
		return "adminpanel/homepage-clients-settings";
	}

	@PostMapping("/save-homepage-client")
	public String postMethodName(
								@RequestParam("logo")MultipartFile file,
								@RequestParam("name")String name,
								@RequestParam("url")String url,
								@RequestParam("description")String description
								
								) throws IOException {
		Integer smmmOfisId = smmmOfisService.getFirstSmmmOfis().get().getId();
		hpcService.saveHomePageClient(file, name, url, description,smmmOfisId );
		
		return "redirect:/api/v1/smmm-homepage-clients";
	}
	
	
	
	
}
