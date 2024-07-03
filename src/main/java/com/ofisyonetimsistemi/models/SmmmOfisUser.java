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
@Table(name = "smmmofissuser")
public class SmmmOfisUser {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String firstName;
	private String lastName;
	private String email;
	private String pwd;
	private String tel;
	private boolean active;
	private String ofisUserAdres;
	
	@ManyToOne
	@JoinColumn(name = "smmmofis_id",updatable = false,insertable = false)
	private SmmmOfis smmmofis;
	private Integer smmmofis_id;
	
}
