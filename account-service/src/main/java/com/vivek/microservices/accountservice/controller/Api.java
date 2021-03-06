package com.vivek.microservices.accountservice.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.microservices.accountservice.enums.AccountStatus;
import com.vivek.microservices.accountservice.exceptions.AccountIdInternalFieldException;
import com.vivek.microservices.accountservice.exceptions.AccountNotFoundException;
import com.vivek.microservices.accountservice.exceptions.AccountNumberInternalFieldException;
import com.vivek.microservices.accountservice.exceptions.CustomerIdManadatoryFieldException;
import com.vivek.microservices.accountservice.model.Account;

@RestController
public class Api {
	
	protected static Logger logger = LoggerFactory.getLogger(Api.class.getName());

	List<Account> accounts;

	@Value("${server.port}")
	private String serverPort;

	public Api() {
		accounts = new ArrayList<>();
		accounts.add(new Account("AccId1", 1L, "CustId101", 0.0, new Date(), AccountStatus.NEW));
		accounts.add(new Account("AccId2", 2L, "CustId101", 0.0, new Date(), AccountStatus.ACTIVE));
		accounts.add(new Account("AccId3", 3L, "CustId102", 0.0, new Date(), AccountStatus.NEW));
		accounts.add(new Account("AccId4", 4L, "CustId103", 0.0, new Date(), AccountStatus.NEW));
		accounts.add(new Account("AccId5", 5L, "CustId103", 0.0, new Date(), AccountStatus.DORMANT));
		accounts.add(new Account("AccId6", 6L, "CustId103", 0.0, new Date(), AccountStatus.NEW));
		accounts.add(new Account("AccId7", 7L, "CustId104", 0.0, new Date(), AccountStatus.NEW));
		accounts.add(new Account("AccId8", 8L, "CustId104", 0.0, new Date(), AccountStatus.NEW));
		accounts.add(new Account("AccId9", 9L, "CustId104", 0.0, new Date(), AccountStatus.INACTIVE));
		accounts.add(new Account("AccId10", 10L, "CustId104", 0.0, new Date(), AccountStatus.NEW));
	}

	@GetMapping(path = "/accounts/accountNumber/{accountNumber}")
	public Account findByAccountNumber(@PathVariable("accountNumber") Long accountNumber)
			throws AccountNotFoundException {
		populateServerPort();
		return accounts.stream().filter(account -> account.getAccountNumber().equals(accountNumber)).findFirst()
				.orElseThrow(() -> new AccountNotFoundException("Account Number: " + accountNumber));
	}

	@GetMapping(path = "/accounts/accountId/{accountId}")
	public Account findByAccountId(@PathVariable("accountId") String accountId) throws AccountNotFoundException {
		populateServerPort();
		return accounts.stream().filter(account -> account.getAccountId().equals(accountId)).findFirst()
				.orElseThrow(() -> new AccountNotFoundException("Account Id: " + accountId));
	}

	@GetMapping(path = "/accounts/customerId/{customerId}")
	public List<Account> findByCustomerId(@PathVariable("customerId") String customerId) {
		populateServerPort();
		return accounts.stream().filter(account -> account.getCustomerId().equals(customerId))
				.collect(Collectors.toList());

	}

	@GetMapping(path = "/accounts")
	public List<Account> getAllAccount() {
		populateServerPort();
		logger.info("Account.findAll()");
		logger.info(String.format("Account.findAll: %s", accounts));
		return accounts;
	}

	@PostMapping(path = "/accounts/createNewAccount")
	public Account createNewAccount(@RequestBody Account account) throws AccountNumberInternalFieldException,
			AccountIdInternalFieldException, CustomerIdManadatoryFieldException {

		populateServerPort();

		if (account.getCustomerId() == null) {
			throw new CustomerIdManadatoryFieldException();
		}

		if (account.getAccountId() != null) {
			throw new AccountIdInternalFieldException();
		}

		if (account.getAccountNumber() != null) {
			throw new AccountNumberInternalFieldException();
		}

		int size = accounts.size();

		account.setAccountId("AccId" + (size + 1));
		account.setAccountNumber(size + 1L);

		if (account.getAccountStatus() == null) {
			account.setAccountStatus(AccountStatus.NEW);
		}

		if (account.getAccountStartDate() == null) {
			account.setAccountStartDate(new Date());
		}

		if (account.getAmount() == null) {
			account.setAmount(0.0);
		}

		accounts.add(account);
		return account;
	}

	@DeleteMapping(path = "/accounts/closeAccount/{accountId}")
	public Account closeAccountById(@PathVariable("accountId") String accountId) throws AccountNotFoundException {

		populateServerPort();
		findByAccountId(accountId).setAccountStatus(AccountStatus.CLOSED);

		return findByAccountId(accountId);
	}

	public void populateServerPort() {
		accounts.stream().forEach(account -> {
			if (account.getApplicationPort() == null) {
				account.setApplicationPort(serverPort);
			}
		});
	}

}
