/*
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Copyright (c) Accenture
 * All rights reserved.
 */

package com.retail.data;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.couchbase.client.core.CouchbaseException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.PersistTo;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.JsonLongDocument;
import com.couchbase.client.java.document.StringDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.error.CASMismatchException;
import com.couchbase.client.java.transcoder.JsonTranscoder;
import com.retail.domain.Entity;
import com.retail.exception.RepositoryException;
import com.retail.exception.RepositoryRetriableException;
import com.retail.json.JacksonConverter;
import com.retail.json.JsonConverter;

/**
 * Implementation of the Repository interface that uses the synchronous API 
 * exposed by the Couchbase Java SDK.
 * 
 * @author Julián Rueda
 */
public class CouchbaseRepository implements IRepository {
	private final JsonConverter converter = new JacksonConverter();
	private final JsonTranscoder transcoder = new JsonTranscoder();
	private Bucket bucket;

	/**
	 * Constructor
	 * 
	 * @param cluster Couchbase Cluster
	 * @param bucketName Name of the bucket to use
	 */
	public CouchbaseRepository(Cluster cluster, String bucketName) {
		try {
			bucket = cluster.openBucket(bucketName);
		} catch (CouchbaseException e) {
			throw new RepositoryException(e);
		}
	}

	/**
	 * Constructor
	 * 
	 * @param cluster Couchbase Cluster
	 * @param bucketName Name of the bucket to use
	 * @param bucketPassword Password for the specified bucket
	 */
	public CouchbaseRepository(Cluster cluster, String bucketName,
		String bucketPassword) {
		try {
			bucket = cluster.openBucket(bucketName, bucketPassword);
		} catch (CouchbaseException e) {
			throw new RepositoryException(e);
		}
	}

	/**
	  * @see Repository#findById(String, Class<? extends T>) findById
	  */
	
	public <T extends Entity> T findById(String id, Class<? extends T> type) {
		System.out.println("Antes del findByID");
		JsonDocument doc = bucket.get(id);
		System.out.println("Json doc: " + doc.toString());
		return doc == null ? null : fromJsonDocument(doc, type);
	}
	
	/**
	  * @see Repository#findById(String, Class<? extends T>) findById
	  */
	
	public long findByIdPA(String id) {
		System.out.println("Antes del findByIDPA");
		StringDocument doc = StringDocument.create(id);
		doc = bucket.get(doc);
//		long cas = doc.cas();
//		long value = Long.parseLong(doc.content().toString());
		return doc == null ? null : Long.parseLong(doc.content().toString());
	}
	


	/**
	  * @see Repository#create(T, Class<? extends T>) create
	  */

	public <T extends Entity> T create(T entity, Class<? extends T> type) {
		if(isBlank(entity.getId())) {
			String id = getNextId(type, 1, 100);
			entity.setId(id);
		}
		JsonDocument docIn = toJsonDocument(entity);
		JsonDocument docOut;
		try {
			docOut = bucket.insert(docIn);
		} catch (CouchbaseException e) {
			throw new RepositoryException(e);
		}
		return fromJsonDocument(docOut, type);
	}

	/**
	  * @see Repository#update(T, Class<? extends T>) update
	  */
	
	public <T extends Entity> T update(T entity, Class<? extends T> type) {
		JsonDocument docIn = toJsonDocument(entity);
		JsonDocument docOut;
		try {
			docOut = bucket.replace(docIn);
		} catch (CouchbaseException e) {
			throw new RepositoryException(e);
		}
		return fromJsonDocument(docOut, type);
	}

	/**
	  * @see Repository#upsert(T, Class<? extends T>) upsert
	  */
	
	public <T extends Entity> T upsert(T entity, Class<? extends T> type) {
		JsonDocument docIn = toJsonDocument(entity);
		JsonDocument docOut;
		try {
			docOut = bucket.upsert(docIn);
		} catch (CouchbaseException e) {
			throw new RepositoryException(e);
		}
		return fromJsonDocument(docOut, type);
	}

