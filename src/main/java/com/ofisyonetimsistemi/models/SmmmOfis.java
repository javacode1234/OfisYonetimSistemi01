package com.ofisyonetimsistemi.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "smmmofis")
public class SmmmOfis {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private byte[] logo;
	private String unvan;
	private String firstName;
	private String lastName;
	private String fullName = firstName+" "+lastName;
	//TITLE
	private String mainpageTitle;
	private String headerTitle;
	//HERO
	private String heroTitle;
	private String heroPragraf;
	//CLIENT
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisHomePageClient> clients;
	//ABOUT US
	private String aboutusmainheader;
	private String aboutUsColumnOneHeader;
	
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisAboutUsColumnOne> aboutuscolumnone;
	
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisAboutUsColumnTwo> aboutuscolumntwo;
	//WHY US
	private String whyusmainheader;
	private String whyusheader;
	
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisWhyUs> whyus;
	//EMAİL
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisEmail> emails;
	//USER
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisUser> users;
	//CUSTOMER
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisCustomer> customers;
	//ADRES
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisAdres> adreses;
	//TELEFON
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisTelelefon> telefons;
	//SKILLS
	private String skillsMainHeader;
	private String skillsHeader;
	
	//SERVICEES
	private String serviceMainHeader;
	private String serviceHeader;
	
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisService> services;
	//CALL TO ACTIONS
	private String callToActionHeader;
	private String callToActionText;
	//PORTFOLİO
	private String portfolioHeader;
	private String portfolioText;
	
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisBusinesSector> sectors;
	//TEAM
	private String teammainheader;
	private String teamheader;
	
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisTeam> team;
	//PRICING
	private String pricingMainHeader;
	private String pricingHeader;
	
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisPricingPlan> pricing;
	//TESTIMONIALS
	private String testimonialsMainHeader;
	private String testimonialsHeader;
	
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisTestimonials> testimonials;
	//FREQUANTLY ASKED QUESTIONS
	private String smmmofisFrequantlyAskedQuestionsMainHeader;
	private String smmmofisFrequantlyAskedQuestionsHeader;
	
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisAskedQuestions> questions;
	
	

}
