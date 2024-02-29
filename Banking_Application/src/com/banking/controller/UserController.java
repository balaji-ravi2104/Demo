package com.banking.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;

import com.banking.dao.UserDao;
import com.banking.model.CustomerDetails;
import com.banking.model.User;
import com.banking.utils.CustomException;
import com.banking.utils.ErrorMessages;
import com.banking.utils.InputValidator;

public class UserController {

	private final UserDao userDao;
	private final BranchController branchController;
	private final AccountController accountController;
	private static final Logger log = Logger.getLogger(MainController.class.getName());

	public UserController(UserDao userDao, BranchController branchController, AccountController accountController) {
		this.userDao = userDao;
		this.branchController = branchController;
		this.accountController = accountController;
	}

	public User login(int userId, String password) throws CustomException {
		InputValidator.isNull(password, "Password Cannot be Empty or Null!!!");
		User user = null;
		try {
			user = userDao.authendicateUser(userId, password);
		} catch (Exception e) {
			throw new CustomException("Error while loggin!!", e);
		}
		return user;
	}

	public boolean registerNewUser(User user) throws CustomException {
		boolean isRegistred = false;
		if (!validateUserInput(user)) {
			return isRegistred;
		}
		try {
			isRegistred = userDao.addUser(user);
		} catch (Exception e) {
			throw new CustomException("Error while creating new User!!", e);
		}
		return isRegistred;
	}

	public boolean isUserExists(int userId) throws CustomException {
		try {
			return userDao.checkCustomerIdExists(userId);
		} catch (Exception e) {
			throw new CustomException("Error while Checking User Exists!!", e);
		}
	}

	public boolean isUserExistsInTheBranch(int userId, int branchId) throws CustomException {
		try {
			return userDao.checkCustomerIdPresentInBranch(userId, branchId);
		} catch (Exception e) {
			throw new CustomException("Error while Checking User Exists!!", e);
		}
	}

	public CustomerDetails getCustomerDetails(String accountNumber, int branchId) throws CustomException {
		InputValidator.isNull(accountNumber, "Account Number Cannot be Empty or Null!!!");
		CustomerDetails customerDetails = null;
		if (!validateAccountAndBranch(accountNumber, branchId)) {
			return customerDetails;
		}
		try {
			customerDetails = userDao.getCustomerDetails(accountNumber, branchId);
		} catch (Exception e) {
			throw new CustomException("Error while Getting User Exists!!", e);
		}
		return customerDetails;
	}

	public Map<String,CustomerDetails> getAllCustomerDetails(int branchId) throws CustomException {
		Map<String,CustomerDetails> customerDetails = null;
		if(!validateBranchId(branchId)) {
			return customerDetails;
		}
		try {
			customerDetails = userDao.getAllCustomerDetailsInOneBranch(branchId);
		} catch (Exception e) {
			throw new CustomException("Error while Getting All User Exists!!", e);
		}
		return customerDetails;
	}

	public <K, V> boolean updateCustomer(int userIdToUpdate, Map<K, V> fieldsToUpdate, int branchId)
			throws CustomException {
		InputValidator.isNull(fieldsToUpdate, ErrorMessages.INPUT_NULL_MESSAGE);
		boolean isCustomerUpdated = false;
		if (validateUserId(userIdToUpdate) || validateBranchId(branchId)
				|| validateUserIdAndBranchId(userIdToUpdate, branchId)) {
			return isCustomerUpdated;
		}
		if (validateFieldAndValuesToUpdate(fieldsToUpdate)) {
			return isCustomerUpdated;
		}
		try {
			isCustomerUpdated = userDao.updateCustomerDetails(userIdToUpdate, fieldsToUpdate);
		} catch (Exception e) {
			throw new CustomException("Error while Updating User", e);
		}
		return isCustomerUpdated;
	}

	public boolean updatePassword(User user, String password) throws CustomException {
		InputValidator.isNull(user, ErrorMessages.INPUT_NULL_MESSAGE);
		InputValidator.isNull(password, ErrorMessages.INPUT_NULL_MESSAGE);
		try {
			return userDao.updateCustomerPassword(user, password);
		} catch (Exception e) {
			throw new CustomException("Error while Updating Password!!", e);
		}
	}

	public boolean isEmployeeExists(int employeeId) throws CustomException {
		try {
			return userDao.checkEmployeeExists(employeeId);
		} catch (Exception e) {
			throw new CustomException("Error while Checking Employee Exists!!", e);
		}
	}

	public User getEmployeeDetails(int employeeId) throws CustomException {
		try {
			return userDao.getEmployeeDetails(employeeId);
		} catch (Exception e) {
			throw new CustomException("Error while Reterving Employee Details!!", e);
		}
	}

	public List<User> getEmployeeFromOneBranch(int branchId) throws CustomException {
		try {
			return userDao.getAllEmployeeFromOneBranch(branchId);
		} catch (Exception e) {
			throw new CustomException("Error while Reterving Employee Details!!", e);
		}

	}

	public List<User> getEmployeeFromAllBranch() throws CustomException {
		try {
			return userDao.getAllEmployeeFromAllBranch();
		} catch (Exception e) {
			throw new CustomException("Error while Reterving Employee Details!!", e);
		}
	}

	public List<CustomerDetails> getAllCustomerFromAllBranch() throws CustomException {
		try {
			return userDao.getAllCustomersFromAllBranch();
		} catch (Exception e) {
			throw new CustomException("Error while Reterving Employee Details!!", e);
		}
	}

	public List<CustomerDetails> getAllDetailsOfOneCustomerInOneBranch(int userId, int branchId)
			throws CustomException {
		try {
			return userDao.getAllDetailsOfOneCustomerInOneBranch(userId, branchId);
		} catch (Exception e) {
			throw new CustomException("Error while Reterving Employee Details!!", e);
		}
	}

