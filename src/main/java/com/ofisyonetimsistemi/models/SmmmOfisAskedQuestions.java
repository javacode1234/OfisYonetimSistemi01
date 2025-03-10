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

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "smmmofisfrequentlyaskedquestions")
public class SmmmOfisAskedQuestions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	@Lob
	@Column(name = "question", columnDefinition = "LONGTEXT")
	private String question;
	
	@Lob
	@Column(name = "answer", columnDefinition = "LONGTEXT")
	private String answer;
	
	private String description;
	
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name="smmmofis_id",insertable = false,updatable = false)
	@JsonIgnore
	private SmmmOfis smmmofis;
	private Integer smmmofis_id;

}
