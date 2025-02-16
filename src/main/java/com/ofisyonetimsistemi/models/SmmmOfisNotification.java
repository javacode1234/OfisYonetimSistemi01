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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "notifications")
@Builder
public class SmmmOfisNotification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String header;
	
	@Column(length = 2000)
	private String notification;
	
	@Column(length = 2000)
	private String information;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dateofpublish;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dateofread;
	
	private boolean okundu;
	
	@ManyToOne
	@JoinColumn(name="smmmofis_id",insertable = false,updatable = false)
	@JsonIgnore
	private SmmmOfis smmmofis;
	private Integer smmmofis_id;

}
