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
import com.example.demo.service.MarketService;


@Controller
public class MarketController {
	@Autowired
	private MarketService marketService;
	
	@GetMapping("/admin/listMarket")
	public String viewHomeListMarketOnMap(Model model) {
		model.addAttribute("listUsers",marketService.getAllMarket());
		return "admin/listMarketManager";
	}
	
		 //マーケット追加ページ表示
	@GetMapping("/admin/Add")
	public String showNewMarketForm(Model model) {
		Market market = new Market();
		model.addAttribute("market",market);
		
		return "Admin/addMarket";
	}
		 //マーケットの入力した情報はDatabaseに保存する
	@PostMapping("/admin/saveMarket")
	public String saveMarketForm(@RequestParam("name") String name,
			@RequestParam("address") String address,
			@RequestParam("lat") Double lat,
			@RequestParam("lng") Double lng,
			@RequestParam("name_req") String name_req,
			@RequestParam("image") MultipartFile file){
			marketService.saveMarketToDB(file, name, address, lat, lng,name_req);
		return "redirect:/admin/Admin_home";
		
	}
		//マーケットの情報更新ページ表示
	@PostMapping("/admin/updateMarket")
	public String updateMarket(
			@RequestParam("id") long id,
			@RequestParam("name") String name,
			@RequestParam("address") String address,
			@RequestParam("lat") Double lat,
			@RequestParam("lng") Double lng,
			@RequestParam("name_req") String name_req,
			@RequestParam("image") MultipartFile file) {
		marketService.saveMarket(id,name,file,address,lat,lng,name_req);
		return "redirect:/admin/Admin_home";
	}
		//idを使用してDatabaseからマーケットの情報を取得して表示する
	@GetMapping("/admin/Update/{id}")
	public String showFormForUpdate(@PathVariable (value = "id") long id, Model model) {
		
		Market market = marketService.getMarketById(id);
		
		model.addAttribute("market",market);
		
		return "Admin/updateMarket";
	}
	
	@GetMapping("/admin/deleteMarket/{id}")
	public String deleteMarket(@PathVariable (value = "id")long id) {
		this.marketService.deleteMarketById(id);
			return "redirect:/admin/listMarket";
	}
	
}