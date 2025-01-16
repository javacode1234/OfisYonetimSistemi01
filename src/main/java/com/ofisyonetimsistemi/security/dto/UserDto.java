package com.ofisyonetimsistemi.security.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class UserDto {
	
	private Integer id;
	
	@Lob
	@Column(name = "stringResim", columnDefinition = "LONGTEXT")
	private String image;
	
	@NotEmpty(message = "Bu alan boş olamaz !!")
	private String firstname;
	
	@NotEmpty(message = "Bu alan boş olamaz !!")
	private String lastname;
	
	@Email
	@NotEmpty(message = "Bu alan boş olamaz !!")
	private String email;
	
	private String about;

	@NotEmpty(message = "Kullanıcı Adı boş olamaz !!")	
	private String username;
	
	@NotEmpty(message = "Şifre boş olamaz !!")
	private String password;
	
	@NotNull(message = "En az bir Role seçmelisin !!")
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
}
