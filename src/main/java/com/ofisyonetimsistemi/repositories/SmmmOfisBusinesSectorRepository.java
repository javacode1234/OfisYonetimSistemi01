package com.ofisyonetimsistemi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ofisyonetimsistemi.models.SmmmOfisBusinesSector;

@Repository
public interface SmmmOfisBusinesSectorRepository extends JpaRepository<SmmmOfisBusinesSector, Integer> {

}
