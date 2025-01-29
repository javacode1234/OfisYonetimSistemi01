package com.ofisyonetimsistemi.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.models.SmmmOfisAskedQuestions;
import com.ofisyonetimsistemi.security.model.MyUser;
import com.ofisyonetimsistemi.security.service.MyUserService;
import com.ofisyonetimsistemi.services.SmmmOfisAskedQuestionsService;
import com.ofisyonetimsistemi.services.SmmmOfisMessageService;
import com.ofisyonetimsistemi.services.SmmmOfisService;

@Controller
@RequestMapping("/api/v1/")
public class HomePageAskedQuestionsController {

	@Autowired private SmmmOfisService smmmOfisHomePageService;
	@Autowired private SmmmOfisAskedQuestionsService hpFaqService;
	@Autowired private MyUserService myUserService;
	@Autowired private SmmmOfisMessageService messageService;
	

	@GetMapping("/smmm-homepage-faq-settings")
	public String getMethodName(Model model, Principal principal) {
		MyUser myUser = myUserService.getMyUserByUsername(principal.getName());
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		if (!smmmOfis.isEmpty()) {
			model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan() + " " + smmmOfis.get().getFullName());
			model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			model.addAttribute("fullusername", smmmOfis.get().getUserName());
			model.addAttribute("gorev", smmmOfis.get().getUnvan());

			model.addAttribute("smmmOfis", smmmOfis.get());
			model.addAttribute("hpFaq", new SmmmOfisAskedQuestions());
			model.addAttribute("hpFaqList", hpFaqService.getAll());
			
			model.addAttribute("currentUser", myUser);
			model.addAttribute("messageCount", messageService.countOfRecord());
			model.addAttribute("countOfNonReadMessages", messageService.countOfRecordReaded(false));
			
			return "adminpanel/homepagesettings/homepage-faq-settings";

		} else {

			model.addAttribute("dashboardtitle", "SMMM Muammer UZUN");
			model.addAttribute("smmmisim", "Muammer UZUN");
			model.addAttribute("fullusername", "Muammer UZUN");
			model.addAttribute("gorev", "SMMM");
			model.addAttribute("smmmOfis", new SmmmOfis());
			
			model.addAttribute("currentUser", myUser);
			model.addAttribute("messageCount", messageService.countOfRecord());
			model.addAttribute("countOfNonReadMessages", messageService.countOfRecordReaded(false));
		}

		return "adminpanel/homepagesettings/homepage-faq-settings";
	}

	@GetMapping("/get-homepage-faq/{id}")
	@ResponseBody
	public SmmmOfisAskedQuestions getFaqById(@PathVariable("id") Integer id) {
		return hpFaqService.getById(id).get();
	}

	@PostMapping("/save-homepage-faq")
	public String saveHpFaq(@ModelAttribute("hpFaq") SmmmOfisAskedQuestions askedQuestion) {

		hpFaqService.save(askedQuestion);

		return "redirect:/api/v1/smmm-homepage-faq-settings";
	}

	@PostMapping("/update-homepage-faq")
	public String updateHpFaq(@ModelAttribute("hpFaq") SmmmOfisAskedQuestions askedQuestion) {

		hpFaqService.save(askedQuestion);

		return "redirect:/api/v1/smmm-homepage-faq-settings";
	}

	@RequestMapping(value = "/delete-homepage-faq/{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
	public String delHpFaqById(@PathVariable("id") Integer id) {
		hpFaqService.deleteById(id);
		return "redirect:/api/v1/smmm-homepage-faq-settings";
	}

}
