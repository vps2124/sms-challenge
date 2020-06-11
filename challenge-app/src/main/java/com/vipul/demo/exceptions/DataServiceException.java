package com.vipul.demo.exceptions;

public class DataServiceException extends RuntimeException {

	private static final long serialVersionUID = -7595189663290907588L;

	public DataServiceException(String message) {
		super(message);
	}
}
