package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Market;
import com.example.demo.model.UserMarket;
import com.example.demo.service.UserMarketService;

@Controller
public class UserMarketController {
	@Autowired
	UserMarketService userMarketService;
	
	//userMarketRegister.html を表示する
	@GetMapping("/userMarketRegist")
	public String UserMarket(Model model) {
		UserMarket userMarket = new UserMarket();
		model.addAttribute("userMarket", userMarket);
		return "user/userMarketRegister";
	}
	
	//入力したデータ　Databaseに保存する
	@PostMapping("/saveUserMarket")
	public String saveUserMarket(@RequestParam("name") String name,
			@RequestParam("address") String address,
			@RequestParam("phone") String phone,
			@RequestParam("time") String time,
			@RequestParam("description") String description,
			@RequestParam("image") MultipartFile image,
			Model model) {
		userMarketService.saveUserMarketToDB(name,address,phone,time,description,image);
		model.addAttribute("successMessage", "User registered successfully!");
		
		return "auth/login";
	}
	//showuserMarket.html 表示
	@GetMapping("/detail")
	public String DetailsMarket() {
		return "user/showUserMarket";
	}
	//メールを使用してDatabaseからユーザーマーケットの情報を取得してshowUserMarketで表示する

	@GetMapping("/user/getStoreDetailByEmail/{email}")
	public String showFormUserDetailByEmail(@PathVariable (value = "email") String email, Model model) {
		UserMarket userMarket = userMarketService.getMarketByEmail(email);
		model.addAttribute("userMarket",userMarket);
		return "user/showUserMarket";
	}
	//idを使用してDatabaseからユーザーマーケットの情報を取得してshowUserMarketで表示する

	@GetMapping("/user/getStoreDetailById/{id}")
	public String getStoreDetailById(@PathVariable (value = "id") long id, Model model) {
		UserMarket userMarket = userMarketService.getUserMarketById(id);
		model.addAttribute("userMarket",userMarket);
		return "user/showUserMarket";
	}
	//idを使用してDatabaseからユーザーマーケットの情報を取得してshowLoginUsermarketで表示する

	@GetMapping("/user/showUserMarketById/{id}")
	public String showUserMarketById(@PathVariable (value = "id") long id, Model model) {
		UserMarket userMarket = userMarketService.getUserMarketById(id);
		model.addAttribute("userMarket",userMarket);
		return "user/showLoginUserMarket";
	}

	
	//メールを使用してDatabaseからユーザーマーケットの情報を取得してshowLoginUsermarketで表示する
	@GetMapping("/user/getEmailUser/{email}")
	public String showFormForUserMarket(@PathVariable (value = "email") String email, Model model ) {
		UserMarket userMarket = userMarketService.getMarketByEmail(email);
		
		model.addAttribute("userMarket",userMarket);
		return "user/showLoginUserMarket";
	}
	//ユーザマーケットの情報更新ページ表示
	@PostMapping("/user/updateUserMarket")
	public String updateUserMarket(
			@RequestParam("id") long id,
			@RequestParam("name") String name,
			@RequestParam("address") String address,
			@RequestParam("phone") String phone,
			@RequestParam("time") String time,
			@RequestParam("description") String description,
			@RequestParam("image") MultipartFile image
			) {
		userMarketService.saveUserMarket(id, name, address, phone, time, description, image);

		return "redirect:/user/mypage";
	}
	//ユーザの画面表示👇
	@GetMapping("/list")
	public String ListUserMarket(Model model) {
		model.addAttribute("list", userMarketService.getAllMarketWithUserEmail());
		return "/marketList";
	}
	 //検索機能👇
	@GetMapping("/searchResult")
	public String SearchMarket( Market market, Model model, String keyword) {
		if(keyword != null) {
			model.addAttribute("listSearch", userMarketService.getByKeyWord(keyword));
		}else {
			return "redirect:/list";
		}
		return "/marketListSearchResults";
	}
}