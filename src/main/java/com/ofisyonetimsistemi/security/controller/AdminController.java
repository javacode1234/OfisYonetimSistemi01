package com.ofisyonetimsistemi.security.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.ofisyonetimsistemi.security.dto.UserProfileDto;
import com.ofisyonetimsistemi.security.model.MyUser;
import com.ofisyonetimsistemi.security.model.MyUserDetails;
import com.ofisyonetimsistemi.security.service.MyUserService;
import com.ofisyonetimsistemi.services.SmmmOfisMessageService;
import com.ofisyonetimsistemi.services.SmmmOfisNoteService;
import com.ofisyonetimsistemi.services.SmmmOfisNotificationService;
import com.ofisyonetimsistemi.services.SmmmOfisService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cp/")
public class AdminController {

	@Autowired private SmmmOfisService smmmOfisService;
	@Autowired private MyUserService myUserService;
	@Autowired private PasswordEncoder pwdEncoder;
	@Autowired private SmmmOfisMessageService messageService;
	@Autowired private SmmmOfisNotificationService notificationService;
	@Autowired private SmmmOfisNoteService noteService;
	
	@GetMapping("/users")
	public String getAddUserForm(Model model, Principal principal, @AuthenticationPrincipal MyUserDetails loggedUser) {
		
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		if (!smmmOfis.isEmpty()) {

			model.addAttribute("updateBtnActive", true);
			model.addAttribute("smmmOfis", smmmOfis.get());

			model.addAttribute("userDto", new UserDto());
			List<MyUser> dbUsers = myUserService.findAllUsers();
			model.addAttribute("dbUsers", dbUsers);
			List<String> roles = new ArrayList<>(Arrays.asList("ADMIN", "USER", "CUSTOMER"));
			model.addAttribute("roles", roles);
			
			model.addAttribute("currentUser", currentUser);
			
			loadRequaredCommenItems(model);

			return "adminpanel/homepagesettings/add-user";

		} else {

			model.addAttribute("updateBtnActive", false);
			model.addAttribute("smmmOfis", new SmmmOfis());

			model.addAttribute("userDto", new UserDto());
			List<MyUser> dbUsers = myUserService.findAllUsers();
			model.addAttribute("dbUsers", dbUsers);
			List<String> roles = new ArrayList<>(Arrays.asList("ADMIN", "USER", "CUSTOMER"));
			model.addAttribute("roles", roles);
			
			model.addAttribute("currentUser", currentUser);
			
			loadRequaredCommenItems(model);
		}

		return "adminpanel/homepagesettings/add-user";
	}

