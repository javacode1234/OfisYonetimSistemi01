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
@Table(name = "ofistelefon")
public class OfisTelelefon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String telefonNo;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name="smmmofis_id",updatable = false, insertable = false)
	private SmmmOfis smmmofis;
	private Integer smmmofis_id;
}
