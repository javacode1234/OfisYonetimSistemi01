package com.ofisyonetimsistemi.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
	
	@Lob
	private byte[] logo;
	private String unvan;
	private String firstName;
	private String lastName;
	private String fullName = firstName+" "+lastName;
	private String email;
	
	private String mainpageTitle;//TITLE -- sekme başlığı
	private String headerTitle;//navbar sol logonun yanı
	//HERO
	private String heroTitle;//anasayfa başlık (resimüstü)
	private String heroParagraf;//anasayfa paragraf (ersimüstü)
	private String videoLink;//anasayfa video link
	//CLIENT
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisHomePageClient> clients;//kurum logoları ve linkleri
	//ABOUT US
	private String aboutusmainheader;//hakkımızda anabaşlık
	private String aboutUsColumnOneHeader;//hakkımızda 1.kolon başlık
	private String aboutUsColumnTwoHeader;//hakkımızda 2.kolon başlık
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisAboutUsColumnOne> aboutuscolumnone;//hakkımızda 1.kolon alt başlıklar
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisAboutUsColumnTwo> aboutuscolumntwo;//hakkımızda 2.kolon alt başlıklar
	//WHY US
	private String whyusMainHeader;//neden biz kısmı ana başlık
	private String whyusHeader;//neden biz kısmı alt başlık
	private String whyusParagraf;//neden biz kısmı paragraf
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisWhyUs> whyus;//neden biz kısmı liste
	//skills
	private String skillsMainHeader;//başarılar kısmı ana başlık
	private String skillsHeader;//başarılar kısmı alt başlık
	private String skillsParagraf;//başarılar kısmı paragraf
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisSkills> skills;//başarılar kısmı liste
	
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisEmail> emails;//EMAİL
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisUser> users;//USER
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisCustomer> customers;//CUSTOMER
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisAdres> adreses;//ADRES
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisTelelefon> telefons;//TELEFON
		
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
