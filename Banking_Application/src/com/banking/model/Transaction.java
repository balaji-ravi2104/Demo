package com.banking.model;

import java.sql.Date;

public class Transaction {
	private int transactionId;
	private int userId;
	private int branchId;
	private String viewerAccount;
	private String transactedAccount;
	private String transactionType;
	private double transactedAmount;
	private double balance;
	private Date dateOfTransaction;
	private String remark;
	private String status;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getTransactedAmount() {
		return transactedAmount;
	}

	public void setTransactedAmount(double transactedAmount) {
		this.transactedAmount = transactedAmount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getViwerAccount() {
		return viewerAccount;
	}

	public void setViwerAccount(String viewerAccount) {
		this.viewerAccount = viewerAccount;
	}

	public String getTransactedAccount() {
		return transactedAccount;
	}

	public void setTransactedAccount(String transactedAccount) {
		this.transactedAccount = transactedAccount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", viwerAccount=" + viewerAccount
				+ ", transactedAccount=" + transactedAccount + ", transactionType=" + transactionType
				+ ", transactedAmount=" + transactedAmount + ", balance=" + balance + ", dateOfTransaction="
				+ dateOfTransaction + ", remark=" + remark + ", status=" + status + "]";
	}

}
