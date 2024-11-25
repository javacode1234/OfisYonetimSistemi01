package com.ofisyonetimsistemi.models;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate startDate;
	
	private String webUrl;
	
	@Lob
	@Column(name = "resim", columnDefinition = "LONGBLOB")
	private byte[] resim;
	
	@Lob
	@Column(name = "stringResim", columnDefinition = "LONGTEXT")
	private String stringResim;
	
	private String mainheader;
	
	private String header;
	
	private String description;
	
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name="businessector_id",insertable = false,updatable = false)
	@JsonIgnore
	private SmmmOfisBusinesSector sector;
	private Integer businessector_id;
	
	

}
