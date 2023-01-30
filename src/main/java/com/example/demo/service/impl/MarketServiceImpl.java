package com.example.demo.service.impl;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Market;
import com.example.demo.repository.MarketRepository;
import com.example.demo.service.MarketService;

import jakarta.servlet.ServletContext;


@Service
public class MarketServiceImpl implements MarketService{
	
	@Autowired
	private MarketRepository marketRepository;
	@Autowired
	ServletContext application;
	@Override
	public List<Market> getAllMarket() {
		return marketRepository.findAll();
	}
	
//	@Override
//	public List<Market> getAllMarketWithUserEmail() {
//		return marketRepository.findMarketByUserId();
//	}
	
	@Override
	public Market getMarketById(long id) {
		Optional<Market> optional = marketRepository.findById(id);
		Market market = null;
		if(optional.isPresent()) {
			market = optional.get();
		}else {
			throw new RuntimeException(" Market not found for id :: " + id);
		}
		return market;
	}

	@Override
	public void deleteMarketById(long id) {
		this.marketRepository.deleteById(id);
	}

	@SuppressWarnings("null")
	@Override
	public void saveMarketToDB(MultipartFile file, String name, String address, Double lat, Double lng, String name_req) {
		// TODO 自動生成されたメソッド・スタブ
		Market market = new Market();
		if(file.isEmpty()) {
			System.out.println("no image");
		}
		Path path = Paths.get("src/main/resources/static/uploads/");
		try {
			InputStream inputStream = file.getInputStream();
			Files.copy(inputStream, path.resolve(file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		market.setImage(file.getOriginalFilename().toLowerCase());
		market.setName(name);
		market.setAddress(address);;
		market.setLat(lat);;
		market.setLng(lng);
		market.setName_req(name_req);
		
		marketRepository.save(market);
		
	}

	@SuppressWarnings("null")
	@Override
	public void saveMarket(long id, String name, MultipartFile file,String address, Double lat, Double lng, String name_req) {
		Market market = new Market();
		market.setId(id);
		market.setName(name);
		if(file.isEmpty()) {
			System.out.println("no image");
		}
		Path path = Paths.get("src/main/resources/static/uploads/");
		try {
			InputStream inputStream = file.getInputStream();
			Files.copy(inputStream, path.resolve(file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
			market.setImage(file.getOriginalFilename().toLowerCase());
		
		market.setLat(lat);
		market.setLng(lng);
		market.setAddress(address);
		market.setName_req(name_req);
		
		marketRepository.save(market);
	}

	@Override
	public List<Market> getByKeyWord(String keyword) {
		return marketRepository.findByKeyword(keyword);
	}






	
}