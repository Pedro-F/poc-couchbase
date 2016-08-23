package com.accenture.retail.web;

import com.accenture.retail.constant.Constants;
import com.accenture.retail.data.IRepository;
import com.accenture.retail.domain.StockCounter;

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