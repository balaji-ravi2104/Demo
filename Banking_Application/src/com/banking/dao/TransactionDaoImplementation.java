package com.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banking.model.Account;
import com.banking.model.Transaction;
import com.banking.utils.CustomException;
import com.banking.utils.DatabaseConnection;
import com.banking.utils.ErrorMessages;
import com.banking.utils.InputValidator;

public class TransactionDaoImplementation implements TransactionDao {

	private static final String UPDATE_QUERY = "UPDATE Accounts SET balance = ? WHERE account_number = ?;";

	private static final String TRANSACTION_LOG = "INSERT INTO Transaction (user_id, viewer_account_number, "
			+ "transacted_account_number, transaction_type, transaction_amount, balance, transaction_date, "
			+ "remark, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

	private static final String GET_STATEMENT = "SELECT transaction_date, transaction_type,transaction_amount, "
			+ "balance FROM Transaction WHERE viewer_account_number = ? AND transaction_date >= DATE_SUB(CURRENT_DATE(), "
			+ "INTERVAL ? MONTH) order by transaction_id DESC";

	private static final String GET_TRANSACTION_HISTORY = "select * From Transaction WHERE viewer_account_number = ? AND "
			+ "transaction_date >= DATE_SUB(CURRENT_DATE(), INTERVAL ? MONTH) ORDER BY transaction_id DESC;";

	private static final String GET_ALL_TRANSACTION_OF_CUSTOMER_IN_BRANCH = "SELECT t.transaction_id, t.user_id, "
			+ "t.viewer_account_number, t.transacted_account_number, t.transaction_type, t.transaction_amount, "
			+ "t.balance, t.transaction_date, t.remark, t.status FROM Transaction t JOIN Accounts a ON "
			+ "t.viewer_account_number = a.account_number WHERE a.user_id = ? AND a.branch_id = ? AND "
			+ "t.transaction_date >= DATE_SUB(CURRENT_DATE(), INTERVAL ? MONTH) ORDER BY t.transaction_id DESC;";

	@Override
	public boolean deposit(Account selectedAccount, double amountToDeposit) throws CustomException {
		InputValidator.isNull(selectedAccount, ErrorMessages.INPUT_NULL_MESSAGE);
		boolean isAmountDepositedAndLoggedInTransaction = false;
		try {
			if (amountToDeposit <= 0) {
				throw new CustomException("Amount to be Deposited Should be Greater than ZERO!!!");
			}
			boolean isBalanceUpdated = updateAccountBalance(selectedAccount,
					selectedAccount.getBalance() + amountToDeposit);
			if (isBalanceUpdated) {
				isAmountDepositedAndLoggedInTransaction = logTransaction(selectedAccount,
						selectedAccount.getAccountNumber(), amountToDeposit, "Deposit");
			}
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			throw new CustomException("Error While Depositing Money", e);
		}
		return isAmountDepositedAndLoggedInTransaction;
	}

	@Override
	public boolean withdraw(Account selectedAccount, double amountToWithdraw) throws CustomException {
		InputValidator.isNull(selectedAccount, ErrorMessages.INPUT_NULL_MESSAGE);
		boolean isAmountWithdrawnAndLoggedInTransaction = false;
		try {
			if (selectedAccount.getBalance() < amountToWithdraw) {
				throw new CustomException("Insufficient Balance");
			}
			boolean isBalanceUpdated = updateAccountBalance(selectedAccount,
					selectedAccount.getBalance() - amountToWithdraw);
			if (isBalanceUpdated) {
				isAmountWithdrawnAndLoggedInTransaction = logTransaction(selectedAccount,
						selectedAccount.getAccountNumber(), amountToWithdraw, "Withdraw");
			}
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			throw new CustomException("Error While Withdrawing Money", e);
		}
		return isAmountWithdrawnAndLoggedInTransaction;
	}

