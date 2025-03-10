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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.models.SmmmOfisTeam;
import com.ofisyonetimsistemi.security.model.MyUser;
import com.ofisyonetimsistemi.security.service.MyUserService;
import com.ofisyonetimsistemi.services.SmmmOfisHomePageTeamService;
import com.ofisyonetimsistemi.services.SmmmOfisMessageService;
import com.ofisyonetimsistemi.services.SmmmOfisNoteService;
import com.ofisyonetimsistemi.services.SmmmOfisNotificationService;
import com.ofisyonetimsistemi.services.SmmmOfisService;



@Controller
@RequestMapping("/cp/")
public class HomePageTeamController {

	@Autowired private SmmmOfisService smmmOfisService;
	@Autowired private SmmmOfisHomePageTeamService hptService;
	@Autowired private MyUserService myUserService;
	@Autowired private SmmmOfisMessageService messageService;
	@Autowired private SmmmOfisNotificationService notificationService;
	@Autowired private SmmmOfisNoteService noteService;
	
	@GetMapping("/smmm-homepage-team-settings")
	public String getHomePageTeamsSettingsPage(Model model, Principal principal) {
		MyUser myUser = myUserService.getMyUserByUsername(principal.getName());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		  if(!smmmOfis.isEmpty()) {
			 			  
			  model.addAttribute("smmmOfis", smmmOfis.get());
			  model.addAttribute("hpTeam", new SmmmOfisTeam());
			  model.addAttribute("hpTeams", hptService.getAllHomePageTeams());
			  
			  model.addAttribute("currentUser", myUser);
			  
			  loadRequaredCommenItems(model);
			  
			  return "adminpanel/homepagesettings/homepage-team-settings";
			  
		   }else {
		  
			  model.addAttribute("dashboardtitle", "SMMM Muammer UZUN");
			  model.addAttribute("smmmisim", "Muammer UZUN");
			  model.addAttribute("fullusername", "Muammer UZUN");
			  model.addAttribute("gorev", "SMMM");
			  model.addAttribute("smmmOfis", new SmmmOfis());
			  
			  model.addAttribute("currentUser", myUser);
			  
			 loadRequaredCommenItems(model);
			 
		   }
		  
		return "adminpanel/homepagesettings/homepage-team-settings";
	}

	@PostMapping("/save-homepage-team-settings")
	public String saveHomePageTeam(
										@RequestParam("resim")MultipartFile file,
										@RequestParam("name")String name,
										@RequestParam("mainheader")String mainheader,
										@RequestParam("header")String header,
										@RequestParam("text")String text,
										@RequestParam("xlink")String xlink,
										@RequestParam("facelink")String facelink,
										@RequestParam("inslink")String inslink,
										@RequestParam("linklink")String linklink,										
										@RequestParam("description")String description,
										@RequestParam(value="active", required = false)boolean active
										
										) throws IOException {
		
		Integer smmmOfisId = smmmOfisService.getFirstSmmmOfis().get().getId();
		hptService.saveHomePageTeam(file, name, mainheader, header, text, xlink, facelink, inslink, linklink, description, active, smmmOfisId);
		
		return "redirect:/cp/smmm-homepage-team-settings";
	}
	
	@PostMapping("/update-homepage-team-settings/{id}")
	public String updateUserById(   
									@PathVariable("id")Integer id,
									@RequestParam("resim")MultipartFile file,
									@RequestParam("name")String name,
									@RequestParam("mainheader")String mainheader,
									@RequestParam("header")String header,
									@RequestParam("text")String text,
									@RequestParam("xlink")String xlink,
									@RequestParam("facelink")String facelink,
									@RequestParam("inslink")String inslink,
									@RequestParam("linklink")String linklink,										
									@RequestParam("description")String description,
									@RequestParam(value="active", required = false)boolean active 
									
								) throws IOException {
		
		Integer smmmOfisId = smmmOfisService.getFirstSmmmOfis().get().getId();
		
		hptService.updateHomePageTeam(id, file, name, mainheader, header, text, xlink, facelink, inslink, linklink, description, active, smmmOfisId);
		
		return "redirect:/cp/smmm-homepage-team-settings";
	}

	@GetMapping("/get-homepage-team-settings/{id}")
	@ResponseBody
	public Optional<SmmmOfisTeam> getUserById(@PathVariable("id")Integer id) {
		return hptService.getById(id);
	}
	
	@RequestMapping(value="/delete-homepage-team-settings/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delById(@PathVariable("id") Integer id) {
		hptService.deleteById(id);
		return "redirect:/cp/smmm-homepage-team-settings";
	}
	
	public void loadRequaredCommenItems(Model model) {
		
		model.addAttribute("countOfUnReadMessages", messageService.countOfUnReadMessages());
		model.addAttribute("listOfUnreadMessages", messageService.getAllUnReadMessages());
		
		model.addAttribute("countOfUnReadNotifications", notificationService.countOfUnReadNotifications());
		model.addAttribute("listOfUnreadNotifications", notificationService.getAllUnReadNotifications());
		
		model.addAttribute("countOfUnReadNotes", noteService.countOfUnReadNotes());
		model.addAttribute("listOfUnReadNotes", noteService.getAllUnreadNotes());
		
	}
	
}
