package com.ofisyonetimsistemi.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.models.SmmmOfisHomePageClient;
import com.ofisyonetimsistemi.services.SmmmOfisHomePageClientService;
import com.ofisyonetimsistemi.services.SmmmOfisService;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/api/v1/")
public class HomePageClientController {

	@Autowired
	private SmmmOfisService smmmOfisService;
	
	@Autowired
	private SmmmOfisHomePageClientService hpcService;
	
	@GetMapping("/smmm-homepage-clients-settings")
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

	@PostMapping("/save-homepage-client-settings")
	public String saveHomePageClient(
										@RequestParam("logo")MultipartFile file,
										@RequestParam("name")String name,
										@RequestParam("url")String url,
										@RequestParam("description")String description,
										@RequestParam(value="active", required = false)boolean active
										
										) throws IOException {
		
		Integer smmmOfisId = smmmOfisService.getFirstSmmmOfis().get().getId();
		hpcService.saveHomePageClient(file, name, url, description, active, smmmOfisId );
		
		return "redirect:/api/v1/smmm-homepage-clients-settings";
	}
	
	@PostMapping("/update-homepage-client-settings/{id}")
	public String updateUserById(   
									@PathVariable Integer id, 
									@RequestParam("logo")MultipartFile file,
									@RequestParam("name")String name,
									@RequestParam("url")String url,
									@RequestParam("description")String description,
									@RequestParam(value="active", required = false)boolean active 
									
								) throws IOException {
		
		Integer smmmOfisId = smmmOfisService.getFirstSmmmOfis().get().getId();
		
		hpcService.updateHomePageClient(id, file, name, url, description, active, smmmOfisId);
		
		return "redirect:/api/v1/smmm-homepage-clients-settings";
	}

	@GetMapping("/get-homepage-client-settings/{id}")
	@ResponseBody
	public Optional<SmmmOfisHomePageClient> getUserById(@PathVariable("id")Integer id) {
		return hpcService.getById(id);
	}
	
	@RequestMapping(value="/delete-homepage-client-settings/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delById(@PathVariable("id") Integer id) {
		hpcService.deleteById(id);
		return "redirect:/api/v1/smmm-homepage-clients-settings";
	}
	
	
	
}
