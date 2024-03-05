package com.banking.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;

import com.banking.dao.UserDao;
import com.banking.dao.UserDaoImplementation;
import com.banking.model.CustomerDetails;
import com.banking.model.User;
import com.banking.model.UserType;
import com.banking.utils.CommonUtils.Field;
import com.banking.utils.CustomException;
import com.banking.utils.ErrorMessages;
import com.banking.utils.InputValidator;
import com.banking.view.UserView;

public class UserController {

	private static final Logger log = Logger.getLogger(MainController.class.getName());
	private UserDao userDao = new UserDaoImplementation();;
	private BranchController branchController = new BranchController();
	private UserView userView = new UserView();
	private AccountController accountController;

	public UserController(AccountController accountController) {
		this.accountController = accountController;
	}

	public UserController() {
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
		InputValidator.isNull(user, ErrorMessages.INPUT_NULL_MESSAGE);
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
		boolean isExists = false;
		if (!validateUser(userId) || !branchController.validateBranchId(branchId)) {
			return isExists;
		}
		try {
			isExists = userDao.checkCustomerIdPresentInBranch(userId, branchId);
		} catch (Exception e) {
			throw new CustomException("Error while Checking User Exists!!", e);
		}
		return isExists;
	}

	public CustomerDetails getCustomerDetails(String accountNumber, int branchId) throws CustomException {
		InputValidator.isNull(accountNumber, "Account Number Cannot be Empty or Null!!!");
		CustomerDetails customerDetails = null;
		if (!accountController.validateAccountAndBranch(accountNumber, branchId)) {
			return customerDetails;
		}
		try {
			customerDetails = userDao.getCustomerDetails(accountNumber, branchId);
		} catch (Exception e) {
			throw new CustomException("Error while Getting User Exists!!", e);
		}
		return customerDetails;
	}

	public <K extends Enum<K>, V> boolean updateCustomer(int userIdToUpdate, Map<K, V> fieldsToUpdate)
			throws CustomException {
		InputValidator.isNull(fieldsToUpdate, ErrorMessages.INPUT_NULL_MESSAGE);
		boolean isCustomerUpdated = false;
		if (!validateUser(userIdToUpdate) || !validateFieldAndValuesToUpdate(fieldsToUpdate)) {
			return isCustomerUpdated;
		}
		try {
			isCustomerUpdated = userDao.updateCustomerDetails(userIdToUpdate, fieldsToUpdate);
		} catch (Exception e) {
			throw new CustomException("Error while Updating User", e);
		}
		return isCustomerUpdated;
	}

	public boolean updatePassword(int userId, String password) throws CustomException {
		InputValidator.isNull(password, ErrorMessages.INPUT_NULL_MESSAGE);
		boolean isPasswordUpdated = false;
		if (!validatePassword(password)) {
			return isPasswordUpdated;
		}
		try {
			isPasswordUpdated = userDao.updatePassword(userId, password);
		} catch (Exception e) {
			throw new CustomException("Error while Updating Password!!", e);
		}
		return isPasswordUpdated;
	}

	public User getEmployeeDetails(int employeeId) throws CustomException {
		User employee = null;
		if (!isEmployeeExists(employeeId)) {
			userView.displayInvalidEmployeeId();
			return employee;
		}
		try {
			employee = userDao.getEmployeeDetails(employeeId);
		} catch (Exception e) {
			throw new CustomException("Error while Reterving Employee Details!!", e);
		}
		return employee;
	}

	public Map<Integer, User> getEmployeeFromOneBranch(int branchId) throws CustomException {
		Map<Integer, User> allEmployee = null;
		if (!branchController.validateBranchId(branchId)) {
			return allEmployee;
		}
		try {
			allEmployee = userDao.getEmployeesInBranch(branchId);
		} catch (Exception e) {
			throw new CustomException("Error while Reterving Employee Details!!", e);
		}
		return allEmployee;
	}

	public Map<Integer, Map<Integer, User>> getEmployeeFromAllBranch() throws CustomException {
		try {
			return userDao.getEmployeesFromAllBranch();
		} catch (Exception e) {
			throw new CustomException("Error while Reterving Employee Details!!", e);
		}
	}

	public Map<String, CustomerDetails> getCustomerDetailsInBranch(int userId, int branchId) throws CustomException {
		Map<String, CustomerDetails> allDetails = null;
		if (!validateUserIdAndBranchId(userId, branchId)) {
			return allDetails;
		}
		try {
			allDetails = userDao.getDetailsOfCustomerInBranch(userId, branchId);
		} catch (Exception e) {
			throw new CustomException("Error while Reterving Employee Details!!", e);
		}
		return allDetails;
	}

