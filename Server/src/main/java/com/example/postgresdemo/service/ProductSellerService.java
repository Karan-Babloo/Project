package com.example.postgresdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Product;
import com.example.postgresdemo.model.ProductSeller;
import com.example.postgresdemo.model.Seller;
import com.example.postgresdemo.repository.ProductRepository;
import com.example.postgresdemo.repository.ProductSellerRepository;
import com.example.postgresdemo.repository.SellerRepository;

@Service(value = "productSellerService")
public class ProductSellerService {

	@Autowired
	private ProductSellerRepository productSellerRepository;

	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private ProductRepository productRepository;

	public List<ProductSeller> getAll() {
		return this.productSellerRepository.findAll();
	}

	public ProductSeller insert(ProductSeller ps) {
		ProductSeller newPS = null;
		if (ps.getId().getProductId() != null && ps.getId().getSellerId() != null) {
			// check if records exists
			boolean productExists = this.productRepository.existsById(ps.getId().getProductId());
			boolean sellerExists = this.sellerRepository.existsById(ps.getId().getSellerId());
			// If Both records exist then fetch entire objects
			if (productExists && sellerExists) {
				Optional<Product> product = this.productRepository.findById(ps.getId().getProductId());
				Optional<Seller> seller = this.sellerRepository.findById(ps.getId().getSellerId());
				// Since we know the objects exits use get
				newPS = new ProductSeller(product.get(), seller.get(), ps.getPrice());
				this.productSellerRepository.save(newPS);
			} else {
				throw new ResourceNotFoundException("Dependent resources not found for the Ids provided");
			}

		} else {
			throw new NullPointerException("Passed null id");
		}
		return newPS;
	}

}
