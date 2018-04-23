package com.zrgj.exception.service;

/**
 * 	分类下面包含子分类异常
*/
public class ProductTypeContainsSubTypeException extends Exception {

	private static final long serialVersionUID = -8463089311618912457L;

	public ProductTypeContainsSubTypeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductTypeContainsSubTypeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ProductTypeContainsSubTypeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ProductTypeContainsSubTypeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ProductTypeContainsSubTypeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