	public List<CustomerDetails> getAllDetailsOfOneCustomerInAllBranch(int userId) throws CustomException {
		try {
			return userDao.getAllDetailsOfOneCustomerInAllBranch(userId);
		} catch (Exception e) {
			throw new CustomException("Error while Reterving Employee Details!!", e);
		}
	}

	private boolean validateUserInput(User user) throws CustomException {
		InputValidator.isNull(user, ErrorMessages.INPUT_NULL_MESSAGE);
		boolean isValid = true;
		if (InputValidator.validateString(user.getFirstName().trim())) {
			log.log(Level.WARNING, "First Name Cannot be Empty");
			isValid = false;
		}
		if (InputValidator.validateString(user.getLastName().trim())) {
			log.log(Level.WARNING, "Last Name Cannot be Empty");
			isValid = false;
		}
		if (InputValidator.validateString(user.getGender())) {
			log.log(Level.WARNING, "Gender Cannot be Empty");
			isValid = false;
		}
		if (!InputValidator.validateEmail(user.getEmail())) {
			log.log(Level.WARNING, "Invalid Email Address");
			isValid = false;
		}
		if (!InputValidator.validateMobileNumber(user.getContactNumber())) {
			log.log(Level.WARNING, "Invalid Mobile Number");
			isValid = false;
		}
		if (InputValidator.validateString(user.getAddress())) {
			log.log(Level.WARNING, "Address Cannot be Empty");
			isValid = false;
		}
		if (InputValidator.validateString(user.getTypeOfUser())) {
			log.log(Level.WARNING, "Type Of the User Cannot't Be Empty!!");
			isValid = false;
		}
		if (!InputValidator.validateDateOfBirth(user.getDateOfBirth())) {
			log.log(Level.WARNING, "Invalid Date Of Birth!! Please Provide(YYYY-MM-DD)");
			isValid = false;
		}
		if (!InputValidator.validatepanNumber(user.getPanNumber())) {
			log.log(Level.WARNING, "Invalid PAN Number!! Please Provide Valid PAN Number");
			isValid = false;
		}
		if (!InputValidator.validateaadharNumber(user.getAadharNumber())) {
			log.log(Level.WARNING, "Invalid Aadhar Number!! Please Provide Valid Aadhar Number");
			isValid = false;
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

	private boolean validateUserId(int userId) throws CustomException {
		boolean isUserIdPresent = isUserExists(userId);
		if (!isUserIdPresent) {
			log.warning("Invalid User Id!!!");
		}
		return isUserIdPresent;
	}

	private boolean validateUserIdAndBranchId(int userId, int branchId) throws CustomException {
		boolean isValidId = isUserExistsInTheBranch(userId, branchId);
		if (!isValidId) {
			log.warning("Invalid UserID or UserID is Not present in this Branch!!");
		}
		return isValidId;
	}

	private boolean validateAccountAndBranch(String accountNumber, int branchId) throws CustomException {
		boolean isValid = true;
		if (!accountController.isAccountExistsInTheBranch(accountNumber, branchId)) {
			log.log(Level.WARNING,"Account Number Doesn't Exists in this Branch!!!");
			isValid = false;
		}
		return isValid;
	}

	private <K, V> boolean validateFieldAndValuesToUpdate(Map<K, V> fieldsToUpdate)
			throws PatternSyntaxException, CustomException {
		boolean isValid = true;
		for (Entry<K, V> entry : fieldsToUpdate.entrySet()) {
			String fieldName = (String) entry.getKey();
			String fieldValue = (String) entry.getValue();
			switch (fieldName) {
			case "FirstName":
				if (InputValidator.validateString(fieldValue)) {
					log.log(Level.WARNING, "First Name Cannot be Empty");
					isValid = false;
				}
				break;
			case "LastName":
				if (InputValidator.validateString(fieldValue)) {
					log.log(Level.WARNING, "Last Name Cannot be Empty");
					isValid = false;
				}
				break;
			case "Gender":
				if (InputValidator.validateString(fieldValue)) {
					log.log(Level.WARNING, "Gender Cannot be Empty");
					isValid = false;
				}
				break;
			case "Email":
				if (!InputValidator.validateEmail(fieldValue)) {
					log.log(Level.WARNING, "Invalid Email Address");
					isValid = false;
				}
				break;
			case "ContactNumber":
				if (!InputValidator.validateMobileNumber(fieldValue)) {
					log.log(Level.WARNING, "Invalid Mobile Number");
					isValid = false;
				}
				break;
			case "Address":
				if (InputValidator.validateString(fieldValue)) {
					log.log(Level.WARNING, "Address Cannot be Empty");
					isValid = false;
				}
				break;
			case "DateOfBirth":
				if (!InputValidator.validateDateOfBirth(fieldValue)) {
					log.log(Level.WARNING, "Invalid Date Of Birth!! Please Provide(YYYY-MM-DD)");
					isValid = false;
				}
				break;
			case "Pan":
				if (!InputValidator.validatepanNumber(fieldValue)) {
					log.log(Level.WARNING, "Invalid PAN Number!! Please Provide Valid PAN Number");
					isValid = false;
				}
				break;
			case "Aadhar":
				if (!InputValidator.validateaadharNumber(fieldValue)) {
					log.log(Level.WARNING, "Invalid Aadhar Number!! Please Provide Valid Aadhar Number");
					isValid = false;
				}
				break;
			default:
				log.log(Level.WARNING, "Unknown field: " + fieldName);
				isValid = false;
				break;
			}
		}
		return isValid;
	}

}
