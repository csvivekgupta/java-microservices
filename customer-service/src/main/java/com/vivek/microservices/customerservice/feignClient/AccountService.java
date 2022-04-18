package com.vivek.microservices.customerservice.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vivek.microservices.customerservice.model.Account;

@FeignClient("account-service")
public interface AccountService {

	@RequestMapping(method = RequestMethod.GET, path= "/accounts/customerId/{customerId}")
	List<Account> getAccounts(@PathVariable("customerId") String customerId);
	
	@DeleteMapping(path= "/accounts/closeAccount/{accountId}")
	public Account closeAccountById(@PathVariable("accountId") String accountId);
	
}
