package com.vivek.microservices.accountservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Account Number is internal field.")
public class AccountNumberInternalFieldException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AccountNumberInternalFieldException() {
		super("Account Number is internal field.");
	}

}
