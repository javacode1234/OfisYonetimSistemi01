package com.ofisyonetimsistemi.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
@Entity
@Table(name = "subscribedemails")
public class SmmmOfisSubscribedEmail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Bir e-mail adresi giriniz!!!")
	@Email
	@Column(name = "email", unique=true)
	private String email;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime date;
	
	private boolean active;
		
	@ManyToOne
	@JoinColumn(name = "smmmofis_id",insertable = false,updatable = false)
	@JsonIgnore
	private SmmmOfis smmmofis;
	private Integer smmmofis_id;

}
