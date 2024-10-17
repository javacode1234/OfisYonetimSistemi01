package com.ofisyonetimsistemi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ofisyonetimsistemi.models.SmmmOfis;

@Repository
public interface SmmmOfisRepository extends JpaRepository<SmmmOfis, Integer>{

}
