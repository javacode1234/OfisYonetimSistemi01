package com.ofisyonetimsistemi.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "smmmofisnotes")
@Builder
public class SmmmOfisNotes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Bu alan boş olamaz !!!")
	private String subject;
	
	@NotEmpty(message = "Bu alan boş olamaz !!!")
	@Column(length = 2000)
	private String noteText;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime readDate;
	
	private boolean okundu;
			
	@ManyToOne
	@JoinColumn(name="smmmofis_id", insertable = false, updatable = false)
	@JsonIgnore
	private SmmmOfis smmmofis;
	private Integer smmmofis_id;

}
