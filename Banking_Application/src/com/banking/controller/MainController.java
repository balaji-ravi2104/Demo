package com.banking.controller;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.banking.dao.AccountDao;
import com.banking.dao.AccountDaoImplementation;
import com.banking.dao.BranchDao;
import com.banking.dao.BranchDaoImplementation;
import com.banking.dao.TransactionDao;
import com.banking.dao.TransactionDaoImplementation;
import com.banking.dao.UserDao;
import com.banking.dao.UserDaoImplementation;
import com.banking.model.Account;
import com.banking.model.CustomerDetails;
import com.banking.model.Transaction;
import com.banking.model.User;
import com.banking.model.UserType;
import com.banking.utils.CommonUtils;
import com.banking.utils.CustomException;
import com.banking.utils.InputValidator;
import com.banking.utils.PasswordGenerator;
import com.banking.view.AccountView;
import com.banking.view.BranchView;
import com.banking.view.MainView;
import com.banking.view.TransactionView;
import com.banking.view.UserView;

public class MainController {
	private static final Logger log = Logger.getLogger(MainController.class.getName());

	private MainView mainView;
	private UserView userView;
	private AccountView accountView;
	private TransactionView transactionView;
	private BranchView branchView;
	private UserController userController;
	private AccountController accountController;
	private TransactionController transactionController;
	private BranchController branchController;
	public User user;
	public UserDao userDao;
	public AccountDao accountDao;
	public TransactionDao transactionDao;
	public BranchDao branchDao;
	private boolean isAppliactionAlive;
	private boolean isLoggedIn;

	public MainController() {
		this.mainView = new MainView();
		this.userView = new UserView();
		this.accountView = new AccountView();
		this.transactionView = new TransactionView();
		this.branchView = new BranchView();
		this.userDao = new UserDaoImplementation();
		this.accountDao = new AccountDaoImplementation();
		this.transactionDao = new TransactionDaoImplementation();
		this.branchDao = new BranchDaoImplementation();
		this.branchController = new BranchController(branchDao);
		this.userController = new UserController(userDao,branchController);
		this.transactionController = new TransactionController(transactionDao);
		this.accountController = new AccountController(accountDao, userController, branchController);
		this.isLoggedIn = false;
	}

	public void startApplication() {
		this.isAppliactionAlive = true;
		while (isAppliactionAlive) {
			try {
				mainView.displayWelcomeMessage();
				log.info("1. Login");
				log.info("2. Logout");
				log.info("3. Exit");
				log.info("Enter your choice: ");
				int choice = mainView.promptForMainMenuChoice();
				switch (choice) {
				case 1:
					if (!isLoggedIn) {
						login();
					} else {
						log.info("You are already logged in!");
					}
					break;
				case 2:
					if (isLoggedIn) {
						logout();
					} else {
						log.info("You are not Logged In !!");
					}
					break;
				case 3:
					isAppliactionAlive = false;
					log.log(Level.WARNING, "Exiting");
					break;
				default:
					log.info("Invalid choice. Please try again.");
					break;
				}
			} catch (InputMismatchException e) {
				mainView.displayInputMissMatchMessage();
				mainView.promptNewLine();
				continue;
			} catch (Exception e) {
				mainView.displayExceptionMessage(e);
				mainView.promptNewLine();
				continue;
			}
		}
	}

	private void login() {
		while (!isLoggedIn) {
			try {
				log.info("Please login to continue:");
				int userId = mainView.promptForUserID();
				if (userId <= 0) {
					throw new IllegalArgumentException("UserId must be greater the ZERO!!!");
				}
				mainView.promptNewLine();
				String password = mainView.promptForPassword();

				user = userController.login(userId, password);
				if (user != null) {
					log.info("Logged in Successfully!!");
					isLoggedIn = true;
					String userType = user.getTypeOfUser();
					UserType userTypeEnum = UserType.fromString(userType);
					if (userTypeEnum == UserType.CUSTOMER) {
						performCustomerOperations(user);
					} else if (userTypeEnum == UserType.EMPLOYEE) {
						performEmployeeOperations(user);
					} else {
						preformAdminOperation(user);
					}
				} else {
					log.info("Invalid username or password!");
				}
			} catch (IllegalArgumentException e) {
				log.log(Level.WARNING, e.getMessage());
				mainView.promptNewLine();
				continue;
			} catch (InputMismatchException e) {
				mainView.displayInputMissMatchMessage();
				mainView.promptNewLine();
				continue;
			} catch (Exception e) {
				mainView.displayExceptionMessage(e);
				mainView.promptNewLine();
				continue;
			}
		}
	}

