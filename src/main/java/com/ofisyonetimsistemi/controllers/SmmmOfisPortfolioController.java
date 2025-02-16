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

import com.ofisyonetimsistemi.models.PortfolioCompany;
import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.models.SmmmOfisBusinesSector;
import com.ofisyonetimsistemi.security.model.MyUser;
import com.ofisyonetimsistemi.security.service.MyUserService;
import com.ofisyonetimsistemi.services.HomePagePortfolioCompanyService;
import com.ofisyonetimsistemi.services.SmmmOfisBusinesSectorService;
import com.ofisyonetimsistemi.services.SmmmOfisMessageService;
import com.ofisyonetimsistemi.services.SmmmOfisNotificationService;
import com.ofisyonetimsistemi.services.SmmmOfisService;

@Controller
@RequestMapping("/cp/")
public class SmmmOfisPortfolioController {
	
	@Autowired private SmmmOfisService smmmOfisHomePageService ;
	@Autowired private SmmmOfisBusinesSectorService sectorService;
	@Autowired private HomePagePortfolioCompanyService portCompService;
	@Autowired private MyUserService myUserService;
	@Autowired private SmmmOfisMessageService messageService;
	@Autowired private SmmmOfisNotificationService notificationService;
	
	@GetMapping("/smmm-homepage-sector-settings")
	public String getMethodName(Model model, Principal principal) {
		MyUser myUser = myUserService.getMyUserByUsername(principal.getName());
		Optional<SmmmOfis> smmmOfis = smmmOfisHomePageService.getFirstSmmmOfis();
		if (!smmmOfis.isEmpty()) {
			model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan() + " " + smmmOfis.get().getFullName());
			model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			model.addAttribute("fullusername", smmmOfis.get().getUserName());
			model.addAttribute("gorev", smmmOfis.get().getUnvan());

			model.addAttribute("smmmOfis", smmmOfis.get());
			model.addAttribute("hpSector", new SmmmOfisBusinesSector());
			model.addAttribute("sectorList", sectorService.getAllSector());
			
			model.addAttribute("currentUser", myUser);
			model.addAttribute("messageCount", messageService.countOfRecord());
			model.addAttribute("countOfUnReadMessages", messageService.countOfUnReadMessages(false));
			
			model.addAttribute("notificationCount", notificationService.countOfRecord());
			model.addAttribute("countOfUnReadNotifications", notificationService.countOfUnReadNotifications(false));
			model.addAttribute("allNotifications", notificationService.getAllNotifications());
			
			model.addAttribute("listOfUnreadMessages", messageService.getAllUnReadMessages());
			model.addAttribute("listOfUnreadNotifications", notificationService.getAllUnReadNotifications());

			return "adminpanel/homepagesettings/homepage-sector-settings";

		} else {

			model.addAttribute("dashboardtitle", "SMMM Muammer UZUN");
			model.addAttribute("smmmisim", "Muammer UZUN");
			model.addAttribute("fullusername", "Muammer UZUN");
			model.addAttribute("gorev", "SMMM");
			model.addAttribute("smmmOfis", new SmmmOfis());
			
			model.addAttribute("currentUser", myUser);
			model.addAttribute("messageCount", messageService.countOfRecord());
			model.addAttribute("countOfUnReadMessages", messageService.countOfUnReadMessages(false));
			
			model.addAttribute("notificationCount", notificationService.countOfRecord());
			model.addAttribute("countOfUnReadNotifications", notificationService.countOfUnReadNotifications(false));
		    model.addAttribute("allNotifications", notificationService.getAllNotifications());
		    
		    model.addAttribute("listOfUnreadMessages", messageService.getAllUnReadMessages());
			model.addAttribute("listOfUnreadNotifications", notificationService.getAllUnReadNotifications());
		}

		return "adminpanel/homepagesettings/homepage-sector-settings";
	}

	@GetMapping("/get-homepage-sector/{id}")
	@ResponseBody
	public Optional<SmmmOfisBusinesSector> getServiceById(@PathVariable("id") Integer id) {
		return sectorService.getSectorById(id);
	}

	@PostMapping("/save-homepage-sector")
	public String saveSector(@ModelAttribute("hpSector") SmmmOfisBusinesSector homepageSector) {

		homepageSector.setSmmmofis_id(smmmOfisHomePageService.getFirstSmmmOfis().get().getId());
		sectorService.saveSector(homepageSector);

		return "redirect:/cp/smmm-homepage-sector-settings";
	}

	@PostMapping("/update-homepage-sector")
	public String updateSector(@ModelAttribute("hpSector") SmmmOfisBusinesSector homePageSector) {

		homePageSector.setSmmmofis_id(smmmOfisHomePageService.getFirstSmmmOfis().get().getId());
		sectorService.saveSector(homePageSector);

		return "redirect:/cp/smmm-homepage-sector-settings";
	}

	@RequestMapping(value = "/delete-homepage-sector/{id}", method = { RequestMethod.DELETE, RequestMethod.GET })
	public String delSectorById(@PathVariable("id") Integer id, RedirectAttributes redirectAttr) {
		List<PortfolioCompany> companyList = portCompService.getByPortfoyId(id);
		if(!companyList.isEmpty()) {
			redirectAttr.addFlashAttribute("msg", "Silmek istediğin kaydın alt kayıtları var. Önce alt kayıtları sil." );
			return "redirect:/api/v1/smmm-homepage-sector-settings";
		}
		sectorService.deleteSectorById(id);
		return "redirect:/cp/smmm-homepage-sector-settings";
	}


}
