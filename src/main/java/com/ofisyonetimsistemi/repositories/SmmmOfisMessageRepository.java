package com.ofisyonetimsistemi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ofisyonetimsistemi.models.SmmmOfisMessage;

@Repository
public interface SmmmOfisMessageRepository extends JpaRepository<SmmmOfisMessage, Integer> {

	@Query(value="select count(*) from Messages m where m.okundu= :okundu ", nativeQuery=true)
	Long countOfNonReadMessage(@Param("okundu")boolean okundu);
	
	@Query(value="select * from Messages m where m.okundu= :okundu ", nativeQuery=true)
	List<SmmmOfisMessage> getAllUnReadMessages(@Param("okundu")boolean okundu);

	@Query(value="select * from Messages m where m.okundu= :okundu ", nativeQuery=true)
	List<SmmmOfisMessage> getAllReadMessages(@Param("okundu")boolean okundu);

}
