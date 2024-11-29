package com.ofisyonetimsistemi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ofisyonetimsistemi.models.PortfolioCompany;

@Repository
public interface PortfolioCompanyRepository extends JpaRepository<PortfolioCompany, Integer> {

	@Query(value="select c from PortfolioCompany c where c.businessector_id = :sectorid", nativeQuery = false)
	List<PortfolioCompany> findAllByPortfoyId(@Param("sectorid")Integer id);

}
