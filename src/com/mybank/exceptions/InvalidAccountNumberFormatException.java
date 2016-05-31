package com.mybank.exceptions;

public class InvalidAccountNumberFormatException extends RuntimeException {
		public InvalidAccountNumberFormatException(String message) {
			super(message);
			
		}
	}
