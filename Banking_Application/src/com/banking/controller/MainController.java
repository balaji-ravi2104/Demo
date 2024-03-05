package com.banking.controller;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.banking.model.Account;
import com.banking.model.AccountStatus;
import com.banking.model.CustomerDetails;
import com.banking.model.Transaction;
import com.banking.model.User;
import com.banking.model.UserType;
import com.banking.utils.CommonUtils;
import com.banking.utils.CommonUtils.Field;
import com.banking.utils.CustomException;
import com.banking.utils.PasswordGenerator;
import com.banking.view.AccountView;
import com.banking.view.MainView;
import com.banking.view.TransactionView;
import com.banking.view.UserView;

public class MainController {
	private static final Logger log = Logger.getLogger(MainController.class.getName());

	private MainView mainView;
	private UserView userView;
	private AccountView accountView;
	private TransactionView transactionView;
	public UserController userController;
	public AccountController accountController;
	public TransactionController transactionController;
	public BranchController branchController;
	public User user;
	private boolean isAppliactionAlive;
	private boolean isLoggedIn;

	public MainController() {
		this.mainView = new MainView();
		this.userView = new UserView();
		this.accountView = new AccountView();
		this.transactionView = new TransactionView();
		this.userController = new UserController(new AccountController());
		this.accountController = new AccountController(new UserController());
		this.branchController = new BranchController();
		this.transactionController = new TransactionController();
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
				if (user != null && user.getStatus().equalsIgnoreCase(AccountStatus.INACTIVE.name())) {
					log.warning("Your Account Have Been Blocked!! Please Contact Bank!!");
					break;
				}
				if (user != null) {
					log.info("Logged in Successfully!!");
					isLoggedIn = true;
					String userType = user.getTypeOfUser();
					if (userType.equalsIgnoreCase(UserType.CUSTOMER.name())) {
						performCustomerOperations(user);
					} else if (userType.equalsIgnoreCase(UserType.EMPLOYEE.name())) {
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
					log.info("Enter the Branch Id");
					int branchId = mainView.promtForIntegerInput();
					log.info("Enter the Amount to Transfer");
					double amountToTransfer = mainView.promptDoubleInput();
					Account accountToTransfer = accountController.getAccountDetails(accountNumber, branchId);
					System.out.println(accountToTransfer);
					if (accountToTransfer == null) {
						transactionView.displayTransactionFailedMessage();
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
					List<Transaction> statement = transactionController.getStatement(selectedAccount, numberOfMonths);
					if (statement == null) {
						transactionView.displayStatementTakenFailed();
						break;
					}
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
					boolean isPasswordUpdated = userController.updatePassword(user.getUserId(), password);
					if (isPasswordUpdated) {
						userView.displayPasswordUpdatedSuccessMessage();
					} else {
						userView.displayPasswordUpdatedFailedMessage();
					}
					break;
				case 10:
					log.info("10.Switch Account");
					selectedAccount = accountSelectionOperation(user);
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

	public Account accountSelectionOperation(User user) {
		Account selectedAccount = null;
		boolean isAccountSelected = false;
		try {
			List<Account> accounts = accountController.getAccountsOfCustomerInBranch(user.getUserId());
			if (accounts.isEmpty()) {
				log.info("You don't have any accounts.");
				return selectedAccount;
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
				return selectedAccount;
			}
			while (!isAccountSelected) {
				log.info("Please choose an account to continue:");
				int selectedAccountNumber = mainView.promptForAccountNumber();
				selectedAccount = accountMap.get(selectedAccountNumber);
				if (selectedAccount == null) {
					log.info("Invalid account selected! Please enter a valid choice Or INACTIVE ACCOUNT SELECTED!!!.");
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

	private void performEmployeeOperations(User user) {
		boolean isEmployeeAlive = true;
		while (isEmployeeAlive) {
			try {
				log.info("Employee Operations");
				log.info("1.Create Customer");
				log.info("2.Create Account");
				log.info("3.Update Customer Details");
				log.info("4.View Particular Customer Details");
				log.info("5.View Particular Customer All Details Within Branch");
				log.info("6.View Transaction History For a Particular Customer(Account)");
				log.info("7.View All Transaction of A Customer In Branch");
				log.info("8.Update Password");
				log.info("9.Deposie Amount For the Customer");
				log.info("10.ACTIVATE or DE-ACTIVATE Customer Bank Account");
				log.info("11.Exit");
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
					String typeOfUser = UserType.CUSTOMER.name();
					User newUser = new User();
					newUser.setPassword(password);
					newUser.setFirstName(firstName);
					newUser.setLastName(lastName);
					newUser.setGender(gender);
					newUser.setEmail(email);
					newUser.setContactNumber(number);
					newUser.setAddress(address);
					newUser.setDateOfBirth(dob);
					newUser.setTypeOfUser(typeOfUser);
					newUser.setPanNumber(panNumber);
					newUser.setAadharNumber(aadharNumber);
					boolean isUserCreated = userController.registerNewUser(newUser);
					if (isUserCreated) {
						userView.displayUserCreationSuccessMessage();
					} else {
						userView.displayUserCreationFailureMessage();
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
					log.info("3.Update User Details");
					Map<Integer, Field> fieldMap = CommonUtils.generateFieldMap();
					mainView.displayFieldName(fieldMap);
					log.info("Enter the UserId to Update");
					int userIdToUpdate = mainView.promptForUserID();
					Map<Field, String> fieldsToUpdate = new HashMap<>();
					log.info("Enter the Number Of Field To be Updated");
					int count = mainView.promtForIntegerInput();
					log.info("Please Enter the Field Number to Update");
					for (int i = 1; i <= count; i++) {
						log.info("Enter the choice(Number) from the list");
						int choice = mainView.promtForIntegerInput();
						if (choice < 0 || choice > 10) {
							throw new CustomException(
									"The Field Selection Choice Should be greater than Zero or Less than Ten!!");
						}
						mainView.promptNewLine();
						if (choice == 10) {
							log.info("1.ACTIVE");
							log.info("2.INACTIVE");
							log.info("Enter the Value to Update");
							int subChoice = mainView.promtForIntegerInput();
							if (subChoice == 1) {
								fieldsToUpdate.put(fieldMap.get(choice), AccountStatus.ACTIVE.name());
							} else if (subChoice == 2) {
								fieldsToUpdate.put(fieldMap.get(choice), AccountStatus.INACTIVE.name());
							} else {
								throw new CustomException(
										"The Field Selection Choice Should be greater than Zero or Less than Two!!");
							}
							continue;
						}
						log.info("Enter the Value to Update");
						String value = mainView.promptStringInput();
						fieldsToUpdate.put(fieldMap.get(choice), value);
					}
					if (fieldsToUpdate.size() == count) {
						boolean isUserUpdated = userController.updateCustomer(userIdToUpdate, fieldsToUpdate);
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
					CustomerDetails customerDetails = userController.getCustomerDetails(accountNumber,
							user.getBranchId());
					if (customerDetails == null) {
						userView.displayDetailsRetervingFailedMessage();
						break;
					}
					userView.displayCustomerDetails(customerDetails);
					break;
				case 5:
					log.info("5.View All Account Details of One Customer in Branch");
					log.info("Enter the userID");
					userId = mainView.promptForUserID();
					Map<String, CustomerDetails> allDetails = userController.getCustomerDetailsInBranch(userId,
							user.getBranchId());
					if (allDetails == null) {
						log.warning("Error While Getting Customer Detail!! Try Again!!");
						break;
					}
					userView.displayCustomerDetails(allDetails);
					break;
				case 6:
					log.info("6.View Transaction History of a Particular Account");
					log.info("Enter the Account Number to Get Transaction History");
					String accountNumberToGetTransaction = mainView.promptStringInput();
					log.info("Enter the number of months to view the customer's transaction history:");
					int month = mainView.promtForIntegerInput();
					List<Transaction> transactionsHistory = transactionController
							.getCustomerTransaction(accountNumberToGetTransaction, user.getBranchId(), month);
					if (transactionsHistory == null) {
						log.warning("Transaction History Taken Failed!!!");
						break;
					}
					transactionView.displayTransActionHistory(transactionsHistory);
					break;
				case 7:
					log.info("7.View All Transaction of A Customer In Branch");
					log.info("Enter the Customer Id");
					userId = mainView.promtForIntegerInput();
					log.info("Enter the number of months to view the customer's transaction history:");
					month = mainView.promtForIntegerInput();
					Map<String, List<Transaction>> allTransactionsOfCustomer = transactionController
							.getAllTransactionsOfCustomer(userId, user.getBranchId(), month);
					if (allTransactionsOfCustomer == null) {
						log.warning("Transaction History Taken Failed!!!");
						break;
					}
					transactionView.displayAllTransActionHistory(allTransactionsOfCustomer);
					break;
				case 8:
					log.info("8.Update Password");
					log.info("Enter the Password to Change");
					password = mainView.promptStringInput();
					boolean isPasswordUpdated = userController.updatePassword(user.getUserId(), password);
					if (isPasswordUpdated) {
						userView.displayPasswordUpdatedSuccessMessage();
					} else {
						userView.displayPasswordUpdatedFailedMessage();
					}
					break;
				case 9:
					log.info("9.Deposie Amount For the Customer");
					log.info("Enter the Account Number");
					accountNumber = mainView.promptStringInput();
					log.info("Enter the Amount to Deposite");
					double amountToDeposite = mainView.promptDoubleInput();
					Account accountToDeposite = accountController.getAccountDetails(accountNumber, user.getBranchId());
					// System.out.println(accountToDeposite);
					boolean isAmountDeposited = transactionController.depositAmount(accountToDeposite,
							amountToDeposite);
					if (isAmountDeposited) {
						transactionView.displayDepositSuccessMessage();
					} else {
						transactionView.displayDepositFailedMessage();
					}
					break;
				case 10:
					log.info("10.ACTIVATE or DE-ACTIVATE Customer Bank Account");
					log.info("Enter the Account number");
					accountNumber = mainView.promptStringInput();
					log.info("Choose the Status to Update");
					log.info("1.ACTIVE");
					log.info("2.INACTIVE");
					log.info("Enter the Value to Update");
					int subChoice = mainView.promtForIntegerInput();
					String status = subChoice == 1 ? AccountStatus.ACTIVE.name() : AccountStatus.INACTIVE.name();
					boolean isAccountStatusChanged = accountController.activateDeactivateCustomerAccount(accountNumber,
							user.getBranchId(), status);
					if (isAccountStatusChanged) {
						accountView.displayAccountStatusUpdatedSuccess();
					} else {
						accountView.displayAccountStatusUpdatedFailed();
					}
					break;
				case 11:
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
				log.info("2. View Particular Employee details");
				log.info("3. View All Employees in One Branch");
				log.info("4. View All Employees From Accross All Branch");
				log.info("5. Create Customer");
				log.info("6. Create Account");
				log.info("7. ACTIVATE or DE-ACTIVATE Customer Bank Account");
				log.info("8. View Particular Customer(Account) Details");
				log.info("9. View All Account Details of One Customer in One Branch");
				log.info("10. View All Account Details of One Customer in All Branch");
				log.info("11. View Particular Customer Transaction by Account");
				log.info("12. View Particular Customers One Branch Transaction");
				log.info("13. Update Password");
				log.info("14. Update Customer Details");
				log.info("15. Exit");
				log.info("Enter the choice");
				int adminChoice = mainView.promptForMainMenuChoice();
				mainView.promptNewLine();
				switch (adminChoice) {
				case 1:
					log.info("1. Add new employee");
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
					log.info("Enter the Branch Id:");
					int branchId = mainView.promtForIntegerInput();
					String typeOfUser = UserType.EMPLOYEE.name();
					User newUser = new User();
					newUser.setPassword(password);
					newUser.setFirstName(firstName);
					newUser.setLastName(lastName);
					newUser.setGender(gender);
					newUser.setEmail(email);
					newUser.setContactNumber(number);
					newUser.setAddress(address);
					newUser.setDateOfBirth(dob);
					newUser.setTypeOfUser(typeOfUser);
					newUser.setBranchId(branchId);
					boolean isEmployeeCreated = userController.registerNewUser(newUser);
					if (isEmployeeCreated) {
						userView.displayUserCreationSuccessMessage();
					} else {
						userView.displayUserCreationFailureMessage();
					}
					break;
				case 2:
					log.info("2. View Particular Employee details");
					log.info("Enter the Employee Id");
					int employeeId = mainView.promtForIntegerInput();
					User employeeDetails = userController.getEmployeeDetails(employeeId);
					if (employeeDetails == null) {
						userView.displayDetailsRetervingFailedMessage();
						break;
					}
					userView.displayUserProfile(employeeDetails);
					break;
				case 3:
					log.info("3. View All Employees in One Branch");
					log.info("Enter the Branch Id");
					int branchIdToGetEmployees = mainView.promtForIntegerInput();
					Map<Integer, User> employeesList = userController.getEmployeeFromOneBranch(branchIdToGetEmployees);
					if (employeesList == null) {
						userView.displayDetailsRetervingFailedMessage();
						break;
					}
					userView.displayListOfEmployees(employeesList);
					break;
				case 4:
					log.info("4. View All Employees From Accross All Branch");
					Map<Integer, Map<Integer, User>> allEmployeesList = userController.getEmployeeFromAllBranch();
					if (allEmployeesList == null) {
						userView.displayDetailsRetervingFailedMessage();
						break;
					}
					userView.displayEmployeesByBranch(allEmployeesList);
					break;
				case 5:
					log.info("5.Create Customer");
					password = PasswordGenerator.generatePassword();
					log.info("Enter the First Name");
					firstName = mainView.promptStringInput();
					log.info("Enter the Last Name");
					lastName = mainView.promptStringInput();
					log.info("Enter the Gender");
					gender = mainView.promptStringInput();
					log.info("Enter the Email");
					email = mainView.promptStringInput().trim();
					log.info("Enter the Contact Number");
					number = mainView.promptStringInput().trim();
					log.info("Enter the Address");
					address = mainView.promptStringInput().trim();
					log.info("Enter the date of birth(YYYY-MM-DD)");
					dob = mainView.promptStringInput().trim();
					log.info("Enter the PAN Number");
					String panNumber = mainView.promptStringInput();
					log.info("Enter the Aadhar Number");
					String aadharNumber = mainView.promptStringInput();
					typeOfUser = UserType.CUSTOMER.name();
					newUser = new User();
					newUser.setPassword(password);
					newUser.setFirstName(firstName);
					newUser.setLastName(lastName);
					newUser.setGender(gender);
					newUser.setEmail(email);
					newUser.setContactNumber(number);
					newUser.setAddress(address);
					newUser.setDateOfBirth(dob);
					newUser.setTypeOfUser(typeOfUser);
					newUser.setPanNumber(panNumber);
					newUser.setAadharNumber(aadharNumber);
					boolean isUserCreated = userController.registerNewUser(newUser);
					if (isUserCreated) {
						userView.displayUserCreationSuccessMessage();
					} else {
						userView.displayUserCreationFailureMessage();
					}
					break;
				case 6:
					log.info("6. Create Account");
					log.info("Enter the userID");
					int userId = mainView.promptForUserID();
					mainView.promptNewLine();
					branchId = mainView.promtForIntegerInput();
					log.info("Enter the Type of Account");
					String typeOfAccount = mainView.promptStringInput();
					log.info("Enter the Balance");
					double balance = mainView.promptDoubleInput();
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
				case 7:
					log.info("Enter the Account number");
					String accountNumber = mainView.promptStringInput();
					log.info("Enter the Branch Id");
					branchId = mainView.promtForIntegerInput();
					log.info("Choose the Status to Update");
					log.info("1.ACTIVE");
					log.info("2.INACTIVE");
					log.info("Enter the Value to Update");
					int subChoice = mainView.promtForIntegerInput();
					String status = subChoice == 1 ? AccountStatus.ACTIVE.name() : AccountStatus.INACTIVE.name();
					boolean isAccountStatusChanged = accountController.activateDeactivateCustomerAccount(accountNumber,
							branchId, status);
					if (isAccountStatusChanged) {
						accountView.displayAccountStatusUpdatedSuccess();
					} else {
						accountView.displayAccountStatusUpdatedFailed();
					}
					break;
				case 8:
					log.info("8. View Particular Customer(Account) Details");
					log.info("Enter the Account Number");
					accountNumber = mainView.promptStringInput();
					log.info("Enter the Branch Id");
					branchId = mainView.promtForIntegerInput();
					CustomerDetails customerDetails = userController.getCustomerDetails(accountNumber,
							user.getBranchId());
					if (customerDetails == null) {
						userView.displayDetailsRetervingFailedMessage();
						break;
					}
					userView.displayCustomerDetails(customerDetails);
					break;
				case 9:
					log.info("9.View All Account Details of One Customer in One Branch");
					log.info("Enter the userID");
					userId = mainView.promptForUserID();
					log.info("Enter the Branch Id");
					branchId = mainView.promtForIntegerInput();
					Map<String, CustomerDetails> allDetails = userController.getCustomerDetailsInBranch(userId,
							branchId);
					if (allDetails == null) {
						log.warning("Error While Getting Customer Detail!! Try Again!!");
						break;
					}
					if (allDetails.isEmpty()) {
						log.info("The User Doesn't Have Any Account");
						break;
					}
					userView.displayCustomerDetails(allDetails);
					break;
				case 10:
					log.info("10.View All Account Details of One Customer in All Branch");
					log.info("Enter the userID");
					userId = mainView.promptForUserID();
					Map<Integer, List<CustomerDetails>> allDetail = userController
							.getCustomerDetailsInAllBranch(userId);
					if (allDetail == null) {
						log.warning("Error While Getting Customer Detail!! Try Again!!");
						break;
					}
					userView.displayCustomerDetailsByBranch(allDetail);
					break;
				case 11:
					log.info("11. View Particular Customer Transaction by Account");
					log.info("Enter the Account Number to Get Transaction History");
					String accountNumberToGetTransaction = mainView.promptStringInput();
					log.info("Enter the Branch Id");
					branchId = mainView.promtForIntegerInput();
					log.info("Enter the number of months to view the customer's transaction history:");
					int month = mainView.promtForIntegerInput();
					List<Transaction> transactionsHistory = transactionController
							.getCustomerTransaction(accountNumberToGetTransaction, branchId, month);
					if (transactionsHistory == null) {
						log.warning("Transaction History Taken Failed!!!");
						break;
					}
					transactionView.displayTransActionHistory(transactionsHistory);
					break;
				case 12:
					log.info("12. View Particular Customers One Branch Transaction");
					log.info("Enter the Customer Id");
					userId = mainView.promtForIntegerInput();
					log.info("Enter the Branch Id");
					branchId = mainView.promtForIntegerInput();
					log.info("Enter the number of months to view the customer's transaction history:");
					month = mainView.promtForIntegerInput();
					Map<String, List<Transaction>> allTransactionsOfCustomer = transactionController
							.getAllTransactionsOfCustomer(userId, branchId, month);
					if (allTransactionsOfCustomer == null) {
						log.warning("Transaction History Taken Failed!!!");
						break;
					}
					transactionView.displayAllTransActionHistory(allTransactionsOfCustomer);
					break;
				case 13:
					log.info("13. Update Password");
					log.info("Enter the Password to Change");
					password = mainView.promptStringInput();
					boolean isPasswordUpdated = userController.updatePassword(user.getUserId(), password);
					if (isPasswordUpdated) {
						userView.displayPasswordUpdatedSuccessMessage();
					} else {
						userView.displayPasswordUpdatedFailedMessage();
					}
					break;
				case 14:
					log.info("14. Update Customer Details");
					Map<Integer, Field> fieldMap = CommonUtils.generateFieldMap();
					mainView.displayFieldName(fieldMap);
					log.info("Enter the UserId to Update");
					int userIdToUpdate = mainView.promptForUserID();
					Map<Field, String> fieldsToUpdate = new HashMap<>();
					log.info("Enter the Number Of Field To be Updated");
					int count = mainView.promtForIntegerInput();
					log.info("Please Enter the Field Number to Update");
					for (int i = 1; i <= count; i++) {
						log.info("Enter the choice(Number) from the list");
						int choice = mainView.promtForIntegerInput();
						if (choice < 0 || choice > 10) {
							throw new CustomException(
									"The Field Selection Choice Should be greater than Zero or Less than Ten!!");
						}
						mainView.promptNewLine();
						if (choice == 10) {
							log.info("1.ACTIVE");
							log.info("2.INACTIVE");
							log.info("Enter the Value to Update");
							subChoice = mainView.promtForIntegerInput();
							if (subChoice == 1) {
								fieldsToUpdate.put(fieldMap.get(choice), AccountStatus.ACTIVE.name());
							} else if (subChoice == 2) {
								fieldsToUpdate.put(fieldMap.get(choice), AccountStatus.INACTIVE.name());
							} else {
								throw new CustomException(
										"The Field Selection Choice Should be greater than Zero or Less than Two!!");
							}
							continue;
						}
						log.info("Enter the Value to Update");
						String value = mainView.promptStringInput();
						fieldsToUpdate.put(fieldMap.get(choice), value);
					}
					if (fieldsToUpdate.size() == count) {
						boolean isUserUpdated = userController.updateCustomer(userIdToUpdate, fieldsToUpdate);
						if (isUserUpdated) {
							userView.displayUpdateSuccessMessage();
						} else {
							userView.displayUpdateFailedMessage();
						}
					}
					break;
				case 15:
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
