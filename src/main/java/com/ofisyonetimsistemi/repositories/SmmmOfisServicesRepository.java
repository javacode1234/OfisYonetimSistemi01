package com.ofisyonetimsistemi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ofisyonetimsistemi.models.SmmmOfisServices;

@Repository
public interface SmmmOfisServicesRepository extends JpaRepository<SmmmOfisServices, Integer> {

}
