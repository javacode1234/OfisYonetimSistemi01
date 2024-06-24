package com.ofisyonetimsistemi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ofisyonetimsistemi.models.User;



@Controller
public class HomeController {
	
	
	@GetMapping("/index1")
	public String getHomePage1(Model model) {
		model.addAttribute("title","SMMM Ofis Yönetim Sistemi.");
		model.addAttribute("ilkMesaj","Yeni Proje ilk mesaj!!!");
		
		List<User> userList = new ArrayList<>();
		
		User user1 = new User(1,"Muammer","uzun","mumyuzun@hotmail.com","muammer","pwd1");
		User user2 = new User(2,"Muammer","uzun","mumyuzun@hotmail.com","muammer","pwd2");
		User user3 = new User(3,"Muammer","uzun","mumyuzun@hotmail.com","muammer","pwd3");
		User user4 = new User(4,"Muammer","uzun","mumyuzun@hotmail.com","muammer","pwd4");
		
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		userList.add(user4);
			
		model.addAttribute("users",userList);
		return "index1";
	}
	
	@GetMapping("/")
	public String getHomePage(Model model) {
		model.addAttribute("title","SMMM Muammer UZUN");
		model.addAttribute("brandText","SMMMOYS");
		return "index";
		
	}
}
