package com.vivek.microservices.customerservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.microservices.customerservice.enums.CustomerStatus;
import com.vivek.microservices.customerservice.enums.CustomerType;
import com.vivek.microservices.customerservice.exceptions.CustomerNotFoundException;
import com.vivek.microservices.customerservice.feignClient.AccountService;
import com.vivek.microservices.customerservice.model.Customer;

@RestController
public class Api {

	@Autowired
	AccountService accountService;

	List<Customer> customers;

	@Value("${server.port}")
	private String serverPort;

	public Api() {
		customers = new ArrayList<>();
		customers.add(new Customer("CustId101", "uid1", "name1", CustomerType.INDIVIDUAL));
		customers.add(new Customer("CustId102", "uid2", "name2", CustomerType.INDIVIDUAL));
		customers.add(new Customer("CustId103", "uid3", "name3", CustomerType.INDIVIDUAL));
		customers.add(new Customer("CustId104", "uid4", "name4", CustomerType.COMPANY));
		customers.add(new Customer("CustId105", "uid5", "name5", CustomerType.INDIVIDUAL));
		customers.add(new Customer("CustId106", "uid6", "name6", CustomerType.INDIVIDUAL));
		customers.add(new Customer("CustId107", "uid7", "name7", CustomerType.INDIVIDUAL));
		customers.add(new Customer("CustId108", "uid8", "name8", CustomerType.INDIVIDUAL));
		customers.add(new Customer("CustId109", "uid9", "name9", CustomerType.INDIVIDUAL));
		customers.add(new Customer("CustId110", "uid10", "name10", CustomerType.INDIVIDUAL));
	}

	@GetMapping(path = "/customers/uid/{uid}")
	public Customer findByUid(@PathVariable("uid") String uid) throws CustomerNotFoundException {

		Customer cust = customers.stream().filter(customer -> uid.equals(customer.getUid())).findFirst()
				.orElseThrow(() -> new CustomerNotFoundException("uid:" + uid));
		cust.setAccounts(accountService.getAccounts(cust.getCustomerId()));

		populateServerPort();
		return cust;
	}

	@GetMapping(path = "")
	public List<Customer> findAll() {
		customers.stream()
				.forEach(customer -> customer.setAccounts(accountService.getAccounts(customer.getCustomerId())));

		populateServerPort();
		return customers;
	}

	@GetMapping(path = "/customers/customerId/{custId}")
	public Customer findByCustId(@PathVariable("custId") String custId) throws CustomerNotFoundException {

		Customer cust = customers.stream().filter(customer -> custId.equals(customer.getCustomerId())).findFirst()
				.orElseThrow(() -> new CustomerNotFoundException("CustomerId:" + custId));

		cust.setAccounts(accountService.getAccounts(cust.getCustomerId()));

		populateServerPort();

		return cust;
	}

	@PostMapping(path = "/customers/createNewCustomer")
	public Customer createNewCustomer(@RequestBody Customer customer) {
		if (customer.getCustomerId() != null) {
			return null;
		}

		customer.setCustomerId("CustId" + customers.size() + 1);

		if (customer.getCustomerType() == null) {
			customer.setCustomerType(CustomerType.INDIVIDUAL);
		}
		customers.add(customer);

		populateServerPort();

		return customer;
	}

	@DeleteMapping(path = "/customers/deleteCustomer/{customerId}")
	public Customer removeCustomer(@PathVariable("customerId") String customerId) throws CustomerNotFoundException {

		Customer customer = findByCustId(customerId);

		// first close any associated account
		accountService.getAccounts(customer.getCustomerId()).stream()
				.forEach(acct -> accountService.closeAccountById(acct.getAccountId()));

		customer.setAccounts(accountService.getAccounts(customer.getCustomerId()));

		customer.setCustomerStatus(CustomerStatus.DELETED);

		customer.setApplicationPort(serverPort);

		return customer;
	}

	public void populateServerPort() {
		customers.stream().forEach(customer -> {
			if (customer.getApplicationPort() == null) {
				customer.setApplicationPort(serverPort);
			}
		});
	}

}
