package com.ofisyonetimsistemi.models.commonmodels;

import java.util.List;

import com.ofisyonetimsistemi.models.OfisCustomer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name = "nacekodu")
public class NaceKodu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String sectorkodu;
	private String sectortanim;
	private String meslekkodu;
	private String meslektanim;
	private String nacekodu;
	private String nacetanim;
	
	@ManyToMany
	private List<OfisCustomer> customers;
	

}
