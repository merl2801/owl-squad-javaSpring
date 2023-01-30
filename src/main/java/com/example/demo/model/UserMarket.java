package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "UserMarket")
public class UserMarket {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name ;
	
	@Column(name = "address")
	private String address ;
	
	@Column(name = "phone")
	private String phone ;
	
	@Column(name = "time")
	private String time ;
	
	@Column(name = "description")
	private String description ;
	
	@Column(name = "image")
	private String image ;
	
	private String email;
	
}
