package com.ofisyonetimsistemi.controllers;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.models.SmmmOfisMessage;
import com.ofisyonetimsistemi.models.SmmmOfisSubscribedEmail;
import com.ofisyonetimsistemi.services.HomePagePortfolioCompanyService;
import com.ofisyonetimsistemi.services.SmmmOfisBusinesSectorService;
import com.ofisyonetimsistemi.services.SmmmOfisHomePageServicesService;
import com.ofisyonetimsistemi.services.SmmmOfisMessageService;
import com.ofisyonetimsistemi.services.SmmmOfisPricingService;
import com.ofisyonetimsistemi.services.SmmmOfisService;
import com.ofisyonetimsistemi.services.SmmmOfisSubsCribedEmailService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired private SmmmOfisService smmmOfisHomePageService;
	@Autowired private SmmmOfisBusinesSectorService businesSectorService;
	@Autowired private HomePagePortfolioCompanyService companyService;
	@Autowired private SmmmOfisHomePageServicesService homepageServicesServis;
	@Autowired private SmmmOfisPricingService pricingService;
	@Autowired private SmmmOfisMessageService messageService;
	@Autowired private SmmmOfisSubsCribedEmailService subscribedEmailService;
		
	@GetMapping
	public String getHomePage(Model model) {		
		
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		
		if(smmmOfis.isPresent()) {
			model.addAttribute("smmmOfisHomePage", smmmOfis.get());
			model.addAttribute("sectors", businesSectorService.getAllSector());
			model.addAttribute("companies", companyService.getAll());
			model.addAttribute("pricingList", pricingService.getAll());
			model.addAttribute("smmmOfisMessage", new SmmmOfisMessage());
			model.addAttribute("subscribedEmail", new SmmmOfisSubscribedEmail());
		}else if(!smmmOfis.isPresent()){
			model.addAttribute("smmmOfisHomePage", new SmmmOfis());
			model.addAttribute("smmmOfisMessage", new SmmmOfisMessage());
			model.addAttribute("subscribedEmail", new SmmmOfisSubscribedEmail());
		}		
		
		return "index";		
		
	}
	
	@GetMapping("/portfolio-details")
	public String portfolioDetails(@RequestParam("id")Integer id, Model model ) { 
		
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		
		if(smmmOfis.isPresent()) {
			model.addAttribute("smmmOfisHomePage", smmmOfis.get());
			model.addAttribute("company", companyService.getById(id).get());
		}else if(!smmmOfis.isPresent()){
			model.addAttribute("smmmOfisHomePage", new SmmmOfis());
		}		
		return "portfolio-details";
	}
		
	@GetMapping("/service-details")
	public String serviceDetails(@RequestParam("id")Integer id, Model model) {
		
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		
		if(smmmOfis.isPresent()) {
			model.addAttribute("smmmOfisHomePage", smmmOfis.get());
			model.addAttribute("homepageService", homepageServicesServis.getServiceById(id).get());
		}else if(!smmmOfis.isPresent()){
			model.addAttribute("smmmOfisHomePage", new SmmmOfis());
		}		
		return "service-details";
	}
	
	@PostMapping("/send-message")
	public String sendMessage(
									@RequestParam("name")String name,
									@RequestParam("email")String email,
									@RequestParam("subject")String subject,
									@RequestParam("message")String message,
									RedirectAttributes redirectAttr
									
							  ) {
		
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();		
			    
		SmmmOfisMessage newMessage = SmmmOfisMessage.builder()
				.name(name)
				.email(email)
				.subject(subject)
				.message(message)
				.date(LocalDateTime.now().withNano(0))
				.smmmofis_id(smmmOfis.get().getId())
				.build();
		
		messageService.saveMessage(newMessage);	
		redirectAttr.addFlashAttribute("sendMessageSuccess", "Mesajınız başarıyla gönderildi. Teşekkür ederiz.");
		return "redirect:/#contact";
	}
	
	@PostMapping("/add-subscribed-email")
	public String addSubscribedEmail(
			
									 @Valid @ModelAttribute("subscribedEmail")SmmmOfisSubscribedEmail email,
						             BindingResult result, RedirectAttributes redirectAttr
			                        
									) {
		boolean emailExist = subscribedEmailService.emailExist(email.getEmail());
		if(emailExist) {
			redirectAttr.addFlashAttribute("emailExistMessage", "Email adresi zaten kayıtlı. Başka bir email adresi deneyin.");
			return "redirect:/#footer";
		}
		
		if(result.hasErrors()) {
			
			return "redirect:/";
		}
		SmmmOfis smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis().get();
		
		SmmmOfisSubscribedEmail newSubscribedEmail = SmmmOfisSubscribedEmail.builder()
				.email(email.getEmail())
				.date(LocalDateTime.now().withNano(0))
				.smmmofis_id(smmmOfis.getId())
				.build();

		subscribedEmailService.save(newSubscribedEmail);
		redirectAttr.addFlashAttribute("successMessage", "E-mail listemize kayıt oldunuz. Teşekkür ederiz.");
		return "redirect:/#footer";
	}
	
}
