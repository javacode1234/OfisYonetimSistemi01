package com.ofisyonetimsistemi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ofisyonetimsistemi.models.PricingItem;

@Repository
public interface PricingItemRepository extends JpaRepository<PricingItem, Integer> {

	@Query(value="select p from PricingItem p where p.smmmofispricing_id = :pid", nativeQuery = false)
	List<PricingItem> findByPricingId(@Param("pid") Integer pricingId);

}
