package com.ofisyonetimsistemi.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.security.dto.UserProfileDto;
import com.ofisyonetimsistemi.security.model.MyUser;
import com.ofisyonetimsistemi.security.model.MyUserDetails;
import com.ofisyonetimsistemi.security.service.MyUserService;
import com.ofisyonetimsistemi.services.SmmmOfisMessageService;
import com.ofisyonetimsistemi.services.SmmmOfisNoteService;
import com.ofisyonetimsistemi.services.SmmmOfisNotificationService;
import com.ofisyonetimsistemi.services.SmmmOfisService;

@Controller
@RequestMapping(value="/cp/")
public class AdminPanelController {

	@Autowired private SmmmOfisService smmmOfisService;
	@Autowired private MyUserService myUserService;
	@Autowired private SmmmOfisMessageService messageService;
	@Autowired private SmmmOfisNotificationService notificationService;
	@Autowired private SmmmOfisNoteService noteService;
	
	@GetMapping(value="/admin-panel")
	public String getAdminPanel(Model model, @AuthenticationPrincipal MyUserDetails loggedUser) {
		
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		if (!smmmOfis.isEmpty()) {
			
			model.addAttribute("updateBtnActive", true);
			model.addAttribute("smmmOfis", smmmOfis.get());			
			model.addAttribute("currentUser", currentUser);
			
			loadRequaredCommenItems(model);
			
			return "adminpanel/index";

		} else {

			model.addAttribute("dashboardtitle", "SMMM İsim Soyisim");
			model.addAttribute("smmmisim", "SMMM İsim Soyisim");
			model.addAttribute("fullusername", "Kullanıcı İsim Soyisim");

			model.addAttribute("updateBtnActive", false);
			model.addAttribute("smmmOfis", new SmmmOfis());
			model.addAttribute("currentUser", currentUser);
			
			loadRequaredCommenItems(model);
			
		}

		return "adminpanel/index";
	}

	@GetMapping("/user-profile")
	public String getUserProfile(Model model, @AuthenticationPrincipal MyUserDetails loggedUser) {
		
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		UserProfileDto userProfileDto = new UserProfileDto();
		
		if (smmmOfis.isPresent()) {
			
			model.addAttribute("tabpane", "view");			
			model.addAttribute("userProfileDto", userProfileDto);
			model.addAttribute("smmmOfis", smmmOfis.get());			
			model.addAttribute("currentUser", currentUser);
			
			loadRequaredCommenItems(model);
			
			return "adminpanel/homepagesettings/user-profile";

		} else {
			
			model.addAttribute("dashboardtitle", "SMMM İsim Soyisim");
			model.addAttribute("smmmisim", "SMMM İsim Soyisim");
			model.addAttribute("fullusername", "Kullanıcı İsim Soyisim");
			model.addAttribute("tabpane", "view");

			model.addAttribute("smmmOfis", new SmmmOfis());
			
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("userProfileDto", userProfileDto);
			
			loadRequaredCommenItems(model);
			
		}
		
		return "adminpanel/homepagesettings/user-profile";
		
	}

	@GetMapping("/user-profile-view")
	public String getUserProfileWiew(Model model, @AuthenticationPrincipal MyUserDetails loggedUser) {
		
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		UserProfileDto userProfileDto = new UserProfileDto();
		
		if (smmmOfis.isPresent()) {
			
			model.addAttribute("tabpane", "view");
			model.addAttribute("smmmOfis", smmmOfis.get());

			model.addAttribute("currentUser", currentUser);
			model.addAttribute("userProfileDto", userProfileDto);
			
			loadRequaredCommenItems(model);
			
			return "adminpanel/homepagesettings/user-profile";

		} else {
			
			model.addAttribute("dashboardtitle", "SMMM İsim Soyisim");
			model.addAttribute("smmmisim", "SMMM İsim Soyisim");
			model.addAttribute("fullusername", "Kullanıcı İsim Soyisim");

			model.addAttribute("tabpane", "view");
			model.addAttribute("smmmOfis", new SmmmOfis());
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("userProfileDto", userProfileDto);
			
			loadRequaredCommenItems(model);
			
		}
		
		return "adminpanel/homepagesettings/user-profile";
		
	}

