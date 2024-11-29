package com.ofisyonetimsistemi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ofisyonetimsistemi.models.SmmmOfisTeam;

@Repository
public interface SmmmOfisHomePageTeamRepo extends JpaRepository<SmmmOfisTeam, Integer>{

}
