package com.banking.dao;

import java.util.List;
import java.util.Map;

import com.banking.model.Account;
import com.banking.utils.CustomException;

public interface AccountDao {

	boolean checkAccountExists(String accountNumber, int branchId) throws CustomException;

	boolean createNewAccount(Account account) throws CustomException;

	Account getAccountDetail(String accountNumber) throws CustomException;

	List<Account> getAllAccountsOfCustomer(int userId) throws CustomException;

	boolean checkCustomerAccountPresentInBranch(String accountNumber, int branchId) throws CustomException;

	boolean activateDeactivateCustomerAccount(String accountNumber, int branchId, String status) throws CustomException;

	Map<String, Account> getCustomerAccounts(int userId, int employeeBranchId) throws CustomException;

	Map<Integer, List<Account>> getCustomersAllAccount(int userId) throws CustomException;
}
