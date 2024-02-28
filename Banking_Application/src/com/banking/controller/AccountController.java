package com.banking.controller;

import java.util.List;
import java.util.logging.Logger;
import com.banking.dao.AccountDao;
import com.banking.model.Account;
import com.banking.utils.CustomException;
import com.banking.utils.ErrorMessages;
import com.banking.utils.InputValidator;

public class AccountController {

	private static final Logger log = Logger.getLogger(MainController.class.getName());
	private final AccountDao accountDao;
	private final UserController userController;
	private final BranchController branchController;

	public AccountController(AccountDao accountDao, UserController userController, BranchController branchController) {
		this.accountDao = accountDao;
		this.userController = userController;
		this.branchController = branchController;
	}

	public boolean isAccountExistsInTheBranch(String accountNumber, int branchId) throws CustomException {
		boolean isAccountExists = false;
		if (validateAccountNumber(accountNumber)) {
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

		if (!validateUserId(account.getUserId()) || !validateBranchId(account.getBranchId())
				|| validateAccountType(account.getAccountType()) || validateBalance(account.getBalance())) {
			return isAccountCreated;
		}

		try {
			isAccountCreated = accountDao.createNewAccount(account);
		} catch (Exception e) {
			throw new CustomException("Erroe While Creating Account!!", e);
		}
		return isAccountCreated;
	}

	public boolean closeAccount(String accountNumber) throws CustomException {
		boolean isAccountClosed = false;
		if (validateAccountNumber(accountNumber)) {
			return isAccountClosed;
		}
		try {
			isAccountClosed = accountDao.closeBankAccount(accountNumber);
		} catch (Exception e) {
			throw new CustomException("Error while Closing the Account!!", e);
		}
		return isAccountClosed;
	}

	public Account getAccountDetails(String accountNumber) throws CustomException {
		Account account = null;
		if (validateAccountNumber(accountNumber)) {
			return account;
		}
		try {
			account = accountDao.getAccountDetail(accountNumber);
		} catch (Exception e) {
			throw new CustomException("Error while Reterving Account Details !!", e);
		}
		return account;
	}

	public List<Account> getAllAccountsOfCustomer(int userId) throws CustomException {
		List<Account> accounts = null;
		try {
			accounts = accountDao.getAllAccountsOfCustomer(userId);
		} catch (Exception e) {
			throw new CustomException("Error while Reterving Accounts!!", e);
		}
		return accounts;
	}

	private boolean validateAccountType(String accountType) throws CustomException {
		boolean isValid = false;
		if (InputValidator.validateString(accountType)) {
			log.warning("Account Type Cannot be Empty!!!");
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

	private boolean validateUserId(int userId) throws CustomException {
		boolean isUserIdPresent = userController.isUserExists(userId);
		if (!isUserIdPresent) {
			log.warning("Invalid User Id!!!");
		}
		return isUserIdPresent;
	}

	private boolean validateAccountNumber(String accountNumber) throws CustomException {
		boolean isValid = false;
		if (InputValidator.validateString(accountNumber)) {
			log.warning("Account Number Cannot be Empty!!!");
			isValid = true;
		}
		return isValid;
	}

	private boolean validateBranchId(int branchId) throws CustomException {
		boolean isValidBranchId = branchController.isBranchExists(branchId);
		if (!isValidBranchId) {
			log.warning("Invalid Branch Id!!!");
		}
		return isValidBranchId;
	}

}
