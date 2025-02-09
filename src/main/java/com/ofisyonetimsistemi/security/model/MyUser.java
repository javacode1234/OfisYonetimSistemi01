package com.ofisyonetimsistemi.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ofisyonetimsistemi.models.SmmmOfis;

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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "myuser")
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MyUser {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String fullname;
	private String unvan;
	
	@Lob
	@Column(name = "stringResim", columnDefinition = "LONGTEXT")
	private String image;
	
	//@NotEmpty(message = "Bu alan boş olamaz !!")
	private String firstname;
	
	//@NotEmpty(message = "Bu alan boş olamaz !!")
	private String lastname;
	
	//@NotEmpty(message = "Bu alan boş olamaz !!")
	private String email;
	
	//@NotEmpty(message = "Bu alan boş olamaz !!")
	private String about;
	
	//@NotEmpty(message = "Bu alan boş olamaz !!")
	private String username;
	
	//@NotEmpty(message = "Bu alan boş olamaz !!")
	private String password;
	private String openpassword;
	
	//@NotEmpty(message = "Bu alan boş olamaz !!")
	private String roles;
	
	private String x;
	private String f;
	private String i;
	private String l;
	
	private String company;
	private String job;
	private String adres;
	private String telefon;
	private String country;
	
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	@ManyToOne
	@JoinColumn(name = "smmmofis_id",updatable = false,insertable = false)
	@JsonIgnore
	private SmmmOfis smmmofis;
	private Integer smmmofis_id;
	
	
	public MyUser() {
		super();
	}
	
	
	
	
	
}
