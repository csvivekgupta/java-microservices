package com.vivek.microservices.accountservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Account does not exist.")
public class AccountNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public AccountNotFoundException(String exception) {
		super("Account not found.");
	}

}
