package com.example.demo.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Market;
import com.example.demo.repository.MarketRepository;

@Controller
@RequestMapping(path = "/userMarket")
public class Market2Controller {
	@Autowired
	private MarketRepository marketRepository;
	
	 @GetMapping("/")
	    public String sample(Model model) {
	        model.addAttribute("title", "Hello world");
	        return "user/index";
	    }
	 
	 @GetMapping(path = "/all")
	    public @ResponseBody Iterable<Market> getAllUsers() {
	        return marketRepository.findAll();
	    }
	 
	 
	 @GetMapping("getimage/{image}")
		@ResponseBody
		public ResponseEntity<ByteArrayResource> getImage(@PathVariable("image") String image){
			if(!image.equals("") || image != null) {
				try {
					Path filename = Paths.get("src/main/resources/static/uploads/",image);
					byte[] buffer = Files.readAllBytes(filename);
					ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
					return ResponseEntity.ok()
							.contentLength(buffer.length)
							.contentType(MediaType.parseMediaType("image/png"))
							.body(byteArrayResource);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			return ResponseEntity.badRequest().build();
		}
}
