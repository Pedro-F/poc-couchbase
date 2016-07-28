package com.accenture.retail.domain;

public class StockCounter extends Entity {
	private String distributionCenter;
	private String productId;
	private long stock;
	
	public String getDistributionCenter() {
		return distributionCenter;
	}
	public void setDistributionCenter(String distributionCenter) {
		this.distributionCenter = distributionCenter;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public long getStock() {
		return stock;
	}
	public void setStock(long stock) {
		this.stock = stock;
	}

}