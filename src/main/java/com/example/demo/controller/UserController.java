package com.example.demo.controller;
import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	//ログインしてマイページ表示
	@GetMapping("/user/mypage")
	public String HomePage(Principal principal, Model model) {
		 // ログイン処理時のユーザのメールをDBから取得する
		Authentication authentication = (Authentication)principal;
		String username = authentication.getName();
		model.addAttribute("username", username);
		return "user/myPage";
	}
	
	
	
}
