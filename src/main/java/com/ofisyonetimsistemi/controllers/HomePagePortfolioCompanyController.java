package com.ofisyonetimsistemi.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ofisyonetimsistemi.models.PortfolioCompany;
import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.models.SmmmOfisBusinesSector;
import com.ofisyonetimsistemi.services.HomePagePortfolioCompanyService;
import com.ofisyonetimsistemi.services.SmmmOfisBusinesSectorService;
import com.ofisyonetimsistemi.services.SmmmOfisService;



@Controller
@RequestMapping("/api/v1/")
public class HomePagePortfolioCompanyController {

	@Autowired
	private SmmmOfisService smmmOfisService;
	
	@Autowired
	private HomePagePortfolioCompanyService companyService;
	
	@Autowired
	private SmmmOfisBusinesSectorService sectorService;
	
	@GetMapping("/smmm-homepage-portfoy-company-settings")
	public String getHomePageSectorCompanySettingsPage(Model model) {
		
		Optional<SmmmOfis> smmmOfis = smmmOfisService.getFirstSmmmOfis();
		  if(!smmmOfis.isEmpty()) {
			  model.addAttribute("dashboardtitle", smmmOfis.get().getUnvan()+" "+smmmOfis.get().getFullName());
			  model.addAttribute("smmmisim", smmmOfis.get().getFullName());
			  model.addAttribute("fullusername", smmmOfis.get().getUserName());
			  model.addAttribute("gorev", smmmOfis.get().getUnvan());
			  
			  model.addAttribute("smmmOfis", smmmOfis.get());
			  model.addAttribute("hpCompany", new PortfolioCompany());
			  model.addAttribute("hpCompanies", companyService.getAll());
			  model.addAttribute("hpSector", new SmmmOfisBusinesSector());
			  model.addAttribute("sectorList", sectorService.getAllSector());
			  
			  return "adminpanel/homepage-portfoy-company-settings";
			  
		   }else {
		  
			  model.addAttribute("dashboardtitle", "SMMM Muammer UZUN");
			  model.addAttribute("smmmisim", "Muammer UZUN");
			  model.addAttribute("fullusername", "Muammer UZUN");
			  model.addAttribute("gorev", "SMMM");
			  model.addAttribute("smmmOfis", new SmmmOfis());
		   }
		  
		return "adminpanel/homepage-portfoy-company-settings";
	}

	@PostMapping("/save-homepage-portfoy-company-settings")
	public String saveSectorCompany(
										@RequestParam("resim")MultipartFile file,
										@RequestParam("name")String name,
										@RequestParam("unvan")String unvan,
										@RequestParam("startDate")LocalDate startDate,
										@RequestParam("webUrl")String webUrl,
										@RequestParam("header")String header,
										@RequestParam("mainheader")String mainheader,
										@RequestParam("description")String description,
										@RequestParam(value="active", required = false)boolean active,
										@RequestParam("businessector_id")Integer sector_id
										
										) throws IOException {
		
		companyService.saveHomePageCompany(file, name, unvan, startDate, webUrl, mainheader, header, description, active, sector_id);
		
		return "redirect:/api/v1/smmm-homepage-portfoy-company-settings";
	}
	
	@PostMapping("/update-homepage-portfoy-company-settings/{id}")
	public String updateSectorCompanyById(   
									@PathVariable Integer id, 
									@RequestParam("resim")MultipartFile file,
									@RequestParam("name")String name,
									@RequestParam("unvan")String unvan,
									@RequestParam("startDate")LocalDate startDate,
									@RequestParam("webUrl")String webUrl,
									@RequestParam("mainheader")String mainheader,
									@RequestParam("header")String header,
									@RequestParam("description")String description,
									@RequestParam(value="active", required = false)boolean active,
									@RequestParam("businessector_id")Integer sector_id
								) throws IOException {
		
		companyService.updateHomePageCompany(id, file, name, unvan, startDate, webUrl, mainheader, header, description, active, sector_id);
		
		return "redirect:/api/v1/smmm-homepage-portfoy-company-settings";
	}

	@GetMapping("/get-homepage-portfoy-company-settings/{id}")
	@ResponseBody
	public Optional<PortfolioCompany> getSectorCompanyById(@PathVariable("id")Integer id) {
		return companyService.getById(id);
	}
	
	@RequestMapping(value="/delete-homepage-portfoy-company-settings/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delSectorCompanyById(@PathVariable("id") Integer id) {
		companyService.deleteById(id);
		return "redirect:/api/v1/smmm-homepage-portfoy-company-settings";
	}
	
	
	
}
