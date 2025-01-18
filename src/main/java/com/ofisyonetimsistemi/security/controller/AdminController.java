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
import com.ofisyonetimsistemi.security.dto.UserProfileDto;
import com.ofisyonetimsistemi.security.model.MyUser;

import com.ofisyonetimsistemi.security.service.MyUserService;
import com.ofisyonetimsistemi.services.SmmmOfisService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/")
public class AdminController {

	@Autowired private SmmmOfisService smmmOfisService;
	@Autowired private MyUserService myUserService;
	@Autowired private PasswordEncoder pwdEncoder;

	@GetMapping("/users")
	public String getAddUserForm(Model model, Principal principal) {
		MyUser myUser = myUserService.getMyUserByUsername(principal.getName());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		if (!smmmOfis.isEmpty()) {

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
			RedirectAttributes redirectAttr, @RequestParam("stringResim") MultipartFile file, Principal principal) throws IOException {
		
		MyUser myUser = myUserService.getMyUserByUsername(principal.getName());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		boolean userExist = myUserService.userExistForNewUser(userDto.getUsername());

		if (userExist) {
			
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

		return "redirect:/api/v1/users";
	}

	@PostMapping("/update/user")
	public String updateUserDtoForModal(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result,
			Model model, RedirectAttributes redirectAttr, @RequestParam("stringResim") MultipartFile file, Principal principal) throws IOException {

		MyUser myUser = myUserService.getMyUserByUsername(principal.getName());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		boolean userExist = myUserService.userExistForUpdate(userDto.getUsername(), userDto.getId());
		MyUser updateUser = myUserService.getMyUserById(userDto.getId()).get();
		if (userExist) {
			
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
		}else {
			MyUser user = MyUser.builder()
					.id(userDto.getId())
					.image(updateUser.getImage())
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
		

		return "redirect:/api/v1/users";
	}

	@GetMapping("/get/user/{id}")
	@ResponseBody
	public MyUser getUserById(@PathVariable Integer id) {
		return myUserService.getMyUserById(id).get();
	}

	@RequestMapping(value = "/delete/user/{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
	public String deleteUserById(@PathVariable("id") Integer id) {
		myUserService.deleteMyUserById(id);
		return "redirect:/api/v1/users";
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
										Principal principal
									
									) throws IOException {
		
		MyUser myUser = myUserService.getMyUserByUsername(principal.getName());
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		
		boolean userExist = myUserService.userExistForUpdate(username, myUser.getId());
		MyUser updateUser = myUserService.getMyUserById(myUser.getId()).get();
		if (userExist) {
			
			model.addAttribute("userProfileDto", userProfileDto);
			redirectAttr.addFlashAttribute("existUsername", userProfileDto.getUsername());
			redirectAttr.addFlashAttribute("userExist", true);
			model.addAttribute("currentUser", myUser);
			model.addAttribute("smmmOfis", smmmOfis.get());
			
			return "redirect:/api/v1/user-profile-edit";
		}

		if(file.getBytes()!=null && !file.getOriginalFilename().isEmpty()) {
			MyUser myuser = MyUser.builder()
					.id(updateUser.getId())
					.image(Base64.getEncoder().encodeToString(file.getBytes()))
					.username(username)
					.password(updateUser.getPassword())
					.openpassword(updateUser.getOpenpassword())
					.roles(updateUser.getRoles())
					.enabled(updateUser.isEnabled())
					.accountNonExpired(updateUser.isAccountNonExpired())
					.accountNonLocked(updateUser.isAccountNonLocked())
					.credentialsNonExpired(updateUser.isCredentialsNonExpired())
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
					.id(updateUser.getId())
					.image(updateUser.getImage())
					.username(username)
					.password(updateUser.getPassword())
					.openpassword(updateUser.getOpenpassword())
					.roles(updateUser.getRoles())
					.enabled(updateUser.isEnabled())
					.accountNonExpired(updateUser.isAccountNonExpired())
					.accountNonLocked(updateUser.isAccountNonLocked())
					.credentialsNonExpired(updateUser.isCredentialsNonExpired())
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

		return "redirect:/api/v1/user-profile-edit";
	}
	
	@PostMapping("update/user-profile-settings")
	public String updateUserSettings(
									@RequestParam(value="enabled", required=false)boolean enabled,
									@RequestParam(value="accountNonExpired", required=false)boolean accountNonExpired,
									@RequestParam(value="accountNonLocked", required=false)boolean accountNonLocked,
									@RequestParam(value="credentialsNonExpired", required=false)boolean credentialsNonExpired,
									Model model, RedirectAttributes redirectAttr, 
									Principal principal
									) {
		MyUser myDbUser = myUserService.getMyUserByUsername(principal.getName());
		
		MyUser myUser = MyUser.builder()
				.id(myDbUser.getId())
				.image(myDbUser.getImage())
				.username(myDbUser.getUsername())
				.password(myDbUser.getPassword())
				.openpassword(myDbUser.getOpenpassword())
				.roles(myDbUser.getRoles())
				.enabled(enabled)
				.accountNonExpired(accountNonExpired)
				.accountNonLocked(accountNonLocked)
				.credentialsNonExpired(credentialsNonExpired)
				.firstname(myDbUser.getFirstname())
				.lastname(myDbUser.getLastname())
				.email(myDbUser.getEmail())
				.about(myDbUser.getAbout())
				.x(myDbUser.getX())
				.f(myDbUser.getF())
				.i(myDbUser.getI())
				.l(myDbUser.getL())
				.company(myDbUser.getCompany())
				.country(myDbUser.getCountry())
				.adres(myDbUser.getAdres())
				.telefon(myDbUser.getTelefon())
				.job(myDbUser.getJob())
				.build();
		myUserService.saveMyUser(myUser);			

	return "redirect:/api/v1/user-profile-settings";		
		
	}

	@PostMapping("/update/user-password")
	public String updateUserPassword(@RequestParam("password")String password, Principal principal) {
		
		MyUser myDbUser = myUserService.getMyUserByUsername(principal.getName());
		
		MyUser myUser = MyUser.builder()
				.id(myDbUser.getId())
				.image(myDbUser.getImage())
				.username(myDbUser.getUsername())
				.password(pwdEncoder.encode(password))
				.openpassword(password)
				.roles(myDbUser.getRoles())
				.enabled(myDbUser.isEnabled())
				.accountNonExpired(myDbUser.isAccountNonExpired())
				.accountNonLocked(myDbUser.isAccountNonLocked())
				.credentialsNonExpired(myDbUser.isCredentialsNonExpired())
				.firstname(myDbUser.getFirstname())
				.lastname(myDbUser.getLastname())
				.email(myDbUser.getEmail())
				.about(myDbUser.getAbout())
				.x(myDbUser.getX())
				.f(myDbUser.getF())
				.i(myDbUser.getI())
				.l(myDbUser.getL())
				.company(myDbUser.getCompany())
				.country(myDbUser.getCountry())
				.adres(myDbUser.getAdres())
				.telefon(myDbUser.getTelefon())
				.job(myDbUser.getJob())
				.build();
		myUserService.saveMyUser(myUser);
		
		return "redirect:/api/v1/user-profile-changepwd";
	}
	
}
