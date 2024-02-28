package runner;

import arraylistoperations.ArrayListHelper;
import customclass.CustomObject;
import exception.CustomException;
import message.ConstantMessage;
import utils.UtilMethods;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ArrayListRunner {
	private static final Logger logger = Logger.getLogger(ArrayListRunner.class.getName());
	static ArrayListHelper alh = new ArrayListHelper();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <E> void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int choice, count;
		try {
			while (true) {
				printDetails();
				try {
					logger.log(Level.INFO, "Enter your choice");
					choice = scanner.nextInt();
					if (choice < 0) {
						throw new IllegalArgumentException("Choice must be greater than Zero!!");
					}
				} catch (InputMismatchException e) {
					printExceptionDetails(e);
					scanner.nextLine();
					continue;
				} catch (IllegalArgumentException e) {
					printExceptionDetails(e);
					scanner.nextLine();
					continue;
				}
				scanner.nextLine();
				switch (choice) {
				case 1:
					logger.log(Level.INFO, "1.Creating an ArrayList");
					try {
						List<E> list = alh.getNewArrayList();
						printListDetails(list);
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 2:
					logger.log(Level.INFO, "2.Add Five String and Print List and Size");
					try {
						List<String> stringList = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of Strings");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						scanner.nextLine();
						String[] stringArray1 = new String[count];
						logger.log(Level.INFO, "Enter the Strings");
						for (int i = 0; i < count; i++) {
							stringArray1[i] = scanner.nextLine();
						}
						alh.appendData(stringList, stringArray1);
						printListDetails(stringList);
					} catch (InputMismatchException e) {
						printExceptionDetails(e);
						scanner.nextLine();
					} catch (IllegalArgumentException e) {
						printExceptionDetails(e);
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 3:
					logger.log(Level.INFO, "3.Add Five Integer and Print List and Size");
					try {
						List<Integer> stringList = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of Integers");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						Integer[] intArray = new Integer[count];
						logger.log(Level.INFO, "Enter the Integers");
						for (int i = 0; i < count; i++) {
							intArray[i] = scanner.nextInt();
						}
						alh.appendData(stringList, intArray);
						printListDetails(stringList);
					} catch (InputMismatchException e) {
						printExceptionDetails(e);
						scanner.nextLine();
					} catch (IllegalArgumentException e) {
						printExceptionDetails(e);
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 4:
					logger.log(Level.INFO, "4.Add Custom Objects and Print List and Size");
					try {
						List<CustomObject<E>> customArrayList = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of data for Custom Class");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						scanner.nextLine();
						CustomObject[] customObjectsArray = new CustomObject[count];
						logger.log(Level.INFO, "Enter String data for the Custom Class");
						for (int i = 0; i < count; i++) {
							customObjectsArray[i] = new CustomObject<Object>(scanner.nextLine());
						}
						alh.appendData(customArrayList, customObjectsArray);
						printListDetails(customArrayList);
					} catch (InputMismatchException e) {
						printExceptionDetails(e);
						scanner.nextLine();
					} catch (IllegalArgumentException e) {
						printExceptionDetails(e);
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 5:
					System.out
							.println("5.Add Two Integers and Three Strings and Custom Objects and Print List and Size");
					try {
						List<Object> objectList = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of the Integers");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						Integer[] intArray1 = new Integer[count];
						logger.log(Level.INFO, "Enter the Integers");
						for (int i = 0; i < count; i++) {
							intArray1[i] = scanner.nextInt();
						}
						logger.log(Level.INFO, "Enter the count of the strings");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						String[] stringArray = new String[count];
						scanner.nextLine();
						logger.log(Level.INFO, "Enter the Strings");
						for (int i = 0; i < count; i++) {
							stringArray[i] = scanner.nextLine();
						}
						logger.log(Level.INFO, "Enter the count of Strings for Custom Class");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						CustomObject[] customObjectsArray = new CustomObject[count];
						logger.log(Level.INFO, "Enter the Strings for the Custom Class");
						scanner.nextLine();
						for (int i = 0; i < count; i++) {
							customObjectsArray[i] = new CustomObject<Object>(scanner.nextLine());
						}
						alh.appendData(objectList, intArray1);
						alh.appendData(objectList, stringArray);
						alh.appendData(objectList, customObjectsArray);
						printListDetails(objectList);
					} catch (IllegalArgumentException e) {
						printExceptionDetails(e);
					} catch (InputMismatchException e) {
						printExceptionDetails(e);
						scanner.nextLine();
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 6:
					logger.log(Level.INFO, "6.Add Strings and Find Particular String and Print List and Size");
					try {
						List<String> stringList = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of Strings");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						scanner.nextLine();
						String[] stringArray = new String[count];
						logger.log(Level.INFO, "Enter the Strings");
						for (int i = 0; i < count; i++) {
							stringArray[i] = scanner.nextLine();
						}
						alh.appendData(stringList, stringArray);
						logger.log(Level.INFO, "Enter the String to find the index");
						String string = scanner.nextLine();
						int index = alh.findElementIndex(stringList, string);
						if (index != -1) {
							logger.log(Level.INFO,
									String.format("The %s is present in the list at the index %d", string, index));
						} else {
							logger.log(Level.INFO, String.format("The %s not present in te list", string));
						}

						printListDetails(stringList);
					} catch (IllegalArgumentException e) {
						printExceptionDetails(e);
					} catch (InputMismatchException e) {
						printExceptionDetails(e);
						scanner.nextLine();
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 7:
					logger.log(Level.INFO, "7.Print list using Iterator ans For loop");
					try {
						List<String> stringList = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of Strings");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						scanner.nextLine();
						String[] stringArray = new String[count];
						logger.log(Level.INFO, "Enter the Strings");
						for (int i = 0; i < count; i++) {
							stringArray[i] = scanner.nextLine();
						}
						alh.appendData(stringList, stringArray);
						printIterator(stringList);
						printLoop(stringList);
					} catch (IllegalArgumentException e) {
						printExceptionDetails(e);
					} catch (InputMismatchException e) {
						printExceptionDetails(e);
						scanner.nextLine();
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 8:
					logger.log(Level.INFO, "8.Add Strings and Find the string at particular Index");
					try {
						List<String> stringList = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of Strings");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						scanner.nextLine();
						String[] stringArray = new String[count];
						logger.log(Level.INFO, "Enter the Strings");
						for (int i = 0; i < count; i++) {
							stringArray[i] = scanner.nextLine();
						}
						logger.log(Level.INFO, "Enter the index to find the string");
						int index = scanner.nextInt();
						alh.appendData(stringList, stringArray);
						String result = (String) alh.findElement(stringList, index);
						logger.log(Level.INFO, String.format("The String in the given index is %s", result));

						printListDetails(stringList);
					} catch (IllegalArgumentException e) {
						printExceptionDetails(e);
					} catch (InputMismatchException e) {
						printExceptionDetails(e);
						scanner.nextLine();
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 9:
					logger.log(Level.INFO, "9.Find the duplicate string first and last index");
					try {
						List<String> stringList = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of Strings");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						scanner.nextLine();
						String[] stringArray = new String[count];
						logger.log(Level.INFO, "Enter the Strings");
						for (int i = 0; i < count; i++) {
							stringArray[i] = scanner.nextLine();
						}
						alh.appendData(stringList, stringArray);
						int[] indexes = alh.findDuplicateIndexes(stringList);
						if (indexes[0] != indexes[1]) {
							logger.log(Level.INFO, String.format("The Duplicate Strings are at the index are %d and %d",
									indexes[0], indexes[1]));

						} else {
							logger.log(Level.INFO, "There is no duplicate strings are there in the list");
						}
					} catch (IllegalArgumentException e) {
						printExceptionDetails(e);
					} catch (InputMismatchException e) {
						printExceptionDetails(e);
						scanner.nextLine();
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 10:
					logger.log(Level.INFO, "10.Add new String at particular index");
					try {
						List<String> stringList = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of Strings");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						scanner.nextLine();
						String[] stringArray = new String[count];
						logger.log(Level.INFO, "Enter the Strings");
						for (int i = 0; i < count; i++) {
							stringArray[i] = scanner.nextLine();
						}
						alh.appendData(stringList, stringArray);
						logger.log(Level.INFO, "Enter the index to find the string");
						int index = scanner.nextInt();
						scanner.nextLine();
						logger.log(Level.INFO, "Enter the string to at the index");
						String string = scanner.nextLine();
						alh.addElementAtIndex(stringList, index, string);
						printListDetails(stringList);
					} catch (IllegalArgumentException e) {
						printExceptionDetails(e);
					} catch (InputMismatchException e) {
						printExceptionDetails(e);
						scanner.nextLine();
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 11:
					logger.log(Level.INFO, "11.Duplicate list from the original list");
					try {
						List<String> stringList = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of Strings");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						scanner.nextLine();
						String[] stringArray = new String[count];
						logger.log(Level.INFO, "Enter the Strings");
						for (int i = 0; i < count; i++) {
							stringArray[i] = scanner.nextLine();
						}
						alh.appendData(stringList, stringArray);
						logger.log(Level.INFO, "Enter the start index");
						int startIndex = scanner.nextInt();
						logger.log(Level.INFO, "Enter the last index");
						int lastIndex = scanner.nextInt();
						List<String> duplicateList = alh.getSubList(stringList, startIndex, lastIndex);
						printListDetails(stringList);
						logger.log(Level.INFO, "Duplicate list is " + duplicateList);
					} catch (InputMismatchException e) {
						printExceptionDetails(e);
						scanner.nextLine();
					} catch (IllegalArgumentException e) {
						printExceptionDetails(e);
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 12:
					logger.log(Level.INFO, "12.Create a third list using first and second list");
					try {
						List<String> stringList1 = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of Strings for First List");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						scanner.nextLine();
						String[] stringArray1 = new String[count];
						logger.log(Level.INFO, "Enter the Strings");
						for (int i = 0; i < count; i++) {
							stringArray1[i] = scanner.nextLine();
						}
						alh.appendData(stringList1, stringArray1);
						List<String> stringList2 = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of Strings for Second List");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						scanner.nextLine();
						String[] stringArray2 = new String[count];
						logger.log(Level.INFO, "Enter the Strings");
						for (int i = 0; i < count; i++) {
							stringArray2[i] = scanner.nextLine();
						}
						alh.appendData(stringList2, stringArray2);
						List<String> resultList = alh.combineLists(stringList1, stringList2);
						printListDetails(resultList);
					} catch (IllegalArgumentException e) {
						printExceptionDetails(e);
					} catch (InputMismatchException e) {
						printExceptionDetails(e);
						scanner.nextLine();
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 13:
					logger.log(Level.INFO,
							"13.Create a third list using first and second list and second list ahead of");
					try {
						List<String> stringList1 = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of Strings for First List");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						scanner.nextLine();
						String[] stringArray1 = new String[count];
						logger.log(Level.INFO, "Enter the Strings");
						for (int i = 0; i < count; i++) {
							stringArray1[i] = scanner.nextLine();
						}
						alh.appendData(stringList1, stringArray1);
						List<String> stringList2 = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of Strings for Second List");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						scanner.nextLine();
						String[] stringArray2 = new String[count];
						logger.log(Level.INFO, "Enter the Strings");
						for (int i = 0; i < count; i++) {
							stringArray2[i] = scanner.nextLine();
						}
						alh.appendData(stringList2, stringArray2);
						List<String> resultList = alh.combineListsSecondAhead(stringList1, stringList2);
						printListDetails(resultList);
					} catch (IllegalArgumentException e) {
						printExceptionDetails(e);
					} catch (InputMismatchException e) {
						printExceptionDetails(e);
						scanner.nextLine();
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 14:
					logger.log(Level.INFO, "14.Delete value from the particular Index");
					try {
						List<Float> floatList = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of the decimal values");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						Float[] floatArray = new Float[count];
						logger.log(Level.INFO, "Enter the decimal values");
						for (int i = 0; i < count; i++) {
							floatArray[i] = scanner.nextFloat();
						}
						alh.appendData(floatList, floatArray);
						logger.log(Level.INFO, "Enter the index to remove the value");
						int index = scanner.nextInt();
						alh.removeElement(floatList, index);
						printListDetails(floatList);
					} catch (IllegalArgumentException e) {
						printExceptionDetails(e);
					} catch (InputMismatchException e) {
						printExceptionDetails(e);
						scanner.nextLine();
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 15:
					logger.log(Level.INFO, "15.Delete the first list values from the second list");
					try {
						List<String> stringList1 = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of Strings for First List");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						scanner.nextLine();
						String[] stringArray1 = new String[count];
						logger.log(Level.INFO, "Enter the Strings");
						for (int i = 0; i < count; i++) {
							stringArray1[i] = scanner.nextLine();
						}
						alh.appendData(stringList1, stringArray1);
						List<String> stringList2 = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of Strings for Second List");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						scanner.nextLine();
						String[] stringArray2 = new String[count];
						logger.log(Level.INFO, "Enter the Strings");
						for (int i = 0; i < count; i++) {
							stringArray2[i] = scanner.nextLine();
						}
						alh.appendData(stringList2, stringArray2);
						alh.deleteFirstListFromSecond(stringList1, stringList2);
						printListDetails(stringList1);
					} catch (IllegalArgumentException e) {
						printExceptionDetails(e);
					} catch (InputMismatchException e) {
						printExceptionDetails(e);
						scanner.nextLine();
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 16:
					logger.log(Level.INFO, "16.Retain the first list values from the second list");
					try {
						List<String> stringList1 = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of Strings for First List");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						scanner.nextLine();
						String[] stringArray1 = new String[count];
						logger.log(Level.INFO, "Enter the Strings");
						for (int i = 0; i < count; i++) {
							stringArray1[i] = scanner.nextLine();
						}
						alh.appendData(stringList1, stringArray1);
						List<String> stringList2 = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of Strings for Second List");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						scanner.nextLine();
						String[] stringArray2 = new String[count];
						logger.log(Level.INFO, "Enter the Strings");
						for (int i = 0; i < count; i++) {
							stringArray2[i] = scanner.nextLine();
						}
						alh.appendData(stringList2, stringArray2);
						alh.retainFirstListFromSecond(stringList1, stringList2);
						printListDetails(stringList1);
					} catch (IllegalArgumentException e) {
						printExceptionDetails(e);
					} catch (InputMismatchException e) {
						printExceptionDetails(e);
						scanner.nextLine();
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 17:
					logger.log(Level.INFO, "17.Add long values and delete all values and print list and size");
					try {
						List<Long> longList = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of Strings");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						scanner.nextLine();
						Long[] longArray = new Long[count];
						logger.log(Level.INFO, "Enter the Long values");
						for (int i = 0; i < count; i++) {
							longArray[i] = scanner.nextLong();
						}
						alh.appendData(longList, longArray);
						alh.clearList(longList);
						printListDetails(longList);
					} catch (InputMismatchException e) {
						printExceptionDetails(e);
						scanner.nextLine();
					} catch (IllegalArgumentException e) {
						printExceptionDetails(e);
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 18:
					logger.log(Level.INFO, "18.Add Strings and find the string is already present or not");
					try {
						List<String> stringList = alh.getNewArrayList();
						logger.log(Level.INFO, "Enter the count of Strings");
						count = scanner.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count cannot be negative");
						}
						scanner.nextLine();
						String[] stringArray = new String[count];
						logger.log(Level.INFO, "Enter the Strings");
						for (int i = 0; i < count; i++) {
							stringArray[i] = scanner.nextLine();
						}
						alh.appendData(stringList, stringArray);
						logger.log(Level.INFO, "Enter the string to check");
						String check = scanner.nextLine();
						boolean isPresent = alh.checkIfElementPresent(stringList, check);
						if (isPresent) {
							logger.log(Level.INFO, String.format("Yes,The %s is present in the list", check));
						} else {
							logger.log(Level.INFO, String.format("No,The %s is present in the list", check));
						}

						printListDetails(stringList);
					} catch (InputMismatchException e) {
						printExceptionDetails(e);
						scanner.nextLine();
					} catch (IllegalArgumentException e) {
						printExceptionDetails(e);
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 0:
					logger.log(Level.INFO, "Exiting...");
					System.exit(0);
				default:
					logger.log(Level.INFO, "Please enter the valid choice");
				}
			}
		} catch (Exception e) {
			printExceptionDetails(e);
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

	}

	private static void printDetails() {
		logger.log(Level.INFO,
				"--------------------------------------------------------------------------------------");
		logger.log(Level.INFO, "1.Creating an ArrayList");
		logger.log(Level.INFO, "2.Add Five String and Print List and Size");
		logger.log(Level.INFO, "3.Add Five Integer and Print List and Size");
		logger.log(Level.INFO, "4.Add Custom Objects and Print List and Size");
		logger.log(Level.INFO, "5.Add Two Integers and Three Strings and Custom Objects and Print List and Size");
		logger.log(Level.INFO, "6.Add Strings and Find Particular String and Print List and Size");
		logger.log(Level.INFO, "7.Print list using Iterator ans For loop");
		logger.log(Level.INFO, "8.Add Strings and Find the string at particular Index");
		logger.log(Level.INFO, "9.Find the duplicate string first and last index");
		logger.log(Level.INFO, "10.Add new String at particular index");
		logger.log(Level.INFO, "11.Duplicate list from the original list");
		logger.log(Level.INFO, "12.Create a third list using first and second list");
		logger.log(Level.INFO, "13.Create a third list using first and second list and second list ahead of");
		logger.log(Level.INFO, "14.Delete value from the particular Index");
		logger.log(Level.INFO, "15.Delete the first list values from the second list");
		logger.log(Level.INFO, "16.Retain the first list values from the second list");
		logger.log(Level.INFO, "17.Add long values and delete all values and print list and size");
		logger.log(Level.INFO, "18.Add Strings and find the string is already present or not");
	}

	private static void printIterator(List<String> stringList) throws CustomException {
		try {
			UtilMethods.isNull(stringList, ConstantMessage.INPUT_NULL_MESSAGE);
			logger.log(Level.INFO, "Output of the String Using Iterator");
			Iterator<String> iterator = stringList.iterator();
			while (iterator.hasNext()) {
				logger.log(Level.INFO, iterator.next());
			}
		} catch (CustomException e) {
			throw new CustomException("Error in printing ArrayList", e);
		}
	}

	private static void printLoop(List<String> stringList) throws CustomException {
		try {
			UtilMethods.isNull(stringList, ConstantMessage.INPUT_NULL_MESSAGE);
			logger.log(Level.INFO, "Output of the String Using For Loop");
			for (String string : stringList) {
				logger.log(Level.INFO, string);
			}
		} catch (CustomException e) {
			throw new CustomException("Error in printing ArrayList", e);
		}
	}

	private static <E> void printListDetails(List<E> list) throws CustomException {
		try {
			logger.log(Level.INFO, "The length of the ArrayList is " + alh.findListSize(list));
			logger.log(Level.INFO, "The ArrayList is " + list);
		} catch (CustomException e) {
			throw new CustomException("Error in printing ArrayList", e);
		}
	}

	private static void printExceptionDetails(Exception e) {
		logger.log(Level.WARNING, "An Exception Occured", e);
	}
}