	@GetMapping("/user-profile-edit")
	public String getUserProfileEdit(Model model, @AuthenticationPrincipal MyUserDetails loggedUser) {
		
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		UserProfileDto userProfileDto = new UserProfileDto();
		
		if (smmmOfis.isPresent()) {
			
			model.addAttribute("tabpane", "edit");
			model.addAttribute("smmmOfis", smmmOfis.get());

			model.addAttribute("currentUser", currentUser);
			model.addAttribute("userProfileDto", new UserProfileDto());
			model.addAttribute("userProfileDto", userProfileDto);
			
			loadRequaredCommenItems(model);

			return "adminpanel/homepagesettings/user-profile";

		} else {
			
			model.addAttribute("dashboardtitle", "SMMM İsim Soyisim");
			model.addAttribute("smmmisim", "SMMM İsim Soyisim");
			model.addAttribute("fullusername", "Kullanıcı İsim Soyisim");

			model.addAttribute("tabpane", "edit");
			model.addAttribute("smmmOfis", new SmmmOfis());
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("userProfileDto", userProfileDto);
			
			loadRequaredCommenItems(model);
			
		}
		
		return "adminpanel/homepagesettings/user-profile";
		
	}

	@GetMapping("/user-profile-settings")
	public String getUserProfileSettings(Model model, @AuthenticationPrincipal MyUserDetails loggedUser) {
		
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		UserProfileDto userProfileDto = new UserProfileDto();
		
		if (smmmOfis.isPresent()) {
			
			model.addAttribute("tabpane", "settings");
			model.addAttribute("smmmOfis", smmmOfis.get());

			model.addAttribute("currentUser", currentUser);
			model.addAttribute("userProfileDto", userProfileDto);
			
			loadRequaredCommenItems(model);
			
			return "adminpanel/homepagesettings/user-profile";

		} else {
			
			model.addAttribute("dashboardtitle", "SMMM İsim Soyisim");
			model.addAttribute("smmmisim", "SMMM İsim Soyisim");
			model.addAttribute("fullusername", "Kullanıcı İsim Soyisim");

			model.addAttribute("tabpane", "settings");
			model.addAttribute("smmmOfis", new SmmmOfis());
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("userProfileDto", userProfileDto);
			
			loadRequaredCommenItems(model);
			
		}
		
		return "adminpanel/homepagesettings/user-profile";
		
	}

	@GetMapping("/user-profile-changepwd")
	public String getUserProfileChangepwd(Model model, @AuthenticationPrincipal MyUserDetails loggedUser) {
		
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		UserProfileDto userProfileDto = new UserProfileDto();
		
		if (smmmOfis.isPresent()) {
			
			model.addAttribute("tabpane", "changepwd");
			model.addAttribute("smmmOfis", smmmOfis.get());

			model.addAttribute("currentUser", currentUser);
			model.addAttribute("userProfileDto", userProfileDto);
			
			loadRequaredCommenItems(model);
			
			return "adminpanel/homepagesettings/user-profile";

		} else {
			
			model.addAttribute("dashboardtitle", "SMMM İsim Soyisim");
			model.addAttribute("smmmisim", "SMMM İsim Soyisim");
			model.addAttribute("fullusername", "Kullanıcı İsim Soyisim");

			model.addAttribute("tabpane", "changepwd");
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("userProfileDto", userProfileDto);
			model.addAttribute("smmmOfis", new SmmmOfis());
			
			loadRequaredCommenItems(model);
			
		}
		
		return "adminpanel/homepagesettings/user-profile";
		
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
