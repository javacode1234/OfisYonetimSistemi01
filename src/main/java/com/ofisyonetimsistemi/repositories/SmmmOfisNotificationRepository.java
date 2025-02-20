package com.ofisyonetimsistemi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ofisyonetimsistemi.models.SmmmOfisNotification;

@Repository
public interface SmmmOfisNotificationRepository extends JpaRepository<SmmmOfisNotification, Integer> {

	@Query(value="select count(*) from notifications n where n.okundu= :okundu ", nativeQuery=true)
	Long countOfUnReadOrReadNotifications(@Param("okundu")boolean okundu);
	
	@Query(value="select * from notifications n where n.okundu= :okundu ", nativeQuery=true)
	List<SmmmOfisNotification> getAllUnReadNotifications(@Param("okundu")boolean okundu);

	@Query(value="select * from notifications n where n.okundu= :okundu ", nativeQuery=true)
	List<SmmmOfisNotification> getAllReadNotifications(@Param("okundu")boolean okundu);

}
