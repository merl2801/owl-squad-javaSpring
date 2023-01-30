package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	@Autowired
	UserService userService;
	
	//ログインページ表示
	@GetMapping("/login")
	 public String login(){
        return "auth/login";
    }
	
	//新規登録のページ表示
	@GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "auth/register";
    }
	
	//新規登録　Databaseに保存する
	 @PostMapping("/register")
	    public String registerUser(Model model, @Valid User user, BindingResult bindingResult){
	        if(bindingResult.hasErrors()){
	            model.addAttribute("successMessage", "User registered successfully!");
	            model.addAttribute("bindingResult", bindingResult);
	            return "auth/register";
	        }
	        List<Object> userPresentObj = userService.isUserPresent(user);
	        if((Boolean) userPresentObj.get(0)){
	            model.addAttribute("successMessage", userPresentObj.get(1));
	            return "auth/register";
	        }

	        userService.saveUser(user);
	        return "redirect:/userMarketRegist";
	    }

}
