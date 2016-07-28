/*
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Copyright (c) Accenture
 * All rights reserved.
 */
package com.accenture.retail;

import static com.accenture.retail.constant.Constants.BASE_URL_DEFAULT_VALUE;
import static com.accenture.retail.constant.Constants.BASE_URL_KEY;
import static com.accenture.retail.constant.Constants.BUCKET_DEFAULT_VALUE;
import static com.accenture.retail.constant.Constants.BUCKET_KEY;
import static com.accenture.retail.constant.Constants.NODES_DEFAULT_VALUE;
import static com.accenture.retail.constant.Constants.NODES_KEY;
import static com.accenture.retail.constant.Constants.PROPERTIES_FILENAME;
import static java.lang.ClassLoader.getSystemResourceAsStream;

import java.io.IOException;
import java.util.Properties;

import com.accenture.retail.data.CouchbaseRepository;
import com.accenture.retail.data.IRepository;
import com.accenture.retail.web.StockController;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;

/**
 * Entry point for the Retail application.
 * 
 * @author Julián Rueda
 */

public class Application {

	public static void main(String[] args) {
		try {
			Properties props = loadProperties();
			String[] nodes = props.getProperty(NODES_KEY, NODES_DEFAULT_VALUE).split(",");
			Cluster cluster = create(nodes);
			String bucket = props.getProperty(BUCKET_KEY, BUCKET_DEFAULT_VALUE);
			String baseURL = props.getProperty(BASE_URL_KEY, BASE_URL_DEFAULT_VALUE);
			IRepository repo = new CouchbaseRepository(cluster, bucket);
			new StockController(baseURL, repo);
		} catch (IOException e) {
			System.err.println("Unable to load properties file: " + PROPERTIES_FILENAME);
		}
	}

	public static Properties loadProperties() throws IOException {
		final Properties props = System.getProperties();
		props.load(getSystemResourceAsStream(PROPERTIES_FILENAME));
		return props;
	}

	public static Cluster create(final String... nodes) {
		CouchbaseEnvironment env = DefaultCouchbaseEnvironment
			    .builder().mutationTokensEnabled(true).build();
		return CouchbaseCluster.create(env, nodes);
	}
}