package com.ofisyonetimsistemi.controllers;

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
import com.ofisyonetimsistemi.services.SmmmOfisAskedQuestionsService;
import com.ofisyonetimsistemi.services.SmmmOfisService;

@Controller
@RequestMapping("/api/v1/")
public class HomePageAskedQuestionsController {

	@Autowired
	private SmmmOfisService smmmOfisHomePageService;
	@Autowired
	private SmmmOfisAskedQuestionsService hpFaqService;

	@GetMapping("/smmm-homepage-faq-settings")
	public String getMethodName(Model model) {
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		if (!smmmOfis.isEmpty()) {
			model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan() + " " + smmmOfis.get().getFullName());
			model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			model.addAttribute("fullusername", smmmOfis.get().getUserName());
			model.addAttribute("gorev", smmmOfis.get().getUnvan());

			model.addAttribute("smmmOfis", smmmOfis.get());
			model.addAttribute("hpFaq", new SmmmOfisAskedQuestions());
			model.addAttribute("hpFaqList", hpFaqService.getAll());

			return "adminpanel/homepage-faq-settings";

		} else {

			model.addAttribute("dashboardtitle", "SMMM Muammer UZUN");
			model.addAttribute("smmmisim", "Muammer UZUN");
			model.addAttribute("fullusername", "Muammer UZUN");
			model.addAttribute("gorev", "SMMM");
			model.addAttribute("smmmOfis", new SmmmOfis());
		}

		return "adminpanel/homepage-faq-settings";
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
