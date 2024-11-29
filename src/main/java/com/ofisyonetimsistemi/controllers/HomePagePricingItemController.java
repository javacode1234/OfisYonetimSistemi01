package com.ofisyonetimsistemi.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ofisyonetimsistemi.models.PricingItem;
import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.services.PricingItemService;
import com.ofisyonetimsistemi.services.SmmmOfisService;


@Controller
@RequestMapping("/api/v1/")
public class HomePagePricingItemController {

	@Autowired
	SmmmOfisService smmmOfisHomePageService;
	@Autowired
	PricingItemService pricingItemService;
	

	@GetMapping("/smmm-homepage-pricing-item-settings")
	public String get(Model model) {
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		if (!smmmOfis.isEmpty()) {
			model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan() + " " + smmmOfis.get().getFullName());
			model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			model.addAttribute("fullusername", smmmOfis.get().getUserName());
			model.addAttribute("gorev", smmmOfis.get().getUnvan());

			model.addAttribute("smmmOfis", smmmOfis.get());
			model.addAttribute("pricingitem", new PricingItem());
			model.addAttribute("listPricingItem", pricingItemService.getAll() );

			return "adminpanel/homepage-pricing-item-settings";

		} else {

			model.addAttribute("dashboardtitle", "SMMM Muammer UZUN");
			model.addAttribute("smmmisim", "Muammer UZUN");
			model.addAttribute("fullusername", "Muammer UZUN");
			model.addAttribute("gorev", "SMMM");
			model.addAttribute("smmmOfis", new SmmmOfis());
			model.addAttribute("pricingitem", new PricingItem());
		}

		return "adminpanel/homepage-pricing-item-settings";
	}
	
	@GetMapping("/smmm-homepage-pricing-item-setting")
	public String getByPricingId(@RequestParam("id")Integer pricingId, Model model) {
		
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		List<PricingItem> pricingItems = pricingItemService.getPricingItemByPricingId(pricingId);
		
		if (!smmmOfis.isEmpty() && !pricingItems.isEmpty() ) {
			model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan() + " " + smmmOfis.get().getFullName());
			model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			model.addAttribute("fullusername", smmmOfis.get().getUserName());
			model.addAttribute("gorev", smmmOfis.get().getUnvan());

			model.addAttribute("smmmOfis", smmmOfis.get());
			model.addAttribute("pricingitem", new PricingItem());
			model.addAttribute("pricing_id", pricingId);
			model.addAttribute("listOfPricingItem", pricingItems );

			return "adminpanel/homepage-pricing-item-settings";

		} else {

			model.addAttribute("dashboardtitle", "SMMM Muammer UZUN");
			model.addAttribute("smmmisim", "Muammer UZUN");
			model.addAttribute("fullusername", "Muammer UZUN");
			model.addAttribute("gorev", "SMMM");
			model.addAttribute("smmmOfis", new SmmmOfis());
			model.addAttribute("pricingitem", new PricingItem());
			model.addAttribute("pricing_id", pricingId);
			model.addAttribute("listOfPricingItem", pricingItems );
		}
		
		return "adminpanel/homepage-pricing-item-settings";
	}

	@GetMapping("/get-homepage-pricing-item/{id}")
	@ResponseBody
	public Optional<PricingItem> getById(@PathVariable("id") Integer id) {
		return pricingItemService.getById(id);
	}

	@PostMapping("/save-homepage-pricing-item")
	public String save(@ModelAttribute("pricingitem") PricingItem pricingitem) {

		PricingItem savedPricingItem = pricingItemService.save(pricingitem);
		Integer id=savedPricingItem.getSmmmofispricing_id();

		return "redirect:/api/v1/smmm-homepage-pricing-item-setting?id="+id;
	}

	@PostMapping("/update-homepage-pricing-item")
	public String update(@ModelAttribute("pricingitem") PricingItem pricingitem) {

		PricingItem updatedPricingItem = pricingItemService.save(pricingitem);
		Integer id=updatedPricingItem.getSmmmofispricing_id();
		
		return "redirect:/api/v1/smmm-homepage-pricing-item-setting?id="+id;
	}

	@RequestMapping(value = "/delete-homepage-pricing-item/{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
	public String delById(@PathVariable("id") Integer id) {
		PricingItem deletedItem = pricingItemService.getById(id).get();
		Integer deletedItemPricingId = deletedItem.getSmmmofispricing_id();
		pricingItemService.deleteById(id);
		
		
		return "redirect:/api/v1/smmm-homepage-pricing-item-setting?id="+deletedItemPricingId;
	}
	
	
	

}
