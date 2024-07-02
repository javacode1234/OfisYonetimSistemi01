package com.ofisyonetimsistemi.models.commonmodels;

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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name="ilce")
public class Ilce {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String code;
	
	private String name;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name="sehir_id",insertable = false,updatable = false)
	private Sehir sehir;
	private Integer sehir_id;

}
