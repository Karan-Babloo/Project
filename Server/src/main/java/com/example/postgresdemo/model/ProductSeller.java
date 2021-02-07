package com.example.postgresdemo.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
@Entity
@Table(name = "product_seller")
public class ProductSeller {  
    @EmbeddedId
    private ProductSellerId id;

    @ManyToOne
    @MapsId("productId")
    @JsonIgnore
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("sellerId")
    @JoinColumn(name = "seller_id")
    private Seller seller;

	@Column(name = "price")
	private Double price;
	
    public ProductSeller(Product product, Seller seller, Double price) {
    	//Construct the id using individual objects
        this.id = new ProductSellerId(product.getId(), seller.getId());
        this.product = product;
        this.seller = seller;
        this.price = price;
    }
}