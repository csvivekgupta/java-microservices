package com.vivek.microservices.customerservice.model;

import java.util.Date;
import java.util.Objects;

public class Account {
	private String accountId;
	private Long accountNumber;
	private String customerId;
	private Double amount;
	private Date accountStartDate;
	private String accountStatus;
	private String applicationPort;

	public Account() {

	}

	/**
	 * @param accountId
	 * @param accountNumber
	 * @param customerId
	 * @param amount
	 * @param accountStartDate
	 * @param accountStatus
	 */
	public Account(String accountId, Long accountNumber, String customerId, Double amount, Date accountStartDate,
			String accountStatus) {
		super();
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.customerId = customerId;
		this.amount = amount;
		this.accountStartDate = accountStartDate;
		this.accountStatus = accountStatus;
	}

	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the accountNumber
	 */
	public Long getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
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
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the accountStartDate
	 */
	public Date getAccountStartDate() {
		return accountStartDate;
	}

	/**
	 * @param accountStartDate the accountStartDate to set
	 */
	public void setAccountStartDate(Date accountStartDate) {
		this.accountStartDate = accountStartDate;
	}

	/**
	 * @return the accountStatus
	 */
	public String getAccountStatus() {
		return accountStatus;
	}

	/**
	 * @param accountStatus the accountStatus to set
	 */
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
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
	public int hashCode() {
		return Objects.hash(accountId, accountNumber, accountStartDate, accountStatus, amount, customerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Account))
			return false;
		Account other = (Account) obj;
		return Objects.equals(accountId, other.accountId) && Objects.equals(accountNumber, other.accountNumber)
				&& Objects.equals(accountStartDate, other.accountStartDate) && accountStatus == other.accountStatus
				&& Objects.equals(amount, other.amount) && Objects.equals(customerId, other.customerId);
	}

}
