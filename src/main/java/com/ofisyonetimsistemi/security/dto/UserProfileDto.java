package com.ofisyonetimsistemi.security.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserProfileDto {
	
	
	@Lob
	@Column(name = "stringResim", columnDefinition = "LONGTEXT")
	private String image;
	
	private String username;
	
	@NotEmpty(message = "Bu alan boş olamaz !!")
	private String firstname;
	
	@NotEmpty(message = "Bu alan boş olamaz !!")
	private String lastname;
	
	@Email
	@NotEmpty(message = "Bu alan boş olamaz !!")
	private String email;
	
	private String about;
		
	private String x;
	private String f;
	private String i;
	private String l;
	
	private String company;
	private String job;
	private String adres;
	
	@NotEmpty(message = "Bu alan boş olamaz !!")
	private String telefon;
	private String country;
	
	
}
