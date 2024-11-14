package com.ofisyonetimsistemi.services;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ofisyonetimsistemi.models.SmmmOfis;
import com.ofisyonetimsistemi.repositories.SmmmOfisRepository;

@Service
public class SmmmOfisService {

	@Autowired
	SmmmOfisRepository smmmOfisRepo;

	public void saveHomePageSettings(SmmmOfis smmmOfis) {

		smmmOfisRepo.save(smmmOfis);
	}
	
	public Optional<SmmmOfis> getFirstSmmmOfis() {
		return smmmOfisRepo.findAll().stream().findFirst();
	}
	
	public void updateHomePageSettingsPersonalInfo(
													Integer id,MultipartFile file, String unvan, 
													String firstName, String lastName, String fullName,
													String email, String userName, String password
													) throws IOException {
		SmmmOfis smmmOfis = smmmOfisRepo.findById(id).get();
		if(file.getBytes()!=null && !file.getOriginalFilename().isEmpty()) {
			
			smmmOfis.setLogo(file.getBytes());
			smmmOfis.setStringLogo(Base64.getEncoder().encodeToString(file.getBytes()));
			smmmOfis.setUnvan(unvan);
			smmmOfis.setFirstName(firstName);
			smmmOfis.setLastName(lastName);
			smmmOfis.setFullName(fullName);
			smmmOfis.setEmail(email);
			smmmOfis.setUserName(userName);
			smmmOfis.setPassword(password);
			
			smmmOfisRepo.save(smmmOfis);
		}
		
		smmmOfis.setUnvan(unvan);
		smmmOfis.setFirstName(firstName);
		smmmOfis.setLastName(lastName);
		smmmOfis.setFullName(fullName);
		smmmOfis.setEmail(email);
		smmmOfis.setUserName(userName);
		smmmOfis.setPassword(password);

		smmmOfisRepo.save(smmmOfis);
	}

	
		
	public SmmmOfis saveHomePageSettingsPersonalInfo(  
														MultipartFile file, String unvan, String firstName, String lastName,
														String fullName, String email, String userName, String password
													) throws IOException {
		
		byte[] byteResim = file.getBytes();
		String stringResim = Base64.getEncoder().encodeToString(byteResim);
		
			try {
				SmmmOfis smmmOfis = new SmmmOfis();
				
				smmmOfis.setLogo(byteResim);
				smmmOfis.setStringLogo(stringResim);
				smmmOfis.setUnvan(unvan);
				smmmOfis.setFirstName(firstName);
				smmmOfis.setLastName(lastName);
				smmmOfis.setFullName(fullName);
				smmmOfis.setEmail(email);
				smmmOfis.setUserName(userName);
				smmmOfis.setPassword(password);
				
				return smmmOfisRepo.save(smmmOfis);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;		
			
			
	}

	public SmmmOfis getSmmmOfisById(Integer id) {
		return smmmOfisRepo.findById(id).get();
	}

	public void saveHomePageSettingsHomeInfo(String mainpageTitle, String headerTitle, 
												String heroTitle, String heroParagraf, String videoLink) {
			
		SmmmOfis smmmOfis = new SmmmOfis();
		smmmOfis.setMainpageTitle(mainpageTitle);
		smmmOfis.setHeaderTitle(headerTitle);
		smmmOfis.setHeroTitle(heroTitle);
		smmmOfis.setHeroParagraf(heroParagraf);
		smmmOfis.setVideoLink(videoLink);
		
		smmmOfisRepo.save(smmmOfis);
		
	}
	
	public void updateHomePageSettingsHomeInfo(Integer id, String mainpageTitle, String headerTitle, String heroTitle,String heroParagraf,
													String videoLink) {
		SmmmOfis smmmOfis = smmmOfisRepo.findById(id).get();
		
		smmmOfis.setMainpageTitle(mainpageTitle);
		smmmOfis.setHeaderTitle(headerTitle);
		smmmOfis.setHeroTitle(heroTitle);
		smmmOfis.setHeroParagraf(heroParagraf);
		smmmOfis.setVideoLink(videoLink);
		
		smmmOfisRepo.save(smmmOfis);
		
	}

	public void saveHomePageSettingsAboutUsInfo(String aboutusmainheader, String aboutUsColumnOneHeader,
												String aboutUsColumnTwoParagraf) {
		SmmmOfis smmmOfis = new SmmmOfis();
		smmmOfis.setAboutusmainheader(aboutusmainheader);
		smmmOfis.setAboutUsColumnOneHeader(aboutUsColumnOneHeader);
		smmmOfis.setAboutUsColumnTwoParagraf(aboutUsColumnTwoParagraf);
		
		smmmOfisRepo.save(smmmOfis);
		
	}
	
	public void updateHomePageSettingsAboutUsInfo(Integer id, String aboutusmainheader, String aboutUsColumnOneHeader,
			String aboutUsColumnTwoParagraf) {
		
		SmmmOfis smmmOfis = smmmOfisRepo.findById(id).get();
		
		smmmOfis.setAboutusmainheader(aboutusmainheader);
		smmmOfis.setAboutUsColumnOneHeader(aboutUsColumnOneHeader);
		smmmOfis.setAboutUsColumnTwoParagraf(aboutUsColumnTwoParagraf);
		
		smmmOfisRepo.save(smmmOfis);
	
	}

	public void saveHomePageSettingsWhyUsInfo(String whyusMainHeader, String whyusHeader, String whyusParagraf) {
		
		SmmmOfis smmmOfis = new SmmmOfis();
		
		smmmOfis.setWhyusMainHeader(whyusMainHeader);
		smmmOfis.setWhyusHeader(whyusHeader);
		smmmOfis.setWhyusParagraf(whyusParagraf);
		
		smmmOfisRepo.save(smmmOfis);
	}

	public void updateHomePageSettingsWhyUsInfo(Integer id, String whyusMainHeader, String whyusHeader, String whyusParagraf) {
																										
		SmmmOfis smmmOfis = smmmOfisRepo.findById(id).get();
		
		smmmOfis.setWhyusMainHeader(whyusMainHeader);
		smmmOfis.setWhyusHeader(whyusHeader);
		smmmOfis.setWhyusParagraf(whyusParagraf);
		
		smmmOfisRepo.save(smmmOfis);
		
	}

	public void saveHomePageSettingsskillsPageInfo(String skillsMainHeader, String skillsHeader,String skillsParagraf) {
		
		SmmmOfis smmmOfis = new SmmmOfis();
		
		smmmOfis.setSkillsMainHeader(skillsMainHeader);
		smmmOfis.setSkillsHeader(skillsHeader);
		smmmOfis.setSkillsParagraf(skillsParagraf);
		
		smmmOfisRepo.save(smmmOfis);
		
	}

	public void updateHomePageSettingsSkillsPageInfo(Integer id, String skillsMainHeader, String skillsHeader, String skillsParagraf) {
			
		SmmmOfis smmmOfis = smmmOfisRepo.findById(id).get();
		
		smmmOfis.setSkillsMainHeader(skillsMainHeader);
		smmmOfis.setSkillsHeader(skillsHeader);
		smmmOfis.setSkillsParagraf(skillsParagraf);
		
		smmmOfisRepo.save(smmmOfis);
		
	}
	
}
