package com.ofisyonetimsistemi.security.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.security.dto.UserDto;
import com.ofisyonetimsistemi.security.model.MyUser;

import com.ofisyonetimsistemi.security.service.MyUserService;
import com.ofisyonetimsistemi.services.SmmmOfisService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/")
public class AdminController {

	@Autowired
	private SmmmOfisService smmmOfisService;

	@Autowired
	private MyUserService myUserService;

	@Autowired
	private PasswordEncoder pwdEncoder;
	
	private static MyUser myUser;
	
	private static Optional<SmmmOfis> smmmOfis;

	@GetMapping("/users")
	public String getAddUserForm(Model model, Principal principal) {
		myUser = myUserService.getMyUserByUsername(principal.getName());
		smmmOfis = smmmOfisService.getFirstSmmmOfis();
		if (!smmmOfis.isEmpty()) {
			model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan() + " " + smmmOfis.get().getFullName());
			model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			model.addAttribute("fullusername", smmmOfis.get().getUserName());
			model.addAttribute("gorev", smmmOfis.get().getUnvan());

			model.addAttribute("updateBtnActive", true);
			model.addAttribute("smmmOfisId", smmmOfis.get().getId());
			model.addAttribute("smmmOfis", smmmOfis.get());

			model.addAttribute("userDto", new UserDto());
			List<MyUser> dbUsers = myUserService.findAllUsers();
			model.addAttribute("dbUsers", dbUsers);
			List<String> roles = new ArrayList<>(Arrays.asList("ADMIN", "USER", "CUSTOMER"));
			model.addAttribute("roles", roles);
			
			model.addAttribute("currentUser", myUser);

			return "adminpanel/homepagesettings/add-user";

		} else {

			model.addAttribute("dashboardtitle", "SMMM Ofis Yönetim Sistemi");
			model.addAttribute("smmmisim", "Smmm İsim");
			model.addAttribute("fullusername", "Smmm AdSoyad");
			model.addAttribute("gorev", "SMMM");
			model.addAttribute("updateBtnActive", false);
			model.addAttribute("smmmOfis", new SmmmOfis());

			model.addAttribute("userDto", new UserDto());
			List<MyUser> dbUsers = myUserService.findAllUsers();
			model.addAttribute("dbUsers", dbUsers);
			List<String> roles = new ArrayList<>(Arrays.asList("ADMIN", "USER", "CUSTOMER"));
			model.addAttribute("roles", roles);
			
			model.addAttribute("currentUser", myUser);
		}

