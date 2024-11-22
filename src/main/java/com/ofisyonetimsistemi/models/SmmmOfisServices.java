package com.ofisyonetimsistemi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "smmmofisservice")
public class SmmmOfisServices {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	private String icon;
	private String header;
	private String description;
	
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name = "smmmofis_id",insertable = false,updatable = false)
	@JsonIgnore
	private SmmmOfis smmmofis;
	private Integer smmmofis_id;
		
}
