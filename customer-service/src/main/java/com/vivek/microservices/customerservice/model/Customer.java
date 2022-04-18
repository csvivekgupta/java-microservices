package com.vivek.microservices.customerservice.model;

import java.util.List;
import java.util.Objects;

import com.vivek.microservices.customerservice.enums.CustomerStatus;
import com.vivek.microservices.customerservice.enums.CustomerType;

public class Customer {

	private String customerId;
	private String uid;
	private String name;
	private CustomerType customerType;
	private CustomerStatus customerStatus;
	private List<Account> accounts;
	private String applicationPort;

	public Customer() {

	}

	/**
	 * @param customerId
	 * @param uid
	 * @param name
	 * @param customerType
	 */
	public Customer(String customerId, String uid, String name, CustomerType customerType) {
		super();
		this.customerId = customerId;
		this.uid = uid;
		this.name = name;
		this.customerType = customerType;
		this.customerStatus = CustomerStatus.ACTIVE;
	}

	@Override
	public int hashCode() {
		return Objects.hash(uid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(uid, other.uid);
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the customerType
	 */
	public CustomerType getCustomerType() {
		return customerType;
	}

	/**
	 * @param customerType the customerType to set
	 */
	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	/**
	 * @return the accounts
	 */
	public List<Account> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * @return the applicationPort
	 */
	public String getApplicationPort() {
		return applicationPort;
	}

	/**
	 * @param applicationPort the applicationPort to set
	 */
	public void setApplicationPort(String applicationPort) {
		this.applicationPort = applicationPort;
	}

	@Override
	public String toString() {
		return String.format("Customer [customerId=%s, uid=%s, name=%s, customerType=%s, accounts=%s]", customerId, uid,
				name, customerType, accounts);
	}

	/**
	 * @return the customerStatus
	 */
	public CustomerStatus getCustomerStatus() {
		return customerStatus;
	}

	/**
	 * @param customerStatus the customerStatus to set
	 */
	public void setCustomerStatus(CustomerStatus customerStatus) {
		this.customerStatus = customerStatus;
	}

}
