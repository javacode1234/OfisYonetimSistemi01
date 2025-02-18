package com.ofisyonetimsistemi.models;

import java.util.List;

import com.ofisyonetimsistemi.security.model.MyUser;

import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
@Entity
@Table(name = "smmmofis")
public class SmmmOfis {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	// genel bilgiler kısmı //
	
	@Lob	
	@Column(name = "logo", columnDefinition = "LONGBLOB")
	private byte[] logo;
	
	@Lob
	@Column(name = "stringLogo", columnDefinition = "LONGTEXT")
	private String stringLogo;
	
	private String unvan;
	private String firstName;
	private String lastName;
	private String fullName;
	private String email;
	private String telephone;	
	private String address;	
	private String userName;
	private String password;	
	
	// anasayfa ayarlar kısmı //
	
	private String mainpageTitle;//TITLE -- sekme başlığı
	private String headerTitle;//navbar sol logonun yanı
	
	//HERO
	private String heroTitle;//anasayfa başlık (resimüstü)
	
	@Column(length = 200)
	private String heroParagraf;//anasayfa paragraf (ersimüstü)
	private String videoLink;//anasayfa video link
	
	//CLIENT //bunun için ayrı bir kayıt sayfası yapılacak *******
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisHomePageClient> clients;//kurum logoları ve linkleri
	
	// ABOUT US - hakkımızda kısmı //
	private String aboutusmainheader;//hakkımızda anabaşlık
	
	@Column(length = 200)
	private String aboutUsColumnOneHeader;//hakkımızda 1.kolon başlık
	
	@Column(length = 200)
	private String aboutUsColumnTwoParagraf;//hakkımızda 2.kolon paragraf
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisAboutUsColumnOne> aboutuscolumnone;//hakkımızda 1.kolon alt başlıklar
	
	// WHY US - neden biz kısmı //
	private String whyusMainHeader;//neden biz kısmı ana başlık
	@Column(length = 200)
	private String whyusHeader;//neden biz kısmı alt başlık
	
	@Column(length = 200)
	private String whyusParagraf;//neden biz kısmı paragraf
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisWhyUs> whyus;//neden biz kısmı liste
	
	// skills - başarılar kısmı //
	private String skillsMainHeader;//başarılar kısmı ana başlık
	
	@Column(length = 200)
	private String skillsHeader;//başarılar kısmı alt başlık
	
	@Column(length = 200)
	private String skillsParagraf;//başarılar kısmı paragraf
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisSkills> skills;//başarılar kısmı liste
	
		
	// SERVICEES - hizmetlerkısmı //
	private String serviceMainHeader;
	
	@Column(length = 200)
	private String serviceHeader;
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisServices> services;
	
	//CALL TO ACTIONS - mevzuat kısmı //
	private String callToActionHeader;
	
	@Column(length = 200)
	private String callToActionText;
	
	// PORTFOLİO - portföy kısmı //
	private String portfolioHeader;
	
	@Column(length = 200)
	private String portfolioText;
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisBusinesSector> sectors;
	
	// TEAM - çalışma takımı kısmı //
	private String teammainheader;
	
	@Column(length = 200)
	private String teamheader;
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisTeam> team;
	
	// PRICING - ücretlendirme kısmı //
	private String pricingMainHeader;
	
	@Column(length = 200)
	private String pricingHeader;
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisPricing> pricing;
	
	// TESTIMONIALS - referanslar-yorumlar kısmı //
	private String testimonialsMainHeader;
	
	@Column(length = 200)
	private String testimonialsHeader;
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisReferanses> referanses;
	
	// FREQUANTLY ASKED QUESTIONS - sık sorulan sorular kısmı //
	private String smmmofisFrequantlyAskedQuestionsMainHeader;
	
	@Column(length = 200)
	private String smmmofisFrequantlyAskedQuestionsHeader;
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisAskedQuestions> questions;
	
	// CONTACT - iletişim kısmı //
	private String contactMainHeader;
	
	@Column(length = 100)
	private String contactHeader;
	private String contactAddress;
	private String contactTelephone;
	private String contactEmail;
	
	@Column(length = 2000)
	private String googleHarita;
	
	//Sosyal medya
	private String smmmOfisTwitter;
	private String smmmOfisFacebook;
	private String smmmOfisInstagram;
	private String smmmOfisLinkedIn;
	
	//***************************************//
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisEmail> emails;//EMAİL
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisUser> users;//USER
	@OneToMany(mappedBy = "smmmofis")
	private List<MyUser> myusers;//USER
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisCustomer> customers;//CUSTOMER
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisAdres> adreses;//ADRES
	@OneToMany(mappedBy = "smmmofis")
	private List<SmmmOfisTelelefon> telefons;//TELEFON
	//*******************************************//
	
	@OneToMany(mappedBy = "smmmofis")	
	private List<SmmmOfisMessage> messages;	
		
	@OneToMany(mappedBy = "smmmofis")	
	private List<SmmmOfisNotification> notifications;
	
	@OneToMany(mappedBy = "smmmofis")	
	private List<SmmmOfisNotes> notes;	
	

}