	private void performCustomerOperations(User user) {
		Account selectedAccount = accountSelectionOperation(user);
		// System.out.println(selectedAccount);
		if (selectedAccount == null) {
			log.info("You Can Logout And Contact the Bank!!");
			return;
		}
		boolean isCustomerAlive = true;
		while (isCustomerAlive) {
			try {
				log.info("Customer Operation");
				log.info("1.View My Profile");
				log.info("2.Account Details");
				log.info("3.View Balance");
				log.info("4.Deposite");
				log.info("5.Withdraw");
				log.info("6.Transfer Within Bank");
				log.info("7.Transfer with other Bank");
				log.info("8.Take Statement");
				log.info("9.Change Password");
				log.info("10.Switch Account");
				log.info("11.Exit");
				log.info("Enter Your Choice");
				int customerChoice = mainView.promptForMainMenuChoice();
				mainView.promptNewLine();

				switch (customerChoice) {
				case 1:
					log.info("1.View My Profile");
					userView.displayUserProfile(user);
					break;
				case 2:
					log.info("2.Account Details");
					accountView.displayAccountDetails(selectedAccount);
					break;
				case 3:
					log.info("3.View Balance");
					accountView.displayBalance(selectedAccount);
					break;
				case 4:
					log.info("4.Deposite Money");
					log.info("Enter the Amount to Deposite");
					double amountToDeposite = mainView.promptDoubleInput();
					if (amountToDeposite <= 0) {
						transactionView.displayInvalidAmmountMessage();
						break;
					}
					boolean isAmountDeposited = transactionController.depositAmount(selectedAccount, amountToDeposite);
					if (isAmountDeposited) {
						transactionView.displayDepositSuccessMessage();
					} else {
						transactionView.displayDepositFailedMessage();
					}
					break;
				case 5:
					log.info("5.Withdraw Money!!");
					log.info("Enter the Amount to Withdraw");
					double amountToWithdraw = mainView.promptDoubleInput();
					if (amountToWithdraw <= 0) {
						transactionView.displayInvalidAmmountMessage();
						break;
					}
					if (amountToWithdraw > selectedAccount.getBalance()) {
						transactionView.displayInsufficientBalanceMessage();
						break;
					}
					boolean isAmountWithdrawed = transactionController.withdrawAmount(selectedAccount,
							amountToWithdraw);
					if (isAmountWithdrawed) {
						transactionView.displayWithdrawSuccessMessage();
					} else {
						transactionView.displayWithdrawFailedMessage();
					}
					break;
				case 6:
					log.info("6.Transfer Within Bank");
					log.info("Enter the Account Number to Transfer the Amount");
					String accountNumber = mainView.promptStringInput();
					Account accountToTransfer = accountController.getAccountDetails(accountNumber);
					System.out.println(accountToTransfer);
					if (accountToTransfer == null) {
						transactionView.displayInvalidAccountMessage();
						break;
					}
					if (accountToTransfer.getStatus().equalsIgnoreCase("INACTIVE")) {
						transactionView.displayAccountInActiveMessage();
						break;
					}
					log.info("Enter the Amount to Transfer");
					double amountToTransfer = mainView.promptDoubleInput();
					if (amountToTransfer > selectedAccount.getBalance()) {
						transactionView.displayInsufficientBalanceMessage();
						break;
					}
					boolean isTransactionSuccess = transactionController.transferWithinBank(selectedAccount,
							accountToTransfer, amountToTransfer);
					if (isTransactionSuccess) {
						transactionView.displayTransactionSuccessMessage();
					} else {
						transactionView.displayTransactionFailedMessage();
					}
					break;
				case 7:
					log.info("7.Transfer With Other Bank!!");
					log.info("Enter the Account Number to Transfer the Amount");
					String accountNumberToTransfer = mainView.promptStringInput();
					log.info("Enter the Amount to Transfer");
					double amountToTransferWithOtherBank = mainView.promptDoubleInput();
					if (amountToTransferWithOtherBank > selectedAccount.getBalance()) {
						transactionView.displayInsufficientBalanceMessage();
						break;
					}
					boolean isTransferSuccess = transactionController.transferWithOtherBank(selectedAccount,
							accountNumberToTransfer, amountToTransferWithOtherBank);
					if (isTransferSuccess) {
						transactionView.displayTransactionSuccessMessage();
					} else {
						transactionView.displayTransactionFailedMessage();
					}
					break;
				case 8:
					log.info("8.Get Statement");
					log.info("Enter the Number of Months to get the Statement(1 to 6)");
					int numberOfMonths = mainView.promtForIntegerInput();
					if (numberOfMonths <= 0 || numberOfMonths > 6) {
						log.info("Please Enter the Valid Month.. From 1 to 6..");
						break;
					}
					List<Transaction> statement = transactionController.getStatement(selectedAccount, numberOfMonths);
					if (statement.isEmpty()) {
						transactionView.displayNoStatementAvaliableMessage();
						break;
					}
					transactionView.displayStatements(statement);
					break;
				case 9:
					log.info("9.Update Password");
					log.info("Enter the Password to Change");
					String password = mainView.promptStringInput();
					if (!InputValidator.validatePassword(password)) {
						mainView.displayInvalidPassword();
					}
					boolean isPasswordUpdated = userController.updatePassword(user, password);
					if (isPasswordUpdated) {
						userView.displayPasswordUpdatedSuccessMessage();
					} else {
						userView.displayPasswordUpdatedFailedMessage();
					}
					break;
				case 10:
					log.info("10.Switch Account");
					selectedAccount = accountSelectionOperation(user);
					// System.out.println(selectedAccount);
					break;
				case 11:
					isCustomerAlive = false;
					log.info("Exiting!!!");
					break;
				default:
					log.info("Invalid option! Please choose again.");
					break;
				}
			} catch (InputMismatchException e) {
				mainView.displayInputMissMatchMessage();
				mainView.promptNewLine();
				continue;
			} catch (Exception e) {
				mainView.displayExceptionMessage(e);
				mainView.promptNewLine();
				continue;
			}
		}
	}