	public Map<Integer, List<CustomerDetails>> getCustomerDetailsInAllBranch(int userId) throws CustomException {
		Map<Integer, List<CustomerDetails>> allDetails = null;
		if (!validateUser(userId)) {
			return allDetails;
		}
		try {
			allDetails = userDao.getDetailsOfCustomerInAllBranch(userId);
		} catch (Exception e) {
			throw new CustomException("Error while Reterving Employee Details!!", e);
		}
		return allDetails;
	}

	private boolean validateUserInput(User user) throws CustomException {
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
		if (user.getTypeOfUser().equalsIgnoreCase(UserType.CUSTOMER.name())
				&& !InputValidator.validatepanNumber(user.getPanNumber())) {
			log.log(Level.WARNING, "Invalid PAN Number!! Please Provide Valid PAN Number");
			isValid = false;
		}
		if (user.getTypeOfUser().equalsIgnoreCase(UserType.CUSTOMER.name())
				&& !InputValidator.validateAadharNumber(user.getAadharNumber())) {
			log.log(Level.WARNING, "Invalid Aadhar Number!! Please Provide Valid Aadhar Number");
			isValid = false;
		}
		if (user.getTypeOfUser().equals(UserType.EMPLOYEE.name())) {
			if (!branchController.validateBranchId(user.getBranchId())) {
				isValid = false;
			}
		}
		return isValid;
	}

	public boolean validateUser(int userId) throws CustomException {
		boolean isUserIdPresent = isUserExists(userId);
		if (!isUserIdPresent) {
			log.warning("Invalid User Id!!!");
		}
		return isUserIdPresent;
	}

	public boolean validateUserIdAndBranchId(int userId, int branchId) throws CustomException {
		boolean isValidId = isUserExistsInTheBranch(userId, branchId);
		if (!isValidId) {
			log.warning("UserID is Not present in this Branch!!");
		}
		return isValidId;
	}

	private boolean validatePassword(String password) throws PatternSyntaxException, CustomException {
		boolean isValid = true;
		if (!InputValidator.validatePassword(password)) {
			isValid = false;
			log.warning(
					"Invalid Password Choosen!! Password Must Contains Atleast, One Captial,One Small,One Special Case,One Number and 8 digits!!!");
		}
		return isValid;
	}

	private boolean isEmployeeExists(int employeeId) throws CustomException {
		boolean isExixts = false;
		try {
			isExixts = userDao.checkEmployeeExists(employeeId);
		} catch (Exception e) {
			throw new CustomException("Error while Checking Employee Exists!!", e);
		}
		return isExixts;
	}

	private <K, V> boolean validateFieldAndValuesToUpdate(Map<K, V> fieldsToUpdate)
			throws PatternSyntaxException, CustomException {
		boolean isValid = true;
		for (Entry<K, V> entry : fieldsToUpdate.entrySet()) {
			Field fieldName = (Field) entry.getKey();
			String fieldValue = (String) entry.getValue();
			switch (fieldName) {
			case FirstName:
				if (InputValidator.validateString(fieldValue)) {
					log.log(Level.WARNING, "First Name Cannot be Empty");
					isValid = false;
				}
				break;
			case LastName:
				if (InputValidator.validateString(fieldValue)) {
					log.log(Level.WARNING, "Last Name Cannot be Empty");
					isValid = false;
				}
				break;
			case Gender:
				if (InputValidator.validateString(fieldValue)) {
					log.log(Level.WARNING, "Gender Cannot be Empty");
					isValid = false;
				}
				break;
			case Email:
				if (!InputValidator.validateEmail(fieldValue)) {
					log.log(Level.WARNING, "Invalid Email Address");
					isValid = false;
				}
				break;
			case ContactNumber:
				if (!InputValidator.validateMobileNumber(fieldValue)) {
					log.log(Level.WARNING, "Invalid Mobile Number");
					isValid = false;
				}
				break;
			case Address:
				if (InputValidator.validateString(fieldValue)) {
					log.log(Level.WARNING, "Address Cannot be Empty");
					isValid = false;
				}
				break;
			case DateOfBirth:
				if (!InputValidator.validateDateOfBirth(fieldValue)) {
					log.log(Level.WARNING, "Invalid Date Of Birth!! Please Provide(YYYY-MM-DD)");
					isValid = false;
				}
				break;
			case Pan:
				if (!InputValidator.validatepanNumber(fieldValue)) {
					log.log(Level.WARNING, "Invalid PAN Number!! Please Provide Valid PAN Number");
					isValid = false;
				}
				break;
			case Aadhar:
				if (!InputValidator.validateAadharNumber(fieldValue)) {
					log.log(Level.WARNING, "Invalid Aadhar Number!! Please Provide Valid Aadhar Number");
					isValid = false;
				}
				break;
			case Status:
				if (InputValidator.validateAccountStatus(fieldValue)) {
					log.log(Level.WARNING, "Account Status Cannot be Empty!!!");
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
