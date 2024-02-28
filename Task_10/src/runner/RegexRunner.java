package runner;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;

import exception.CustomException;
import regex.operations.RegexHelper;

public class RegexRunner {
	private static final Logger logger = Logger.getLogger(RegexRunner.class.getName());

	static RegexHelper regexHelper = new RegexHelper();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int choice;
		boolean isProgramAlive = true;
		try {
			while (isProgramAlive) {
				try {
					logger.log(Level.INFO, "Enter your choice");
					choice = sc.nextInt();
					if (choice < 0) {
						throw new IllegalArgumentException("Choice must be greater than Zero!!");
					}
				} catch (InputMismatchException e) {
					logger.log(Level.WARNING,
							"Please enter the correct choice!!! Avoid entering the Characters for an Integer!!");
					sc.nextLine();
					continue;
				} catch (IllegalArgumentException e) {
					logger.log(Level.WARNING, "Illegal Arrgument Exception Occured");
					sc.nextLine();
					continue;
				}
				sc.nextLine();
				switch (choice) {
				case 1:
					logger.info("1.Phone Number Validation");
					try {
						logger.info("1.Enter the Mobile Number");
						String mobileNumber = sc.nextLine();
						boolean isValid = regexHelper.validateMobileNumber(mobileNumber);
						if (isValid) {
							logger.log(Level.INFO, String.format("Given %s is a Valid Mobile Number", mobileNumber));
						} else {
							logger.log(Level.INFO,
									String.format("Given %s is Not a Valid Mobile Number", mobileNumber));
						}
					} catch (PatternSyntaxException e) {
						logger.log(Level.WARNING, "Please Use the Valid Pattern to validate the String", e);
					} catch (CustomException e) {
						logger.log(Level.WARNING, "An Exception Occured", e);
					}
					break;
				case 2:
					logger.info("2.Alpha numeric characters Validation");
					try {
						logger.info("1.Enter the Alpha numeric characters");
						String alphaNumeric = sc.nextLine();
						boolean isValid = regexHelper.validatealphaNumericCharacter(alphaNumeric);
						if (isValid) {
							logger.log(Level.INFO,
									String.format("Given %s is a Valid Alpha numeric character", alphaNumeric));
						} else {
							logger.log(Level.INFO,
									String.format("Given %s is Not a Alpha numeric character", alphaNumeric));
						}
					} catch (PatternSyntaxException e) {
						logger.log(Level.WARNING, "Please Use the Valid Pattern to validate the String", e);
					} catch (CustomException e) {
						logger.log(Level.WARNING, "An Exception Occured", e);
					}
					break;
				case 3:
					logger.info("3.Accept two strings viz. a given string & a matching string.");
					logger.info("3.1 Check the given string starts with the matching string");
					logger.info("3.2 Check the given string contains the matching string");
					logger.info("3.3 Check the given string ends with the matching string");
					logger.info("3.4 Check the given string is an exact match");
					String givenString, matchString;
					try {
						logger.info("Enter the Matcher String");
						matchString = sc.nextLine();
						logger.info("Enter the String");
						givenString = sc.nextLine();
						boolean isStartsWith = regexHelper.toFindStartsWith(givenString, matchString);
						boolean isContains = regexHelper.isContainsString(givenString, matchString);
						boolean isEndsWith = regexHelper.toFindEndsWith(givenString, matchString);
						boolean isSame = regexHelper.isEquals(givenString, matchString);
						logger.info(isStartsWith ? "Yes" : "No");
						logger.info(isContains ? "Yes" : "No");
						logger.info(isEndsWith ? "Yes" : "No");
						logger.info(isSame ? "Yes" : "No");
					} catch (PatternSyntaxException e) {
						logger.log(Level.WARNING, "Please Use the Valid Pattern to validate the String", e);
					} catch (CustomException e) {
						logger.log(Level.WARNING, "An Exception Occured", e);
					}
					break;
				case 4:
					logger.info("4.Matching String irrespective of the case.");
					try {
						logger.info("Enter the count of strings");
						int count = sc.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Choice must be greater than Zero!!");
						}
						sc.nextLine();
						logger.info("Enter the Strings");
						String[] stringArray = new String[count];
						for (int i = 0; i < count; i++) {
							stringArray[i] = sc.nextLine();
						}
						logger.info("Enter the the Matcher string");
						String matcher = sc.nextLine();
						List<String> ansList = regexHelper.equalsIgnoreCase(stringArray, matcher);
						logger.info(ansList.toString());
					} catch (PatternSyntaxException e) {
						logger.log(Level.WARNING, "Please Use the Valid Pattern to validate the String", e);
					} catch (IllegalArgumentException e) {
						logger.log(Level.WARNING, "Illegal Arrgument Exception Occured");
					} catch (CustomException e) {
						logger.log(Level.WARNING, "An Exception Occured", e);
					}
					break;
				case 5:
					logger.info("5.Email Validation");
					try {
						logger.info("Enter the Email");
						String email = sc.nextLine();
						boolean isEmailValid = regexHelper.validateEmail(email);
						logger.info(isEmailValid ? "Yes, Valid Email" : "No, Invalid Email");
					} catch (PatternSyntaxException e) {
						logger.log(Level.WARNING, "Please Use the Valid Pattern to validate the String", e);
					} catch (CustomException e) {
						logger.log(Level.WARNING, "An Exception Occured", e);
					}
					break;
				case 6:
					logger.info("6. String with minimum and maximum length");
					try {
						logger.info("Enter the count of strings");
						int count = sc.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Choice must be greater than Zero!!");
						}
						sc.nextLine();
						logger.info("Enter the Strings");
						String[] stringArray = new String[count];
						for (int i = 0; i < count; i++) {
							stringArray[i] = sc.nextLine();
						}
						logger.info("Enter the Minimum Length");
						int mininumLength = sc.nextInt();
						logger.info("Enter the Maximum Lenght");
						int maximumLength = sc.nextInt();
						List<String> ansList = regexHelper.stringWithMinMax(stringArray, mininumLength, maximumLength);
						logger.info(ansList.toString());
					} catch (PatternSyntaxException e) {
						logger.log(Level.WARNING, "Please Use the Valid Pattern to validate the String", e);
					} catch (IllegalArgumentException e) {
						logger.log(Level.WARNING, "Illegal Arrgument Exception Occured");
					} catch (CustomException e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 7:
					logger.info("7.Generate Map using Two Lists");
					try {
						logger.info("Enter the count of list one");
						int count = sc.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Choice must be greater than Zero!!");
						}
						sc.nextLine();
						List<String> list1 = generateList(count);
						logger.info("Enter the count of list two");
						count = sc.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Choice must be greater than Zero!!");
						}
						sc.nextLine();
						List<String> list2 = generateList(count);
						Map<String, Integer> ansMap = regexHelper.getMapUsingList(list1, list2);
						logger.info(ansMap.toString());
					} catch (PatternSyntaxException e) {
						logger.log(Level.WARNING, "Please Use the Valid Pattern to validate the String", e);
					} catch (IllegalArgumentException e) {
						logger.log(Level.WARNING, "Illegal Arrgument Exception Occured");
					} catch (CustomException e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 8:
					logger.info("8.HTML tag Extraction");
					try {
						logger.info("Enter the string with HTML tags");
						String htmlInput = sc.nextLine();
						List<String> ansList = regexHelper.extractHTMLTags(htmlInput);
						logger.info(ansList.toString());
					} catch (PatternSyntaxException e) {
						logger.log(Level.WARNING, "Please Use the Valid Pattern to validate the String", e);
					} catch (CustomException e) {
						logger.log(Level.WARNING, "An Exception Occured", e);
					}
					break;
				case 9:
					logger.info("9.Password Validation");
					try {
						logger.info("Enter the Password");
						String password = sc.nextLine();
						boolean isValidPassword = regexHelper.validatePassword(password);
						logger.info(isValidPassword ? "Yes, Valid Password" : "No, Invalid Password");
					} catch (PatternSyntaxException e) {
						logger.log(Level.WARNING, "Please Use the Valid Pattern to validate the String", e);
					} catch (CustomException e) {
						logger.log(Level.WARNING, "An Exception Occured", e);
					}
					break;
				case 0:
					isProgramAlive = false;
					logger.info("Exiting...");
					break;
				default:
					logger.info("Please Enter the valid Choice");
				}
			}
		} catch (

		Exception e) {
			logger.log(Level.WARNING, "An Exception Occured", e);
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}

	private static List<String> generateList(int count) {
		List<String> list = RegexHelper.getNewArrayList();
		logger.info("Enter the Strings");
		for (int i = 0; i < count; i++) {
			list.add(sc.nextLine());
		}
		return list;
	}

}
