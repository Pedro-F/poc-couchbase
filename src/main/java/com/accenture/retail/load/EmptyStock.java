package com.accenture.retail.load;

import static com.accenture.retail.Application.create;
import static com.accenture.retail.Application.loadProperties;
import static com.accenture.retail.constant.Constants.BUCKET_DEFAULT_VALUE;
import static com.accenture.retail.constant.Constants.BUCKET_KEY;
import static com.accenture.retail.constant.Constants.NODES_DEFAULT_VALUE;
import static com.accenture.retail.constant.Constants.NODES_KEY;

import java.io.IOException;
import java.util.Properties;

import com.accenture.retail.action.StockAction;
import com.accenture.retail.data.CouchbaseRepository;
import com.accenture.retail.data.IRepository;
import com.couchbase.client.java.Cluster;

public class EmptyStock {

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
			String res = stockAction.createStock(distributionCenter, productId, 501);
			System.out.println(res);
			MultiThreadEmptyStock e1 = new MultiThreadEmptyStock("1", stockAction, productId,distributionCenter);
			MultiThreadEmptyStock e2 = new MultiThreadEmptyStock("2", stockAction, productId,distributionCenter);
			MultiThreadEmptyStock e3 = new MultiThreadEmptyStock("3", stockAction, productId,distributionCenter);
			MultiThreadEmptyStock e4 = new MultiThreadEmptyStock("4", stockAction, productId,distributionCenter);
			MultiThreadEmptyStock e5 = new MultiThreadEmptyStock("5", stockAction, productId,distributionCenter);
			MultiThreadEmptyStock e6 = new MultiThreadEmptyStock("6", stockAction, productId,distributionCenter);
			MultiThreadEmptyStock e7 = new MultiThreadEmptyStock("7", stockAction, productId,distributionCenter);
			MultiThreadEmptyStock e8 = new MultiThreadEmptyStock("8", stockAction, productId,distributionCenter);
			Thread th1 = new Thread(e1);
			Thread th2 = new Thread(e2);
			Thread th3 = new Thread(e3);
			Thread th4 = new Thread(e4);
			Thread th5 = new Thread(e5);
			Thread th6 = new Thread(e6);
			Thread th7 = new Thread(e7);
			Thread th8 = new Thread(e8);
			th1.start();
			th2.start();
			th3.start();
			th4.start();
			th5.start();
			th6.start();
			th7.start();
			th8.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
