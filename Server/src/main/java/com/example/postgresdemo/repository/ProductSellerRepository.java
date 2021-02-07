package com.example.postgresdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.postgresdemo.model.ProductSeller;
import com.example.postgresdemo.model.ProductSellerId;

@Repository
public interface ProductSellerRepository extends JpaRepository<ProductSeller, ProductSellerId> {
	List<Integer> findByIdProductId(Integer productId);
}
