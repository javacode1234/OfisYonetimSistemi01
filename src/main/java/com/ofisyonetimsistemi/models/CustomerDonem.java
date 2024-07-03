package com.ofisyonetimsistemi.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customerdonem")
public class CustomerDonem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	private Date startdate;
	private Date enddate;
	
	private String description;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "customer_donem", 
        joinColumns = { @JoinColumn(name = "customer_id",referencedColumnName = "id") }, 
        inverseJoinColumns = { @JoinColumn(name = "donem_id",referencedColumnName = "id") }
    )
	private List<SmmmOfisCustomer> customer;
}
