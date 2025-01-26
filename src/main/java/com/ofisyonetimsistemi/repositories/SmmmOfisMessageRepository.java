package com.ofisyonetimsistemi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ofisyonetimsistemi.models.SmmmOfisMessage;

@Repository
public interface SmmmOfisMessageRepository extends JpaRepository<SmmmOfisMessage, Integer> {

}
