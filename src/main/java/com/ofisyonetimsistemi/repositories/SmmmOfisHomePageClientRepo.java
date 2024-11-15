package com.ofisyonetimsistemi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ofisyonetimsistemi.models.SmmmOfisHomePageClient;

@Repository
public interface SmmmOfisHomePageClientRepo extends JpaRepository<SmmmOfisHomePageClient, Integer> {

}
