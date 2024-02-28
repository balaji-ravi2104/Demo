package runner;

import hashmapoperations.HashMapHelper;
import message.ConstantMessage;
import utils.UtilMethods;

import java.util.InputMismatchException;
import java.util.Map;  
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

import customclass.CustomObject;
import exception.CustomException;

public class HashMapRunner {
	private static final Logger logger = Logger.getLogger(HashMapRunner.class.getName());
	static HashMapHelper hmh = new HashMapHelper();         
	
	@SuppressWarnings({"rawtypes" })
	public static<K,V> void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice,count,integerKey,integerValue;
		String stringKey,stringValue,nullValue = null,value="demo";
		boolean runner = true;
		try {
			while(runner) {
				printDetails();
				try {
					logger.log(Level.INFO,"Enter your choice");
					choice = sc.nextInt();
					if (choice < 0) {
						throw new IllegalArgumentException("Choice must be greater than Zero!!");
					}
				} catch (InputMismatchException e) {
					printExceptionDetails(e);
					sc.nextLine();
					continue;
				} catch (IllegalArgumentException e) {
					printExceptionDetails(e);
					sc.nextLine();
					continue;
				}
				sc.nextLine();
				switch(choice) {
					case 1:
						logger.log(Level.INFO,"1.Create HashMap and Print Map & Size");
						try {
							Map<K,V> hashMap = hmh.getNewHashMap();
							printMapDetails(hashMap);
						}catch(CustomException e) {
							printExceptionDetails(e);
						}	
						break;
					case 2:
						logger.log(Level.INFO,"2.Create HashMap and Add string pairs, Print Map & Size");
						try {
							Map<String,String> hashMap = hmh.getNewHashMap();
							logger.log(Level.INFO,"Enter the Number Of Pairs");
							count = sc.nextInt();
							if (count < 0) {
								throw new IllegalArgumentException("Count must be greater than Zero!!");
							}
							sc.nextLine();
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							for(int i=0;i<count;i++) {
								logger.log(Level.INFO,"Enter the string key");
								stringKey = sc.nextLine();
								logger.log(Level.INFO,"Enter the string value");
								stringValue = sc.nextLine();
						        hmh.addKeyAndValue(hashMap, stringKey,stringValue);
							}
							printMapDetails(hashMap);
						}catch(InputMismatchException e) {
							printExceptionDetails(e);
							sc.nextLine();
						}catch (IllegalArgumentException e) {
							printExceptionDetails(e);
						}catch(CustomException e) {
							printExceptionDetails(e);
						}
						break;
					case 3:
						logger.log(Level.INFO,"3.Create HashMap and Add Integer pairs, Print Map & Size");
						try {
							Map<Integer,Integer> hashMap = hmh.getNewHashMap(); 
							logger.log(Level.INFO,"Enter the Number Of Pairs");
							count = sc.nextInt();
							if (count < 0) {
								throw new IllegalArgumentException("Count must be greater than Zero!!");
							}
							sc.nextLine();
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							for(int i=0;i<count;i++) {
								logger.log(Level.INFO,"Enter the Integer key");
								integerKey = sc.nextInt();
								logger.log(Level.INFO,"Enter the Integer value");
								integerValue = sc.nextInt();
								hmh.addKeyAndValue(hashMap, integerKey,integerValue);
							}
							printMapDetails(hashMap);
						}catch(InputMismatchException e) {
							printExceptionDetails(e);
							sc.nextLine();
						}catch (IllegalArgumentException e) {
							printExceptionDetails(e);
						}catch(CustomException e) {
							printExceptionDetails(e);
						}
						break;
					case 4:
						logger.log(Level.INFO,"4.Create HashMap With String as Key and Integer as Value, Print Map & Size");
						try {
							Map<String,Integer> hashMap = hmh.getNewHashMap(); 
							logger.log(Level.INFO,"Enter the Number Of Pairs");
							count = sc.nextInt();
							if (choice < 0) {
								throw new IllegalArgumentException("Choice must be greater than Zero!!");
							}
							sc.nextLine();
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							for(int i=0;i<count;i++) {
								logger.log(Level.INFO,"Enter the string key");
								stringKey = sc.nextLine();
								logger.log(Level.INFO,"Enter the Integer value");
							    integerValue = sc.nextInt();
								sc.nextLine();
								hmh.addKeyAndValue(hashMap, stringKey,integerValue);
							}
							printMapDetails(hashMap);
						}catch(InputMismatchException e) {
							printExceptionDetails(e);
							sc.nextLine();
						}catch (IllegalArgumentException e) {
							printExceptionDetails(e);
						}catch(CustomException e) {
							printExceptionDetails(e);
						}
						break;
					case 5:
						logger.log(Level.INFO,"5.Create HashMap With String as Key and Custom Object as Value, Print Map & Size");
						try {
							Map<String,CustomObject> hashMap = hmh.getNewHashMap();
							logger.log(Level.INFO,"Enter the Number Of Pairs");
							count = sc.nextInt();
							if (choice < 0) {
								throw new IllegalArgumentException("Choice must be greater than Zero!!");
							}
							sc.nextLine();
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							for(int i=0;i<count;i++) {
								logger.log(Level.INFO,"Enter the string key");
								stringKey = sc.nextLine();
								logger.log(Level.INFO,"Enter the String value for Custom Class");
							    stringValue = sc.nextLine();
								hmh.addKeyAndValue(hashMap, stringKey, new CustomObject<Object>(stringValue));
							}
							printMapDetails(hashMap);
						}catch(InputMismatchException e) {
							printExceptionDetails(e);
							sc.nextLine();
						}catch (IllegalArgumentException e) {
							printExceptionDetails(e);
						}catch(CustomException e) {
							printExceptionDetails(e);
						}
						break;
					case 6:
						logger.log(Level.INFO,"6.Create HashMap and Add String Key and One Null value");
						try {
							Map<String,String> hashMap = hmh.getNewHashMap();
							hmh.addKeyAndValue(hashMap, value,nullValue);
							printMapDetails(hashMap);
						}catch(CustomException e) {
							printExceptionDetails(e);
						}
						break;
					case 7:
						logger.log(Level.INFO,"7.Create HashMap and Add NUll key and Non Null value");
						try {
							Map<String,String> hashMap = hmh.getNewHashMap();
							hmh.addKeyAndValue(hashMap,nullValue,value);
							printMapDetails(hashMap);
						}catch(CustomException e) {
							printExceptionDetails(e);
						}
						break;
					case 8:
						logger.log(Level.INFO,"8.Check A Key Exists in the Map");
						try {
							Map<String,String> hashMap = hmh.getNewHashMap();
							logger.log(Level.INFO,"Enter the Number Of Pairs");
							count = sc.nextInt();
							if (choice < 0) {
								throw new IllegalArgumentException("Choice must be greater than Zero!!");
							}
							sc.nextLine();
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							for(int i=0;i<count;i++) {
								logger.log(Level.INFO,"Enter the string key");
								stringKey = sc.nextLine();
								logger.log(Level.INFO,"Enter the string value");
								stringValue = sc.nextLine();
								hmh.addKeyAndValue(hashMap, stringKey,stringValue);
							}
							logger.log(Level.INFO,"Enter the Key to Find is Exists");
							stringKey = sc.nextLine();
							boolean isExists = hmh.isKeyExists(hashMap, stringKey);
							if(isExists) {
								logger.log(Level.INFO,String.format("Yes,The Value %s present in the Map",stringKey));
							}else {
								logger.log(Level.INFO,String.format("Yes,The Value %s present in the Map",stringKey));
							}
						}catch(InputMismatchException e) {
							printExceptionDetails(e);
							sc.nextLine();
						}catch (IllegalArgumentException e) {
							printExceptionDetails(e);
						}catch(CustomException e) {
							printExceptionDetails(e);
						}
						break;
					case 9:
						logger.log(Level.INFO,"9.Check A Value Exists in the Map");
						try {
							Map<String,String> hashMap = hmh.getNewHashMap();
							logger.log(Level.INFO,"Enter the Number Of Pairs");
							count = sc.nextInt();
							if (choice < 0) {
								throw new IllegalArgumentException("Choice must be greater than Zero!!");
							}
							sc.nextLine();
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							for(int i=0;i<count;i++) {
								logger.log(Level.INFO,"Enter the string key");
								stringKey = sc.nextLine();
								logger.log(Level.INFO,"Enter the string value");
								stringValue = sc.nextLine();
								hmh.addKeyAndValue(hashMap, stringKey,stringValue);
							}
							logger.log(Level.INFO,"Enter the Value to Find is Exists");
							stringValue = sc.nextLine();
							boolean isExists = hmh.isValueExists(hashMap, stringValue);
							if(isExists) {
								logger.log(Level.INFO,String.format("Yes,The Value %s present in the Map",stringValue));
							}else {
								logger.log(Level.INFO,String.format("No,The Value %s present in the Map",stringValue));
							}
							
						}catch(InputMismatchException e) {
							printExceptionDetails(e);
							sc.nextLine();
						}catch (IllegalArgumentException e) {
							printExceptionDetails(e);
						}catch(CustomException e) {
							printExceptionDetails(e);
						}
						break;
					case 10:
						logger.log(Level.INFO,"10.Create HashMap and Replace All the Keys");
						try {
							Map<String,String> hashMap = hmh.getNewHashMap(); 
							logger.log(Level.INFO,"Enter the Number Of Pairs");
							count = sc.nextInt();
							if (choice < 0) {
								throw new IllegalArgumentException("Choice must be greater than Zero!!");
							}
							sc.nextLine();
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							for(int i=0;i<count;i++) {
								logger.log(Level.INFO,"Enter the string key");
								stringKey = sc.nextLine();
								logger.log(Level.INFO,"Enter the string value");
								stringValue = sc.nextLine();
								hmh.addKeyAndValue(hashMap, stringKey,stringValue);
							}
							printMapDetails(hashMap);
							logger.log(Level.INFO,"Enter the Key and Value Of Replace");
							for(int i=0;i<count;i++) {
								logger.log(Level.INFO,"Enter the string key");
								stringKey = sc.nextLine();
								logger.log(Level.INFO,"Enter the string value");
								stringValue = sc.nextLine();
								hmh.replaceValue(hashMap, stringKey, stringValue);
							}
							printMapDetails(hashMap);
						}catch(InputMismatchException e) {
							printExceptionDetails(e);
							sc.nextLine();
						}catch (IllegalArgumentException e) {
							printExceptionDetails(e);
						}catch(CustomException e) {
							printExceptionDetails(e);
						}
						break;
					case 11:
						logger.log(Level.INFO,"11.Get Value of the Existing Key");
						try {
							Map<String,String> hashMap = hmh.getNewHashMap();
							logger.log(Level.INFO,"Enter the Number Of Pairs");
							count = sc.nextInt();
							if (choice < 0) {
								throw new IllegalArgumentException("Choice must be greater than Zero!!");
							}
							sc.nextLine();
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							for(int i=0;i<count;i++) {
								logger.log(Level.INFO,"Enter the string key");
								stringKey = sc.nextLine();
								logger.log(Level.INFO,"Enter the string value");
								stringValue = sc.nextLine();
								hmh.addKeyAndValue(hashMap, stringKey,stringValue);
							}
							logger.log(Level.INFO,"Enter the Existing Key to get the Value");
							stringKey = sc.nextLine();
							String value1 = (String) hmh.getValue(hashMap, stringKey);
							logger.log(Level.INFO,String.format("The Value for the Key %s is %s",stringKey,value1));
							
						}catch(InputMismatchException e) {
							printExceptionDetails(e);
							sc.nextLine();
						}catch (IllegalArgumentException e) {
							printExceptionDetails(e);
						}catch(CustomException e) {
							printExceptionDetails(e);
						}
						break;
					case 12:
						logger.log(Level.INFO,"12.Get Value of the Non-Existing Key");
						try {
							Map<String,String> hashMap = hmh.getNewHashMap();
							logger.log(Level.INFO,"Enter the Number Of Pairs");
							count = sc.nextInt();
							if (choice < 0) {
								throw new IllegalArgumentException("Choice must be greater than Zero!!");
							}
							sc.nextLine();
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							for(int i=0;i<count;i++) {
								logger.log(Level.INFO,"Enter the string key");
								stringKey = sc.nextLine();
								logger.log(Level.INFO,"Enter the string value");
								stringValue = sc.nextLine();
								hmh.addKeyAndValue(hashMap, stringKey,stringValue);
							}
							logger.log(Level.INFO,"Enter the Key to get the Value");
							stringKey = sc.nextLine();
							String value1 = (String) hmh.getValue(hashMap, stringKey);
							logger.log(Level.INFO,String.format("The Value for the Key %s is %s",stringKey,value1));
							
						}catch(InputMismatchException e) {
							printExceptionDetails(e);
							sc.nextLine();
						}catch (IllegalArgumentException e) {
							printExceptionDetails(e);
						}catch(CustomException e) {
							printExceptionDetails(e);
						}
						break;
					case 13:
						logger.log(Level.INFO,"13.Get Value of for Non-Existing Key as ZOHO");
						try {
							Map<String,String> hashMap = hmh.getNewHashMap();
							logger.log(Level.INFO,"Enter the Number Of Pairs");
							count = sc.nextInt();
							if (choice < 0) {
								throw new IllegalArgumentException("Choice must be greater than Zero!!");
							}
							sc.nextLine();
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							for(int i=0;i<count;i++) {
								logger.log(Level.INFO,"Enter the string key");
								stringKey = sc.nextLine();
								logger.log(Level.INFO,"Enter the string value");
								stringValue = sc.nextLine();
								hmh.addKeyAndValue(hashMap, stringKey,stringValue);
							}
							printMapDetails(hashMap);
							logger.log(Level.INFO,"Enter the Non-Key to get the Value as ZOHO");
							stringKey = sc.nextLine();
							String value1 = (String) hmh.getDefaultValue(hashMap, stringKey);
							hmh.addKeyAndValue(hashMap, stringKey,value1);
							printMapDetails(hashMap);
						}catch(InputMismatchException e) {
							printExceptionDetails(e);
							sc.nextLine();
						}catch (IllegalArgumentException e) {
							printExceptionDetails(e);
						}catch(CustomException e) {
							printExceptionDetails(e);
						}
						break;
					case 14:
						logger.log(Level.INFO,"14.Remove Key From By using Exixting Key");
						try {
							Map<String,String> hashMap = hmh.getNewHashMap();
							logger.log(Level.INFO,"Enter the Number Of Pairs");
							count = sc.nextInt();
							if (choice < 0) {
								throw new IllegalArgumentException("Choice must be greater than Zero!!");
							}
							sc.nextLine();
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							for(int i=0;i<count;i++) {
								logger.log(Level.INFO,"Enter the string key");
								stringKey = sc.nextLine();
								logger.log(Level.INFO,"Enter the string value");
								stringValue = sc.nextLine();
								hmh.addKeyAndValue(hashMap, stringKey,stringValue);
							}
							printMapDetails(hashMap);
							logger.log(Level.INFO,"Enter the Existing Key to Remove");
							stringKey = sc.nextLine();
							hmh.removeFromMap(hashMap, stringKey);
							printMapDetails(hashMap);
						}catch(InputMismatchException e) {
							printExceptionDetails(e);
							sc.nextLine();
						}catch (IllegalArgumentException e) {
							printExceptionDetails(e);
						}catch(CustomException e) {
							printExceptionDetails(e);
						}
						break;
					case 15:
						logger.log(Level.INFO,"15.Remove Key by using value");
						try {
							Map<String,String> hashMap = hmh.getNewHashMap();
							logger.log(Level.INFO,"Enter the Number Of Pairs");
							count = sc.nextInt();
							if (choice < 0) {
								throw new IllegalArgumentException("Choice must be greater than Zero!!");
							}
							sc.nextLine();
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							for(int i=0;i<count;i++) {
								logger.log(Level.INFO,"Enter the string key");
								stringKey = sc.nextLine();
								logger.log(Level.INFO,"Enter the string value");
								stringValue = sc.nextLine();
								hmh.addKeyAndValue(hashMap, stringKey,stringValue);
							}
							printMapDetails(hashMap);
							logger.log(Level.INFO,"Enter the Value to Remove Key");
							stringValue = sc.nextLine();
							hmh.removeKeyByValue(hashMap,stringValue);
							printMapDetails(hashMap);
						}catch(InputMismatchException e) {
							printExceptionDetails(e);
							sc.nextLine();
						}catch (IllegalArgumentException e) {
							printExceptionDetails(e);
						}catch(CustomException e) {
							printExceptionDetails(e);
						}
						break;
					case 16:
						logger.log(Level.INFO,"16.Replace Value By using Key");
						try {
							Map<String,String> hashMap = hmh.getNewHashMap();
							logger.log(Level.INFO,"Enter the Number Of Pairs");
							count = sc.nextInt();
							if (choice < 0) {
								throw new IllegalArgumentException("Choice must be greater than Zero!!");
							}
							sc.nextLine();
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							for(int i=0;i<count;i++) {
								logger.log(Level.INFO,"Enter the string key");
								stringKey = sc.nextLine();
								logger.log(Level.INFO,"Enter the string value");
								stringValue = sc.nextLine();
								hmh.addKeyAndValue(hashMap, stringKey,stringValue);
							}
							printMapDetails(hashMap);
							logger.log(Level.INFO,"Enter the Key To Replace the Value");
							stringKey = sc.nextLine();
							logger.log(Level.INFO,"Enter the Value to Replace");
							stringValue = sc.nextLine();
							hmh.replaceValue(hashMap,stringKey,stringValue);
							printMapDetails(hashMap);
						}catch(InputMismatchException e) {
							printExceptionDetails(e);
							sc.nextLine();
						}catch (IllegalArgumentException e) {
							printExceptionDetails(e);
						}catch(CustomException e) {
							printExceptionDetails(e);
						}
						break;
					case 17:
						logger.log(Level.INFO,"17.Replace Existing Value By using Existing Key and Value");
						try {
							Map<String,String> hashMap = hmh.getNewHashMap();
							logger.log(Level.INFO,"Enter the Number Of Pairs");
							count = sc.nextInt();
							if (choice < 0) {
								throw new IllegalArgumentException("Choice must be greater than Zero!!");
							}
							sc.nextLine();
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							for(int i=0;i<count;i++) {
								logger.log(Level.INFO,"Enter the string key");
								stringKey = sc.nextLine();
								logger.log(Level.INFO,"Enter the string value");
								stringValue = sc.nextLine();
								hmh.addKeyAndValue(hashMap, stringKey,stringValue);
							}
							printMapDetails(hashMap);
							logger.log(Level.INFO,"Enter the Existing Key To Replace the Value");
							stringKey = sc.nextLine();
							logger.log(Level.INFO,"Enter the Value to the Corrosponding Key");
							stringValue = sc.nextLine();
							logger.log(Level.INFO,"Enter the New Value to Replace");
							String newValue = sc.nextLine();
							hmh.replaceValueByExisitingValue(hashMap,stringKey,stringValue,newValue);
							printMapDetails(hashMap);
						}catch(InputMismatchException e) {
							printExceptionDetails(e);
							sc.nextLine();
						}catch (IllegalArgumentException e) {
							printExceptionDetails(e);
						}catch(CustomException e) {
							printExceptionDetails(e);
						}
						break;
					case 18:
						logger.log(Level.INFO,"18.Transfer all the keys & values of a HashMap to another HashMap");
						try {
							Map<String,String> hashMap1 = hmh.getNewHashMap();
							Map<String,String> hashMap2 = hmh.getNewHashMap();
							logger.log(Level.INFO,"Enter the Number Of Pairs Map-1");
							count = sc.nextInt();
							if (choice < 0) {
								throw new IllegalArgumentException("Choice must be greater than Zero!!");
							}
							sc.nextLine();
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							for(int i=0;i<count;i++) {
								logger.log(Level.INFO,"Enter the string key");
								stringKey = sc.nextLine();
								logger.log(Level.INFO,"Enter the string value");
								stringValue = sc.nextLine();
								hmh.addKeyAndValue(hashMap1, stringKey,stringValue);
							}
							logger.log(Level.INFO,"Enter the Number Of Pairs Map-2");
							count = sc.nextInt();
							if (choice < 0) {
								throw new IllegalArgumentException("Choice must be greater than Zero!!");
							}
							sc.nextLine();
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							for(int i=0;i<count;i++) {
								logger.log(Level.INFO,"Enter the string key");
								stringKey = sc.nextLine();
								logger.log(Level.INFO,"Enter the string value");
								stringValue = sc.nextLine();
								hmh.addKeyAndValue(hashMap2, stringKey,stringValue);
							}
							printMapDetails(hashMap1);
							printMapDetails(hashMap2);
							logger.log(Level.INFO,"After,Transfer all the keys & values of a HashMap-2 to HashMap-1");
							hmh. transferHashMapContents(hashMap1,hashMap2);
							printMapDetails(hashMap1);
							printMapDetails(hashMap2);
						}catch(InputMismatchException e) {
							printExceptionDetails(e);
							sc.nextLine();
						}catch (IllegalArgumentException e) {
							printExceptionDetails(e);
						}catch(CustomException e) {
							printExceptionDetails(e);
						}
						break;
					case 19:
						logger.log(Level.INFO,"19.Iterate the Map");
						try {
							Map<String,String> hashMap = hmh.getNewHashMap();
							logger.log(Level.INFO,"Enter the Number Of Pairs");
							count = sc.nextInt();
							if (choice < 0) {
								throw new IllegalArgumentException("Choice must be greater than Zero!!");
							}
							sc.nextLine();
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							for(int i=0;i<count;i++) {
								logger.log(Level.INFO,"Enter the string key");
								stringKey = sc.nextLine();
								logger.log(Level.INFO,"Enter the string value");
								stringValue = sc.nextLine();
						        hmh.addKeyAndValue(hashMap, stringKey,stringValue);
							}
							iterateMap(hashMap); 
						}catch(InputMismatchException e) {
							printExceptionDetails(e);
							sc.nextLine();
						}catch (IllegalArgumentException e) {
							printExceptionDetails(e);
						}catch(CustomException e) {
							printExceptionDetails(e);
						}
						break;
					case 20:
						logger.log(Level.INFO,"20.Remove All the Entries");
						try {
							Map<String,String> hashMap = hmh.getNewHashMap();
							logger.log(Level.INFO,"Enter the Number Of Pairs");
							count = sc.nextInt();
							if (choice < 0) {
								throw new IllegalArgumentException("Choice must be greater than Zero!!");
							}
							sc.nextLine();
							logger.log(Level.INFO,"Enter the Key and Corresponding value");
							for(int i=0;i<count;i++) {
								logger.log(Level.INFO,"Enter the string key");
								stringKey = sc.nextLine();
								logger.log(Level.INFO,"Enter the string value");
								stringValue = sc.nextLine();
						        hmh.addKeyAndValue(hashMap, stringKey,stringValue);
							}
							printMapDetails(hashMap);
							hmh.removeAllEntries(hashMap);
							printMapDetails(hashMap);
						}catch(InputMismatchException e) {
							printExceptionDetails(e);
							sc.nextLine();
						}catch (IllegalArgumentException e) {
							printExceptionDetails(e);
						}catch(CustomException e) {
							printExceptionDetails(e);
						}
						break;
					case 0:
						logger.log(Level.INFO,"Exiting...");
						runner = false;
						break;
					default:
						logger.log(Level.INFO,"Please enter the valid choice");
				}
			}
		}catch(Exception e) {
			logger.log(Level.INFO,e.getMessage());
		}
		sc.close();

	}
	
	private static <K,V> void printMapDetails(Map<K, V> hashMap) throws CustomException{
		try {
			logger.log(Level.INFO,"The length of the HashMap is "+hmh.getMapSize(hashMap));
			logger.log(Level.INFO,"The Map is "+hashMap);
		}catch(CustomException e) {
			printExceptionDetails(e);
		}
		
	}
	private static void printExceptionDetails(Exception e) {
		logger.log(Level.WARNING,e.getMessage());
	}
	
	private static <K,V> void iterateMap(Map<K, V> hashMap) throws CustomException {
		UtilMethods.isNull(hashMap,ConstantMessage.INPUT_NULL_MESSAGE);
		for(Map.Entry<K, V> entry:hashMap.entrySet()) {
			logger.log(Level.INFO,"Key :"+entry.getKey()+", Value :"+entry.getValue());
		}
	}
	private static void printDetails() {
		logger.log(Level.INFO,"-------------------------------------");
		logger.log(Level.INFO,"-------------------------------------");
		logger.log(Level.INFO,"1.Create HashMap ans Print Map & Size");
		logger.log(Level.INFO,"2.Create HashMap and Add pairs, Print Map & Size");
		logger.log(Level.INFO,"3.Create HashMap and Add Integer pairs, Print Map & Size");
		logger.log(Level.INFO,"4.Create HashMap With String as Key and Integer as Value, Print Map & Size");
		logger.log(Level.INFO,"5.Create HashMap With String as Key and Custom Object as Value, Print Map & Size");
		logger.log(Level.INFO,"6.Create HashMap and Add String Key and One Null value");
		logger.log(Level.INFO,"7.Create HashMap and Add NUll key and Non Null value");
		logger.log(Level.INFO,"8.Check A Key Exists in the Map");
		logger.log(Level.INFO,"9.Check A Value Exists in the Map");
		logger.log(Level.INFO,"10.Create HashMap and Replace All the Keys");
		logger.log(Level.INFO,"11.Get Value of the Existing Key");
		logger.log(Level.INFO,"12.Get Value of the Non-Existing Key");
		logger.log(Level.INFO,"13.Get Value of for Non-Existing Key as ZOHO");
		logger.log(Level.INFO,"14.Remove Key From By using Exixting Key");
		logger.log(Level.INFO,"15.Remove Key by using value");
		logger.log(Level.INFO,"16.Replace Value By using Key");
		logger.log(Level.INFO,"17.Replace Existing Value By using Existing Key and Value");
		logger.log(Level.INFO,"18.Transfer all the keys & values of a HashMap to another HashMap");
		logger.log(Level.INFO,"19.Iterate the Map");
		logger.log(Level.INFO,"20.Remove All the Entries");
		logger.log(Level.INFO,"-------------------------------------");
		logger.log(Level.INFO,"-------------------------------------");
	}

}