	private Account accountSelectionOperation(User user) {
		Account selectedAccount = null;
		boolean isAccountSelected = false;
		try {
			List<Account> accounts = accountController.getAllAccountsOfCustomer(user.getUserId());
			if (accounts.isEmpty()) {
				log.info("You don't have any accounts.");
				return null;
			}
			Map<Integer, Account> accountMap = new HashMap<>();
			int accountNumber = 1;
			log.info("Your accounts:");
			for (Account account : accounts) {
				log.info("((" + accountNumber + "))");
				accountView.displayAccountDetails(account);
				if (account.getStatus().equalsIgnoreCase("ACTIVE")) {
					accountMap.put(accountNumber, account);
					accountNumber++;
				}
			}
			if (accountMap.isEmpty()) {
				log.info("You don't have any active accounts.");
				return null;
			}
			while (!isAccountSelected) {
				log.info("Please choose an account to continue:");
				int selectedAccountNumber = mainView.promptForAccountNumber();
				selectedAccount = accountMap.get(selectedAccountNumber);
				if (selectedAccount == null) {
					log.info("Invalid account selected! Please enter a valid choice.");
				} else {
					log.info("Account Selected Successfully");
					isAccountSelected = true;
				}
			}
		} catch (CustomException e) {
			mainView.displayExceptionMessage(e);
		}
		return selectedAccount;
	}

