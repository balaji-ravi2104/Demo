package com.banking.dao;

import java.util.List;
import java.util.Map;

import com.banking.model.CustomerDetails;
import com.banking.model.User;
import com.banking.utils.CustomException;

public interface UserDao {
	User authendicateUser(int userID, String password) throws CustomException;

	boolean addUser(User user) throws CustomException;

	boolean checkCustomerIdExists(int userId) throws CustomException;

	CustomerDetails getCustomerDetails(String accountNumber, int branchID) throws CustomException;

	boolean checkCustomerIdPresentInBranch(int userId, int branchId) throws CustomException;

	<K extends Enum<K>, V> boolean updateCustomerDetails(int userIdToUpdate, Map<K, V> fieldsToUpdate)
			throws CustomException;

	boolean updatePassword(int userId, String password) throws CustomException;

	boolean checkEmployeeExists(int employeeId) throws CustomException;

	User getEmployeeDetails(int employeeId) throws CustomException;

	Map<Integer, User> getEmployeesInBranch(int branchId) throws CustomException;

	Map<Integer, Map<Integer, User>> getEmployeesFromAllBranch() throws CustomException;

	Map<String, CustomerDetails> getDetailsOfCustomerInBranch(int userId, int branchId) throws CustomException;

	Map<Integer, List<CustomerDetails>> getDetailsOfCustomerInAllBranch(int userId) throws CustomException;
}
