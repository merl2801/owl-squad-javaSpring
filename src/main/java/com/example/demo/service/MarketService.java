package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Market;

public interface MarketService {
	List<Market> getAllMarket();
	
	void saveMarket(long id ,String name, MultipartFile image,String address, Double lat, Double lng, String name_req);
	
	Market getMarketById(long id);
	
	void deleteMarketById(long id);
	
	void saveMarketToDB(MultipartFile image, String name, String address, Double lat, Double lng, String name_req );
	
	List<Market> getByKeyWord(String key);
		
}
