package com.agreggio.challenge.birras.santander.common.exception;

public class ServiceException extends Exception {

	public ServiceException(String msg, Exception e) {
		super(msg, e);
	}

	public ServiceException(String msg) {
		super(msg);
	}
}
