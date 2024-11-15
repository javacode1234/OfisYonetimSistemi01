package com.ofisyonetimsistemi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "smmmofishomepageclient")
public class SmmmOfisHomePageClient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Lob
	@Column(name="logo", columnDefinition = "LONGBLOB")
	private byte[] logo;
	
	@Lob
	@Column(name="stringLogo", columnDefinition = "LONGTEXT")
	private String stringLogo;
	
	private String name;
		
	private String url;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "smmmofis_id",insertable = false,updatable = false)
	private SmmmOfis smmmofis;
	private Integer smmmofis_id;
	
	

}
