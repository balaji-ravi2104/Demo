package com.banking.view;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.banking.model.Account;
import com.banking.model.Customer;
import com.banking.model.Employee;
import com.banking.model.User;
import com.banking.utils.CustomException;
import com.banking.utils.ErrorMessages;
import com.banking.utils.InputValidator;
import com.banking.utils.ThreadLocalStroage;

public class UserView {
	private static final Logger log = Logger.getLogger(UserView.class.getName());

	public void displayUserCreationSuccessMessage() {
		log.info("User Created Successfully");
	}

	public void displayUserCreationFailureMessage() {
		log.warning("User Creation Failed!! Try Again");
	}

	public void displayDetailsRetervingFailedMessage() {
		log.warning("Error While Reterving Detail!! Please Try Again!!");
	}

	public void displayCustomerNotFoundMessage() {
		log.info("No Customer Found in Your Branch");
	}

//	public void displayCustomerDetails(Map<String, Customer> allCustomerDetails) throws CustomException {
//		if (allCustomerDetails.isEmpty()) {
//			log.info("No Customer Found!!!");
//			return;
//		}
//		for (Map.Entry<String, Customer> entry : allCustomerDetails.entrySet()) {
//			Customer customerDetails = entry.getValue();
//			displayCustomerDetails(customerDetails);
//		}
//	}

	public void displayUpdateSuccessMessage() {
		log.info("Customer Details Updated Successfully!!");
	}

	public void displayUpdateFailedMessage() {
		log.info("Customer Updation Failed!! Try Again!!");
	}

	public void displayUserProfile(User user) throws CustomException {
		InputValidator.isNull(user, ErrorMessages.INPUT_NULL_MESSAGE);
		log.info("-".repeat(60));
		log.info("User Id : " + user.getUserId());
		log.info("First Name : " + user.getFirstName());
		log.info("Last Name : " + user.getLastName());
		log.info("Gender : " + user.getGender());
		log.info("Email : " + user.getEmail());
		log.info("Contact Number : " + user.getContactNumber());
		log.info("Address : " + user.getAddress());
		log.info("DOB : " + user.getDateOfBirth());
		log.info("Type of User : " + user.getTypeOfUser());
		log.info("Login Account Status : " + user.getStatus());
		log.info("-".repeat(60));
	}

	public void displayCustomerDetails(Customer customerDetails) throws CustomException {
		InputValidator.isNull(customerDetails, ErrorMessages.INPUT_NULL_MESSAGE);
		log.info("-------------------------------------------------------");
		log.info(String.format("| %-15s | %-15s |", "Customer Id", customerDetails.getUserId()));
		log.info(String.format("| %-15s | %-15s |", "First Name", customerDetails.getFirstName()));
		log.info(String.format("| %-15s | %-15s |", "Last Name", customerDetails.getLastName()));
		log.info(String.format("| %-15s | %-15s |", "Gender", customerDetails.getGender()));
		log.info(String.format("| %-15s | %-15s |", "Email", customerDetails.getEmail()));
		log.info(String.format("| %-15s | %-15s |", "Contact Number", customerDetails.getContactNumber()));
		log.info(String.format("| %-15s | %-15s |", "Address", customerDetails.getAddress()));
		log.info(String.format("| %-15s | %-15s |", "Date of Birth", customerDetails.getDateOfBirth()));
		log.info(String.format("| %-15s | %-15s |", "PAN Number", customerDetails.getPanNumber()));
		log.info(String.format("| %-15s | %-15s |", "Aadhar Number", customerDetails.getAadharNumber()));
		log.info(String.format("| %-15s | %-15s |", "Login Account Status", customerDetails.getStatus()));
		log.info("-------------------------------------------------------");
	}

	public void displayPasswordUpdatedSuccessMessage() {
		log.info("Password Updated Successfully!!");
	}

	public void displayPasswordUpdatedFailedMessage() {
		log.info("Password Updated Failed!!");
	}

	public void displayListOfEmployees(Map<Integer, Employee> employeesList) throws CustomException {
		InputValidator.isNull(employeesList, ErrorMessages.INPUT_NULL_MESSAGE);
		if (employeesList.isEmpty()) {
			log.info("No Employee Avaliable!!!");
			return;
		}
		for (Map.Entry<Integer, Employee> entry : employeesList.entrySet()) {
			displayEmployeeProfile(entry.getValue());
		}
	}

	public void displayInvalidEmployeeId() {
		log.warning("Invalid Employee Id!!!");
	}

	public void displayEmployeesByBranch(Map<Integer, Map<Integer, Employee>> allEmployeesList) throws CustomException {
		for (Map.Entry<Integer, Map<Integer, Employee>> entry : allEmployeesList.entrySet()) {
			int branchId = entry.getKey();
			log.info("Employees From Branch Id : " + branchId);
			displayListOfEmployees(entry.getValue());
		}
	}

	public void displayEmployeeProfile(Employee employeeDetails) throws CustomException {
		InputValidator.isNull(employeeDetails, ErrorMessages.INPUT_NULL_MESSAGE);
		displayUserProfile(employeeDetails);
		log.info("Branch Id : " + employeeDetails.getBranchId());
		log.info("-".repeat(60));
	}

}
