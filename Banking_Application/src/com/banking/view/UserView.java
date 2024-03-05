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

	public void displayCustomerDetails(Map<String, CustomerDetails> allCustomerDetails) throws CustomException {
		if (allCustomerDetails.isEmpty()) {
			log.info("No Customer Found!!!");
			return;
		}
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
		log.info("Type of User : "+user.getTypeOfUser());
		log.info("Login Account Status : "+user.getStatus());
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
		log.info(String.format("| %-15s | %-15s |", "Branch Id", customerDetails.getBranchId()));
		log.info("-------------------------------------------------------");
	}

	public void displayPasswordUpdatedSuccessMessage() {
		log.info("Password Updated Successfully!!");
	}

	public void displayPasswordUpdatedFailedMessage() {
		log.info("Password Updated Failed!!");
	}

	public void displayListOfEmployees(Map<Integer, User> employeesList) throws CustomException {
		InputValidator.isNull(employeesList, ErrorMessages.INPUT_NULL_MESSAGE);
		if (employeesList.isEmpty()) {
			log.info("No Employee Avaliable!!!");
			return;
		}
		for (Map.Entry<Integer, User> entry : employeesList.entrySet()) {
			displayUserProfile(entry.getValue());
		}
	}

	public void displayInvalidEmployeeId() {
		log.warning("Invalid Employee Id!!!");
	}

	public void displayCustomersDetails(Map<Integer, List<CustomerDetails>> allCustomerDetails) throws CustomException {
		for (Map.Entry<Integer, List<CustomerDetails>> entry : allCustomerDetails.entrySet()) {
			log.info("Details Of Customer Id : " + entry.getKey());
			for (CustomerDetails details : entry.getValue()) {
				displayCustomerDetails(details);
			}
		}
	}

	public void displayCustomerDetailsByBranch(Map<Integer, List<CustomerDetails>> allDetailsOfCustomer)
			throws CustomException {
		if (allDetailsOfCustomer.isEmpty()) {
			log.info("The User Doesn't Have Any Account");
			return;
		}
		for (Map.Entry<Integer, List<CustomerDetails>> entry : allDetailsOfCustomer.entrySet()) {
			log.info("Details of the Customer in Branch " + entry.getKey());
			for (CustomerDetails customerDetails : entry.getValue()) {
				displayCustomerDetails(customerDetails);
			}
		}
	}

	public void displayAllCustomersDetail(Map<Integer, Map<Integer, List<CustomerDetails>>> customersFromAllBranch)
			throws CustomException {
		for (Map.Entry<Integer, Map<Integer, List<CustomerDetails>>> branchEntry : customersFromAllBranch.entrySet()) {
			int branchId = branchEntry.getKey();
			log.info("Details Of Customers From Branch ID: " + branchId);

			Map<Integer, List<CustomerDetails>> customerMap = branchEntry.getValue();
			for (Map.Entry<Integer, List<CustomerDetails>> customerEntry : customerMap.entrySet()) {
				int customerId = customerEntry.getKey();
				log.info("Details Of Customer ID: " + customerId);
				for (CustomerDetails customerDetails : customerEntry.getValue()) {
					displayCustomerDetails(customerDetails);
				}
			}
		}
	}

	public void displayEmployeesByBranch(Map<Integer, Map<Integer, User>> allEmployeesList) throws CustomException {
		for (Map.Entry<Integer, Map<Integer, User>> entry : allEmployeesList.entrySet()) {
			int branchId = entry.getKey();
			log.info("Employees From Branch Id : "+branchId);
			displayListOfEmployees(entry.getValue());
		}
	}

}
