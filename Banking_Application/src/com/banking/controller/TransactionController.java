package com.banking.controller;

import java.util.List;
import java.util.Map;

import com.banking.dao.TransactionDao;
import com.banking.dao.TransactionDaoImplementation;
import com.banking.model.Account;
import com.banking.model.Transaction;
import com.banking.utils.CustomException;
import com.banking.utils.ErrorMessages;
import com.banking.utils.InputValidator;
import com.banking.view.TransactionView;

public class TransactionController {

	private TransactionDao transactionDao;
	private AccountController accountController;
	private BranchController branchController;
	private UserController userController;
	private TransactionView transactionView;

	public TransactionController() {
		this.accountController = new AccountController();
		this.transactionDao = new TransactionDaoImplementation();
		this.userController = new UserController();
		this.branchController = new BranchController();
		this.transactionView = new TransactionView();
	}

	public boolean depositAmount(Account selectedAccount, double amountToDeposite) throws CustomException {
		InputValidator.isNull(selectedAccount, ErrorMessages.INPUT_NULL_MESSAGE);
		boolean isDepositeSuccess = false;
		if(!validateAmount(amountToDeposite)) {
			return isDepositeSuccess;
		}
		try {
			isDepositeSuccess = transactionDao.deposit(selectedAccount, amountToDeposite);
		} catch (Exception e) {
			throw new CustomException("Error while Depositing Money!!", e);
		}
		return isDepositeSuccess;
	}

	public boolean withdrawAmount(Account selectedAccount, double amountToWithdraw) throws CustomException {
		InputValidator.isNull(selectedAccount, ErrorMessages.INPUT_NULL_MESSAGE);
		boolean isWithdrawSuccess = false;
		try {
			isWithdrawSuccess = transactionDao.withdraw(selectedAccount, amountToWithdraw);
		} catch (Exception e) {
			throw new CustomException("Error while Depositing Money!!", e);
		}
		return isWithdrawSuccess;
	}

	public boolean transferWithinBank(Account accountFromTransfer, Account accountToTransfer, double amountToTransfer)
			throws CustomException {
		InputValidator.isNull(accountFromTransfer, ErrorMessages.INPUT_NULL_MESSAGE);
		InputValidator.isNull(accountToTransfer, ErrorMessages.INPUT_NULL_MESSAGE);
		boolean isTransactionSuccess = false;
		try {
			isTransactionSuccess = transactionDao.transferMoneyWithinBank(accountFromTransfer, accountToTransfer,
					amountToTransfer);
		} catch (Exception e) {
			throw new CustomException("Error while Transferring Money!! " + e.getMessage(), e);
		}
		return isTransactionSuccess;
	}

	public boolean transferWithOtherBank(Account accountFromTransfer, String accountNumberToTransfer,
			double amountToTransferWithOtherBank) throws CustomException {
		InputValidator.isNull(accountFromTransfer, ErrorMessages.INPUT_NULL_MESSAGE);
		InputValidator.isNull(accountNumberToTransfer, ErrorMessages.INPUT_NULL_MESSAGE);
		boolean isTransactionSuccess = false;
		try {
			isTransactionSuccess = transactionDao.transferMoneyWithOtherBank(accountFromTransfer,
					accountNumberToTransfer, amountToTransferWithOtherBank);
		} catch (Exception e) {
			throw new CustomException("Error while Transferring Money!! " + e.getMessage(), e);
		}
		return isTransactionSuccess;
	}

	public List<Transaction> getStatement(Account account, int numberOfMonths) throws CustomException {
		InputValidator.isNull(account, ErrorMessages.INPUT_NULL_MESSAGE);
		List<Transaction> statement = null;
		try {
			statement = transactionDao.getUsersStatement(account, numberOfMonths);
		} catch (Exception e) {
			throw new CustomException("Error while Getting Transaction!!!", e);
		}
		return statement;
	}

	public List<Transaction> getCustomerTransaction(String accountNumber, int branchId) throws CustomException {
		List<Transaction> transactionHistory = null;
		if (!accountController.validateAccountAndBranch(accountNumber, branchId)) {
			return transactionHistory;
		}
		try {
			transactionHistory = transactionDao.getCustomerTransactionHistory(accountNumber);
		} catch (Exception e) {
			throw new CustomException("Error while Getting Transaction History!!!", e);
		}
		return transactionHistory;
	}

	public Map<String, List<Transaction>> getAllTransactionsOfCustomer(int userId, int branchId) throws CustomException {
		Map<String, List<Transaction>> transactions = null;
		if(!userController.validateUserIdAndBranchId(userId, branchId)) {
			return transactions;
		}
		try {
			transactions = transactionDao.getAllTransactionHistorys(userId,branchId);
		}catch (Exception e) {
			throw new CustomException("Error while Getting Transaction History!!!", e);
		}
		return transactions;
	}

	public Map<String, List<Transaction>> getAllCustomersTransaction(int branchId) throws CustomException {
		Map<String, List<Transaction>> transactionHistory = null;
		if (!branchController.validateBranchId(branchId)) {
			return transactionHistory;
		}
		try {
			transactionHistory = transactionDao.getAllCustomersTransactionHistory(branchId);
		} catch (Exception e) {
			throw new CustomException("Error while Getting Transaction History!!!", e);
		}
		return transactionHistory;
	}
	
	private boolean validateAmount(double amountToDeposite) {
		boolean isValid = true;
		if (amountToDeposite <= 0) {
			transactionView.displayInvalidAmmountMessage();
			isValid = false;
		}
		return isValid;
	}

}
