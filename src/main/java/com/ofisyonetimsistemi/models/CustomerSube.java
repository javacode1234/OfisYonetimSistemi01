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
@Table(name = "customersube")
public class CustomerSube {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	private String nacekodu;
	private String naceadi;
	private String adres;
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "smmmofiscustomer_id",insertable = false,updatable = false)
	private SmmmOfisCustomer customer;
	private Integer smmmofiscustomer_id;

}
