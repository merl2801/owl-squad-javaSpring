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
@Table(name = "ListMarket")
public class Market {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name ;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "lat")
	private Double lat;
	
	@Column(name = "lng")
	private Double lng;
	
	@Column(name = "name_req")
	private String name_req;
	
}
