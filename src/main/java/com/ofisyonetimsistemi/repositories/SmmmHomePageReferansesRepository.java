package com.ofisyonetimsistemi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ofisyonetimsistemi.models.SmmmOfisReferanses;

@Repository
public interface SmmmHomePageReferansesRepository extends JpaRepository<SmmmOfisReferanses, Integer> {

}
