package com.ofisyonetimsistemi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ofisyonetimsistemi.models.SmmmOfisPricing;

@Repository
public interface SmmmOfisPricingRepository extends JpaRepository<SmmmOfisPricing, Integer>{

}
