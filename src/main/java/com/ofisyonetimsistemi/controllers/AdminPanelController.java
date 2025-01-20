package com.ofisyonetimsistemi.controllers;

import java.security.Principal;
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
import com.ofisyonetimsistemi.services.SmmmOfisService;

@Controller
@RequestMapping("/api/v1/")
public class AdminPanelController {

	@Autowired
	private SmmmOfisService smmmOfisService;
	@Autowired
	private MyUserService myUserService;
	
	@GetMapping("/admin-panel")
	public String getAdminPanel(Model model, Principal principal, @AuthenticationPrincipal MyUserDetails loggedUser) {
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		if (!smmmOfis.isEmpty()) {
			model.addAttribute("updateBtnActive", true);
			model.addAttribute("smmmOfis", smmmOfis.get());

			model.addAttribute("currentUser", currentUser);

			return "adminpanel/index";

		} else {

			model.addAttribute("dashboardtitle", "SMMM İsim Soyisim");
			model.addAttribute("smmmisim", "SMMM İsim Soyisim");
			model.addAttribute("fullusername", "Kullanıcı İsim Soyisim");

			model.addAttribute("updateBtnActive", false);
			model.addAttribute("smmmOfis", new SmmmOfis());

			model.addAttribute("currentUser", currentUser);
		}

		return "adminpanel/index";
	}

	@GetMapping("/user-profile")
	public String getUserProfile(Model model, Principal principal, @AuthenticationPrincipal MyUserDetails loggedUser) {
		
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		UserProfileDto userProfileDto = new UserProfileDto();
		
		if (smmmOfis.isPresent()) {
			
			model.addAttribute("tabpane", "view");
			model.addAttribute("smmmOfis", smmmOfis.get());
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("userProfileDto", userProfileDto);
			
			return "adminpanel/homepagesettings/user-profile";

		} else {
			
			model.addAttribute("dashboardtitle", "SMMM İsim Soyisim");
			model.addAttribute("smmmisim", "SMMM İsim Soyisim");
			model.addAttribute("fullusername", "Kullanıcı İsim Soyisim");
			model.addAttribute("tabpane", "view");

			model.addAttribute("smmmOfis", new SmmmOfis());
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("userProfileDto", userProfileDto);
			
		}
		return "adminpanel/homepagesettings/user-profile";
	}

	@GetMapping("/user-profile-view")
	public String getUserProfileWiew(Model model, Principal principal, @AuthenticationPrincipal MyUserDetails loggedUser) {
		
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		UserProfileDto userProfileDto = new UserProfileDto();
		
		if (smmmOfis.isPresent()) {
			model.addAttribute("tabpane", "view");
			model.addAttribute("smmmOfis", smmmOfis.get());

			model.addAttribute("currentUser", currentUser);
			model.addAttribute("userProfileDto", userProfileDto);
			
			return "adminpanel/homepagesettings/user-profile";

		} else {
			
			model.addAttribute("dashboardtitle", "SMMM İsim Soyisim");
			model.addAttribute("smmmisim", "SMMM İsim Soyisim");
			model.addAttribute("fullusername", "Kullanıcı İsim Soyisim");

			model.addAttribute("tabpane", "view");
			model.addAttribute("smmmOfis", new SmmmOfis());
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("userProfileDto", userProfileDto);
			
		}
		return "adminpanel/homepagesettings/user-profile";
	}

	@GetMapping("/user-profile-edit")
	public String getUserProfileEdit(Model model, Principal principal, @AuthenticationPrincipal MyUserDetails loggedUser) {
		
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		UserProfileDto userProfileDto = new UserProfileDto();
		
		if (smmmOfis.isPresent()) {
			model.addAttribute("tabpane", "edit");
			model.addAttribute("smmmOfis", smmmOfis.get());

			model.addAttribute("currentUser", currentUser);
			model.addAttribute("userProfileDto", new UserProfileDto());
			model.addAttribute("userProfileDto", userProfileDto);

			return "adminpanel/homepagesettings/user-profile";

		} else {
			
			model.addAttribute("dashboardtitle", "SMMM İsim Soyisim");
			model.addAttribute("smmmisim", "SMMM İsim Soyisim");
			model.addAttribute("fullusername", "Kullanıcı İsim Soyisim");

			model.addAttribute("tabpane", "edit");
			model.addAttribute("smmmOfis", new SmmmOfis());
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("userProfileDto", userProfileDto);
		}
		return "adminpanel/homepagesettings/user-profile";
	}

	@GetMapping("/user-profile-settings")
	public String getUserProfileSettings(Model model, Principal principal, @AuthenticationPrincipal MyUserDetails loggedUser) {
		
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		UserProfileDto userProfileDto = new UserProfileDto();
		
		if (smmmOfis.isPresent()) {
			
			model.addAttribute("tabpane", "settings");
			model.addAttribute("smmmOfis", smmmOfis.get());

			model.addAttribute("currentUser", currentUser);
			model.addAttribute("userProfileDto", userProfileDto);
			
			return "adminpanel/homepagesettings/user-profile";

		} else {
			
			model.addAttribute("dashboardtitle", "SMMM İsim Soyisim");
			model.addAttribute("smmmisim", "SMMM İsim Soyisim");
			model.addAttribute("fullusername", "Kullanıcı İsim Soyisim");

			model.addAttribute("tabpane", "settings");
			model.addAttribute("smmmOfis", new SmmmOfis());
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("userProfileDto", userProfileDto);
			
		}
		return "adminpanel/homepagesettings/user-profile";
	}

	@GetMapping("/user-profile-changepwd")
	public String getUserProfileChangepwd(Model model, Principal principal, @AuthenticationPrincipal MyUserDetails loggedUser) {
		
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		UserProfileDto userProfileDto = new UserProfileDto();
		
		if (smmmOfis.isPresent()) {
			
			model.addAttribute("tabpane", "changepwd");
			model.addAttribute("smmmOfis", smmmOfis.get());

			model.addAttribute("currentUser", currentUser);
			model.addAttribute("userProfileDto", userProfileDto);
			
			return "adminpanel/homepagesettings/user-profile";

		} else {
			
			model.addAttribute("dashboardtitle", "SMMM İsim Soyisim");
			model.addAttribute("smmmisim", "SMMM İsim Soyisim");
			model.addAttribute("fullusername", "Kullanıcı İsim Soyisim");

			model.addAttribute("tabpane", "changepwd");
			model.addAttribute("smmmOfis", new SmmmOfis());
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("userProfileDto", userProfileDto);
			
		}
		return "adminpanel/homepagesettings/user-profile";
	}

}
