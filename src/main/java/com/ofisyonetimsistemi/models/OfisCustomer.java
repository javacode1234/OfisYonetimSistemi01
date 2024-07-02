package com.ofisyonetimsistemi.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.ofisyonetimsistemi.models.commonmodels.BusinesSector;
import com.ofisyonetimsistemi.models.commonmodels.IsletmeTipi;
import com.ofisyonetimsistemi.models.commonmodels.NaceKodu;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name = "ofiscustomer")
public class OfisCustomer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String unvan;
	
	private String firstName;
	private String lastName;
	private String fullName=firstName+" "+lastName;
	
	@ManyToOne
	@JoinColumn(name="isletmetipi_id",updatable = false,insertable = false)
	private IsletmeTipi isletmetipi;
	private Integer isletmetipi_id;
	
	@ManyToOne
	@JoinColumn(name="businessector_id",updatable = false,insertable = false)
	private BusinesSector businessector;
	private Integer businessector_id;
	
	@ManyToMany(mappedBy = "customers")
	private List<NaceKodu> nacekodlari;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date kurulusTarihi;
	
	@OneToMany(mappedBy = "customer")
	private List<CustomerAdres> adresler;
	
	@OneToMany(mappedBy = "customer")
	private List<CustomerTelelefon> telefonlar;
	
	@OneToMany(mappedBy = "customer")
	private List<CustomerEmail> emailler;
	
	@ManyToOne
	@JoinColumn(name = "smmmofis_id",insertable = false, updatable = false)
	private SmmmOfis smmmofis;
	private Integer smmmofis_id;
	
	
	

}
