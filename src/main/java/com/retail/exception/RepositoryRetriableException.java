/*
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Copyright (c) Accenture
 * All rights reserved.
 */

package com.retail.exception;

public class RepositoryRetriableException extends RepositoryException {
	private static final long serialVersionUID = 1L;

	public RepositoryRetriableException(String message) {
		super(message);
	}

	public RepositoryRetriableException(Throwable t) {
		super(t);
	}

	public RepositoryRetriableException(String message, Throwable t) {
		super(message, t);
	}
}