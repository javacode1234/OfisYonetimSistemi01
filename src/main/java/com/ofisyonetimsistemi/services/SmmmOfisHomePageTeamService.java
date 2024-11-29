package com.ofisyonetimsistemi.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ofisyonetimsistemi.models.SmmmOfisTeam;
import com.ofisyonetimsistemi.repositories.SmmmOfisHomePageTeamRepo;

@Service
public class SmmmOfisHomePageTeamService {

	@Autowired
	private SmmmOfisHomePageTeamRepo hptRepo;

	public void saveHomePageTeam(SmmmOfisTeam homepageTeam) {
		hptRepo.save(homepageTeam);
	}

	public void saveHomePageTeam(
									MultipartFile file, String name, String mainheader, String header,
									String text, String xlink, String facelink, String inslink, String linklink,
									String description, boolean active, Integer smmmOfisId
									
								) throws IOException {
		
		SmmmOfisTeam hpTeam = new SmmmOfisTeam();
		
		if(file.getBytes()!=null && !file.getOriginalFilename().isEmpty()) {

			//hpTeam.setResim(file.getBytes());
			hpTeam.setStringResim(Base64.getEncoder().encodeToString(file.getBytes()));
			hpTeam.setName(name);
			hpTeam.setMainheader(mainheader);
			hpTeam.setHeader(header);
			hpTeam.setText(text);
			hpTeam.setXlink(xlink);
			hpTeam.setFacelink(facelink);
			hpTeam.setInslink(inslink);
			hpTeam.setLinklink(linklink);
			hpTeam.setDescription(description);
			hpTeam.setActive(active);
			hpTeam.setSmmmofis_id(smmmOfisId);

			hptRepo.save(hpTeam);
		} 
		
		hpTeam.setName(name);
		hpTeam.setMainheader(mainheader);
		hpTeam.setHeader(header);
		hpTeam.setText(text);
		hpTeam.setXlink(xlink);
		hpTeam.setFacelink(facelink);
		hpTeam.setInslink(inslink);
		hpTeam.setLinklink(linklink);
		hpTeam.setDescription(description);
		hpTeam.setActive(active);
		hpTeam.setSmmmofis_id(smmmOfisId);

		hptRepo.save(hpTeam);
	}

	public void updateHomePageTeam(		Integer id, 
										MultipartFile file, String name, String mainheader, String header,
										String text, String xlink, String facelink, String inslink, String linklink,
										String description, boolean active, Integer smmmOfisId
										
									) throws IOException {
		SmmmOfisTeam hptSelected = hptRepo.findById(id).get();
		
		if(file.getBytes()!=null && !file.getOriginalFilename().isEmpty()) {
			
			//hptSelected.setResim(file.getBytes());
			hptSelected.setStringResim(Base64.getEncoder().encodeToString(file.getBytes()));
			hptSelected.setName(name);
			hptSelected.setMainheader(mainheader);
			hptSelected.setHeader(header);
			hptSelected.setText(text);
			hptSelected.setXlink(xlink);
			hptSelected.setFacelink(facelink);
			hptSelected.setInslink(inslink);
			hptSelected.setLinklink(linklink);
			hptSelected.setDescription(description);
			hptSelected.setActive(active);
			hptSelected.setSmmmofis_id(smmmOfisId);
			
			hptRepo.save(hptSelected);
		}
		
		hptSelected.setName(name);
		hptSelected.setMainheader(mainheader);
		hptSelected.setHeader(header);
		hptSelected.setText(text);
		hptSelected.setXlink(xlink);
		hptSelected.setFacelink(facelink);
		hptSelected.setInslink(inslink);
		hptSelected.setLinklink(linklink);
		hptSelected.setDescription(description);
		hptSelected.setActive(active);
		hptSelected.setSmmmofis_id(smmmOfisId);
		
		hptRepo.save(hptSelected);
		
	}

	public List<SmmmOfisTeam> getAllHomePageTeams() {
		return hptRepo.findAll();
	}

	public Optional<SmmmOfisTeam> getById(Integer id) {
		return hptRepo.findById(id);
	}

	public void deleteById(Integer id) {
		hptRepo.deleteById(id);
	}

}
