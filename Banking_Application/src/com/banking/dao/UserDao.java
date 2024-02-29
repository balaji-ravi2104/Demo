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

	Map<String,CustomerDetails> getAllCustomerDetailsInOneBranch(int branchID) throws CustomException;

	boolean checkCustomerIdPresentInBranch(int userId, int branchId) throws CustomException;

	<K, V> boolean updateCustomerDetails(int userIdToUpdate, Map<K, V> fieldsToUpdate) throws CustomException;

	boolean updateCustomerPassword(User user, String password) throws CustomException;

	boolean checkEmployeeExists(int employeeId) throws CustomException;

	User getEmployeeDetails(int employeeId) throws CustomException;

	List<User> getAllEmployeeFromOneBranch(int branchId) throws CustomException;

	List<User> getAllEmployeeFromAllBranch() throws CustomException;

	List<CustomerDetails> getAllCustomersFromAllBranch() throws CustomException;

	List<CustomerDetails> getAllDetailsOfOneCustomerInOneBranch(int userId, int branchId) throws CustomException;

	List<CustomerDetails> getAllDetailsOfOneCustomerInAllBranch(int userId) throws CustomException;
}
