package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {
	//マップの部分👇
	@GetMapping("/map")
	public String map() {
		return "/map/test";
	}
	@GetMapping("/map/life")
	public String life() {
		return "/map/market/life";
	}
	@GetMapping("/map/lopia")
	public String lopia() {
		return "/map/market/lopia";
	}
	@GetMapping("/map/lozen")
	public String lozen() {
		return "/map/market/lo-zen";
	}
}
