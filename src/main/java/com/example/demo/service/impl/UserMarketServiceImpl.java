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

import com.example.demo.model.UserMarket;
import com.example.demo.repository.UserMaketRepository;
import com.example.demo.service.UserMarketService;
@Service
public class UserMarketServiceImpl implements UserMarketService{
	@Autowired
	UserMaketRepository userMaketRepository;
	@SuppressWarnings("null")
	@Override
	public void saveUserMarket(long id, String name, String address, String phone, String time, String description,
			MultipartFile file) {
		UserMarket userMarket = new UserMarket();
		userMarket.setId(id);
		userMarket.setName(name);
		userMarket.setAddress(address);
		userMarket.setPhone(phone);
		userMarket.setTime(time);
		userMarket.setDescription(description);
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
		userMarket.setImage(file.getOriginalFilename().toLowerCase());
		userMaketRepository.save(userMarket);
	}
	@SuppressWarnings("null")
	@Override
	public void saveUserMarketToDB(String name, String address, String phone, String time, String description,
			MultipartFile file) {
		UserMarket userMarket = new UserMarket();
		userMarket.setName(name);
		userMarket.setAddress(address);
		userMarket.setPhone(phone);
		userMarket.setTime(time);
		userMarket.setDescription(description);
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
			userMarket.setImage(file.getOriginalFilename().toLowerCase());
		userMaketRepository.save(userMarket);
	}

	@Override
	public UserMarket getMarketByEmail(String email) {
		// TODO 自動生成されたメソッド・スタブ
		Optional<UserMarket> optional = userMaketRepository.findByEmail(email);
		UserMarket userMarket = null;
		if(optional.isPresent()) {
			userMarket = optional.get();
		}else {
			throw new RuntimeException(" Market not found for id :: " + email);
		}
		return userMarket;
	}
	@Override
	public UserMarket getUserMarketById(long id) {
		// TODO 自動生成されたメソッド・スタブ
		Optional<UserMarket> optional = userMaketRepository.findById(id);
		UserMarket userMarket = null;
		if(optional.isPresent()) {
			userMarket = optional.get();
		}else {
			throw new RuntimeException(" Market not found for id :: " + id);
		}
		return userMarket;
	}
	@Override
	public List<UserMarket> getAllMarketWithUserEmail() {	
		return userMaketRepository.findMarketByUserId();
	}
	@Override
	public List<UserMarket> getByKeyWord(String key) {
		return userMaketRepository.findByKeyword(key);
	}
	@Override
	public void deleteUserMarketbyId(long id) {
		// TODO 自動生成されたメソッド・スタブ
		this.userMaketRepository.deleteById(id);
		
	}
}
