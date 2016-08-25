/*
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Copyright (c) Accenture
 * All rights reserved.
 */

package com.retail.json;

/**
 * Simple interface that defines methods for converting between Java types
 * and JSON.
 * 
 * @author Julián Rueda
 */
public interface JsonConverter {
	<T> T fromJson(String source, Class<T> type);

	<T> String toJson(T source);
}