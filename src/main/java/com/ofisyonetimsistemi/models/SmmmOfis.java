package com.ofisyonetimsistemi.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "smmmofis")
public class SmmmOfis {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String unvan;
	
	private String firstName;
	
	private String lastName;
	
	private String fullName = firstName+" "+lastName;
	
	private String mainpageTitle;
	
	private String headerTitle;
	
	private String heroTitle;
	
	@Column
	private String heroPragraf;
	
	@OneToMany(mappedBy = "smmmofis")
	private List<HomePageClient> clients;
	
	private String aboutusmainheader;
	
	private String aboutUsColumnOneHeader;
	
	@OneToMany(mappedBy = "smmmofis")
	private List<AboutUsColumnOne> aboutuscolumnone;
	
	@OneToMany(mappedBy = "smmmofis")
	private List<AboutUsColumnTwo> aboutuscolumntwo;
	
	private String whyusmainheader;
	
	private String whyusheader;
	
	@OneToMany(mappedBy = "smmmofis")
	private List<WhyUs> whyus;
	
	@OneToMany(mappedBy = "smmmofis")
	private List<OfisEmail> emails;
	
	@OneToMany(mappedBy = "smmmofis")
	private List<OfisUser> users;
	
	@OneToMany(mappedBy = "smmmofis")
	private List<OfisCustomer> customers;
	
	@OneToMany(mappedBy = "smmmofis")
	private List<OfisAdres> adreses;
	
	@OneToMany(mappedBy = "smmmofis")
	private List<OfisTelelefon> telefons;
	
	
	

}