	/**
	  * @see IRepository#delete(T) delete
	  */
	
	public <T extends Entity> void delete(T entity) {
		JsonDocument doc = toJsonDocument(entity);
		try {
			bucket.remove(doc);
		} catch (CouchbaseException e) {
			throw new RepositoryException(e);
		}
	}

	/**
	 * Generates an ID using the Couchbase counter feature.
	 * 
	 * @param type Class<T> that represents the type of the entity
	 * @param init Initial value of the counter
	 * @param incr Amount to increment the counter value each time
	 * @return Next value of the counter
	 */
	private <T extends Entity> String getNextId(Class<T> type, long incr, 
		long init) {
		String name = type.getSimpleName().toLowerCase();
		JsonLongDocument doc = 
			bucket.counter("counter::" + name, incr, init);
		return name + "::" + doc.content().toString();
	}
	
	/**
	 * Generates an ID using the Couchbase counter feature.
	 
	 */
	public  String getNextId(String name, long incr, long init) {
		JsonLongDocument doc = bucket.counter(name, incr, init);
		return doc.content().toString();
	}
	
	/**
	 * decrement counter atomic
	 */
	
	public String decrementAtomic(String counter, long decr, long init, PersistTo persist) {
			JsonLongDocument doc = bucket.counter(counter, decr, init, persist);
			return doc.content().toString();
		}
	
	/**create or update counter to specific value
	 * 
	 */
	
	public  String createOrUpdate(String counter, long init, PersistTo persist) {
		StringDocument updatedDoc = StringDocument.create(counter, Long.toString(init));
		updatedDoc = bucket.upsert(updatedDoc, persist);
		return updatedDoc.content().toString();
	}
	
	public String generateCounterId(String dc, String productId) {
		return "stock::" + dc + "::" + productId;
	}
	/**
	 * increment counter with a minimum value using CAS
	 */
	public String incrementCAS(String counter, long incr, long init, PersistTo persist) throws RepositoryException {
		StringDocument doc = StringDocument.create(counter);
		doc = bucket.get(doc);
		long cas = doc.cas();
		long value = Long.parseLong(doc.content().toString());
		if(value<init){
			value=init + incr;
		}else{
			value+=incr;
		}
		try{
			StringDocument updatedDoc = StringDocument.create(counter, Long.toString(value), cas);
			doc = bucket.replace(updatedDoc, persist);
			return doc.content().toString();
		}
		catch (CASMismatchException e){
			throw new RepositoryRetriableException(e);
		}
	}
	
	/**
	 * Converts a JsonDocument into an object of the specified type
	 * 
	 * @param doc JsonDocument to be converted
	 * @param type Class<T> that represents the type of this or a parent class
	 * @return Reference to an object of the specified type
	 */
	protected <T extends Entity> T fromJsonDocument(JsonDocument doc, Class<T> type) {
		if (doc == null) {
			throw new IllegalArgumentException("document is null");
		}
		JsonObject content = doc.content();
		if (content == null) {
			throw new IllegalStateException("document has no content");
		}
		if (type == null) {
			throw new IllegalArgumentException("type is null");
		}
		T result = converter.fromJson(content.toString(), type);
		result.setCas(doc.cas());
		return result;
	}

	/**
	 * Converts an object to a JsonDocument
	 * 
	 * @param source Object to be converted
	 * @return JsonDocument that represents the specified object
	 */
	protected <T extends Entity> JsonDocument toJsonDocument(T source) {
		if (source == null) {
			throw new IllegalArgumentException("entity is null");
		}
		String id = source.getId();
		if (id == null) {
			throw new IllegalStateException("entity ID is null");
		}
		try {
			JsonObject content = 
				transcoder.stringToJsonObject(converter.toJson(source));
			JsonDocument doc = JsonDocument.create(id, content, source.getCas());
			return doc;
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}
}