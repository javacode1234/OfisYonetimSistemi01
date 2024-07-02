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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "isletmetipi")
public class IsletmeTipi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String isletmeTipi;
	private String description;
	
	@OneToMany(mappedBy = "isletmetipi")
	private List<OfisCustomer> customers;
	

}
