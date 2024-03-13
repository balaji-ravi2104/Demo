package com.banking.controller;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.banking.cache.Cache;
import com.banking.cache.LRUCache;
import com.banking.dao.AccountDao;
import com.banking.dao.implementation.AccountDaoImplementation;
import com.banking.model.Account;
import com.banking.model.AccountStatus;
import com.banking.utils.CustomException;
import com.banking.utils.ErrorMessages;
import com.banking.utils.InputValidator;

public class AccountController {

	private static final Logger log = Logger.getLogger(MainController.class.getName());
	private AccountDao accountDao = new AccountDaoImplementation();
	private UserController userController;
	private BranchController branchController = new BranchController();

	public static final Cache<String, Account> accountCache = new LRUCache<String, Account>(10);
	public static final Cache<Integer, Map<String, Account>> allAccountCache = new LRUCache<Integer, Map<String, Account>>(
			10);

	public AccountController(UserController userController) {
		this.userController = userController;
	}

	public AccountController() {
	}

	public boolean isAccountExistsInTheBranch(String accountNumber, int branchId) throws CustomException {
		InputValidator.isNull(accountNumber, "Account Number Cannot be Null!!!");
		boolean isAccountExists = false;
		if (validateAccountNumber(accountNumber) || !branchController.validateBranchId(branchId)) {
			return isAccountExists;
		}
		try {
			isAccountExists = accountDao.checkAccountExists(accountNumber, branchId);
		} catch (Exception e) {
			throw new CustomException("Error while Checking Account", e);
		}
		return isAccountExists;
	}

	public boolean createAccount(Account account) throws CustomException {
		InputValidator.isNull(account, ErrorMessages.INPUT_NULL_MESSAGE);
		boolean isAccountCreated = false;
		boolean isPrimary = false;
		if (!userController.validateUser(account.getUserId())
				|| !branchController.validateBranchId(account.getBranchId()) || validateBalance(account.getBalance())) {
			return isAccountCreated;
		}
		allAccountCache.rem(account.getUserId());
		if (!accountDao.customerHasAccount(account.getUserId())) {
			isPrimary = true;
		}
		try {
			isAccountCreated = accountDao.createAccount(account, isPrimary);
		} catch (Exception e) {
			throw new CustomException("Erroe While Creating Account!!", e);
		}
		return isAccountCreated;
	}

	public Account getAccountDetails(String accountNumber, int branchId) throws CustomException {
		InputValidator.isNull(accountNumber, ErrorMessages.INPUT_NULL_MESSAGE);
		Account account = null;
		if (accountCache.get(accountNumber) != null) {
			// System.out.println("Inside Cache Account Number " + accountNumber);
			return accountCache.get(accountNumber);
		}
		if (!validateAccountAndBranch(accountNumber, branchId)) {
			return account;
		}
		try {
			account = accountDao.getAccountDetail(accountNumber);
			if (account != null) {
				accountCache.set(accountNumber, account);
			}
		} catch (Exception e) {
			throw new CustomException("Error while Reterving Account Details !!", e);
		}
		return account;
	}

	public List<Account> getAccountsOfCustomer(int userId) throws CustomException {
		List<Account> accounts = null;
		try {
			accounts = accountDao.getAllAccountsOfCustomer(userId);
		} catch (Exception e) {
			throw new CustomException("Error while Reterving Accounts!!", e);
		}
		return accounts;
	}

	public Map<String, Account> getCustomerAccountsInBranch(int userId, int employeeBranchId) throws CustomException {
		Map<String, Account> customerAccounts = null;
		if (allAccountCache.get(userId) != null) {
			// System.out.println("Inside Cache User Id "+userId);
			return allAccountCache.get(userId);
		}
		if (!userController.validateUserIdAndBranchId(userId, employeeBranchId)) {
			return customerAccounts;
		}
		try {
			customerAccounts = accountDao.getCustomerAccounts(userId, employeeBranchId);
			if (customerAccounts != null) {
				allAccountCache.set(userId, customerAccounts);
			}
		} catch (Exception e) {
			throw new CustomException("Error while Reterving Customer Accounts!!", e);
		}
		return customerAccounts;
	}

	public Map<Integer, Map<String, Account>> getCustomerAccountsInAllBranch(int userId) throws CustomException {
		Map<Integer, Map<String, Account>> customerAccounts = null;
		if (!userController.validateUser(userId)) {
			return customerAccounts;
		}
		try {
			customerAccounts = accountDao.getCustomersAllAccount(userId);
		} catch (Exception e) {
			throw new CustomException("Error while Reterving Customer Accounts!!", e);
		}
		return customerAccounts;
	}

	public boolean activateDeactivateCustomerAccount(String accountNumber, int branchId, int status)
			throws CustomException {
		InputValidator.isNull(accountNumber, ErrorMessages.INPUT_NULL_MESSAGE);
		boolean isAccountStatusChanged = false;
		if (!validateAccountAndBranch(accountNumber, branchId) || validateAccountStatus(status)) {
			return isAccountStatusChanged;
		}
		try {
			isAccountStatusChanged = accountDao.activateDeactivateCustomerAccount(accountNumber, branchId, status);
		} catch (Exception e) {
			throw new CustomException("Error while Updating Bank Account Status!!", e);
		}
		return isAccountStatusChanged;
	}

	private boolean validateAccountStatus(int status) {
		boolean isValid = false;
		if (status <= 0 || status > AccountStatus.values().length) {
			log.warning("Invalid Account Status Updation Choice!!");
			isValid = true;
		}
		return isValid;
	}

	private boolean validateBalance(double balance) {
		boolean isValid = false;
		if (InputValidator.validateBalance(balance)) {
			log.warning("Balance Should Be Greater than Zero!!!");
			isValid = true;
		}
		return isValid;
	}

	private boolean validateAccountNumber(String accountNumber) throws CustomException {
		boolean isValid = false;
		if (InputValidator.validateString(accountNumber)) {
			log.warning("Account Number Cannot be Empty!!!");
			isValid = true;
		}
		return isValid;
	}

	public boolean validateAccountAndBranch(String accountNumber, int branchId) throws CustomException {
		boolean isValid = true;
		if (!isAccountExistsInTheBranch(accountNumber, branchId)) {
			log.log(Level.WARNING, "Account Number Doesn't Exists in this Branch!!!");
			isValid = false;
		}
		return isValid;
	}
}
