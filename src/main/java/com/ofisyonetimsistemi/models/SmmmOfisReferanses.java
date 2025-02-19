package com.ofisyonetimsistemi.models;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "smmmofisreferans")
public class SmmmOfisReferanses {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Lob
	@Column(name = "resim", columnDefinition = "LONGBLOB")
	private byte[] resim;
	
	@Lob
	@Column(name = "stringResim", columnDefinition = "LONGTEXT")
	private String stringResim;
	
	private String name;
	private String meslek;
	private boolean star1;
	private boolean star2;
	private boolean star3;
	private boolean star4;
	private boolean star5;
	private String gorus;
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name = "smmmofis_id",insertable = false,updatable = false)
	@JsonIgnore
	private SmmmOfis smmmofis;
	private Integer smmmofis_id;

}
