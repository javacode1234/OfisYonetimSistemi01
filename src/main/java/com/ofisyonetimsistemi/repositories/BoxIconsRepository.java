package com.ofisyonetimsistemi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ofisyonetimsistemi.models.commonmodels.BoxIcons;

@Repository
public interface BoxIconsRepository extends JpaRepository<BoxIcons, Integer>{

}
