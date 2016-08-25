package com.retail.web;

import com.retail.constant.Constants;
import com.retail.data.IRepository;
import com.retail.domain.StockCounter;

/**
 * Provides RESTful API for Customer data 
 * 
 * @author Julián Rueda
 */
public class StockController extends BaseController {
	
	public StockController(String baseURL, IRepository repo) {
		super(repo);

		mapRoutes(baseURL + Constants.PATH, StockCounter.class);
	}
}