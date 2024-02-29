package com.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.banking.model.CustomerDetails;
import com.banking.model.User;
import com.banking.model.UserType;
import com.banking.utils.CustomException;
import com.banking.utils.DatabaseConnection;
import com.banking.utils.ErrorMessages;
import com.banking.utils.InputValidator;

public class UserDaoImplementation implements UserDao {

	private static final String GET_USER = "SELECT  u.userId,u.FirstName,u.LastName,u.Gender,u.Email,u.ContactNumber,u.Address,u.DateOfBirth,u.Type,u.Status FROM Users u WHERE u.userId = ? and u.password = ?";

	private static final String GET_EMPLOYEE_BRANCH = "SELECT branch_id FROM Employee WHERE User_id = ?";

	private static final String GET_CUSTOMER_QUERY = "SELECT Pan,Aadhar from Customer WHERE User_id = ?";

	private static final String CREATE_EMPLOYEE = "INSERT INTO Employee (User_id,Employee_Role,branch_id) Values (?,?,?);";

	private static final String CREATE_NEW_USER = "INSERT INTO Users (Password, FirstName, LastName, Gender, Email, ContactNumber, Address, DateOfBirth, Type)\n"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

	private static final String CREATE_CUSTOMER = "INSERT INTO Customer (User_id, Pan, Aadhar) VALUES (?, ?, ?);";

	private static final String CHECK_CUSTOMER_ID_EXISTS_QUERY = "SELECT COUNT(*) FROM Users u WHERE u.UserId = ? AND u.Type = 'Customer';";

	private static final String CHECK_CUSTOMER_ID_EXISTS_QUERY_IN_BRANCH = "SELECT COUNT(*) FROM Users u JOIN Accounts a ON u.UserId = a.user_id\n"
			+ " WHERE u.UserId = ? AND a.branch_id = ? AND u.Type = 'Customer';";

	private static final String GET_CUSTOMER_ACCOUNT_DETAIL = "SELECT u.UserId, u.FirstName, u.LastName, u.Gender, u.Email,c.Pan, c.Aadhar,a.Account_id, a.account_number, a.balance,a.status\n"
			+ " FROM Users u JOIN Customer c ON u.UserId = c.User_id\n"
			+ " JOIN Accounts a ON u.UserId = a.user_id WHERE a.account_number = ? and a.branch_id = ?;";

	private static final String GET_ALL_CUSTOMER_DETAIL = "SELECT u.UserId, u.FirstName, u.LastName, u.Gender, u.Email,c.Pan, c.Aadhar,a.Account_id, a.account_number, a.balance,a.status\n"
			+ " FROM Users u JOIN Customer c ON u.UserId = c.User_id\n"
			+ " JOIN Accounts a ON u.UserId = a.user_id WHERE a.branch_id = ? ORDER BY u.UserId;";

	private static final String UPDATE_PASSWORD = "UPDATE Users SET Password = ? WHERE UserId = ?;";

	private static final String CHECK_EMPLOYEE_ID_EXISTS_QUERY = "SELECT COUNT(*) FROM Users u WHERE u.UserId = ? AND u.Type = 'Employee';";

	private static final String GET_EMPLOYEE_DETAILS = "SELECT u.UserId,u.FirstName,u.LastName,u.Gender,u.Email,u.ContactNumber,"
			+ " u.Address,u.DateOfBirth,u.Type,e.branch_id FROM Users u INNER JOIN Employee e ON u.UserId = e.user_id where u.UserId = ?";

	private static final String GET_ALL_EMPLOYEE_IN_ONE_BRANCH = "SELECT u.UserId,u.FirstName,u.LastName,u.Gender,u.Email,u.ContactNumber,\n"
			+ " u.Address,u.DateOfBirth,u.Type,e.branch_id FROM Users u INNER JOIN Employee e ON u.UserId = e.user_id where e.branch_id = ? AND u.Type = 'Employee'";

	private static final String GET_ALL_EMPLOYEE_FROM_ALL_BRANCH = "SELECT u.UserId,u.FirstName,u.LastName,u.Gender,u.Email,u.ContactNumber,\n"
			+ " u.Address,u.DateOfBirth,u.Type,e.branch_id FROM Users u INNER JOIN Employee e ON u.UserId = e.user_id WHERE u.Type = 'Employee' ORDER BY e.branch_id;";

	private static final String GET_ALL_CUSTOMER_FROM_ALL_BRANCH = "SELECT u.UserId,u.FirstName,u.LastName,u.Gender,u.Email,c.Pan,"
			+ "c.Aadhar,a.account_id,a.account_number,a.balance,a.status FROM Users u INNER JOIN Customer c ON u.UserId = c.User_id INNER JOIN Accounts a ON u.UserId = a.user_id;";

