package runner;

import sboperations.StringBuilderHelper;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

import java.util.InputMismatchException;
import exception.CustomException;

public class StringBuilderRunner{
	private static final Logger logger = Logger.getLogger(StringBuilderRunner.class.getName());
	static StringBuilderHelper sbh = new StringBuilderHelper();
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		boolean isProgramAlive = true;
		try{
			while(isProgramAlive){
				printDetails();
				try{
					logger.log(Level.INFO,"Enter the choice");
					choice = sc.nextInt();
				}catch(InputMismatchException e){
					logger.log(Level.WARNING,"Please enter the correct choice!!! Avoid entering the Characters for an Integer!!",e);
					sc.nextLine();
					continue;
				}
				sc.nextLine();
				switch(choice){
					case 1:
						logger.log(Level.INFO,"1.Append new String and find string length");
						try{
							StringBuilder sb1 = sbh.getStringBuilder();
							printInitialStringDetails(sb1);
							logger.log(Level.INFO,"Enter the String to Add");
							String string1 = sc.nextLine();
							sbh.appendStrings(sb1,string1);
							printStringDetails(sb1);
						}catch(CustomException e){
							printExceptionDetails(e);
						}
						break;

					case 2:
						logger.log(Level.INFO,"2.Add 4 string each separated by a “#”");
						logger.log(Level.INFO,"Enter the initilaString");
						String initilaString1 = sc.nextLine();
						String separator1 = "#";
						try{
							StringBuilder sb2 = sbh.getStringBuilder();
							sbh.appendStrings(sb2,initilaString1);
							printInitialStringDetails(sb2);
							logger.log(Level.INFO,"Enter the Count of Strings");
							int count = sc.nextInt();
							 
    							if (count < 0) {
        							throw new IllegalArgumentException("Count cannot be negative");
    							}
							sc.nextLine();
							logger.log(Level.INFO,"Enter the strings to add");
							String[] array = new String[count];
							for(int i=0;i<count;i++){
								array[i] = sc.nextLine();
							}
							sbh.appendString(sb2,array,separator1);
							printStringDetails(sb2);
						}catch (InputMismatchException e) {
                            logger.log(Level.WARNING,"Please enter the correct position!! Avoid entering Character and String for an Integer",e);
                            sc.nextLine();
                        }catch (IllegalArgumentException e) {
    							logger.log(Level.WARNING,"Negative count value is not allowed.",e);
						}catch(CustomException e){
							printExceptionDetails(e);
						}
						break;

					case 3:
						logger.log(Level.INFO,"3.Insert string between two strings");
						logger.log(Level.INFO,"Enter the initilaString with space");
						String initilaString2 = sc.nextLine();
						String spaceSeparator1 = " ";
						try{
							StringBuilder sb3 = sbh.getStringBuilder();
							sbh.appendStrings(sb3,initilaString2);
							printInitialStringDetails(sb3);
							logger.log(Level.INFO,"Enter the String to insert");
							String string2 = sc.nextLine();
							sbh.insertString(sb3,string2,spaceSeparator1);
							printStringDetails(sb3);
						}catch(CustomException e){
							printExceptionDetails(e);
						}
						break;

					case 4:
						logger.log(Level.INFO,"4.Delete the first String");
						logger.log(Level.INFO,"Enter the initilaString with space");
						String initilaString3 = sc.nextLine();
						String spaceSeparator2 = " ";
						int startPosition = 0;
						try{
							StringBuilder sb4 = sbh.getStringBuilder();
							sbh.appendStrings(sb4,initilaString3);
							printInitialStringDetails(sb4);
							sbh.deleteString(sb4,spaceSeparator2,startPosition);
							printStringDetails(sb4);
						}catch(CustomException e){
							printExceptionDetails(e);
						}
						break;

					case 5:
						logger.log(Level.INFO,"5.Replace space by hypen (-)");
						logger.log(Level.INFO,"Enter the initilaString with space and atleast 3 words");
						String initilaString4 = sc.nextLine();
						String spaceSeparator3 = " ";
						char separator2 = '-';
						try{
							StringBuilder sb5 = sbh.getStringBuilder();
							sbh.appendStrings(sb5,initilaString4);
							printInitialStringDetails(sb5);
							sbh.replaceString(sb5,spaceSeparator3,separator2);
							printStringDetails(sb5);
						}catch(CustomException e){
							printExceptionDetails(e);
						}
						break;

					case 6:
						logger.log(Level.INFO,"6.Reverse the String");
						logger.log(Level.INFO,"Enter the initilaString with space and atleast 3 words");
						String initilaString5 = sc.nextLine();
						try{
							StringBuilder sb6 = sbh.getStringBuilder();
							sbh.appendStrings(sb6,initilaString5);
							printInitialStringDetails(sb6);
							sbh.reverseString(sb6);
							printStringDetails(sb6);
						}catch(CustomException e){
							printExceptionDetails(e);
						}
						break;

					case 7:
						logger.log(Level.INFO,"7.Delete Character from start to end Index");
						logger.log(Level.INFO,"Enter the initilaString with minimum 10 characters");
						String initilaString6  = sc.nextLine();
						try{
							StringBuilder sb7 = sbh.getStringBuilder();
							sbh.appendStrings(sb7,initilaString6);
							printInitialStringDetails(sb7);
							logger.log(Level.INFO,"Enter the start Index");
							int startIndex = sc.nextInt();
							logger.log(Level.INFO,"Enter the end Index");
							int endIndex = sc.nextInt();
							sbh.deleteCharacterByIndex(sb7,startIndex,endIndex);
							printStringDetails(sb7);
						}catch (InputMismatchException e) {
                            logger.log(Level.WARNING,"Please enter the correct position!! Avoid entering Character and String for an Integer",e);
                            sc.nextLine();
                        }catch(CustomException e){
							printExceptionDetails(e);
						}
						break;

					case 8:
						logger.log(Level.INFO,"8.Replace Character from start to end Index");
						logger.log(Level.INFO,"Enter the initilaString with minimum 10 characters");
						String initilaString7  = sc.nextLine();
						try{
							StringBuilder sb8 = sbh.getStringBuilder();
							sbh.appendStrings(sb8,initilaString7);
							printInitialStringDetails(sb8);
							logger.log(Level.INFO,"Enter the start Index");
							int startIndex = sc.nextInt();
							logger.log(Level.INFO,"Enter the end Index");
							int endIndex = sc.nextInt();
							sc.nextLine();
							logger.log(Level.INFO,"Enter the String for replace");
							String replace = sc.nextLine();
							sbh.replaceCharacterByIndex(sb8,startIndex,endIndex,replace);
							printStringDetails(sb8);
						}catch (InputMismatchException e) {
                            logger.log(Level.WARNING,"Please enter the correct position!! Avoid entering Character and String for an Integer",e);
                            sc.nextLine();
                        }catch(CustomException e){
							printExceptionDetails(e);
						}
						break;

					case 9:
						logger.log(Level.INFO,"9.Find the first occurance of the # in the string");
						String spaceSeparator4 = "#";
						logger.log(Level.INFO,"Enter the initilaString with includes #");
						String initilaString8 = sc.nextLine();
						try{
							StringBuilder sb9 = sbh.getStringBuilder();
							sbh.appendStrings(sb9,initilaString8);
							printInitialStringDetails(sb9);
							int firstOccurance = sbh.toFindFirstOccurance(sb9,spaceSeparator4);
							if(firstOccurance == -1){
								logger.log(Level.INFO,String.format("The %s is not present in the string",spaceSeparator4));
							}else{
								logger.log(Level.INFO,String.format("The %s is present in the %d index",spaceSeparator4,firstOccurance));
							}
						}catch(CustomException e){
							printExceptionDetails(e);
						}
						break;

					case 10:
						logger.log(Level.INFO,"10.Find the last first occurance of the # in the string");
						String spaceSeparator5 = "#";
						logger.log(Level.INFO,"Enter the initilaString with includes #");
						String initilaString9 = sc.nextLine();
						try{
							StringBuilder sb10 = sbh.getStringBuilder();
							sbh.appendStrings(sb10,initilaString9);
							printInitialStringDetails(sb10);
							int lastOccurance = sbh.toFindLastOccurance(sb10,spaceSeparator5);
							if(lastOccurance == -1){
								logger.log(Level.INFO,String.format("The %s is not present in the string",spaceSeparator5));
							}else{
								logger.log(Level.INFO,String.format("The %s is present in the %d index",spaceSeparator5,lastOccurance));
							}
						}catch(CustomException e){
							printExceptionDetails(e);
						}
						break;

					case 0:
						logger.log(Level.INFO,"Exiting...");
						isProgramAlive = false;
						break;
					default:
						logger.log(Level.INFO,"Please enter the correct choice!!");
				}
			}
		}catch(Exception e){
			printExceptionDetails(e);
		}finally {
			if(sc!=null) {
				sc.close();
			}
		}


	}
	public static void printDetails(){
		logger.log(Level.INFO,"---------------------------------------------");
		logger.log(Level.INFO,"1.Create a StringBuilder with no String & print the length of it. Then add a String to it & print the length & the final String");
		logger.log(Level.INFO,"2.Create a StringBuilder with a String & print the length of it. Then add 4 Strings to it & each String separated by a “#” & print the length & the final String");
		logger.log(Level.INFO,"3.Create a StringBuilder with two Strings with a space in between & print the length of it. Then insert another String in between those two Strings & \n print the length & the final String. Space should be there between Strings.");
		logger.log(Level.INFO,"4.Create a StringBuilder with two Strings with a space in between & print the length of it. Then delete the first String & print the length & the final String");
		logger.log(Level.INFO,"5.Create a StringBuilder with 3 Strings with a space in between & print the length of it. Then replace the space with “-” & print the length & the final String");
		logger.log(Level.INFO,"6.Create a StringBuilder with 3 Strings with a space in between & print the length of it. Then reverse the string & print the length & the final String");
		logger.log(Level.INFO,"7.Create a StringBuilder with a String(minimum 10 chars) & print the length of it. Then delete the characters from 6 to 8 index & print the length & the final String");
		logger.log(Level.INFO,"8.Create a StringBuilder with a String(minimum 10 chars) & print the length of it. Then replace the characters from 6 to 8 index with “XYZ” & print the length & the final String");
		logger.log(Level.INFO,"9.Create a StringBuilder with 3 Strings with a “#” in between & print the length of it. Then find the index of the first “#” in the StringBuilder");
		logger.log(Level.INFO,"10.Create a StringBuilder with 3 Strings with a “#” in between & print the length of it. Then find the index of the last “#” in the StringBuilder");
		logger.log(Level.INFO,"0.Exit");
	}
	
	public static void printInitialStringDetails(StringBuilder sb){
		try{
			logger.log(Level.INFO,"Initial length of the String :"+sbh.toFindStringLength(sb));
			logger.log(Level.INFO,"Initila String :"+sb.toString());
		}catch(CustomException e){
			printExceptionDetails(e);
		}
	}
	
	public static void printStringDetails(StringBuilder sb){
		try{
			logger.log(Level.INFO,"After the operations, length of the String :"+sbh.toFindStringLength(sb));
			logger.log(Level.INFO,"The original string after the operation is "+sb.toString());
		}catch(CustomException e){
			printExceptionDetails(e);
		}
	}
	
	public static void printExceptionDetails(Exception e) {
		logger.log(Level.WARNING,"An Exception Occured",e);
	}
}