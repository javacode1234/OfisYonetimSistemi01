package com.ofisyonetimsistemi.controllers;

import java.io.IOException;
import java.security.Principal;
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
import com.ofisyonetimsistemi.models.SmmmOfisReferanses;
import com.ofisyonetimsistemi.security.model.MyUser;
import com.ofisyonetimsistemi.security.service.MyUserService;
import com.ofisyonetimsistemi.services.SmmmOfisReferansesService;
import com.ofisyonetimsistemi.services.SmmmOfisService;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/api/v1/")
public class HomePageReferansController {

	@Autowired
	private SmmmOfisService smmmOfisService;
	
	@Autowired
	private SmmmOfisReferansesService hpReferansService;
	
	@Autowired 
	private MyUserService myUserService;
	
	private static MyUser myUser;
	
	@GetMapping("/smmm-homepage-referans-settings")
	public String getHomePageReferanses(Model model, Principal principal) {
		myUser = myUserService.getMyUserByUsername(principal.getName());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		  if(!smmmOfis.isEmpty()) {
			  model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan()+" "+smmmOfis.get().getFullName());
			  model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			  model.addAttribute("fullusername", smmmOfis.get().getUserName());
			  model.addAttribute("gorev", smmmOfis.get().getUnvan());
			  
			  model.addAttribute("smmmOfis", smmmOfis.get());
			  model.addAttribute("hpReferans", new SmmmOfisReferanses());
			  model.addAttribute("hpReferanses", hpReferansService.getAllHomePageReferanses());
			  
			  model.addAttribute("currentUser", myUser);
			  
			  return "adminpanel/homepagesettings/homepage-referans-settings";
			  
		   }else {
		  
			  model.addAttribute("dashboardtitle", "SMMM Muammer UZUN");
			  model.addAttribute("smmmisim", "Muammer UZUN");
			  model.addAttribute("fullusername", "Muammer UZUN");
			  model.addAttribute("gorev", "SMMM");
			  model.addAttribute("smmmOfis", new SmmmOfis());
			  
			  model.addAttribute("currentUser", myUser);
		   }
		  
		return "adminpanel/homepagesettings/homepage-referans-settings";
	}

	@PostMapping("/save-homepage-referans-settings")
	public String saveHomePageClient(
											@RequestParam("resim")MultipartFile file,
											@RequestParam("name")String name,
											@RequestParam("meslek")String meslek,
											@RequestParam(value="star1", required = false)boolean star1,
											@RequestParam(value="star2", required = false)boolean star2,
											@RequestParam(value="star3", required = false)boolean star3,
											@RequestParam(value="star4", required = false)boolean star4,
											@RequestParam(value="star5", required = false)boolean star5,
											@RequestParam("gorus")String gorus,
											@RequestParam(value="active", required = false)boolean active,
											@RequestParam("smmmofis_id")Integer smmmofis_id
										
										) throws IOException {
		
		//Integer smmmOfisId = smmmOfisService.getFirstSmmmOfis().get().getId();
		hpReferansService.save(file, name, meslek, star1, star2, star3, star4, star5, gorus, active, smmmofis_id);
		
		return "redirect:/api/v1/smmm-homepage-referans-settings";
	}
	
	@PostMapping("/update-homepage-referans-settings/{id}")
	public String updateUserById(   
									@PathVariable Integer id, 
									@RequestParam("resim")MultipartFile file,
									@RequestParam("name")String name,
									@RequestParam("meslek")String meslek,
									@RequestParam(value="star1", required = false)boolean star1,
									@RequestParam(value="star2", required = false)boolean star2,
									@RequestParam(value="star3", required = false)boolean star3,
									@RequestParam(value="star4", required = false)boolean star4,
									@RequestParam(value="star5", required = false)boolean star5,
									@RequestParam("gorus")String gorus,
									@RequestParam(value="active", required = false)boolean active,
									@RequestParam("smmmofis_id")Integer smmmofis_id
									 
									
								) throws IOException {
		
		//Integer smmmOfisId = smmmOfisService.getFirstSmmmOfis().get().getId();
		
		hpReferansService.update(id, file, name, meslek, star1, star2, star3, star4, star5, gorus, active, smmmofis_id);
		
		return "redirect:/api/v1/smmm-homepage-referans-settings";
	}

	@GetMapping("/get-homepage-referans-settings/{id}")
	@ResponseBody
	public Optional<SmmmOfisReferanses> getUserById(@PathVariable("id")Integer id) {
		return hpReferansService.getById(id);
	}
	
	@RequestMapping(value="/delete-homepage-referans-settings/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delById(@PathVariable("id") Integer id) {
		hpReferansService.deleteById(id);
		return "redirect:/api/v1/smmm-homepage-referans-settings";
	}
	
	
	
}
