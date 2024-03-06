package com.banking.view;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.banking.model.Transaction;
import com.banking.utils.CustomException;
import com.banking.utils.DateUtils;
import com.banking.utils.ErrorMessages;
import com.banking.utils.InputValidator;

public class TransactionView {

	private static final Logger log = Logger.getLogger(TransactionView.class.getName());

	public void displayInvalidAmmountMessage() {
		log.info("Deposite or Withdrawal or Transfer Amount Should be greater than ZERO!!");
	}

	public void displayDepositSuccessMessage() {
		log.info("Amount Deposited Successfully!!");
	}

	public void displayDepositFailedMessage() {
		log.info("Amount Deposit Failed!! Try Again!!");
	}

	public void displayInsufficientBalanceMessage() {
		log.info("Insufficient Balance!! Can't able to Tranfer or Withdraw!!!");
	}

	public void displayWithdrawSuccessMessage() {
		log.info("Amount Withdrawed Successfully!!");
	}

	public void displayWithdrawFailedMessage() {
		log.info("Amount Withdraw Failed!! Try Again!!");
	}

	public void displayInvalidAccountMessage() {
		log.info("Invalid Account Number or No,Account is There With this Account Number");
	}

	public void displayAccountInActiveMessage() {
		log.info("The Account is INACTIVE!! Please Try With Different Account!!");
	}

	public void displayTransactionSuccessMessage() {
		log.info("Transaction Successfull!!!");
	}

	public void displayTransactionFailedMessage() {
		log.info("Transaction Failed!!! Try Again!!");
	}

	public void displayNoStatementAvaliableMessage() {
		log.info("No Statement Avaliable For your Account!!!");
	}

	public void displayNoHistoryMessage() {
		log.info("No transaction history available!!");
	}

	public void displayStatements(List<Transaction> statement) throws CustomException {
		InputValidator.isNull(statement, ErrorMessages.INPUT_NULL_MESSAGE);
		if (statement.isEmpty()) {
			displayNoStatementAvaliableMessage();
			return;
		}
		for (Transaction transaction : statement) {
			log.info("-".repeat(60));
			log.info("Date : " + DateUtils.formateLongToDate(transaction.getDateOfTransaction()));
			log.info("Transaction Type : " + transaction.getTransactionType());
			log.info("Transaction Amount : " + transaction.getTransactedAmount());
			log.info("Balance : " + transaction.getBalance());
			log.info("-".repeat(60));
		}
	}

	public void displayTransActionHistory(List<Transaction> transactionsHistory) throws CustomException {
		InputValidator.isNull(transactionsHistory, ErrorMessages.INPUT_NULL_MESSAGE);
		if (transactionsHistory.isEmpty()) {
			displayNoHistoryMessage();
			return;
		}
		log.info("-".repeat(150));
		log.info(String.format("| %-12s | %-8s | %-18s | %-18s | %-15s | %-12s | %-10s | %-20s | %-15s | %-10s |",
				"TransactionId", "UserId", "ViewerAccount", "TransactedAccount", "TransactionType", "TransactionAmount",
				"Balance", "TransactionDate", "Remark", "Status"));
		log.info("-".repeat(150));
		for (Transaction transaction : transactionsHistory) {
			log.info(String.format(
					"| %-12d | %-8d | %-18s | %-18s | %-15s | %-12.2f | %-10.2f | %-20s | %-15s | %-10s |",
					transaction.getTransactionId(), transaction.getUserId(), transaction.getViwerAccount(),
					transaction.getTransactedAccount(), transaction.getTransactionType(),
					transaction.getTransactedAmount(), transaction.getBalance(),
					DateUtils.formateLongToDate(transaction.getDateOfTransaction()), transaction.getRemark(),
					transaction.getStatus()));
		}
		log.info("-".repeat(150));
	}

	public void displayAllTransActionHistory(Map<String, List<Transaction>> allTransactionHistoryMap)
			throws CustomException {
		if (allTransactionHistoryMap.isEmpty()) {
			displayNoHistoryMessage();
			return;
		}
		for (String accountNumber : allTransactionHistoryMap.keySet()) {
			List<Transaction> transactionList = allTransactionHistoryMap.get(accountNumber);
			log.info("Transaction History OF Account Number :" + accountNumber);
			displayTransActionHistory(transactionList);
		}
	}

	public void displayTransactionsHistory(Map<Integer, Map<String, List<Transaction>>> allTransactionsHistory)
			throws CustomException {
		if (allTransactionsHistory.isEmpty()) {
			displayNoHistoryMessage();
			return;
		}
		for (Map.Entry<Integer, Map<String, List<Transaction>>> entry : allTransactionsHistory.entrySet()) {
			int userId = entry.getKey();
			log.info("Transaction History Of User Id : " + userId);
			displayAllTransActionHistory(entry.getValue());
		}
	}

	public void displayTransactionByBranch(Map<Integer, Map<String, List<Transaction>>> transactionsOfCustomer)
			throws CustomException {
		if (transactionsOfCustomer.isEmpty()) {
			displayNoHistoryMessage();
			return;
		}
		for (Map.Entry<Integer, Map<String, List<Transaction>>> entry : transactionsOfCustomer.entrySet()) {
			int branchId = entry.getKey();
			log.info("Transaction History Of Branch Id : " + branchId);
			displayAllTransActionHistory(entry.getValue());
		}
	}

	public void displayINvalidMonthSelectionMessage() {
		log.info("Please Enter the Valid Month.. From 1 to 6..");
	}

	public void displayStatementTakenFailed() {
		log.info("Statement Taken Failed !! Try Again!!");
	}

	public void displayNoHistoryInBranchMessage() {
		log.info("No transaction history available for any customer in this branch.");

	}

}
