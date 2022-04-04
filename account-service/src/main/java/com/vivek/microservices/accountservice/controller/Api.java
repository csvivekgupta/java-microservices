package com.vivek.microservices.accountservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api {

	@GetMapping(path = "/")
	public String testAppAvailability() {
		return "Applciation is up and running";
	}

}
