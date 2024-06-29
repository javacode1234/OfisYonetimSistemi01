package com.ofisyonetimsistemi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {
	
	
		
	@GetMapping("/")
	public String getHomePage(Model model) {
		model.addAttribute("title","SMMM Muammer UZUN");
		model.addAttribute("brandText","SMMM_OYS");
		model.addAttribute("anaSayfaBaslik","Serbest Muhasebeci Mali Müşavir Ofis Yönetim Sistemi.");
		model.addAttribute("anaSayfaAltBaslik","Nerede olursanız olun müşterilerinizin bilgilerine ulaşın. Müşteriler de kendi durumlarını görsün.");
		model.addAttribute("videoLink","https://www.youtube.com/watch?v=tuus0A9u2jo");
		model.addAttribute("adres","Yakuplu Mah. 100. Sk. No:30/B Beylikdüzü / İstanbul");
		model.addAttribute("telefon","538 332 63 34");
		model.addAttribute("email","mumyuzun@hotmail.com");
		model.addAttribute("konum","https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d752.876889483895!2d28.670593283877572!3d40.99226275881076!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x14b55f89aa23eed9%3A0xcd20d5bf724b110b!2sYakuplu%2C%20100.%20Sk.%2030%20B%2C%2034524%20Beylikd%C3%BCz%C3%BC%2F%C4%B0stanbul!5e0!3m2!1str!2str!4v1719420695704!5m2!1str!2str");
		
		return "index";
		
	}
	
	@GetMapping("/portfolio-details")
	public String portfolioDetails(Model model) {
		model.addAttribute("title","SMMM Muammer UZUN");
		model.addAttribute("brandText","SMMM_OYS");
		return "portfolio-details";
	}
	
	@GetMapping("/service-details")
	public String serviceDetails(Model model) {
		model.addAttribute("title","SMMM Muammer UZUN");
		model.addAttribute("brandText","SMMM_OYS");
		return "service-details";
	}
	
	
}
