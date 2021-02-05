package com.example.postgresdemo.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Product;
import com.example.postgresdemo.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepo;

	@PostConstruct
	private void initalize() {
	}

	@GetMapping(value = "/product/all")
	public List<Product> getAll() {
		return this.productRepo.findAll();
	}

	@GetMapping(value = "/product/{id}")
	public Product getAll(@PathVariable String id) {
		if (productRepo.existsById(id)) {
			return this.productRepo.findByProductSku(id);
		} else {
			throw new ResourceNotFoundException("No Records exist");
		}
	}
	
	@DeleteMapping(value = "/product/{id}")
	public String deleteById(@PathVariable String id) {
		if (productRepo.existsById(id)) {
			this.productRepo.deleteById(id);
			return "Deleted Successfully";
		} else {
			throw new ResourceNotFoundException("No Records exist");
		}
	}
}