	private <K, V> void performEmployeeOperations(User user) {
		boolean isEmployeeAlive = true;
		while (isEmployeeAlive) {
			try {
				log.info("Employee Operations");
				log.info("1.Create Customer");
				log.info("2.Create Account");
				log.info("3.Update Customer");
				log.info("4.View Particular Customer Details");
				log.info("5.View All Customer Details");
				log.info("6.Close Account");
				log.info("7.View Transaction History For a Particular Customer(Account)");
				log.info("8.View All Transaction of One Customer by Customer ID");
				log.info("8.View All Transaction History OF All the Customers");
				log.info("9.Exit");
				log.info("Enter the choice");
				int employeeChoice = mainView.promptForMainMenuChoice();
				mainView.promptNewLine();
				switch (employeeChoice) {
				case 1:
					log.info("1.Create Customer");
					String password = PasswordGenerator.generatePassword();
					log.info("Enter the First Name");
					String firstName = mainView.promptStringInput();
					log.info("Enter the Last Name");
					String lastName = mainView.promptStringInput();
					log.info("Enter the Gender");
					String gender = mainView.promptStringInput();
					log.info("Enter the Email");
					String email = mainView.promptStringInput().trim();
					log.info("Enter the Contact Number");
					String number = mainView.promptStringInput().trim();
					log.info("Enter the Address");
					String address = mainView.promptStringInput().trim();
					log.info("Enter the date of birth(YYYY-MM-DD)");
					String dob = mainView.promptStringInput().trim();
					log.info("Enter the PAN Number");
					String panNumber = mainView.promptStringInput();
					log.info("Enter the Aadhar Number");
					String aadharNumber = mainView.promptStringInput();
					UserType typeOfUser = UserType.CUSTOMER;
					User newUser = new User();
					newUser.setPassword(password);
					newUser.setFirstName(firstName);
					newUser.setLastName(lastName);
					newUser.setGender(gender);
					newUser.setEmail(email);
					newUser.setContactNumber(number);
					newUser.setAddress(address);
					newUser.setDateOfBirth(dob);
					newUser.setTypeOfUser(typeOfUser.toString());
					newUser.setPanNumber(panNumber);
					newUser.setAadharNumber(aadharNumber);
					boolean isUserCreated = userController.registerNewUser(newUser);
					if (isUserCreated) {
						userView.displaySuccessMessage();
					} else {
						userView.displayFailureMessage();
					}
					break;
				case 2:
					log.info("2.Create Account");
					log.info("Enter the userID");
					int userId = mainView.promptForUserID();
					mainView.promptNewLine();
					log.info("Enter the Type of Account");
					String typeOfAccount = mainView.promptStringInput();
					log.info("Enter the Balance");
					double balance = mainView.promptDoubleInput();
					Account account = new Account();
					account.setUserId(userId);
					account.setBranchId(user.getBranchId());
					account.setAccountType(typeOfAccount);
					account.setBalance(balance);
					boolean isAccountCreated = accountController.createAccount(account);
					if (isAccountCreated) {
						accountView.displayAccountCreationSuccessMessage();
					} else {
						accountView.displayAccountCreationFailureMessage();
					}
					break;
				case 3:
					log.info("3.Update Customer");
					Map<Integer, String> fieldMap = CommonUtils.generateFieldMap();
					mainView.displayFieldName(fieldMap);
					log.info("Enter the UserId to Update");
					int userIdToUpdate = mainView.promptForUserID();
					Map<String, String> fieldsToUpdate = new HashMap<>();
					log.info("Enter the Number Of Field To be Updated");
					int count = mainView.promtForIntegerInput();
					log.info("Please Enter the Field Number to Update");
					for (int i = 1; i <= count; i++) {
						log.info("Enter the choice(Number) from the list");
						int choice = mainView.promtForIntegerInput();
						if (choice < 0 || choice > 10) {
							throw new IllegalArgumentException(
									"The Field Selection Choice Should be greater than Zero or Less than or Equal to Ten!!");
						}
						mainView.promptNewLine();
						log.info("Enter the Value to Update");
						String value = mainView.promptStringInput();
						fieldsToUpdate.put(fieldMap.get(choice), value);
					}
					if (fieldsToUpdate.size() == count) {
						boolean isUserUpdated = userController.updateCustomer(userIdToUpdate, fieldsToUpdate,user.getBranchId());
						if (isUserUpdated) {
							userView.displayUpdateSuccessMessage();
						} else {
							userView.displayUpdateFailedMessage();
						}
					}
					break;
				case 4:
					log.info("4.View Particual Customer Details");
					log.info("Enter the Account Number");
					String accountNumber = mainView.promptStringInput();

					boolean isUserIDPresentInBranch = accountController.isAccountExistsInTheBranch(accountNumber,
							user.getBranchId());
					if (!isUserIDPresentInBranch) {
						log.warning("Invalid Account Number  or Account is Not present in this Branch!!");
						break;
					}
					CustomerDetails customerDetails = userController.getCustomerDetails(accountNumber,
							user.getBranchId());
					if (customerDetails == null) {
						userView.displayUserDetailsFailedMessage();
						break;
					}
					userView.displayCustomerDetails(customerDetails);
					break;
				case 5:
					log.info("5.Get All Customer Details");
					List<CustomerDetails> allCustomerDetails = userController.getAllCustomerDetails(user.getBranchId());
					if (allCustomerDetails.isEmpty()) {
						userView.displayCustomerNotFoundMessage();
						break;
					}
					userView.displayAllCustomerDetails(allCustomerDetails);
					break;
				case 6:
					log.info("6.Close Account");
					log.info("Enter the Account Number to Close the Account");
					String accountNumberToClose = mainView.promptStringInput();
					isUserIDPresentInBranch = accountController.isAccountExistsInTheBranch(accountNumberToClose,
							user.getBranchId());
					if (!isUserIDPresentInBranch) {
						log.warning("Invalid Account Number  or Account is Not present in this Branch!!");
						break;
					}
					boolean isAccountClosed = accountController.closeAccount(accountNumberToClose);
					if (isAccountClosed) {
						accountView.displayAccountClosureSuccessMessage();
					} else {
						accountView.displayAccountClosureFailureMessage();
					}
					break;
				case 7:
					log.info("7.View Transaction History of a Particular Account(Account)");
					log.info("Enter the Account Number to Get Transaction History");
					String accountNumberToGetTransaction = mainView.promptStringInput();
					isUserIDPresentInBranch = accountController
							.isAccountExistsInTheBranch(accountNumberToGetTransaction, user.getBranchId());
					if (!isUserIDPresentInBranch) {
						log.warning("Invalid Account Number  or Account is Not present in this Branch!!");
						break;
					}
					List<Transaction> transactionsHistory = transactionController
							.getCustomerTransaction(accountNumberToGetTransaction);
					if (transactionsHistory.isEmpty()) {
						transactionView.displayNoHistoryMessage();
						break;
					}
					transactionView.displayTransActionHistory(transactionsHistory);
					break;
				case 8:
					log.info("8.Get All the Customer Transaction History");
					List<Transaction> allTransactionHistory = transactionController
							.getAllCustomerTransaction(user.getBranchId());
					if (allTransactionHistory.isEmpty()) {
						transactionView.displayNoHistoryMessage();
						break;
					}
					transactionView.displayTransActionHistory(allTransactionHistory);
					break;
				case 9:
					isEmployeeAlive = false;
					log.info("Exiting!");
					break;
				default:
					log.info("Invalid option! Please choose again.");
					break;
				}
			} catch (InputMismatchException e) {
				mainView.displayInputMissMatchMessage();
				mainView.promptNewLine();
				continue;
			} catch (Exception e) {
				mainView.displayExceptionMessage(e);
				mainView.promptNewLine();
				continue;
			}
		}
	}

