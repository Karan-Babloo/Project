package com.example.postgresdemo.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresdemo.model.ProductSeller;
import com.example.postgresdemo.service.ProductSellerService;

@RestController
public class ProductSellerController {

	@Autowired
	private ProductSellerService productSellerService;

	@PostConstruct
	private void initalize() {
	}

	@GetMapping(value = "/productSeller/all")
	public List<ProductSeller> getAll() {
		return this.productSellerService.getAll();
	}
	
	@PostMapping(value = "/productSeller")
	public ProductSeller insert(@RequestBody ProductSeller seller) {
		return this.productSellerService.insert(seller);
	}

}
