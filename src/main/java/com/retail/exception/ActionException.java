/*
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Copyright (c) Accenture
 * All rights reserved.
 */

package com.retail.exception;

public class ActionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ActionException(String message) {
		super(message);
	}

	public ActionException(Throwable t) {
		super(t);
	}

	public ActionException(String message, Throwable t) {
		super(message, t);
	}
}