package com.banking.controller;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.banking.dao.AccountDao;
import com.banking.dao.AccountDaoImplementation;
import com.banking.model.Account;
import com.banking.utils.CustomException;
import com.banking.utils.ErrorMessages;
import com.banking.utils.InputValidator;

public class AccountController {

	private static final Logger log = Logger.getLogger(MainController.class.getName());
	private AccountDao accountDao = new AccountDaoImplementation();
	private UserController userController;
	private BranchController branchController = new BranchController();

	public AccountController(UserController userController) {
		this.userController = userController;
	}

	public AccountController() {
	}

	public Account getPrimaryAccount(int userId) throws CustomException {
		Account account = null;
		if (!userController.validateUser(userId)) {
			return account;
		}
		try {
			account = accountDao.getCustomerPrimaryAccount(userId);
		} catch (Exception e) {
			throw new CustomException("Error while Getting Primary Account", e);
		}
		return account;
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
		if (!userController.validateUser(account.getUserId())
				|| !branchController.validateBranchId(account.getBranchId())
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

	public Account getAccountDetails(String accountNumber, int branchId) throws CustomException {
		InputValidator.isNull(accountNumber, ErrorMessages.INPUT_NULL_MESSAGE);
		Account account = null;
		if (!validateAccountAndBranch(accountNumber, branchId)) {
			return account;
		}
		try {
			account = accountDao.getAccountDetail(accountNumber);
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
		if (!userController.validateUserIdAndBranchId(userId, employeeBranchId)) {
			return customerAccounts;
		}
		try {
			customerAccounts = accountDao.getCustomerAccounts(userId, employeeBranchId);
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

	public boolean activateDeactivateCustomerAccount(String accountNumber, int branchId, String status)
			throws CustomException {
		InputValidator.isNull(accountNumber, ErrorMessages.INPUT_NULL_MESSAGE);
		InputValidator.isNull(status, ErrorMessages.INPUT_NULL_MESSAGE);
		boolean isAccountStatusChanged = false;
		if (!validateAccountAndBranch(accountNumber, branchId)) {
			return isAccountStatusChanged;
		}
		try {
			isAccountStatusChanged = accountDao.activateDeactivateCustomerAccount(accountNumber, branchId, status);
		} catch (Exception e) {
			throw new CustomException("Error while Updating Bank Account Status!!", e);
		}
		return isAccountStatusChanged;
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

//	private boolean validateAccount(Account account) {
//		boolean isValidAccount = true;
//		if (account == null) {
//			transactionView.displayInvalidAccountMessage();
//			isValidAccount = false;
//		}
//		if (account.getStatus().equalsIgnoreCase("INACTIVE")) {
//			transactionView.displayAccountInActiveMessage();
//			isValidAccount = false;
//		}
//		return isValidAccount;
//	}
}
