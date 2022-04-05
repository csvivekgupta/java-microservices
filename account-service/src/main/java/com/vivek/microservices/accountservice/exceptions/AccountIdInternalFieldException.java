package com.vivek.microservices.accountservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_GATEWAY, reason = "Account Id is internal field.")
public class AccountIdInternalFieldException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AccountIdInternalFieldException(){
		super("Account Id is internal field.");
	}

}
