package com.ofisyonetimsistemi.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ofisyonetimsistemi.security.model.MyUser;
import com.ofisyonetimsistemi.security.repository.MyUserRepository;


@Controller
@RequestMapping("/register")
public class UserRegisterController {

	@Autowired private MyUserRepository userRepo;
	@Autowired private PasswordEncoder pwdEncoder;
	
	@PostMapping("/user")
	@ResponseBody
	public MyUser registerUser(@RequestBody MyUser user) {
		user.setPassword(pwdEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
	
}
