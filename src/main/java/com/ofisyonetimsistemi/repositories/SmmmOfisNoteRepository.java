package com.ofisyonetimsistemi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ofisyonetimsistemi.models.SmmmOfisNotes;

@Repository
public interface SmmmOfisNoteRepository extends JpaRepository<SmmmOfisNotes, Integer> {
	
	@Query(value="select count(*) from smmmofisnotes s where s.okundu= :okundu ", nativeQuery=true)
	Long countOfUnReadOrReadNotes(@Param("okundu")boolean okundu);
	
	// false: unread - true: read
	@Query(value="select * from smmmofisnotes s where s.okundu= :okundu ", nativeQuery=true)
	List<SmmmOfisNotes> getAllReadOrUnReadNotes(@Param("okundu")boolean okundu);

}
