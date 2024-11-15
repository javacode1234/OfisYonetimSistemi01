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

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="portfoliocompany")
public class PortfolioCompany {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String unvan;
	
	@Lob
	@Column(name = "resim", columnDefinition = "LONGBLOB")
	private byte[] resim;
	
	@Lob
	@Column(name = "stringResim", columnDefinition = "LONGTEXT")
	private String stringResim;
	
	private String mainheader;
	
	private String header;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name="businessector_id",insertable = false,updatable = false)
	private SmmmOfisBusinesSector sector;
	private Integer businessector_id;
	
	

}
