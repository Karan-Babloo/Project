package com.example.postgresdemo.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresdemo.model.Seller;
import com.example.postgresdemo.service.SellerService;

@RestController
public class SellerController {

	@Autowired
	private SellerService sellerService;

	@PostConstruct
	private void initalize() {
	}

	@GetMapping(value = "/seller/all")
	public List<Seller> getAll() {
		return this.sellerService.getAll();
	}

	@PostMapping(value = "/seller")
	public Seller insert(@RequestBody Seller seller) {
		return this.sellerService.insert(seller);
	}

	@PutMapping(value = "/seller/{id}")
	public Seller update(@PathVariable Integer id, @RequestBody Seller seller) {
		return this.sellerService.update(id, seller);
	}

	@DeleteMapping(value = "/seller/{id}")
	public Seller delete(@PathVariable Integer id, @RequestBody Seller seller) {
		return this.sellerService.delete(id, seller);
	}

}
