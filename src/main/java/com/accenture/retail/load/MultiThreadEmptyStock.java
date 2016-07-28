package com.accenture.retail.load;

import java.util.Random;

import com.accenture.retail.action.IStockAction;
import com.accenture.retail.exception.EmptyStockException;

public class MultiThreadEmptyStock implements Runnable {
	private IStockAction action;
	private String productId;
	private String dc;
	private Random rand = new Random();
	private String id;

	public MultiThreadEmptyStock(String id, IStockAction action, String productId, String dc) {
		this.id = id;
		this.action = action;
		this.productId = productId;
		this.dc = dc;
	}

	@Override
	public void run() {
		int minSleep = 20;
		String counter = "100";
		long error = 0;
		long success = 0;

		do {
			try {
				try {
					counter = action.decrementStock(dc, productId);
					success++;
				}catch (EmptyStockException e){
					break;
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println(e.getCause().getMessage());
					error++;
					
				}

				Thread.sleep(minSleep + rand.nextInt(minSleep));
			} catch (Exception e) {
			}
		} while (Long.parseLong(counter) > 0);

		System.out.println("Thread: " + id + " success: " + success + " errors: " + error);
	}

}
