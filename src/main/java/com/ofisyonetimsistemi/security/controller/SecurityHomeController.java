package com.ofisyonetimsistemi.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.security.dto.UserDto;
import com.ofisyonetimsistemi.security.model.MyUser;
import com.ofisyonetimsistemi.security.service.MyUserService;

import jakarta.validation.Valid;

@Controller
public class SecurityHomeController {
	
	@Autowired
	private MyUserService userService;
	
	@Autowired
	private PasswordEncoder pwdEncoder;

	@GetMapping("/login")
	public String getLoginPage(Model model) {
		
		boolean thereAreAnyUser = userService.thereAreAnyUser();
		
		if(thereAreAnyUser) {
			model.addAttribute("smmmOfis", new SmmmOfis());
			model.addAttribute("userDto", new UserDto());
			return "adminpanel/login";
		}
			
			String roles = "ADMIN,USER,CUSTOMER";
			
			MyUser defaultUser = MyUser.builder()
					.username("admin")
					.password(pwdEncoder.encode("1234"))
					.openpassword("1234")
					.roles(roles)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.enabled(true)
					.build();
			userService.saveMyUser(defaultUser);
			
			model.addAttribute("smmmOfis", new SmmmOfis());
			model.addAttribute("userDto", new UserDto());
			return "adminpanel/login";
		
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result ) {
		if(result.hasErrors()) {
			return "adminpanel/login";
		}
		return "redirect:/login?login";
		
	}
	
	
}