	@Override
	public boolean transferMoneyWithinBank(Account accountFromTransfer, Account accountToTransfer,
			double amountToTransfer) throws CustomException {
		InputValidator.isNull(accountFromTransfer, ErrorMessages.INPUT_NULL_MESSAGE);
		InputValidator.isNull(accountToTransfer, ErrorMessages.INPUT_NULL_MESSAGE);
		if (accountFromTransfer.getBalance() < amountToTransfer) {
			throw new CustomException("Insufficient Balance");
		}
		boolean isTransferSuccess = false;
		try (Connection connection = DatabaseConnection.getConnection()) {
			connection.setAutoCommit(false);

			double newBalanceOfFromAccount = accountFromTransfer.getBalance() - amountToTransfer;
			boolean isFromAccountBalanceUpdated = updateAccountBalance(accountFromTransfer, newBalanceOfFromAccount);

			if (isFromAccountBalanceUpdated) {
				double newBalanceOfToAccount = accountToTransfer.getBalance() + amountToTransfer;
				boolean isToAccountBalanceUpdated = updateAccountBalance(accountToTransfer, newBalanceOfToAccount);

				if (isToAccountBalanceUpdated) {
					boolean isTransactionLoggedWithdraw = logTransaction(accountFromTransfer,
							accountToTransfer.getAccountNumber(), amountToTransfer, "Withdraw");
					boolean isTransactionLoggedDeposit = logTransaction(accountToTransfer,
							accountFromTransfer.getAccountNumber(), amountToTransfer, "Deposit");

					if (isTransactionLoggedWithdraw && isTransactionLoggedDeposit) {
						connection.commit();
						isTransferSuccess = true;
					} else {
						connection.rollback();
					}
				} else {
					connection.rollback();
				}
			} else {
				connection.rollback();
			}
		} catch (SQLException e) {
			throw new CustomException("Error While Transferring Money", e);
		}
		return isTransferSuccess;
	}

	@Override
	public boolean transferMoneyWithOtherBank(Account accountFromTransfer, String accountNumberToTransfer,
			double amountToTransferWithOtherBank) throws CustomException {
		InputValidator.isNull(accountFromTransfer, ErrorMessages.INPUT_NULL_MESSAGE);
		InputValidator.isNull(accountNumberToTransfer, ErrorMessages.INPUT_NULL_MESSAGE);
		if (accountFromTransfer.getBalance() < amountToTransferWithOtherBank) {
			throw new CustomException("Insufficient Balance");
		}
		boolean isTransferSuccess = false;
		try (Connection connection = DatabaseConnection.getConnection()) {
			connection.setAutoCommit(false);
			double newBalanceOfFromAccount = accountFromTransfer.getBalance() - amountToTransferWithOtherBank;

			boolean isFromAccountBalanceUpdated = updateAccountBalance(accountFromTransfer, newBalanceOfFromAccount);

			if (isFromAccountBalanceUpdated) {
				isTransferSuccess = logTransaction(accountFromTransfer, accountNumberToTransfer,
						amountToTransferWithOtherBank, "Withdraw");
			}
		} catch (SQLException e) {
		}
		return isTransferSuccess;
	}

