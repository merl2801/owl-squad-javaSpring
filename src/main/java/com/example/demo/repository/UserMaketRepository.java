package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.UserMarket;

public interface UserMaketRepository extends JpaRepository<UserMarket, Long>{
	@Query(value = "SELECT user_market.id, user_market.name,user_market.address,user_market.phone,user_market.time,user_market.description,user_market.image,user_market.email FROM user_market JOIN users on users.id = user_market.id WHERE users.email = :email", nativeQuery = true)
	Optional<UserMarket> findByEmail(@Param("email") String email);
	
	@Query(value = "SELECT user_market.id,user_market.name,user_market.address,user_market.description,user_market.image,user_market.phone,user_market.time,users.email FROM user_market JOIN users on users.id = user_market.id WHERE users.id and user_market.id > 1;",nativeQuery = true)
	List<UserMarket> findMarketByUserId();
	
	@Query(value = "SELECT user_market.id,user_market.name,user_market.address,user_market.description,user_market.image,user_market.phone,user_market.time,users.email FROM user_market JOIN users ON users.id = user_market.id WHERE user_market.name like %:keyword% or user_market.address like %:keyword%",nativeQuery = true)
	 List<UserMarket> findByKeyword(@Param("keyword") String keyword);
	
}
