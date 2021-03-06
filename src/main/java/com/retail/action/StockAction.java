package com.retail.action;

import static com.retail.constant.Constants.EMPTY_STOCK;
import static com.retail.constant.Constants.NOT_ENOUGH_STOCK;
import static com.retail.constant.Constants.PERSIST_LEVEL;
import static com.retail.constant.Constants.STOCK_DECREMENT;
import static com.retail.constant.Constants.STOCK_INCREMENT;

import java.util.Random;

import com.retail.data.IRepository;
import com.retail.exception.ActionException;
import com.retail.exception.EmptyStockException;
import com.retail.exception.RepositoryRetriableException;

public class StockAction implements IStockAction {

	private IRepository repo;
	private Random rand = new Random();

	public StockAction(IRepository repo) {
		this.repo = repo;
	}

	public String incrementStock(String dc, String productId) {
		int backOff = 150;
		int maxBackOff = 2000;
		try {
			do {
				try {
					return repo.incrementCAS(repo.generateCounterId(dc, productId), STOCK_INCREMENT, EMPTY_STOCK,
							PERSIST_LEVEL);
				} catch (RepositoryRetriableException e) {
					Thread.sleep(backOff);
					backOff = backOff *  2 + rand.nextInt(backOff);
				}
			} while (backOff < maxBackOff);
		} catch (Exception e) {

		}
		throw new ActionException("Max backOff");
	}

	public String decrementStock(String dc, String productId) {
		String count = repo.decrementAtomic(repo.generateCounterId(dc, productId), STOCK_DECREMENT, EMPTY_STOCK, PERSIST_LEVEL);
		if(count.equals(NOT_ENOUGH_STOCK)){
			throw new EmptyStockException("Stock already emtpy");
		} 
		return count;
	}

	public String createStock(String dc, String productId, long init) {
		System.out.println("#########>> ID create: " + repo.generateCounterId(dc, productId));
		String res = repo.createOrUpdate(repo.generateCounterId(dc, productId), init, PERSIST_LEVEL);
		System.out.println(res);
		return res;
	}
	
	
	/**
	 * Metodo que recupera el stock de un producto en un centro de distribucion 
	 * @param dc
	 * @param productId
	 * @return stock
	 */
	public String getStock(String dc, String productId) {

		String counterId = "";
		long stock = 0;
		
		try {
			System.out.println("generamos el ID");
			counterId = repo.generateCounterId(dc, productId);
			System.out.println("##########>> ID: " + counterId);
			stock = repo.findByIdPA(counterId);
			
			System.out.println("##########>> Stock: " + stock);
			
			return String.valueOf(stock-1);
				
		} catch (Exception e) {
			e.printStackTrace();

		}
		throw new EmptyStockException(counterId);
	}

}
