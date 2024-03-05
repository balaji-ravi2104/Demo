package com.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.banking.model.Account;
import com.banking.utils.CustomException;
import com.banking.utils.DatabaseConnection;
import com.banking.utils.ErrorMessages;
import com.banking.utils.InputValidator;

public class AccountDaoImplementation implements AccountDao {

	private static final String CREATE_NEW_ACCOUNT = "INSERT INTO Accounts (user_id, account_number, "
			+ "branch_id, account_type, balance) VALUES (?,?,?,?,?);";

	private static final String GET_COUNT_FOR_BRANCH_QUERY = "SELECT COUNT(*) FROM Accounts WHERE branch_id = ?";

	private static final String GET_ACCOUNT_DETAILS = "SELECT Account_id,user_id,branch_id,account_type, "
			+ "balance,status FROM Accounts WHERE account_number = ?;";

	private static final String GET_ALL_ACCOUNTS_OF_CUSTOMER = "SELECT * FROM Accounts WHERE user_id = ?;";

	private static final String CHECK_CUSTOMER_ACCOUNT_EXISTS_QUERY_IN_BRANCH = "SELECT COUNT(*) FROM Accounts "
			+ "WHERE account_number = ? and branch_id = ?;";

	private static final String UPDATE_BANK_ACCOUNT_STATUS = "UPDATE Accounts SET status = ? WHERE account_number = ?;";

	@Override
	public boolean checkAccountExists(String accountNumber, int branchId) throws CustomException {
		boolean isAccountExists = false;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(CHECK_CUSTOMER_ACCOUNT_EXISTS_QUERY_IN_BRANCH)) {
			preparedStatement.setString(1, accountNumber);
			preparedStatement.setInt(2, branchId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					isAccountExists = (count > 0);
				}
			}

		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Checking Account Existing!!!", e);
		}
		return isAccountExists;
	}

	@Override
	public boolean createNewAccount(Account account) throws CustomException {
		InputValidator.isNull(account, ErrorMessages.INPUT_NULL_MESSAGE);
		boolean isAccountCreated = false;
		String accountNumber = String.format("%04d%08d", account.getBranchId(),
				getCountForBranchId(account.getBranchId()) + 1);
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CREATE_NEW_ACCOUNT)) {
			preparedStatement.setInt(1, account.getUserId());
			preparedStatement.setString(2, accountNumber);
			preparedStatement.setInt(3, account.getBranchId());
			preparedStatement.setString(4, account.getAccountType());
			preparedStatement.setDouble(5, account.getBalance());

			int rowsAffected = preparedStatement.executeUpdate();
			isAccountCreated = rowsAffected > 0;

		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Creating new Account!!!", e);
		}
		return isAccountCreated;
	}

	@Override
	public Account getAccountDetail(String accountNumber) throws CustomException {
		Account accountDetails = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_DETAILS)) {

			preparedStatement.setString(1, accountNumber);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					accountDetails = new Account();
					accountDetails.setAccountId(resultSet.getInt(1));
					accountDetails.setUserId(resultSet.getInt(2));
					accountDetails.setBranchId(resultSet.getInt(3));
					accountDetails.setAccountType(resultSet.getString(4));
					accountDetails.setBalance(resultSet.getDouble(5));
					accountDetails.setStatus(resultSet.getString(6));
					accountDetails.setAccountNumber(accountNumber);
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Reterving Account!!!", e);
		}
		return accountDetails;
	}

	@Override
	public List<Account> getAllAccountsOfCustomer(int userId) throws CustomException {
		List<Account> accounts = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ACCOUNTS_OF_CUSTOMER)) {

			preparedStatement.setInt(1, userId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				accounts = new ArrayList<Account>();
				getAllAccounts(resultSet, accounts);
			}

		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Reterving All Account of a Customer!!!", e);
		}
		return accounts;
	}

	@Override
	public boolean checkCustomerAccountPresentInBranch(String accountNumber, int branchId) throws CustomException {
		boolean userIdExists = false;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(CHECK_CUSTOMER_ACCOUNT_EXISTS_QUERY_IN_BRANCH)) {
			preparedStatement.setString(1, accountNumber);
			preparedStatement.setInt(2, branchId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					userIdExists = (count > 0);
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Checking Customer Account Details", e);
		}
		return userIdExists;
	}

	@Override
	public boolean activateDeactivateCustomerAccount(String accountNumber, int branchId, String status)
			throws CustomException {
		boolean isAccountStatusChanged = false;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BANK_ACCOUNT_STATUS)) {
			preparedStatement.setString(1, status);
			preparedStatement.setString(2, accountNumber);
			int rowsAffected = preparedStatement.executeUpdate();
			isAccountStatusChanged = (rowsAffected > 0);
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Updating Bank Account Status", e);
		}
		return isAccountStatusChanged;
	}

	private void getAllAccounts(ResultSet resultSet, List<Account> accounts) throws SQLException {
		Account account;
		while (resultSet.next()) {
			account = new Account();
			account.setAccountId(resultSet.getInt(1));
			account.setUserId(resultSet.getInt(2));
			account.setAccountNumber(resultSet.getString(3));
			account.setBranchId(resultSet.getInt(4));
			account.setAccountType(resultSet.getString(5));
			account.setBalance(resultSet.getDouble(6));
			account.setStatus(resultSet.getString(7));

			accounts.add(account);
		}
	}

	private int getCountForBranchId(int branchId) throws CustomException {
		int accountCount = 0;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_COUNT_FOR_BRANCH_QUERY)) {
			preparedStatement.setInt(1, branchId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					accountCount = resultSet.getInt(1);
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error getting account count for branch ID: " + branchId, e);
		}
		return accountCount;
	}

}
