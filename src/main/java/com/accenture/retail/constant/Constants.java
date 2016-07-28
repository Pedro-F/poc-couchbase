package com.accenture.retail.constant;

import com.couchbase.client.java.PersistTo;

public class Constants {
	
	private Constants(){	
	}
	
	public static final String PROPERTIES_FILENAME = "application.properties";
	public static final String NODES_KEY = "couchbase.nodes";
	public static final String NODES_DEFAULT_VALUE = "172.30.0.247";
	public static final String BASE_URL_KEY = "base.url";
	public static final String BASE_URL_DEFAULT_VALUE = "/retail";
	public static final String BUCKET_KEY = "bucket";
	public static final String BUCKET_DEFAULT_VALUE = "retailDistributionCenter";
	public static final long EMPTY_STOCK = 1l;
	public static final String NOT_ENOUGH_STOCK = "0";
	public static final long STOCK_INCREMENT = 1l;
	public static final long STOCK_DECREMENT = -1l;
	public static final PersistTo PERSIST_LEVEL = PersistTo.ONE;
	
}
