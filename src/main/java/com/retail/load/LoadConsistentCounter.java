package com.retail.load;

import static com.retail.Application.create;
import static com.retail.Application.loadProperties;
import static com.retail.constant.Constants.BUCKET_DEFAULT_VALUE;
import static com.retail.constant.Constants.BUCKET_KEY;
import static com.retail.constant.Constants.NODES_DEFAULT_VALUE;
import static com.retail.constant.Constants.NODES_KEY;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import com.couchbase.client.java.Cluster;
import com.retail.action.StockAction;
import com.retail.data.CouchbaseRepository;
import com.retail.data.IRepository;

public class LoadConsistentCounter {

	public static void main(String[] args) {

		Properties props;
		try {
			props = loadProperties();
			String[] nodes = props.getProperty(NODES_KEY, NODES_DEFAULT_VALUE).split(",");
			Cluster cluster = create(nodes);
			String bucket = props.getProperty(BUCKET_KEY, BUCKET_DEFAULT_VALUE);
			IRepository repo = new CouchbaseRepository(cluster, bucket);
			StockAction stockAction = new StockAction(repo);
			String productId = "product1";
			String distributionCenter = "dc1";
			stockAction.createStock(distributionCenter, productId, 1);
			Date init = new Date();
			String count;
			for (int i = 1; i <= 1000000; i++) {
				count = stockAction.incrementStock(distributionCenter, productId);
				if (i % 100000 == 0) {
					System.out.println("Counter= " + count + " Iteration= " + i);
				}
			}
			Date end = new Date();
			System.out.println(end.getTime() - init.getTime());
			init = new Date();
			for (int i = 1; i <= 1000000; i++) {
				count = stockAction.decrementStock(distributionCenter, productId);
				if (i % 100000 == 0) {
					System.out.println("Counter= " + count + " Iteration= " + i);
				}
			}
			end = new Date();
			System.out.println(end.getTime() - init.getTime());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
