package com.ofisyonetimsistemi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ofisyonetimsistemi.models.SmmmOfisWhyUs;

@Repository
public interface SmmmOfisWhyUsRepository extends JpaRepository<SmmmOfisWhyUs, Integer>{

}
