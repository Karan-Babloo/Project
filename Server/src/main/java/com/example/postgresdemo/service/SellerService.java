package com.example.postgresdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Seller;
import com.example.postgresdemo.repository.SellerRepository;

@Service(value = "sellerService")
public class SellerService {
	
	@Autowired
	private SellerRepository sellerRepo;
	
	public List<Seller> getAll() {
		return this.sellerRepo.findAll();
	}
	
	public Seller insert(Seller seller) {
		Seller newS = new Seller();
		newS.setSellerName(seller.getSellerName());
		newS.setInventoryAmount(seller.getInventoryAmount());
		return this.sellerRepo.save(newS);
	}

	public Seller update(Integer id, Seller seller) {
		Seller newS = new Seller();
		if (this.sellerRepo.existsById(id)) {
			newS.setSellerName(seller.getSellerName());
			newS.setInventoryAmount(seller.getInventoryAmount());
			this.sellerRepo.save(newS);
		} else {
			throw new ResourceNotFoundException("No seller with give id found");
		}
		return newS;
	}
	
	public Seller delete(Integer id, Seller seller) {
		if (this.sellerRepo.existsById(id)) {
			this.sellerRepo.delete(seller);
		} else {
			throw new ResourceNotFoundException("No seller with give id found");
		}
		return seller;
	}

}
