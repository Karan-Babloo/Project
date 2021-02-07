package com.example.postgresdemo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.postgresdemo.model.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

	Product findById(String id);
	
}
