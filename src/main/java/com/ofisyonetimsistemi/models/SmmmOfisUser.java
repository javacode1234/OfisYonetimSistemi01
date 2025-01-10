package com.ofisyonetimsistemi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
	
	@Lob
	@Column(name = "resim", columnDefinition = "LONGBLOB")
	private byte[] resim;
	
	@Lob
	@Column(name = "stringResim", columnDefinition = "LONGTEXT")
	private String stringResim;
	
	private String firstName;
	private String lastName;
	private String email;
	private String tel;
	
	private String username;
	private String password;
	
	private String role;
	
	private boolean isActive;
	
	private String ofisUserAdres;
	
	@ManyToOne
	@JoinColumn(name = "smmmofis_id",updatable = false,insertable = false)
	@JsonIgnore
	private SmmmOfis smmmofis;
	private Integer smmmofis_id;
	
}
