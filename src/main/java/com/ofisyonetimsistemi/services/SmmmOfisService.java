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
	
	public Optional<SmmmOfis> getFirstSmmmOfis() {
		return smmmOfisRepo.findAll().stream().findFirst();
	}
	
	public SmmmOfis getSmmmOfisById(Integer id) {
		return smmmOfisRepo.findById(id).get();
	}
	
	public SmmmOfis saveHomePageSettingsPersonalInfo(  
															MultipartFile file, String unvan, String firstName, String lastName,
															String fullName, String email
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
				
			
			return smmmOfisRepo.save(smmmOfis);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		return null;		
	
	
	}	
	
	public void updateHomePageSettingsPersonalInfo(
													Integer id,MultipartFile file, String unvan, 
													String firstName, String lastName, String fullName,
													String email
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
			
			
			smmmOfisRepo.save(smmmOfis);
		}
		
		smmmOfis.setUnvan(unvan);
		smmmOfis.setFirstName(firstName);
		smmmOfis.setLastName(lastName);
		smmmOfis.setFullName(fullName);
		smmmOfis.setEmail(email);
		

		smmmOfisRepo.save(smmmOfis);
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

	public void saveHomePageSettingServicesPageInfo(String serviceMainHeader, String serviceHeader) {
		
		SmmmOfis smmmOfis = new SmmmOfis();
		
		smmmOfis.setServiceMainHeader(serviceMainHeader);
		smmmOfis.setServiceHeader(serviceHeader);
		
		smmmOfisRepo.save(smmmOfis);
		
	}

	public void updateHomePageSettingServicesPageInfo(Integer id, String serviceMainHeader, String serviceHeader) {
		
		SmmmOfis smmmOfis = smmmOfisRepo.findById(id).get();
		
		smmmOfis.setServiceMainHeader(serviceMainHeader);
		smmmOfis.setServiceHeader(serviceHeader);
		
		smmmOfisRepo.save(smmmOfis);
	}

	public void saveHomePageSettingMevzuatPageInfo(String callToActionHeader, String callToActionText) {
		
		SmmmOfis smmmOfis = new SmmmOfis();
		
		smmmOfis.setCallToActionHeader(callToActionHeader);
		smmmOfis.setCallToActionText(callToActionText);
		
		smmmOfisRepo.save(smmmOfis);
		
	}

	public void updateHomePageSettingMevzuatPageInfo(Integer id, String callToActionHeader, String callToActionText) {
		
		SmmmOfis smmmOfis = smmmOfisRepo.findById(id).get();
		
		smmmOfis.setCallToActionHeader(callToActionHeader);
		smmmOfis.setCallToActionText(callToActionText);
		
		smmmOfisRepo.save(smmmOfis);
		
	}

	public void saveHomePageSettingPortfoyPageInfo(String portfolioHeader, String portfolioText) {
		
		SmmmOfis smmmOfis = new SmmmOfis();
		
		smmmOfis.setPortfolioHeader(portfolioHeader);
		smmmOfis.setPortfolioText(portfolioText);
		
		smmmOfisRepo.save(smmmOfis);
		
	}

	public void updateHomePageSettingPortfoyPageInfo(Integer id, String portfolioHeader, String portfolioText) {

        SmmmOfis smmmOfis = smmmOfisRepo.findById(id).get();
		
		smmmOfis.setPortfolioHeader(portfolioHeader);
		smmmOfis.setPortfolioText(portfolioText);
		
		smmmOfisRepo.save(smmmOfis);
		
	}

	public void saveHomePageSettingTeamPageInfo(String teammainheader, String teamheader) {
		
		SmmmOfis smmmOfis = new SmmmOfis();
		
		smmmOfis.setTeammainheader(teammainheader);
		smmmOfis.setTeamheader(teamheader);
		
		smmmOfisRepo.save(smmmOfis);
		
	}

	public void updateHomePageSettingTeamPageInfo(Integer id, String teammainheader, String teamheader) {
		
		SmmmOfis smmmOfis = smmmOfisRepo.findById(id).get();
		
		smmmOfis.setTeammainheader(teammainheader);
		smmmOfis.setTeamheader(teamheader);
		
		smmmOfisRepo.save(smmmOfis);
		
	}

	public void saveHomePageSettingPricingPageInfo(String pricingMainHeader, String pricingHeader) {
		
		SmmmOfis smmmOfis = new SmmmOfis();
		
		smmmOfis.setPricingMainHeader(pricingMainHeader);
		smmmOfis.setPricingHeader(pricingHeader);
		
		smmmOfisRepo.save(smmmOfis);
		
	}

	public void updateHomePageSettingPricingPageInfo(Integer id, String pricingMainHeader, String pricingHeader) {
		
		SmmmOfis smmmOfis = smmmOfisRepo.findById(id).get();
		
		smmmOfis.setPricingMainHeader(pricingMainHeader);
		smmmOfis.setPricingHeader(pricingHeader);
		
		smmmOfisRepo.save(smmmOfis);
		
	}

	public void saveHomePageSettingReferansPageInfo(String testimonialsMainHeader, String testimonialsHeader) {
		
		SmmmOfis smmmOfis = new SmmmOfis();
		
		smmmOfis.setTestimonialsMainHeader(testimonialsMainHeader);
		smmmOfis.setTestimonialsHeader(testimonialsHeader);
		
		smmmOfisRepo.save(smmmOfis);
		
	}

	public void updateHomePageSettingReferansPageInfo(Integer id, String testimonialsMainHeader,String testimonialsHeader) {
			
		SmmmOfis smmmOfis = smmmOfisRepo.findById(id).get();
		
		smmmOfis.setTestimonialsMainHeader(testimonialsMainHeader);
		smmmOfis.setTestimonialsHeader(testimonialsHeader);
		
		smmmOfisRepo.save(smmmOfis);
		
	}

	public void saveHomePageSettingQuestionsPageInfo(String smmmofisFrequantlyAskedQuestionsMainHeader, String smmmofisFrequantlyAskedQuestionsHeader) {
			
		SmmmOfis smmmOfis = new SmmmOfis();
		
		smmmOfis.setSmmmofisFrequantlyAskedQuestionsMainHeader(smmmofisFrequantlyAskedQuestionsMainHeader);
		smmmOfis.setSmmmofisFrequantlyAskedQuestionsHeader(smmmofisFrequantlyAskedQuestionsHeader);
		
		smmmOfisRepo.save(smmmOfis);
		
	}

	public void updateHomePageSettingQuestionsPageInfo(Integer id, String smmmofisFrequantlyAskedQuestionsMainHeader, String smmmofisFrequantlyAskedQuestionsHeader) {
			
		SmmmOfis smmmOfis = smmmOfisRepo.findById(id).get();
		
		smmmOfis.setSmmmofisFrequantlyAskedQuestionsMainHeader(smmmofisFrequantlyAskedQuestionsMainHeader);
		smmmOfis.setSmmmofisFrequantlyAskedQuestionsHeader(smmmofisFrequantlyAskedQuestionsHeader);
		
		smmmOfisRepo.save(smmmOfis);
		
	}

	public void saveHomePageSettingContactPageInfo(String contactMainHeader, String contactHeader, String contactAddress, String contactTelephone, String contactEmail, String googleHarita) {
			
		SmmmOfis smmmOfis = new SmmmOfis();
		
		smmmOfis.setContactMainHeader(contactMainHeader);
		smmmOfis.setContactHeader(contactHeader);
		smmmOfis.setContactAddress(contactAddress);
		smmmOfis.setContactTelephone(contactTelephone);
		smmmOfis.setContactEmail(contactEmail);
		smmmOfis.setGoogleHarita(googleHarita);
		
		smmmOfisRepo.save(smmmOfis);
		
	}

	public void updateHomePageSettingContactPageInfo(Integer id, String contactMainHeader, String contactHeader, String contactAddress, String contactTelephone, String contactEmail, String googleHarita) {
			
		SmmmOfis smmmOfis = smmmOfisRepo.findById(id).get();
		
		smmmOfis.setContactMainHeader(contactMainHeader);
		smmmOfis.setContactHeader(contactHeader);
		smmmOfis.setContactAddress(contactAddress);
		smmmOfis.setContactTelephone(contactTelephone);
		smmmOfis.setContactEmail(contactEmail);
		smmmOfis.setGoogleHarita(googleHarita);
		
		smmmOfisRepo.save(smmmOfis);
		
	}
	
}
