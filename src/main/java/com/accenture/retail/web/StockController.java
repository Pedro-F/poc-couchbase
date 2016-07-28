package com.accenture.retail.web;

import com.accenture.retail.data.IRepository;
import com.accenture.retail.domain.StockCounter;

/**
 * Provides RESTful API for Customer data 
 * 
 * @author Julián Rueda
 */
public class StockController extends BaseController {
	public final static String PATH = "/stock";

	public StockController(String baseURL, IRepository repo) {
		super(repo);

		mapRoutes(baseURL + "/stock", StockCounter.class);
	}
}