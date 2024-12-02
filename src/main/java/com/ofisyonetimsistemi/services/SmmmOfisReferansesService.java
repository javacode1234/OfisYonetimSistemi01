package com.ofisyonetimsistemi.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ofisyonetimsistemi.models.SmmmOfisReferanses;
import com.ofisyonetimsistemi.repositories.SmmmHomePageReferansesRepository;

@Service
public class SmmmOfisReferansesService {

	@Autowired
	private SmmmHomePageReferansesRepository hpRreferansesRepo;

	public void save(				MultipartFile file, String name, String meslek, 
									boolean star1, boolean star2, boolean star3, boolean star4, boolean star5, 
									String gorus, boolean active,Integer smmmOfisId
			) throws IOException {

		SmmmOfisReferanses hpReferans = new SmmmOfisReferanses();

		if (file.getBytes() != null && !file.getOriginalFilename().isEmpty()) {

			//hpReferans.setResim(file.getBytes());
			hpReferans.setStringResim(Base64.getEncoder().encodeToString(file.getBytes()));
			hpReferans.setName(name);
			hpReferans.setMeslek(meslek);
			hpReferans.setStar1(star1);
			hpReferans.setStar2(star2);
			hpReferans.setStar3(star3);
			hpReferans.setStar4(star4);
			hpReferans.setStar5(star5);
			hpReferans.setGorus(gorus);
			hpReferans.setActive(active);
			hpReferans.setSmmmofis_id(smmmOfisId);

			hpRreferansesRepo.save(hpReferans);
		}

		hpReferans.setName(name);
		hpReferans.setMeslek(meslek);
		hpReferans.setStar1(star1);
		hpReferans.setStar2(star2);
		hpReferans.setStar3(star3);
		hpReferans.setStar4(star4);
		hpReferans.setStar5(star5);
		hpReferans.setGorus(gorus);
		hpReferans.setActive(active);
		hpReferans.setSmmmofis_id(smmmOfisId);

		hpRreferansesRepo.save(hpReferans);
	}

	public void update(			Integer id, MultipartFile file, String name, String meslek, 
								boolean star1, boolean star2, boolean star3, boolean star4, boolean star5, 
								String gorus, boolean active,Integer smmmOfisId
			) throws IOException {
		SmmmOfisReferanses hpReferans = hpRreferansesRepo.findById(id).get();

		if (file.getBytes() != null && !file.getOriginalFilename().isEmpty()) {

			//hpReferans.setResim(file.getBytes());
			hpReferans.setStringResim(Base64.getEncoder().encodeToString(file.getBytes()));
			hpReferans.setName(name);
			hpReferans.setMeslek(meslek);
			hpReferans.setStar1(star1);
			hpReferans.setStar2(star2);
			hpReferans.setStar3(star3);
			hpReferans.setStar4(star4);
			hpReferans.setStar5(star5);
			hpReferans.setGorus(gorus);
			hpReferans.setActive(active);
			hpReferans.setSmmmofis_id(smmmOfisId);

			hpRreferansesRepo.save(hpReferans);
		}

		hpReferans.setName(name);
		hpReferans.setMeslek(meslek);
		hpReferans.setStar1(star1);
		hpReferans.setStar2(star2);
		hpReferans.setStar3(star3);
		hpReferans.setStar4(star4);
		hpReferans.setStar5(star5);
		hpReferans.setGorus(gorus);
		hpReferans.setActive(active);
		hpReferans.setSmmmofis_id(smmmOfisId);

		hpRreferansesRepo.save(hpReferans);

	}

	public List<SmmmOfisReferanses> getAllHomePageReferanses() {
		return hpRreferansesRepo.findAll();
	}

	public Optional<SmmmOfisReferanses> getById(Integer id) {
		return hpRreferansesRepo.findById(id);
	}

	public void deleteById(Integer id) {
		hpRreferansesRepo.deleteById(id);
	}

}
