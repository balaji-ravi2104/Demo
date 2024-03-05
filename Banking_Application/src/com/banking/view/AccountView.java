package com.banking.view;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.banking.model.Account;
import com.banking.utils.CustomException;
import com.banking.utils.ErrorMessages;
import com.banking.utils.InputValidator;

public class AccountView {
	private static final Logger log = Logger.getLogger(AccountView.class.getName());

	public void displayAccountCreationSuccessMessage() {
		log.info("Account Created Successfully!!");
	}

	public void displayAccountCreationFailureMessage() {
		log.info("Account Creation Failed!! Try Again!!");
	}

	public void displayAccountClosureSuccessMessage() {
		log.info("Account Closed Successfully!!!");
	}

	public void displayAccountClosureFailureMessage() {
		log.info("Account Closing Failed!! Try Again");
	}

	public void displayAllAccounts(Map<String, Account> accountDetails) throws CustomException {
		for (Map.Entry<String, Account> entry : accountDetails.entrySet()) {
			displayAccountDetails(entry.getValue());
		}
	}

	public void displayAccountDetails(Account account) throws CustomException {
		InputValidator.isNull(account, ErrorMessages.INPUT_NULL_MESSAGE);

		log.info("-------------------------------------------------------");
		log.info(String.format("| %-15s | %-15s |", "Account Id", account.getAccountId()));
		log.info(String.format("| %-15s | %-15s |", "Account Number", account.getAccountNumber()));
		log.info(String.format("| %-15s | %-15s |", "Branch Id", account.getBranchId()));
		log.info(String.format("| %-15s | %-15s |", "Account Type", account.getAccountType()));
		log.info(String.format("| %-15s | %-15s |", "Balance", account.getBalance()));
		log.info(String.format("| %-15s | %-15s |", "Account Status", account.getStatus()));
		log.info("-------------------------------------------------------");
	}

	public void displayBalance(Account selectedAccount) throws CustomException {
		InputValidator.isNull(selectedAccount, ErrorMessages.INPUT_NULL_MESSAGE);
		log.info("Account Number : " + selectedAccount.getAccountNumber() + " amd Balance is : "
				+ selectedAccount.getBalance());
	}

	public void displayAllAccountsInActiveMessage() {
		log.info("Your All Accounts are InActive!! Please Contact the Bank!!!");
	}

	public void displayAccountStatusUpdatedSuccess() {
		log.info("Bank Account Status Updated SuccessFully!!!");
	}

	public void displayAccountStatusUpdatedFailed() {
		log.info("Bank Account Status Updation Failed!!! Tyr Again!!");
	}

	public void displayCustomersAllBranchAccount(Map<Integer, List<Account>> allAccountDetails) throws CustomException {
		for (Map.Entry<Integer, List<Account>> entry : allAccountDetails.entrySet()) {
			int branchId = entry.getKey();
			log.info("Accounts of the Customer in Branch Id : "+branchId);
			for(Account account : entry.getValue()) {
				displayAccountDetails(account);
			}
		}
	}
}