	@PostMapping("/users")
	public String saveUserDto(
			
								@Valid @ModelAttribute("userDto") UserDto userDto, 
								BindingResult result, 
								Model model,
								RedirectAttributes redirectAttr, 
								@RequestParam("stringResim") MultipartFile file, 
								Principal principal, 
								@AuthenticationPrincipal MyUserDetails loggedUser
								
							) throws IOException {
		
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		boolean userNameExist = myUserService.userExistForNewUser(userDto.getUsername());

		if (userNameExist) {
			
			model.addAttribute("userDto", userDto);
			List<MyUser> dbUsers = myUserService.findAllUsers();
			model.addAttribute("dbUsers", dbUsers);
			List<String> roles = new ArrayList<>(Arrays.asList("ADMIN", "USER", "CUSTOMER"));
			model.addAttribute("roles", roles);

			model.addAttribute("existUsername", userDto.getUsername());
			model.addAttribute("userNameExist", userNameExist);
			
			model.addAttribute("currentUser", currentUser);
			
			model.addAttribute("smmmOfis", smmmOfis.get());
			
			loadRequaredCommenItems(model);
			
			return "adminpanel/homepagesettings/add-user";
		}

		if (result.hasErrors()) {
			
			model.addAttribute("userDto", userDto);
			List<MyUser> dbUsers = myUserService.findAllUsers();
			model.addAttribute("dbUsers", dbUsers);
			List<String> roles = new ArrayList<>(Arrays.asList("ADMIN", "USER", "CUSTOMER"));
			model.addAttribute("roles", roles);
			
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("smmmOfis", smmmOfis.get());
			
			loadRequaredCommenItems(model);

			return "adminpanel/homepagesettings/add-user";
		}

		if(file.getBytes()!=null && !file.getOriginalFilename().isEmpty()) {
			MyUser user = MyUser.builder()
					.id(userDto.getId())
					.image(Base64.getEncoder().encodeToString(file.getBytes()))
					.firstname(userDto.getFirstname())
					.lastname(userDto.getLastname())
					.email(userDto.getEmail())
					.x(userDto.getX())
					.f(userDto.getF())
					.i(userDto.getI())
					.l(userDto.getL())
					.company(userDto.getCompany())
					.country(userDto.getCountry())
					.adres(userDto.getAdres())
					.telefon(userDto.getTelefon())
					.job(userDto.getJob())
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
					.x(userDto.getX())
					.f(userDto.getF())
					.i(userDto.getI())
					.l(userDto.getL())
					.company(userDto.getCompany())
					.country(userDto.getCountry())
					.adres(userDto.getAdres())
					.telefon(userDto.getTelefon())
					.job(userDto.getJob())
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

		return "redirect:/cp/users";
	}

	@PostMapping("/update/user")
	public String updateUserDto(
			
									@Valid @ModelAttribute("userDto") UserDto userDto, 
									BindingResult result,
									Model model, 
									RedirectAttributes redirectAttr, 
									@RequestParam("stringResim") MultipartFile file, 
									Principal principal,
									@AuthenticationPrincipal MyUserDetails loggedUser
												
								) throws IOException {

		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		boolean userNameExist = myUserService.userExistForUpdate(userDto.getUsername(), userDto.getId());
		MyUser updateThisUser = myUserService.getMyUserById(userDto.getId()).get();
		
		if (userNameExist) {
			
			model.addAttribute("userDto", userDto);
			List<MyUser> dbUsers = myUserService.findAllUsers();
			model.addAttribute("dbUsers", dbUsers);
			List<String> roles = new ArrayList<>(Arrays.asList("ADMIN", "USER", "CUSTOMER"));
			model.addAttribute("roles", roles);

			redirectAttr.addFlashAttribute("existUsername", userDto.getUsername());
			redirectAttr.addFlashAttribute("userNameExist", true);
			
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("smmmOfis", smmmOfis.get());
			
			return "redirect:/cp/users";
		}

		if (result.hasErrors()) {
			
			model.addAttribute("userDto", userDto);
			List<MyUser> dbUsers = myUserService.findAllUsers();
			model.addAttribute("dbUsers", dbUsers);
			List<String> roles = new ArrayList<>(Arrays.asList("ADMIN", "USER", "CUSTOMER"));
			model.addAttribute("roles", roles);
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("smmmOfis", smmmOfis.get());
			
			loadRequaredCommenItems(model);
			
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
					.x(userDto.getX())
					.f(userDto.getF())
					.i(userDto.getI())
					.l(userDto.getL())
					.company(userDto.getCompany())
					.country(userDto.getCountry())
					.adres(userDto.getAdres())
					.telefon(userDto.getTelefon())
					.job(userDto.getJob())
					.username(userDto.getUsername())
					.password(pwdEncoder.encode(userDto.getPassword()))
					.openpassword(userDto.getPassword())
					.roles(userDto.getRoles())
					.accountNonExpired(userDto.isAccountNonExpired())
					.accountNonLocked(userDto.isAccountNonLocked())
					.credentialsNonExpired(userDto.isCredentialsNonExpired())
					.enabled(userDto.isEnabled())
					.build();
			myUserService.saveMyUser(user);
		}else {
			MyUser user = MyUser.builder()
					.id(userDto.getId())
					.image(updateThisUser.getImage())
					.firstname(userDto.getFirstname())
					.lastname(userDto.getLastname())
					.email(userDto.getEmail())
					.about(userDto.getAbout())
					.x(userDto.getX())
					.f(userDto.getF())
					.i(userDto.getI())
					.l(userDto.getL())
					.company(userDto.getCompany())
					.country(userDto.getCountry())
					.adres(userDto.getAdres())
					.job(userDto.getJob())
					.telefon(userDto.getTelefon())
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
		
		return principal.getName().equals(userDto.getUsername()) ? "redirect:/cp/users" : "redirect:/login";
	}

	@GetMapping("/get/user/{id}")
	@ResponseBody
	public MyUser getUserById(@PathVariable Integer id) {
		return myUserService.getMyUserById(id).get();
	}

	@RequestMapping(value = "/delete/user/{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
	public String deleteUserById(@PathVariable("id") Integer id) {
		myUserService.deleteMyUserById(id);
		return "redirect:/cp/users";
	}
	
	@PostMapping("/update/user-profile")
	public String updateUserProfile(
										
										@ModelAttribute("userProfileDto") UserProfileDto userProfileDto,
										@RequestParam("stringResim") MultipartFile file,
										@RequestParam("username")String username,
										@RequestParam("firstname")String firstname,
										@RequestParam("lastname")String lastname,
										@RequestParam("about")String about,
										@RequestParam("company")String company,
										@RequestParam("job")String job,
										@RequestParam("country")String country,
										@RequestParam("adres")String adres,
										@RequestParam("telefon")String telefon,
										@RequestParam("email")String email,
										@RequestParam("x")String x,
										@RequestParam("f")String f,
										@RequestParam("i")String i,
										@RequestParam("l")String l,
										Model model, RedirectAttributes redirectAttr, 
										Principal principal,
										@AuthenticationPrincipal MyUserDetails loggedUser
									
									) throws IOException {
		
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		boolean userNameExist = myUserService.userExistForUpdate(username, currentUser.getId());
		MyUser updateThisUser = myUserService.getMyUserById(currentUser.getId()).get();
		if (userNameExist) {
			
			model.addAttribute("userProfileDto", userProfileDto);
			redirectAttr.addFlashAttribute("existUsername", userProfileDto.getUsername());
			redirectAttr.addFlashAttribute("userNameExist", true);
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("smmmOfis", smmmOfis.get());
			
			return "redirect:/cp/user-profile-edit";
		}

		if(file.getBytes()!=null && !file.getOriginalFilename().isEmpty()) {
			MyUser myuser = MyUser.builder()
					.id(updateThisUser.getId())
					.image(Base64.getEncoder().encodeToString(file.getBytes()))
					.username(username)
					.password(updateThisUser.getPassword())
					.openpassword(updateThisUser.getOpenpassword())
					.roles(updateThisUser.getRoles())
					.enabled(updateThisUser.isEnabled())
					.accountNonExpired(updateThisUser.isAccountNonExpired())
					.accountNonLocked(updateThisUser.isAccountNonLocked())
					.credentialsNonExpired(updateThisUser.isCredentialsNonExpired())
					.firstname(firstname)
					.lastname(lastname)
					.email(email)
					.about(about)
					.x(x)
					.f(f)
					.i(i)
					.l(l)
					.company(company)
					.country(country)
					.adres(adres)
					.telefon(telefon)
					.job(job)
					.build();
			myUserService.saveMyUser(myuser);
		}else {
			MyUser myuser = MyUser.builder()
					.id(updateThisUser.getId())
					.image(updateThisUser.getImage())
					.username(username)
					.password(updateThisUser.getPassword())
					.openpassword(updateThisUser.getOpenpassword())
					.roles(updateThisUser.getRoles())
					.enabled(updateThisUser.isEnabled())
					.accountNonExpired(updateThisUser.isAccountNonExpired())
					.accountNonLocked(updateThisUser.isAccountNonLocked())
					.credentialsNonExpired(updateThisUser.isCredentialsNonExpired())
					.firstname(firstname)
					.lastname(lastname)
					.email(email)
					.about(about)
					.x(x)
					.f(f)
					.i(i)
					.l(l)
					.company(company)
					.country(country)
					.adres(adres)
					.telefon(telefon)
					.job(job)
					.build();
			
			myUserService.saveMyUser(myuser);
		}	
		
		return principal.getName().equals(username) ? "redirect:/cp/user-profile-edit" : "redirect:/login"; 

	}
	
	@PostMapping("/update/user-profile-settings")
	public String updateUserSettings(
			
										@RequestParam(value="enabled", required=false)boolean enabled,
										@RequestParam(value="accountNonExpired", required=false)boolean accountNonExpired,
										@RequestParam(value="accountNonLocked", required=false)boolean accountNonLocked,
										@RequestParam(value="credentialsNonExpired", required=false)boolean credentialsNonExpired,
										Model model, RedirectAttributes redirectAttr, 
										Principal principal,
										@AuthenticationPrincipal MyUserDetails loggedUser
										
									) {
		
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		
		MyUser myUser = MyUser.builder()
				.id(currentUser.getId())
				.image(currentUser.getImage())
				.username(currentUser.getUsername())
				.password(currentUser.getPassword())
				.openpassword(currentUser.getOpenpassword())
				.roles(currentUser.getRoles())
				.enabled(enabled)
				.accountNonExpired(accountNonExpired)
				.accountNonLocked(accountNonLocked)
				.credentialsNonExpired(credentialsNonExpired)
				.firstname(currentUser.getFirstname())
				.lastname(currentUser.getLastname())
				.email(currentUser.getEmail())
				.about(currentUser.getAbout())
				.x(currentUser.getX())
				.f(currentUser.getF())
				.i(currentUser.getI())
				.l(currentUser.getL())
				.company(currentUser.getCompany())
				.country(currentUser.getCountry())
				.adres(currentUser.getAdres())
				.telefon(currentUser.getTelefon())
				.job(currentUser.getJob())
				.build();
		myUserService.saveMyUser(myUser);			

	return "redirect:/cp/user-profile-settings";		
		
	}

	@PostMapping("/update/user-password")
	public String updateUserPassword(
			
										@RequestParam("password")String password, 
										Principal principal, 
										@AuthenticationPrincipal MyUserDetails loggedUser
									
									) {
		
		MyUser currentUser = myUserService.getMyUserByUsername(loggedUser.getUsername());
		
		MyUser myUser = MyUser.builder()
				.id(currentUser.getId())
				.image(currentUser.getImage())
				.username(currentUser.getUsername())
				.password(pwdEncoder.encode(password))
				.openpassword(password)
				.roles(currentUser.getRoles())
				.enabled(currentUser.isEnabled())
				.accountNonExpired(currentUser.isAccountNonExpired())
				.accountNonLocked(currentUser.isAccountNonLocked())
				.credentialsNonExpired(currentUser.isCredentialsNonExpired())
				.firstname(currentUser.getFirstname())
				.lastname(currentUser.getLastname())
				.email(currentUser.getEmail())
				.about(currentUser.getAbout())
				.x(currentUser.getX())
				.f(currentUser.getF())
				.i(currentUser.getI())
				.l(currentUser.getL())
				.company(currentUser.getCompany())
				.country(currentUser.getCountry())
				.adres(currentUser.getAdres())
				.telefon(currentUser.getTelefon())
				.job(currentUser.getJob())
				.build();
		myUserService.saveMyUser(myUser);
		
		return "redirect:/cp/user-profile-changepwd";
	}
	
	public void loadRequaredCommenItems(Model model) {
		
		model.addAttribute("countOfUnReadMessages", messageService.countOfUnReadMessages(false));
		model.addAttribute("listOfUnreadMessages", messageService.getAllUnReadMessages());
		
		model.addAttribute("countOfUnReadNotifications", notificationService.countOfUnReadNotifications(false));
		model.addAttribute("listOfUnreadNotifications", notificationService.getAllUnReadNotifications());
		
		model.addAttribute("countOfUnReadNotes", noteService.countOfUnReadNotes());
		model.addAttribute("listOfUnReadNotes", noteService.getAllUnreadNotes());
		
	}
	
}
