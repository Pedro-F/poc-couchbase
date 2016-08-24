/*
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Copyright (c) Accenture
 * All rights reserved.
 */

package com.accenture.retail.data;

import com.accenture.retail.domain.Entity;
import com.accenture.retail.exception.RepositoryException;
import com.couchbase.client.java.PersistTo;

/**
 * Interface that defines standard CRUD operations for entities.
 * 
 * @author Julián Rueda
 */
public interface IRepository {
	/**
	 * Find an entity by the specified ID and return a reference to
	 * an instance of the specified type.
	 * 
	 * @param id Unique ID of the entity
	 * @param type Type of the entity to return
	 * @return Reference to an instance of the specified type that 
	 * 	corresponds to the ID 
	 */
	<T extends Entity> T findById(String id, Class<? extends T> type);
	
	/**
	 * Find stock of an entity by the specified ID and return it
	 * 
	 * @param id Unique ID of the entity
	 * @return long stock corresponds to the ID 
	 */
	public long findByIdPA(String id);

	/**
	 * Persist a new instance of the specified type in the repository.
	 * 
	 * @param entity Source entity to be persisted
	 * @param type The type of the entity to be persisted
	 * @return Reference to the entity that has been persisted 
	 */
	<T extends Entity> T create(T entity, Class<? extends T> type);

	/**
	 * Update an existing instance of the specified type in the repository.
	 * 
	 * @param entity Source entity to be persisted
	 * @param type The type of the entity to be persisted
	 * @return Reference to the entity that has been persisted 
	 */
	<T extends Entity> T update(T entity, Class<? extends T> type);

	/**
	 * Insert or update an instance of the specified type in the repository.
	 * 
	 * @param entity Source entity to be persisted
	 * @param type The type of the entity to be persisted
	 * @return Reference to the entity that has been persisted 
	 */
	<T extends Entity> T upsert(T entity, Class<? extends T> type);

	/**
	 * Delete the specified entity from the repository.
	 * 
	 * @param entity Source entity to be deleted
	 */
	<T extends Entity> void delete(T entity);
	
	public String incrementCAS(String counter, long incr, long init, PersistTo persist) throws RepositoryException;
	
	public String decrementAtomic(String counter, long incr, long init, PersistTo persist);
	
	public  String createOrUpdate(String counter, long init, PersistTo persist);
	
	public  String getNextId(String name, long incr, long init);
	
	public String generateCounterId(String dc, String productId);

}