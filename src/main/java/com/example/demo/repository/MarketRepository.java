package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Market;

public interface MarketRepository extends JpaRepository<Market, Long> {
	//Custom query
		 @Query(value = "select * from list_market s where s.name like %:keyword% or s.address like %:keyword%", nativeQuery = true)
		 List<Market> findByKeyword(@Param("keyword") String keyword);
		 
}