		return "adminpanel/homepagesettings/add-user";
	}

	@PostMapping("/users")
	public String saveUserDto(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result, Model model,
			RedirectAttributes redirectAttr, @RequestParam("stringResim") MultipartFile file) throws IOException {

		
		boolean userExist = myUserService.userExistForNewUser(userDto.getUsername());

		if (userExist) {
			
			model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan() + " " + smmmOfis.get().getFullName());
			model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			model.addAttribute("fullusername", smmmOfis.get().getUserName());
			model.addAttribute("gorev", smmmOfis.get().getUnvan());

			model.addAttribute("userDto", userDto);
			List<MyUser> dbUsers = myUserService.findAllUsers();
			model.addAttribute("dbUsers", dbUsers);
			List<String> roles = new ArrayList<>(Arrays.asList("ADMIN", "USER", "CUSTOMER"));
			model.addAttribute("roles", roles);

			model.addAttribute("existUsername", userDto.getUsername());
			model.addAttribute("userExist", userExist);
			
			model.addAttribute("currentUser", myUser);
			model.addAttribute("smmmOfis", smmmOfis.get());
			
			return "adminpanel/homepagesettings/add-user";
		}

		if (result.hasErrors()) {
			
			model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan() + " " + smmmOfis.get().getFullName());
			model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			model.addAttribute("fullusername", smmmOfis.get().getUserName());
			model.addAttribute("gorev", smmmOfis.get().getUnvan());

			model.addAttribute("userDto", userDto);
			List<MyUser> dbUsers = myUserService.findAllUsers();
			model.addAttribute("dbUsers", dbUsers);
			List<String> roles = new ArrayList<>(Arrays.asList("ADMIN", "USER", "CUSTOMER"));
			model.addAttribute("roles", roles);
			
			model.addAttribute("currentUser", myUser);
			model.addAttribute("smmmOfis", smmmOfis.get());

			return "adminpanel/homepagesettings/add-user";
		}

		if(file.getBytes()!=null && !file.getOriginalFilename().isEmpty()) {
			MyUser user = MyUser.builder()
					.id(userDto.getId())
					.image(Base64.getEncoder().encodeToString(file.getBytes()))
					.firstname(userDto.getFirstname())
					.lastname(userDto.getLastname())
					.email(userDto.getEmail())
					.about(userDto.getAbout())
					.username(userDto.getUsername())
					.password(pwdEncoder.encode(userDto.getPassword()))
					.openpassword(userDto.getPassword())
					.roles(userDto.getRoles())
					.accountNonExpired(userDto.isAccountNonExpired())
					.accountNonLocked(userDto.isAccountNonLocked())
					.credentialsNonExpired(userDto.isCredentialsNonExpired())
					.enabled(userDto.isEnabled()).build();
			myUserService.saveMyUser(user);
		}else {
			MyUser user = MyUser.builder()
					.id(userDto.getId())
					.firstname(userDto.getFirstname())
					.lastname(userDto.getLastname())
					.email(userDto.getEmail())
					.about(userDto.getAbout())
					.username(userDto.getUsername())
					.password(pwdEncoder.encode(userDto.getPassword()))
					.openpassword(userDto.getPassword())
					.roles(userDto.getRoles())
					.accountNonExpired(userDto.isAccountNonExpired())
					.accountNonLocked(userDto.isAccountNonLocked())
					.credentialsNonExpired(userDto.isCredentialsNonExpired())
					.enabled(userDto.isEnabled()).build();
			myUserService.saveMyUser(user);
		}

		return "redirect:/api/v1/users";
	}

	@PostMapping("/update/user")
	public String updateUserDtoForModal(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result,
			Model model, RedirectAttributes redirectAttr, @RequestParam("stringResim") MultipartFile file) throws IOException {

		
		boolean userExist = myUserService.userExistForUpdate(userDto.getUsername(), userDto.getId());
		MyUser updateUser = myUserService.getMyUserById(userDto.getId()).get();
		if (userExist) {
			
			model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan() + " " + smmmOfis.get().getFullName());
			model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			model.addAttribute("fullusername", smmmOfis.get().getUserName());
			model.addAttribute("gorev", smmmOfis.get().getUnvan());
			
			model.addAttribute("userDto", userDto);
			List<MyUser> dbUsers = myUserService.findAllUsers();
			model.addAttribute("dbUsers", dbUsers);
			List<String> roles = new ArrayList<>(Arrays.asList("ADMIN", "USER", "CUSTOMER"));
			model.addAttribute("roles", roles);

			redirectAttr.addFlashAttribute("existUsername", userDto.getUsername());
			redirectAttr.addFlashAttribute("userExist", true);
			
			model.addAttribute("currentUser", myUser);
			model.addAttribute("smmmOfis", smmmOfis.get());
			
			return "redirect:/api/v1/users";
		}

		if (result.hasErrors()) {
			
			model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan() + " " + smmmOfis.get().getFullName());
			model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			model.addAttribute("fullusername", smmmOfis.get().getUserName());
			model.addAttribute("gorev", smmmOfis.get().getUnvan());
			
			model.addAttribute("userDto", userDto);
			List<MyUser> dbUsers = myUserService.findAllUsers();
			model.addAttribute("dbUsers", dbUsers);
			List<String> roles = new ArrayList<>(Arrays.asList("ADMIN", "USER", "CUSTOMER"));
			model.addAttribute("roles", roles);
			model.addAttribute("currentUser", myUser);
			model.addAttribute("smmmOfis", smmmOfis.get());
			
			return "adminpanel/homepagesettings/add-user";
		}

		if(file.getBytes()!=null && !file.getOriginalFilename().isEmpty()) {
			MyUser user = MyUser.builder()
					.id(userDto.getId())
					.image(Base64.getEncoder().encodeToString(file.getBytes()))
					.firstname(userDto.getFirstname())
					.lastname(userDto.getLastname())
					.email(userDto.getEmail())
					.about(userDto.getAbout())
					.username(userDto.getUsername())
					.password(pwdEncoder.encode(userDto.getPassword()))
					.openpassword(userDto.getPassword())
					.roles(userDto.getRoles())
					.accountNonExpired(userDto.isAccountNonExpired())
					.accountNonLocked(userDto.isAccountNonLocked())
					.credentialsNonExpired(userDto.isCredentialsNonExpired())
					.enabled(userDto.isEnabled()).build();
			myUserService.saveMyUser(user);
		}else {
			MyUser user = MyUser.builder()
					.id(userDto.getId())
					.image(updateUser.getImage())
					.firstname(userDto.getFirstname())
					.lastname(userDto.getLastname())
					.email(userDto.getEmail())
					.about(userDto.getAbout())
					.username(userDto.getUsername())
					.password(pwdEncoder.encode(userDto.getPassword()))
					.openpassword(userDto.getPassword())
					.roles(userDto.getRoles())
					.accountNonExpired(userDto.isAccountNonExpired())
					.accountNonLocked(userDto.isAccountNonLocked())
					.credentialsNonExpired(userDto.isCredentialsNonExpired())
					.enabled(userDto.isEnabled()).build();
			myUserService.saveMyUser(user);
		}
		

		return "redirect:/api/v1/users";
	}

	@GetMapping("/get/user/{id}")
	@ResponseBody
	public MyUser getMethodName(@PathVariable Integer id) {
		return myUserService.getMyUserById(id).get();
	}

	// @PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/delete/user/{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
	public String deleteUserById(@PathVariable("id") Integer id) {
		myUserService.deleteMyUserById(id);
		return "redirect:/api/v1/users";
	}
	
	
}
