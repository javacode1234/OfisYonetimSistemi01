package com.ofisyonetimsistemi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "homepageclient")
public class HomePageClient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String logo;
	
	private String url;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "smmmofis_id",insertable = false,updatable = false)
	private SmmmOfis smmmofis;
	private Integer smmmofis_id;
	
	

}
