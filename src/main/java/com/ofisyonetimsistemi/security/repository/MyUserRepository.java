package com.ofisyonetimsistemi.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ofisyonetimsistemi.security.model.MyUser;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Integer>{
	
	@Query(value="select u from myuser u where u.username= :username", nativeQuery=true)
	Optional<MyUser> findUserByUsername(@Param("username")String username);

	Optional<MyUser> findByUsername(String username);
	
}
