package com.banking.dao;

import java.util.List;

import com.banking.model.Account;
import com.banking.utils.CustomException;

public interface AccountDao {

	boolean checkAccountExists(String accountNumber, int branchId) throws CustomException;

	boolean createNewAccount(Account account) throws CustomException;

	boolean closeBankAccount(String accountNumber) throws CustomException;

	Account getAccountDetail(String accountNumber) throws CustomException;

	List<Account> getAllAccountsOfCustomer(int userId) throws CustomException;

	boolean checkCustomerAccountPresentInBranch(String accountNumber, int branchId) throws CustomException;
}
