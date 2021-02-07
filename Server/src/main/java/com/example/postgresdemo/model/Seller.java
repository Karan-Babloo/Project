package com.example.postgresdemo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "seller")
public class Seller {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "seller_name", nullable = false)
	private String sellerName;
	
	@Column(name = "inventory_amount")
	private Integer inventoryAmount;
	
	@JsonIgnore
	@OneToMany(mappedBy = "seller")
    private Set<ProductSeller> sellerProducts = new HashSet<>();
}

