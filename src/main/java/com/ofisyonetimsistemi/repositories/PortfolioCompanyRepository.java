package com.ofisyonetimsistemi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ofisyonetimsistemi.models.PortfolioCompany;

@Repository
public interface PortfolioCompanyRepository extends JpaRepository<PortfolioCompany, Integer> {

}
