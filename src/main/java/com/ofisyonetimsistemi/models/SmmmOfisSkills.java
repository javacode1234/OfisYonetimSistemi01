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
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "smmmofisskills")
public class SmmmOfisSkills {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String header;
	
	private Long ratio;
	
	@ManyToOne
	@JoinColumn(name="smmmofis_id",insertable = false,updatable = false)
	private SmmmOfis smmmofis;
	private Integer smmmofis_id;

}
