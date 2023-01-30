package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.service.UserMarketService;
import com.example.demo.service.UserService;

@Controller
public class AdminController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMarketService userMarketService;
	
		//adminページ表示
	@GetMapping("/admin/index")
	public String adminHome() {
		return "admin/index";
	}
	
	//Admin 管理の部分👇
	@GetMapping("/admin/Admin_home")
	public String viewHomeAdminArea(Model model) {
		model.addAttribute("listUsers",userService.getAllUsers());
		return "Admin/index";
	}
	//idでマーケット削除
	@GetMapping("/admin/delete/{id}")
	public String delete(@PathVariable (value = "id")long id) {
		
		this.userService.deleteUserById(id);
		this.userMarketService.deleteUserMarketbyId(id);
		return "redirect:/admin/Admin_home";
	}
	
	@GetMapping("/admin/listUserMarket")
	public String AdminListUserMarket(Model model) {
		model.addAttribute("listMarket",userMarketService.getAllMarketWithUserEmail());
		return "admin/userMarketManager";
	}
	
}
