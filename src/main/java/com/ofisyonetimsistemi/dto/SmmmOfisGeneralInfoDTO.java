package com.ofisyonetimsistemi.dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SmmmOfisGeneralInfoDTO {

	@Lob	
	private byte[] logo;
	
	@Lob
	private String stringLogo;
	
	private String unvan;
	private String firstName;
	private String lastName;
	private String fullName = firstName+" "+lastName;
	private String email;
	private String telephone;	
	private String address;	
	private String userName;
	private String password;	
}
