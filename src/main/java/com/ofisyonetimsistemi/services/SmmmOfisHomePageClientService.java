package com.ofisyonetimsistemi.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ofisyonetimsistemi.models.SmmmOfisHomePageClient;
import com.ofisyonetimsistemi.repositories.SmmmOfisHomePageClientRepo;

@Service
public class SmmmOfisHomePageClientService {
	
	@Autowired
	private SmmmOfisService smmmOfisService;
	
	@Autowired
	private SmmmOfisHomePageClientRepo hpcRepo;
	
	public void saveHomePageClient(SmmmOfisHomePageClient homepageClient) {
		hpcRepo.save(homepageClient);
	}

	public SmmmOfisHomePageClient saveHomePageClient(MultipartFile file, String name, String url, String description, Integer smmmOfisId) throws IOException {

		byte[] byteResim = file.getBytes();
		String strResim = Base64.getEncoder().encodeToString(byteResim);
		
		try {
			
			SmmmOfisHomePageClient hpClient = new SmmmOfisHomePageClient();
			
			hpClient.setLogo(byteResim);
			hpClient.setStringLogo(strResim);
			hpClient.setName(name);
			hpClient.setUrl(url);
			hpClient.setDescription(description);
			hpClient.setSmmmofis_id(smmmOfisId);
			
			return hpcRepo.save(hpClient);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<SmmmOfisHomePageClient> getAllHomePageClients() {
		return hpcRepo.findAll();
	}
	
	

}
