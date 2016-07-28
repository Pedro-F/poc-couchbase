package com.accenture.retail.domain;

public class StockEntry extends Entity {
	private String entryId;
	private String distributionCenter;
	private String productId;
	private String status;
	private String source;
	private String destination;
	
	public String getEntryId() {
		return entryId;
	}
	
	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}
	
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
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public void generateId() {
		this.setId(distributionCenter + ":" + productId + ":" + entryId);
	}
	
}