package com.banking.view;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.banking.model.CustomerDetails;
import com.banking.model.User;
import com.banking.utils.CustomException;
import com.banking.utils.ErrorMessages;
import com.banking.utils.InputValidator;

public class UserView {
	private static final Logger log = Logger.getLogger(UserView.class.getName());

	public void displaySuccessMessage() {
		log.info("User Created Successfully");
	}

	public void displayFailureMessage() {
		log.warning("User Creation Failed!! Try Again");
	}

	public void displayUserDetailsFailedMessage() {
		log.warning("Error While Reterving User Detail!! Please Try Again!!");
	}

	public void displayCustomerNotFoundMessage() {
		log.info("No Customer Found in Your Branch");
	}

	public void displayAllCustomerDetails(Map<String, CustomerDetails> allCustomerDetails) throws CustomException {
		for (Map.Entry<String, CustomerDetails> entry : allCustomerDetails.entrySet()) {
			CustomerDetails customerDetails = entry.getValue();
			displayCustomerDetails(customerDetails);
		}
	}

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
		if (user.getPanNumber() != null) {
			log.info("PAN Number : " + user.getPanNumber());
		}
		if (user.getAadharNumber() != null) {
			log.info("Aadhar Number : " + user.getAadharNumber());
		}
		if (user.getBranchId() != 0) {
			log.info("Branch Id : " + user.getBranchId());
		}
		log.info("-".repeat(60));
	}

	public void displayCustomerDetails(CustomerDetails customerDetails) throws CustomException {
		InputValidator.isNull(customerDetails, ErrorMessages.INPUT_NULL_MESSAGE);
		log.info("-------------------------------------------------------");
		log.info(String.format("| %-15s | %-15s |", "Customer Id", customerDetails.getUserId()));
		log.info(String.format("| %-15s | %-15s |", "First Name", customerDetails.getFirstName()));
		log.info(String.format("| %-15s | %-15s |", "Last Name", customerDetails.getLastName()));
		log.info(String.format("| %-15s | %-15s |", "Gender", customerDetails.getGender()));
		log.info(String.format("| %-15s | %-15s |", "Email", customerDetails.getEmail()));
		log.info(String.format("| %-15s | %-15s |", "PAN Number", customerDetails.getPan()));
		log.info(String.format("| %-15s | %-15s |", "Aadhar Number", customerDetails.getAadhar()));
		log.info(String.format("| %-15s | %-15s |", "Account Id", customerDetails.getAccountId()));
		log.info(String.format("| %-15s | %-15s |", "Account Number", customerDetails.getAccountNumber()));
		log.info(String.format("| %-15s | %-15s |", "Balance", customerDetails.getBalance()));
		log.info(String.format("| %-15s | %-15s |", "Account Status", customerDetails.getStatus()));
		log.info("-------------------------------------------------------");
	}

	public void displayPasswordUpdatedSuccessMessage() {
		log.info("Password Updated Successfully!!");
	}

	public void displayPasswordUpdatedFailedMessage() {
		log.info("Password Updated Failed!!");
	}

	public void displayListOfEmployees(List<User> employeeList) throws CustomException {
		InputValidator.isNull(employeeList, ErrorMessages.INPUT_NULL_MESSAGE);
		for (User user : employeeList) {
			displayUserProfile(user);
		}
	}

}
