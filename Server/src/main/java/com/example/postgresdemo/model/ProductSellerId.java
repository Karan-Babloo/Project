package com.example.postgresdemo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProductSellerId implements Serializable {

	private static final long serialVersionUID = -5896901030242170741L;

	@Column(name = "product_id")
	private Integer productId;

	@Column(name = "seller_id")
	private Integer sellerId;
	
}