	private void preformAdminOperation(User user) {
		boolean isAdminAlive = true;
		while (isAdminAlive) {
			try {
				log.info("Admin Operations");
				log.info("1. Add new employee");
				log.info("2. Remove employee");
				log.info("3. View Particular Employee details");
				log.info("4. View All Employees in One Branch");
				log.info("5. View All Employees From Accross All Branch");
				log.info("6. Create Customer");
				log.info("7. Create Account");
				log.info("8. Remove Customer");
				log.info("9. View Particular Customer(Account) Details");
				log.info("10.View All Account Details of One Customer in One Branch");
				log.info("11.View All Account Details of One Customer in All Branch");
				log.info("12.View All Customer Details in One Branch");
				log.info("13. View All Customer Details Accross All Branch");
				log.info("14. View Particular Customer One Branch Transaction");
				log.info("15. View Particular Customer All Branch Transaction");
				log.info("16. View Particular Branch Transaction");
				log.info("17. View All Branch Transaction");
				log.info("18. Exit");
				log.info("Enter the choice");
				int adminChoice = mainView.promptForMainMenuChoice();
				mainView.promptNewLine();
				switch (adminChoice) {
				case 1:
					log.info("1. Add new employee");
					String password = PasswordGenerator.generatePassword();
					log.info("Enter the First Name");
					String firstName = mainView.promptStringInput().trim();
					if (InputValidator.validateString(firstName)) {
						log.log(Level.WARNING, "First Name Cannot be Empty");
						continue;
					}
					log.info("Enter the Last Name");
					String lastName = mainView.promptStringInput().trim();
					log.info("Enter the Gender");
					if (InputValidator.validateString(lastName)) {
						log.log(Level.WARNING, "First Name Cannot be Empty");
						continue;
					}
					String gender = mainView.promptStringInput().trim();
					if (InputValidator.validateString(gender)) {
						log.log(Level.WARNING, "Gender Cannot be Empty");
						continue;
					}
					log.info("Enter the Email");
					String email = mainView.promptStringInput().trim();
					if (!InputValidator.validateEmail(email)) {
						log.log(Level.WARNING, "Invalid Email Address");
						continue;
					}
					log.info("Enter the Contact Number");
					String number = mainView.promptStringInput().trim();
					if (!InputValidator.validateMobileNumber(number)) {
						log.log(Level.WARNING, "Invalid Mobile Number");
						continue;
					}
					log.info("Enter the Address");
					String address = mainView.promptStringInput().trim();
					if (InputValidator.validateString(address)) {
						log.log(Level.WARNING, "Address Cannot be Empty");
						continue;
					}
					log.info("Enter the date of birth(YYYY-MM-DD)");
					String dob = mainView.promptStringInput().trim();
					if (!InputValidator.validateDateOfBirth(dob)) {
						log.log(Level.WARNING, "Invalid Date Of Birth!! Please Provide(YYYY-MM-DD)");
						continue;
					}
					log.info("Enter the Branch Id:");
					int branchId = mainView.promtForIntegerInput();
					boolean isValidBranchId = branchController.isBranchExists(branchId);
					if (!isValidBranchId) {
						branchView.displayInvalidBranchMessage();
						break;
					}
					UserType typeOfUser = UserType.EMPLOYEE;
					User newUser = new User();
					newUser.setPassword(password);
					newUser.setFirstName(firstName);
					newUser.setLastName(lastName);
					newUser.setGender(gender);
					newUser.setEmail(email);
					newUser.setContactNumber(number);
					newUser.setAddress(address);
					newUser.setDateOfBirth(dob);
					newUser.setTypeOfUser(typeOfUser.toString());
					newUser.setBranchId(branchId);
					boolean isEmployeeCreated = userController.registerNewUser(newUser);
					if (isEmployeeCreated) {
						userView.displaySuccessMessage();
					} else {
						userView.displayFailureMessage();
					}
					break;
				case 2:
					break;
				case 3:
					log.info("3. View Particular Employee details");
					log.info("Enter the Employee Id");
					int employeeId = mainView.promtForIntegerInput();
					boolean isUserIdPresent = userController.isEmployeeExists(employeeId);
					if (!isUserIdPresent) {
						log.warning("Invalid EmployeeID!!!");
						break;
					}
					User employeeDetails = userController.getEmployeeDetails(employeeId);
					if (employeeDetails == null) {
						userView.displayUserDetailsFailedMessage();
						break;
					}
					userView.displayUserProfile(employeeDetails);
					break;
				case 4:
					log.info("4. View All Employees in One Branch");
					log.info("Enter the Branch Id");
					int branchIdToGetEmployees = mainView.promtForIntegerInput();
					boolean isValidBranch = branchController.isBranchExists(branchIdToGetEmployees);
					if (!isValidBranch) {
						branchView.displayInvalidBranchMessage();
						break;
					}
					List<User> employeeList = userController.getEmployeeFromOneBranch(branchIdToGetEmployees);
					if (employeeList.isEmpty()) {
						log.info("No Employee Avaliable in this Branch");
						break;
					}
					userView.displayListOfEmployees(employeeList);
					break;
				case 5:
					log.info("5. View All Employees From Accross All Branch");
					List<User> allEmployeeList = userController.getEmployeeFromAllBranch();
					if (allEmployeeList.isEmpty()) {
						log.info("No Employee Avaliable in this Branch");
						break;
					}
					userView.displayListOfEmployees(allEmployeeList);
					break;
				case 6:
					log.info("Create Customer");
					password = PasswordGenerator.generatePassword();
					log.info("Enter the First Name");
					firstName = mainView.promptStringInput().trim();
					if (InputValidator.validateString(firstName)) {
						log.log(Level.WARNING, "First Name Cannot be Empty");
						continue;
					}
					log.info("Enter the Last Name");
					lastName = mainView.promptStringInput().trim();
					log.info("Enter the Gender");
					if (InputValidator.validateString(lastName)) {
						log.log(Level.WARNING, "First Name Cannot be Empty");
						continue;
					}
					gender = mainView.promptStringInput().trim();
					if (InputValidator.validateString(gender)) {
						log.log(Level.WARNING, "Gender Cannot be Empty");
						continue;
					}
					log.info("Enter the Email");
					email = mainView.promptStringInput().trim();
					if (!InputValidator.validateEmail(email)) {
						log.log(Level.WARNING, "Invalid Email Address");
						continue;
					}
					log.info("Enter the Contact Number");
					number = mainView.promptStringInput().trim();
					if (!InputValidator.validateMobileNumber(number)) {
						log.log(Level.WARNING, "Invalid Mobile Number");
						continue;
					}
					log.info("Enter the Address");
					address = mainView.promptStringInput().trim();
					if (InputValidator.validateString(address)) {
						log.log(Level.WARNING, "Address Cannot be Empty");
						continue;
					}
					log.info("Enter the date of birth(YYYY-MM-DD)");
					dob = mainView.promptStringInput().trim();
					if (!InputValidator.validateDateOfBirth(dob)) {
						log.log(Level.WARNING, "Invalid Date Of Birth!! Please Provide(YYYY-MM-DD)");
						continue;
					}
					log.info("Enter the PAN Number");
					String panNumber = mainView.promptStringInput();
					if (!InputValidator.validatepanNumber(panNumber)) {
						log.log(Level.WARNING, "Invalid PAN Number!! Please Provide Valid PAN Number");
						continue;
					}
					log.info("Enter the Aadhar Number");
					String aadharNumber = mainView.promptStringInput();
					if (!InputValidator.validateaadharNumber(aadharNumber)) {
						log.log(Level.WARNING, "Invalid Aadhar Number!! Please Provide Valid Aadhar Number");
						continue;
					}
					typeOfUser = UserType.CUSTOMER;
					User newCustomer = new User();
					newCustomer.setPassword(password);
					newCustomer.setFirstName(firstName);
					newCustomer.setLastName(lastName);
					newCustomer.setGender(gender);
					newCustomer.setEmail(email);
					newCustomer.setContactNumber(number);
					newCustomer.setAddress(address);
					newCustomer.setDateOfBirth(dob);
					newCustomer.setTypeOfUser(typeOfUser.toString());
					newCustomer.setPanNumber(panNumber);
					newCustomer.setAadharNumber(aadharNumber);
					boolean isUserCreated = userController.registerNewUser(newCustomer);
					if (isUserCreated) {
						userView.displaySuccessMessage();
					} else {
						userView.displayFailureMessage();
					}
					break;
				case 7:
					log.info("7. Create Account");
					log.info("Enter the userID");
					int userId = mainView.promptForUserID();
					mainView.promptNewLine();
					boolean isUserIdPresent1 = userController.isUserExists(userId);
					if (!isUserIdPresent1) {
						log.warning("Invalid UserID!!!");
						break;
					}
					log.info("Enter the Branch Id");
					branchId = mainView.promtForIntegerInput();
					isValidBranch = branchController.isBranchExists(branchId);
					if (!isValidBranch) {
						branchView.displayInvalidBranchMessage();
						break;
					}
					mainView.promptNewLine();
					log.info("Enter the Type of Account");
					String typeOfAccount = mainView.promptStringInput();
					log.info("Enter the Balance");
					double balance = mainView.promptDoubleInput();
					if (InputValidator.validateBalance(balance)) {
						mainView.displayInvalidBalanceMessage();
					}
					Account account = new Account();
					account.setUserId(userId);
					account.setBranchId(branchId);
					account.setAccountType(typeOfAccount);
					account.setBalance(balance);
					boolean isAccountCreated = accountController.createAccount(account);
					if (isAccountCreated) {
						accountView.displayAccountCreationSuccessMessage();
					} else {
						accountView.displayAccountCreationFailureMessage();
					}
					break;
				case 8:
					log.info("8.Remove Customer");
				case 9:
					log.info("9. View Particular Customer(Account) Details");
					log.info("Enter the Account Number");
					String accountNumber = mainView.promptStringInput();
					log.info("Enter the Branch Id");
					branchId = mainView.promtForIntegerInput();
					isValidBranchId = branchController.isBranchExists(branchId);
					if (!isValidBranchId) {
						branchView.displayInvalidBranchMessage();
						break;
					}
					boolean isUserIDPresentInBranch = accountController.isAccountExistsInTheBranch(accountNumber,
							branchId);
					if (!isUserIDPresentInBranch) {
						log.warning("Invalid Account Number  or Account is Not present in this Branch!!");
						break;
					}
					CustomerDetails customerDetails = userController.getCustomerDetails(accountNumber, branchId);
					if (customerDetails == null) {
						userView.displayUserDetailsFailedMessage();
						break;
					}
					userView.displayCustomerDetails(customerDetails);
					break;
				case 10:
					log.info("10.View All Account Details of One Customer in One Branch");
					log.info("Enter the userID");
					userId = mainView.promptForUserID();
					isUserIdPresent1 = userController.isUserExists(userId);
					if (!isUserIdPresent1) {
						log.warning("Invalid UserID!!!");
						break;
					}
					log.info("Enter the Branch Id");
					branchId = mainView.promtForIntegerInput();
					isValidBranchId = branchController.isBranchExists(branchId);
					if (!isValidBranchId) {
						branchView.displayInvalidBranchMessage();
						break;
					}
					List<CustomerDetails> customerDetails2 = userController
							.getAllDetailsOfOneCustomerInOneBranch(userId, branchId);
					if (customerDetails2.isEmpty()) {
						log.info("The User Doesn't Have Any Account");
						break;
					}
					userView.displayAllCustomerDetails(customerDetails2);
					break;
				case 11:
					log.info("10.View All Account Details of One Customer in All Branch");
					log.info("Enter the userID");
					userId = mainView.promptForUserID();
					isUserIdPresent1 = userController.isUserExists(userId);
					if (!isUserIdPresent1) {
						log.warning("Invalid UserID!!!");
						break;
					}
					List<CustomerDetails> customerDetails3 = userController
							.getAllDetailsOfOneCustomerInAllBranch(userId);
					if (customerDetails3.isEmpty()) {
						log.info("The User Doesn't Have Any Account");
						break;
					}
					userView.displayAllCustomerDetails(customerDetails3);
					break;
				case 12:
					log.info("10.View All Customer in One Branch");
					log.info("Enter the Branch Id");
					branchId = mainView.promtForIntegerInput();
					isValidBranch = branchController.isBranchExists(branchId);
					if (!isValidBranch) {
						branchView.displayInvalidBranchMessage();
						break;
					}
					List<CustomerDetails> allCustomerDetails = userController.getAllCustomerDetails(branchId);
					if (allCustomerDetails.isEmpty()) {
						userView.displayCustomerNotFoundMessage();
						break;
					}
					userView.displayAllCustomerDetails(allCustomerDetails);
					break;
				case 13:
					log.info("13. View All Customer Accross All Branch");
					List<CustomerDetails> customersFromAllBranch = userController.getAllCustomerFromAllBranch();
					if (customersFromAllBranch.isEmpty()) {
						userView.displayCustomerNotFoundMessage();
						break;
					}
					userView.displayAllCustomerDetails(customersFromAllBranch);
					break;
				case 14:
					log.info("14. View Particular Customer One Branch Transaction");
					break;
				case 18:
					isAdminAlive = false;
					break;
				default:
					log.info("Invalid option! Please choose again.");
					break;
				}
			} catch (InputMismatchException e) {
				mainView.displayInputMissMatchMessage();
				mainView.promptNewLine();
				continue;
			} catch (Exception e) {
				mainView.displayExceptionMessage(e);
				mainView.promptNewLine();
				continue;
			}
		}
	}

	private void logout() {
		isLoggedIn = false;
		log.info("Logged out successfully!");
	}
}
