package com.ofisyonetimsistemi.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ofisyonetimsistemi.models.SmmmOfisHomePageClient;
import com.ofisyonetimsistemi.repositories.SmmmOfisHomePageClientRepo;

@Service
public class SmmmOfisHomePageClientService {

	@Autowired
	private SmmmOfisHomePageClientRepo hpcRepo;

	public void saveHomePageClient(SmmmOfisHomePageClient homepageClient) {
		hpcRepo.save(homepageClient);
	}

	public void saveHomePageClient(MultipartFile file, String name, String url, String description,
														boolean active, Integer smmmOfisId) throws IOException {
		
		SmmmOfisHomePageClient hpClient = new SmmmOfisHomePageClient();
		
		if(file.getBytes()!=null && !file.getOriginalFilename().isEmpty()) {

			hpClient.setLogo(file.getBytes());
			hpClient.setStringLogo(Base64.getEncoder().encodeToString(file.getBytes()));
			hpClient.setName(name);
			hpClient.setUrl(url);
			hpClient.setDescription(description);
			hpClient.setActive(active);
			hpClient.setSmmmofis_id(smmmOfisId);

			hpcRepo.save(hpClient);
		} 
		
		hpClient.setName(name);
		hpClient.setUrl(url);
		hpClient.setDescription(description);
		hpClient.setActive(active);
		hpClient.setSmmmofis_id(smmmOfisId);

		hpcRepo.save(hpClient);
	}

	public void updateHomePageClient(Integer id, MultipartFile file, String name, String url, String description,
																boolean active, Integer smmmOfisId) throws IOException {
		SmmmOfisHomePageClient hpcSelected = hpcRepo.findById(id).get();
		
		if(file.getBytes()!=null && !file.getOriginalFilename().isEmpty()) {
			
			hpcSelected.setLogo(file.getBytes());
			hpcSelected.setStringLogo(Base64.getEncoder().encodeToString(file.getBytes()));
			hpcSelected.setName(name);
			hpcSelected.setUrl(url);
			hpcSelected.setDescription(description);
			hpcSelected.setActive(active);
			
			hpcRepo.save(hpcSelected);
		}
		
		hpcSelected.setName(name);
		hpcSelected.setUrl(url);
		hpcSelected.setDescription(description);
		hpcSelected.setActive(active);
		
		hpcRepo.save(hpcSelected);
		
	}

	public List<SmmmOfisHomePageClient> getAllHomePageClients() {
		return hpcRepo.findAll();
	}

	public Optional<SmmmOfisHomePageClient> getById(Integer id) {
		return hpcRepo.findById(id);
	}

	public void deleteById(Integer id) {
		hpcRepo.deleteById(id);
	}

}
