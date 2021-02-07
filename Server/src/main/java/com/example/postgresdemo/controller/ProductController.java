package com.example.postgresdemo.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresdemo.model.Product;
import com.example.postgresdemo.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostConstruct
	private void initalize() {
	}

	@GetMapping(value = "/product/all")
	public List<Product> getAll() {
		return this.productService.getAll();
	}

	@GetMapping(value = "/product/{id}")
	public Optional<Product> getAll(@PathVariable Integer id) {
		return this.productService.getOne(id);
	}
	
	@PostMapping(value = "/product")
	public Product insert(@RequestBody Product product) {
		return this.productService.insert(product);
	}

	@PutMapping(value = "/product/{id}")
	public Product update(@PathVariable Integer id, @RequestBody Product product) {
		return this.productService.update(id, product);
	}

	@DeleteMapping(value = "/product/{id}")
	public Optional<Product> delete(@PathVariable Integer id) {
		return this.productService.delete(id);
	}

	@GetMapping(value = "/product/search")
	public ResponseEntity<List<Product>> searchAll(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "productSeller.price") String sortBy) {
		List<Product> list = this.productService.searchAll(pageNo, pageSize, sortBy);

		return new ResponseEntity<List<Product>>(list, new HttpHeaders(), HttpStatus.OK);
	}

}