	private static final String GET_ALL_DETAIL_OF_CUSTOMER_IN_ONE_BRANCH = "SELECT u.UserId, u.FirstName, u.LastName, u.Gender, u.Email, "
			+ "c.Pan, c.Aadhar, a.account_id, a.account_number, a.balance, a.status FROM Users u INNER JOIN Customer c ON u.UserId = c.User_id "
			+ "INNER JOIN Accounts a ON u.UserId = a.user_id WHERE u.UserId = ? AND a.branch_id = ?;";

	private static final String GET_ALL_DETAIL_OF_CUSTOMER_IN_ALL_BRANCH = "SELECT u.UserId, u.FirstName, u.LastName, u.Gender, u.Email, "
			+ "c.Pan, c.Aadhar, a.account_id, a.account_number, a.balance, a.status FROM Users u INNER JOIN Customer c ON u.UserId = c.User_id "
			+ "INNER JOIN Accounts a ON u.UserId = a.user_id WHERE u.UserId = ?;";

	@Override
	public User authendicateUser(int userID, String password) throws CustomException {
		InputValidator.isNull(password, ErrorMessages.INPUT_NULL_MESSAGE);
		User user = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_USER)) {
			preparedStatement.setInt(1, userID);
			preparedStatement.setString(2, password);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					user = new User();
					getUserDetails(resultSet, user);

					String userType = user.getTypeOfUser();
					UserType userTypeEnum = UserType.fromString(userType);

					if (userType != null && userTypeEnum == UserType.EMPLOYEE) {
						getEmployeeBranch(userID, user);
					}
					if (userType != null && userTypeEnum == UserType.CUSTOMER) {
						getCustomerPanAndAadhar(userID, user);
					}
				}
			}

		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Reterving User Details", e);
		}
		return user;
	}

	@Override
	public boolean addUser(User user) throws CustomException {
		InputValidator.isNull(user, ErrorMessages.INPUT_NULL_MESSAGE);
		boolean isUserCreated = false;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement createUserStatement = connection.prepareStatement(CREATE_NEW_USER,
						Statement.RETURN_GENERATED_KEYS)) {
			createUserStatement.setString(1, user.getPassword());
			createUserStatement.setString(2, user.getFirstName());
			createUserStatement.setString(3, user.getLastName());
			createUserStatement.setString(4, user.getGender());
			createUserStatement.setString(5, user.getEmail());
			createUserStatement.setString(6, user.getContactNumber());
			createUserStatement.setString(7, user.getAddress());
			createUserStatement.setDate(8, java.sql.Date.valueOf(user.getDateOfBirth()));
			createUserStatement.setString(9, user.getTypeOfUser());

			int rowsAffected = createUserStatement.executeUpdate();
			if (rowsAffected > 0) {
				int userId;
				try (ResultSet generatedKeys = createUserStatement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						userId = generatedKeys.getInt(1);
					} else {
						throw new SQLException("Creating user failed, no ID obtained.");
					}
				}
				if (UserType.CUSTOMER == UserType.fromString(user.getTypeOfUser())) {
					int customerRowsAffected = addCustomer(userId, user);
					isUserCreated = customerRowsAffected > 0;
				} else {
					int customerRowsAffected = addEmployee(userId, user);
					isUserCreated = customerRowsAffected > 0;
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error Creating new User", e);
		}
		return isUserCreated;
	}

	@Override
	public boolean checkCustomerIdExists(int userId) throws CustomException {
		boolean userIdExists = false;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CHECK_CUSTOMER_ID_EXISTS_QUERY)) {
			preparedStatement.setInt(1, userId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					userIdExists = (count > 0);
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Checking User Details", e);
		}
		return userIdExists;
	}

	@Override
	public boolean checkCustomerIdPresentInBranch(int userID, int branchId) throws CustomException {
		boolean userIdExists = false;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(CHECK_CUSTOMER_ID_EXISTS_QUERY_IN_BRANCH)) {
			preparedStatement.setInt(1, userID);
			preparedStatement.setInt(2, branchId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					userIdExists = (count > 0);
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Checking User Details", e);
		}
		return userIdExists;
	}

	@Override
	public CustomerDetails getCustomerDetails(String accountNumber, int branchID) throws CustomException {
		CustomerDetails customerDetails = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_CUSTOMER_ACCOUNT_DETAIL)) {
			preparedStatement.setString(1, accountNumber);
			preparedStatement.setInt(2, branchID);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					customerDetails = getCustomerDetails(resultSet);
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Reterving User Details", e);
		}
		return customerDetails;
	}

	@Override
	public Map<String, CustomerDetails> getAllCustomerDetailsInOneBranch(int branchID) throws CustomException {
		Map<String, CustomerDetails> customerDetails = new TreeMap<>();
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CUSTOMER_DETAIL)) {
			preparedStatement.setInt(1, branchID);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				generateAllCustomerDetails(resultSet, customerDetails);
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Reterving User Details", e);
		}
		return customerDetails;
	}

	@Override
	public <K, V> boolean updateCustomerDetails(int userIdToUpdate, Map<K, V> fieldsToUpdate) throws CustomException {
		String updateQuery = generateUpdateQuery(fieldsToUpdate);
		// System.out.println(updateQuery);
		boolean isUpdated = false;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			int index = 1;
			for (Map.Entry<K, V> entry : fieldsToUpdate.entrySet()) {
				if (entry.getKey().equals("Pan") || entry.getKey().equals("Aadhar")) {
					continue;
				}
				preparedStatement.setObject(index++, entry.getValue());
			}

			if (fieldsToUpdate.containsKey("Pan")) {
				preparedStatement.setObject(index++, fieldsToUpdate.get("Pan"));
			}

			if (fieldsToUpdate.containsKey("Aadhar")) {
				preparedStatement.setObject(index++, fieldsToUpdate.get("Aadhar"));
			}

			preparedStatement.setInt(index++, userIdToUpdate);

			int rowsAffected = preparedStatement.executeUpdate();
			isUpdated = (rowsAffected > 0);

		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Updating User Details", e);
		}
		return isUpdated;
	}

	@Override
	public boolean updateCustomerPassword(User user, String password) throws CustomException {
		InputValidator.isNull(user, ErrorMessages.INPUT_NULL_MESSAGE);
		InputValidator.isNull(password, ErrorMessages.INPUT_NULL_MESSAGE);
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD)) {

			preparedStatement.setString(1, password);
			preparedStatement.setInt(2, user.getUserId());

			return preparedStatement.executeUpdate() > 0;

		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Updating User Details", e);
		}
	}

	@Override
	public boolean checkEmployeeExists(int employeeId) throws CustomException {
		boolean employeeIdExists = false;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CHECK_EMPLOYEE_ID_EXISTS_QUERY)) {
			preparedStatement.setInt(1, employeeId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					employeeIdExists = (count > 0);
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Checking User Details", e);
		}
		return employeeIdExists;
	}

	@Override
	public User getEmployeeDetails(int employeeId) throws CustomException {
		User employeeDetails = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEE_DETAILS)) {
			preparedStatement.setInt(1, employeeId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					employeeDetails = new User();
					getEmployeeDetail(resultSet, employeeDetails);
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Reterving User Details", e);
		}
		return employeeDetails;
	}

	@Override
	public List<User> getAllEmployeeFromOneBranch(int branchId) throws CustomException {
		List<User> employeeList = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EMPLOYEE_IN_ONE_BRANCH)) {
			preparedStatement.setInt(1, branchId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				employeeList = new ArrayList<User>();
				getAllEmployeeDetails(resultSet, employeeList);
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Reterving User Details", e);
		}
		return employeeList;
	}

	@Override
	public List<User> getAllEmployeeFromAllBranch() throws CustomException {
		List<User> employeeList = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EMPLOYEE_FROM_ALL_BRANCH)) {

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				employeeList = new ArrayList<User>();
				getAllEmployeeDetails(resultSet, employeeList);
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Reterving User Details", e);
		}
		return employeeList;
	}

	@Override
	public List<CustomerDetails> getAllCustomersFromAllBranch() throws CustomException {
		List<CustomerDetails> allCustomerDetails = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CUSTOMER_FROM_ALL_BRANCH)) {

//			try (ResultSet resultSet = preparedStatement.executeQuery()) {
//				allCustomerDetails = generateAllCustomerDetails(resultSet);
//			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Reterving User Details", e);
		}
		return allCustomerDetails;
	}

	@Override
	public Map<String, CustomerDetails> getAllDetailsOfCustomerFromOneBranch(int userId, int branchId)
			throws CustomException {
		System.out.println(userId+" "+branchId);
		Map<String, CustomerDetails> allDetails = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(GET_ALL_DETAIL_OF_CUSTOMER_IN_ONE_BRANCH)) {
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, branchId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				allDetails = new TreeMap<String, CustomerDetails>();
				generateAllCustomerDetails(resultSet, allDetails);
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Reterving User Details", e);
		}
		return allDetails;
	}

	@Override
	public Map<String, CustomerDetails> getAllDetailsOfOneCustomerInAllBranch(int userId) throws CustomException {
		Map<String, CustomerDetails> allDetails = null;
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(GET_ALL_DETAIL_OF_CUSTOMER_IN_ALL_BRANCH)) {

			preparedStatement.setInt(1, userId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				allDetails = new TreeMap<String, CustomerDetails>();
				generateAllCustomerDetails(resultSet, allDetails);
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Reterving User Details", e);
		}
		return allDetails;
	}

	private int addCustomer(int userId, User user) throws CustomException {
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement createCustomerStatement = connection.prepareStatement(CREATE_CUSTOMER)) {
			createCustomerStatement.setInt(1, userId);
			createCustomerStatement.setString(2, user.getPanNumber());
			createCustomerStatement.setString(3, user.getAadharNumber());

			return createCustomerStatement.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Creating Customer ", e);
		}
	}

	private int addEmployee(int userId, User user) throws CustomException {
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement createCustomerStatement = connection.prepareStatement(CREATE_EMPLOYEE)) {
			createCustomerStatement.setInt(1, userId);
			createCustomerStatement.setString(2, user.getTypeOfUser());
			createCustomerStatement.setInt(3, user.getBranchId());

			return createCustomerStatement.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Creating Employee ", e);
		}
	}

	private <K, V> String generateUpdateQuery(Map<K, V> fieldsToUpdate) {
		StringBuilder queryBuilder = new StringBuilder("UPDATE Users u JOIN Customer c ON u.UserId = c.User_id SET ");
		Iterator<Map.Entry<K, V>> iterator = fieldsToUpdate.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<K, V> entry = iterator.next();
			if (entry.getKey().equals("Pan") || entry.getKey().equals("Aadhar")) {
				continue;
			}
			queryBuilder.append("u.").append(entry.getKey()).append(" = ?");
			if (iterator.hasNext()) {
				queryBuilder.append(", ");
			}
		}
		if (fieldsToUpdate.containsKey("Pan") || fieldsToUpdate.containsKey("Aadhar")) {
			queryBuilder.append("c.");
			if (fieldsToUpdate.containsKey("Pan")) {
				queryBuilder.append("Pan = ?");
			}
			if (fieldsToUpdate.containsKey("Aadhar")) {
				if (fieldsToUpdate.containsKey("Pan")) {
					queryBuilder.append(", ");
				}
				queryBuilder.append("Aadhar = ?");
			}
		}

		queryBuilder.append(" WHERE u.UserId = ?");
		return queryBuilder.toString();
	}

	private void generateAllCustomerDetails(ResultSet resultSet, Map<String, CustomerDetails> customerMap)
			throws SQLException, CustomException {
		while (resultSet.next()) {
			CustomerDetails customerDetails = getCustomerDetails(resultSet);
			System.out.println(customerDetails.getAccountNumber());
			customerMap.put(customerDetails.getAccountNumber(), getCustomerDetails(resultSet));
		}
	}

	private void getAllEmployeeDetails(ResultSet resultSet, List<User> employeeList) throws SQLException {
		User user;
		while (resultSet.next()) {
			user = new User();
			getUserDetails(resultSet, user);
			user.setBranchId(resultSet.getInt(10));

			employeeList.add(user);
		}
	}

	private void getUserDetails(ResultSet resultSet, User user) throws SQLException {
		user.setUserId(resultSet.getInt(1));
		user.setFirstName(resultSet.getString(2));
		user.setLastName(resultSet.getString(3));
		user.setGender(resultSet.getString(4));
		user.setEmail(resultSet.getString(5));
		user.setContactNumber(resultSet.getString(6));
		user.setAddress(resultSet.getString(7));
		user.setDateOfBirth(resultSet.getString(8));
		user.setTypeOfUser(resultSet.getString(9));
		user.setStatus(resultSet.getString(10));
	}

	private void getEmployeeDetail(ResultSet resultSet, User employeeDetails) throws SQLException {
		getUserDetails(resultSet, employeeDetails);
		employeeDetails.setBranchId(resultSet.getInt(10));
	}

	private CustomerDetails getCustomerDetails(ResultSet resultSet) throws SQLException, CustomException {
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setUserId(resultSet.getInt(1));
		customerDetails.setFirstName(resultSet.getString(2));
		customerDetails.setLastName(resultSet.getString(3));
		customerDetails.setGender(resultSet.getString(4));
		customerDetails.setEmail(resultSet.getString(5));
		customerDetails.setPan(resultSet.getString(6));
		customerDetails.setAadhar(resultSet.getString(7));
		customerDetails.setAccountId(resultSet.getInt(8));
		customerDetails.setAccountNumber(resultSet.getString(9));
		customerDetails.setBalance(resultSet.getDouble(10));
		customerDetails.setStatus(resultSet.getString(11));
		return customerDetails;
	}

	private void getEmployeeBranch(int userID, User user) throws CustomException {
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEE_BRANCH)) {
			preparedStatement.setInt(1, userID);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					user.setBranchId(resultSet.getInt(1));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Reterving Employee Details", e);
		}
	}

	private void getCustomerPanAndAadhar(int userID, User user) throws CustomException {
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_CUSTOMER_QUERY)) {
			preparedStatement.setInt(1, userID);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					user.setPanNumber(resultSet.getString(1));
					user.setAadharNumber(resultSet.getString(2));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error While Reterving Customer Details", e);
		}
	}
}