	@Override
	public List<Transaction> getUsersStatement(Account account, int numberOfMonths) throws CustomException {
		InputValidator.isNull(account, ErrorMessages.INPUT_NULL_MESSAGE);
		List<Transaction> statements = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_STATEMENT)) {

			preparedStatement.setString(1, account.getAccountNumber());
			preparedStatement.setInt(2, numberOfMonths);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				statements = new ArrayList<Transaction>();
				getStatementDetails(resultSet, statements);
			}
		} catch (SQLException e) {
			throw new CustomException("Error While Reterving Transaction!!!", e);
		}
		return statements;
	}

	@Override
	public List<Transaction> getCustomerTransactionHistory(String accountNumber, int month) throws CustomException {
		List<Transaction> historyList = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_TRANSACTION_HISTORY)) {

			preparedStatement.setString(1, accountNumber);
			preparedStatement.setInt(2, month);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				historyList = new ArrayList<Transaction>();
				getCustomerTransactionDetail(resultSet, historyList);
			}
		} catch (SQLException e) {
			throw new CustomException("Error While Reterving Transaction!!!", e);
		}
		return historyList;
	}

	@Override
	public Map<String, List<Transaction>> getAllTransactionHistory(int userId, int branchId, int month)
			throws CustomException {
		Map<String, List<Transaction>> transactionMap = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(GET_ALL_TRANSACTION_OF_CUSTOMER_IN_BRANCH)) {
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, branchId);
			preparedStatement.setInt(3, month);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				transactionMap = new HashMap<String, List<Transaction>>();
				getCustomersTransactionDetail(resultSet, transactionMap);
			}
		} catch (SQLException e) {
			throw new CustomException("Error While Reterving Transaction!!!", e);
		}
		return transactionMap;
	}

	private void getCustomerTransactionDetail(ResultSet resultSet, List<Transaction> historyList) throws SQLException {
		while (resultSet.next()) {
			historyList.add(getTransactionDetail(resultSet));
		}
	}

	private void getCustomersTransactionDetail(ResultSet resultSet, Map<String, List<Transaction>> transactionList)
			throws SQLException {

		while (resultSet.next()) {
			Transaction transaction = getTransactionDetail(resultSet);
			String accountNumber = transaction.getViwerAccount();
			transactionList.computeIfAbsent(accountNumber, k -> new ArrayList<Transaction>()).add(transaction);
		}
	}

	private Transaction getTransactionDetail(ResultSet resultSet) throws SQLException {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(resultSet.getInt(1));
		transaction.setUserId(resultSet.getInt(2));
		transaction.setViwerAccount(resultSet.getString(3));
		transaction.setTransactedAccount(resultSet.getString(4));
		transaction.setTransactionType(resultSet.getString(5));
		transaction.setTransactedAmount(resultSet.getDouble(6));
		transaction.setBalance(resultSet.getDouble(7));
		transaction.setDateOfTransaction(resultSet.getDate(8));
		transaction.setRemark(resultSet.getString(9));
		transaction.setStatus(resultSet.getString(10));
		return transaction;
	}

	private void getStatementDetails(ResultSet resultSet, List<Transaction> statements) throws SQLException {
		Transaction transaction;
		while (resultSet.next()) {
			transaction = new Transaction();
			transaction.setDateOfTransaction(resultSet.getDate(1));
			transaction.setTransactionType(resultSet.getString(2));
			transaction.setTransactedAmount(resultSet.getDouble(3));
			transaction.setBalance(resultSet.getDouble(4));

			statements.add(transaction);
		}
	}

	private boolean logTransaction(Account viewerAccount, String transactedAccountNumber, double amount,
			String transactionType) throws CustomException {
		boolean isLoggedSuccessfully = false;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(TRANSACTION_LOG)) {
			preparedStatement.setInt(1, viewerAccount.getUserId());
			preparedStatement.setString(2, viewerAccount.getAccountNumber());
			preparedStatement.setString(3, transactedAccountNumber);
			preparedStatement.setString(4, transactionType);
			preparedStatement.setDouble(5, amount);
			preparedStatement.setDouble(6, viewerAccount.getBalance());
			preparedStatement.setTimestamp(7, Timestamp.from(Instant.now()));
			preparedStatement.setString(8, transactionType + " Transaction");
			preparedStatement.setString(9, "Success");

			int rowsAffected = preparedStatement.executeUpdate();

			isLoggedSuccessfully = (rowsAffected > 0);

		} catch (SQLException e) {
			throw new CustomException("Error While Logging In Transaction", e);
		}
		return isLoggedSuccessfully;
	}

	private boolean updateAccountBalance(Account selectedAccount, double amountToUpdate) throws CustomException {
		InputValidator.isNull(selectedAccount, ErrorMessages.INPUT_NULL_MESSAGE);
		boolean isBalanceUpdated = false;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

			preparedStatement.setDouble(1, amountToUpdate);
			preparedStatement.setString(2, selectedAccount.getAccountNumber());

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				selectedAccount.setBalance(amountToUpdate);
				isBalanceUpdated = true;
			}
		} catch (SQLException e) {
			throw new CustomException("Error While Updating Balance!!!", e);
		}
		return isBalanceUpdated;
	}

}
