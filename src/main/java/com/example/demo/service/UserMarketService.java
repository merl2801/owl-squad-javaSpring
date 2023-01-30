package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.UserMarket;

public interface UserMarketService {
	void saveUserMarket(long id, String name, String address, String phone, String time, String description, MultipartFile image);
	
	void saveUserMarketToDB(String name, String address, String phone, String time, String description, MultipartFile image);
	
	UserMarket getMarketByEmail(String email);
	
	UserMarket getUserMarketById(long id);
	
	List<UserMarket> getAllMarketWithUserEmail();
	
	List<UserMarket> getByKeyWord(String key);
	
	void deleteUserMarketbyId(long id);
}
