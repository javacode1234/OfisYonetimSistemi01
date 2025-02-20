package com.ofisyonetimsistemi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ofisyonetimsistemi.models.SmmmOfisSubscribedEmail;

@Repository
public interface SmmmOfisSubcribedEmailRepository extends JpaRepository<SmmmOfisSubscribedEmail, Integer> {

	@Query(value="select * from subscribedemails s where s.active= :active", nativeQuery=true)
	List<SmmmOfisSubscribedEmail> activeAndInActiveSubscribedEmailList(@Param("active")boolean active);
	
}
