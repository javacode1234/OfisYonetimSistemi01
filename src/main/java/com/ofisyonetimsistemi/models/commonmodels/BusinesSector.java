package com.ofisyonetimsistemi.models.commonmodels;

import java.util.List;

import com.ofisyonetimsistemi.models.OfisCustomer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name="businessector")
public class BusinesSector {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String description;
	
	@OneToMany(mappedBy = "businessector")
	private List<OfisCustomer> customers;

}
