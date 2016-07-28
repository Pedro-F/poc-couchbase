/*
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Copyright (c) Accenture
 * All rights reserved.
 */

package com.accenture.retail.json;

import static org.apache.commons.lang3.time.DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * JsonConverter implementation based on the GSON library.
 * 
 * @author Julián Rueda
 */
public class GsonConverter implements JsonConverter {
	private final Gson gson = 
		new GsonBuilder()
			.setDateFormat(ISO_DATETIME_TIME_ZONE_FORMAT.getPattern())
			.create();

	public <T> T fromJson(String source, Class<T> type) {
		return gson.fromJson(source, type);
	}

	public <T> String toJson(T source) {
		return gson.toJson(source);
	}
}