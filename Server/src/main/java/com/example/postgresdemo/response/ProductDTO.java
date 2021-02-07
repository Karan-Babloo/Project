package com.example.postgresdemo.response;

import java.util.List;

import lombok.Data;

@Data
public class ProductDTO {

	private Integer id;
	private String productSku;
	private String productName;
	private String createDate;
	private List<SellerDTO> seller;
}

@Data
class SellerDTO {
	private Integer inventoryAmount;
	private String sellerName;
	private Double price;
}