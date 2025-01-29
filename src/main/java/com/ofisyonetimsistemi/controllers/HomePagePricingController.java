package com.ofisyonetimsistemi.controllers;

import java.security.Principal;
import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ofisyonetimsistemi.models.PricingItem;
import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.models.SmmmOfisPricing;
import com.ofisyonetimsistemi.security.model.MyUser;
import com.ofisyonetimsistemi.security.service.MyUserService;
import com.ofisyonetimsistemi.services.PricingItemService;
import com.ofisyonetimsistemi.services.SmmmOfisMessageService;
import com.ofisyonetimsistemi.services.SmmmOfisPricingService;
import com.ofisyonetimsistemi.services.SmmmOfisService;

@Controller
@RequestMapping("/api/v1/")
public class HomePagePricingController {

	@Autowired private SmmmOfisService smmmOfisHomePageService;
	@Autowired private SmmmOfisPricingService pricingService;
	@Autowired private PricingItemService prItemSercise;
	@Autowired private MyUserService myUserService;
	@Autowired private SmmmOfisMessageService messageService;
	
	@GetMapping("/smmm-homepage-pricing-settings")
	public String get(Model model, Principal principal) {
		MyUser myUser = myUserService.getMyUserByUsername(principal.getName());
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		if (!smmmOfis.isEmpty()) {
			model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan() + " " + smmmOfis.get().getFullName());
			model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			model.addAttribute("fullusername", smmmOfis.get().getUserName());
			model.addAttribute("gorev", smmmOfis.get().getUnvan());

			model.addAttribute("smmmOfis", smmmOfis.get());
			model.addAttribute("pricing", new SmmmOfisPricing());
			model.addAttribute("listPricing", pricingService.getAll() );
			
			model.addAttribute("currentUser", myUser);
			model.addAttribute("messageCount", messageService.countOfRecord());
			model.addAttribute("countOfNonReadMessages", messageService.countOfRecordReaded(false));
			
			return "adminpanel/homepagesettings/homepage-pricing-settings";

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

		return "adminpanel/homepagesettings/homepage-pricing-settings";
	}

	@GetMapping("/get-homepage-pricing/{id}")
	@ResponseBody
	public Optional<SmmmOfisPricing> getById(@PathVariable("id") Integer id) {
		return pricingService.getById(id);
	}

	@PostMapping("/save-homepage-pricing")
	public String save(@ModelAttribute("pricing") SmmmOfisPricing pricing) {

		pricing.setSmmmofis_id(smmmOfisHomePageService.getFirstSmmmOfis().get().getId());
		pricingService.save(pricing);

		return "redirect:/api/v1/smmm-homepage-pricing-settings";
	}

	@PostMapping("/update-homepage-pricing")
	public String update(@ModelAttribute("pricing") SmmmOfisPricing pricing) {

		pricing.setSmmmofis_id(smmmOfisHomePageService.getFirstSmmmOfis().get().getId());
		pricingService.save(pricing);

		return "redirect:/api/v1/smmm-homepage-pricing-settings";
	}

	@RequestMapping(value = "/delete-homepage-pricing/{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
	public String delById(@PathVariable("id") Integer id, RedirectAttributes redirectAttr) {
		List<PricingItem> pricingItems = prItemSercise.getPricingItemByPricingId(id);
		if(!pricingItems.isEmpty()) {
			redirectAttr.addFlashAttribute("msg", "Silmek istediğin kaydın alt kayıtları var. Önce alt kayıtları sil." );
			return "redirect:/api/v1/smmm-homepage-pricing-settings";
		}
		pricingService.deleteById(id);
		return "redirect:/api/v1/smmm-homepage-pricing-settings";
	}

}
