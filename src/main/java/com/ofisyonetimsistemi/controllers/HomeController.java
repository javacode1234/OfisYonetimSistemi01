package com.ofisyonetimsistemi.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.services.SmmmOfisService;



@Controller
public class HomeController {
	
	@Autowired SmmmOfisService smmmOfisHomePageService;
		
	@GetMapping("/")
	public String getHomePage(Model model) {
		
		Optional<SmmmOfis> smmmOfisHomePage = smmmOfisHomePageService.getFirstSmmmOfis();
		
		if( smmmOfisHomePage.isPresent() ) {
			model.addAttribute("smmmOfisHomePage", smmmOfisHomePage);
			return "index";			
		}
		
		SmmmOfis smmmOfis = new SmmmOfis();
		
		smmmOfis.setMainpageTitle("SMMM Muammer UZUN");
		smmmOfis.setHeaderTitle("MMOYS");
		smmmOfis.setHeroTitle("Serbest Muhasebeci Mali Müşavir Muammer UZUN");
		smmmOfis.setHeroParagraf("Serbest Muhasebeci Mali Müşavir Ofis Yönetim ve Müşteri İşleri Takip Sistemi.");
		smmmOfis.setVideoLink("https://www.youtube.com/watch?v=tuus0A9u2jo");
		smmmOfis.setAboutusmainheader("Hakkımızda Kısmı Başlık");
		smmmOfis.setAboutUsColumnOneHeader("Hakkımızda kısmı 1. kolon başlık.");
		smmmOfis.setAboutUsColumnTwoParagraf("Hakkımızdakısmı 2. kolon paragraf.");
		smmmOfis.setWhyusMainHeader("Neden Biz Kısmı Başlık");
		smmmOfis.setWhyusHeader("Neden biz kısmı alt başlık.");
		smmmOfis.setWhyusParagraf("Neden biz kısmı paragraf.");
		smmmOfis.setSkillsMainHeader("Başarılar Kısmı Başlık");
		smmmOfis.setSkillsHeader("Başarılar kısmı alt başlık");
		smmmOfis.setSkillsParagraf("Başarılar kısmı paragraf");
		smmmOfis.setServiceMainHeader("Hizmetler kısmı ana başlık");
		smmmOfis.setServiceHeader("hizmetler kısmı alt başlık");
		smmmOfis.setCallToActionHeader("mevzuat kısmı başlık");
		smmmOfis.setCallToActionText("mevzuat kısmı paragraf");
		smmmOfis.setPortfolioHeader("portföy kısmı başlık");
		smmmOfis.setPortfolioText("portföy kısmı paragraf text");
		smmmOfis.setTeammainheader("çalışma ekibi kısmı ana başlık");
		smmmOfis.setTeamheader("çalışma ekibi kısmı alt başlık");
		smmmOfis.setPricingMainHeader("ücretlendirme kısmı ana başlık");
		smmmOfis.setPricingHeader("ücretlendirme kısmı alt başlık");
		smmmOfis.setTestimonialsMainHeader("referanslar kısmı ana başlık");
		smmmOfis.setTestimonialsHeader("referanslar kısmı alt başlık");
		smmmOfis.setSmmmofisFrequantlyAskedQuestionsMainHeader("sık sorulan sorular kısmı ana başlık");
		smmmOfis.setSmmmofisFrequantlyAskedQuestionsHeader("sık sorulan sorular kısmı başlık");
		smmmOfis.setContactMainHeader("İletişim kısmı ana başlık");
		smmmOfis.setContactHeader("iletişim kısmı alt başlık");
		smmmOfis.setContactAddress("İletişim adresi");
		smmmOfis.setContactEmail("contact@contact.net");
		smmmOfis.setContactTelephone("5383326334");
		smmmOfis.setGoogleHarita("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d752.876889483895!2d28.670593283877572!3d40.99226275881076!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x14b55f89aa23eed9%3A0xcd20d5bf724b110b!2sYakuplu%2C%20100.%20Sk.%2030%20B%2C%2034524%20Beylikd%C3%BCz%C3%BC%2F%C4%B0stanbul!5e0!3m2!1str!2str!4v1719420695704!5m2!1str!2str");
		
		model.addAttribute("smmmOfisHomePage", smmmOfis);
		
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
