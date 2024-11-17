package com.ofisyonetimsistemi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name = "smmmofis_id",insertable = false,updatable = false)
	@JsonIgnore
	private SmmmOfis smmmofis;
	private Integer smmmofis_id;
	
	

}
