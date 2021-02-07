package com.example.postgresdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.postgresdemo.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {

}
