package com.vivek.microservices.accountservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Customer Id is mandatory field to create any account. It does not make any sense to create an account without customer mapping.")
public class CustomerIdManadatoryFieldException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerIdManadatoryFieldException() {
		super("Customer Id is mandatory field to create any account. It does not make any sense to create an account without customer mapping");
	}

}
