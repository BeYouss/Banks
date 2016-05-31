package com.mybank.exceptions;

public class NotEnoughFundsException extends RuntimeException {
	public NotEnoughFundsException(String message) {
		super(message);
		
	}
}
