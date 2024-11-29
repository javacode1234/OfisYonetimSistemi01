package com.ofisyonetimsistemi.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ofisyonetimsistemi.models.PortfolioCompany;
import com.ofisyonetimsistemi.repositories.PortfolioCompanyRepository;

@Service
public class HomePagePortfolioCompanyService {
	
	@Autowired private PortfolioCompanyRepository repo;
	
	public void saveHomePageClient(PortfolioCompany portfolioCompany) {
		repo.save(portfolioCompany);
	}

	public PortfolioCompany saveHomePageCompany(MultipartFile file, String name, String unvan, LocalDate startDate, String webUrl,
										 String mainHeader,String header,String description,
											 boolean active, Integer sectorId) throws IOException {
		
		PortfolioCompany portfoyCompany = new PortfolioCompany();
		
		if(file.getBytes()!=null && !file.getOriginalFilename().isEmpty()) {

			//hpClient.setLogo(file.getBytes());
			portfoyCompany.setStringResim(Base64.getEncoder().encodeToString(file.getBytes()));
			portfoyCompany.setName(name);
			portfoyCompany.setUnvan(unvan);
			portfoyCompany.setStartDate(startDate);
			portfoyCompany.setWebUrl(webUrl);
			portfoyCompany.setHeader(header);
			portfoyCompany.setMainheader(mainHeader);
			portfoyCompany.setDescription(description);
			portfoyCompany.setActive(active);
			portfoyCompany.setBusinessector_id(sectorId);

			return repo.save(portfoyCompany);
		} 
		
		portfoyCompany.setName(name);
		portfoyCompany.setUnvan(unvan);
		portfoyCompany.setStartDate(startDate);
		portfoyCompany.setWebUrl(webUrl);
		portfoyCompany.setHeader(header);
		portfoyCompany.setMainheader(mainHeader);
		portfoyCompany.setDescription(description);
		portfoyCompany.setActive(active);
		portfoyCompany.setBusinessector_id(sectorId);

		return repo.save(portfoyCompany);
	}

	public PortfolioCompany updateHomePageCompany(Integer id, MultipartFile file, String name, String unvan, LocalDate startDate, String webUrl,
			  String mainheader,String header,String description, boolean active, Integer sector_id) throws IOException {
		
		PortfolioCompany selectedCompany = repo.findById(id).get();
		
		if(file.getBytes()!=null && !file.getOriginalFilename().isEmpty()) {
			
			//selectedCompany.setResim(file.getBytes());
			selectedCompany.setStringResim(Base64.getEncoder().encodeToString(file.getBytes()));
			selectedCompany.setName(name);
			selectedCompany.setUnvan(unvan);
			selectedCompany.setStartDate(startDate);
			selectedCompany.setWebUrl(webUrl);
			selectedCompany.setDescription(description);
			selectedCompany.setHeader(header);
			selectedCompany.setMainheader(mainheader);
			selectedCompany.setActive(active);
			selectedCompany.setBusinessector_id(sector_id);
			
			return repo.save(selectedCompany);
		}
		
		selectedCompany.setName(name);
		selectedCompany.setUnvan(unvan);
		selectedCompany.setStartDate(startDate);
		selectedCompany.setWebUrl(webUrl);
		selectedCompany.setDescription(description);
		selectedCompany.setHeader(header);
		selectedCompany.setMainheader(mainheader);
		selectedCompany.setActive(active);
		selectedCompany.setBusinessector_id(sector_id);
		
		return repo.save(selectedCompany);
		
	}

	public List<PortfolioCompany> getAll() {
		return repo.findAll();
	}

	public Optional<PortfolioCompany> getById(Integer id) {
		return repo.findById(id);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	public List<PortfolioCompany> getByPortfoyId(Integer id) {
		return repo.findAllByPortfoyId(id);
	}


}
