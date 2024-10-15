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
@Table(name = "pricingplanservices")
public class PricingPlanServices {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String serviceName;
	
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name="pricingplan_id",insertable = false,updatable = false)
	private SmmmOfisPricingPlan smmmofispricingplan;
	private Integer pricingplan_id;

}
