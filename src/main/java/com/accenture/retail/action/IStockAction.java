package com.accenture.retail.action;

public interface IStockAction {
	
	public String incrementStock(String dc, String productId);
	
	public String decrementStock(String dc, String productId);
	
	public  String createStock(String dc, String productId, long init);
	
	public String getStock(String dc, String productId);

}
