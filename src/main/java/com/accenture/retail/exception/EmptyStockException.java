/*
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Copyright (c) Accenture
 * All rights reserved.
 */

package com.accenture.retail.exception;

public class EmptyStockException extends RepositoryException {
	private static final long serialVersionUID = 1L;

	public EmptyStockException(String message) {
		super(message);
	}

	public EmptyStockException(Throwable t) {
		super(t);
	}

	public EmptyStockException(String message, Throwable t) {
		super(message, t);
	}
}