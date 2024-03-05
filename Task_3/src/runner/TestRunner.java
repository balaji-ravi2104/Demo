package runner;

import operations.StringOperation;
import exception.CustomException;

import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class TestRunner {
	
	private static final Logger logger = Logger.getLogger(TestRunner.class.getName());
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringOperation so = new StringOperation();
        int choice = 0;
        boolean isProgramAlive = true;
        try {
            while (isProgramAlive) {
                printDetails();
                try {
                    logger.log(Level.INFO,"Enter your choice");
                    choice = sc.nextInt();
                } catch (InputMismatchException e) {
                    logger.log(Level.WARNING,"Please enter the correct choice!!! Avoid entering the Characters for an Integer!!",e);
                    sc.nextLine();
                    continue;
                }
                sc.nextLine();
                switch (choice) {
                    case 1:
                        logger.log(Level.INFO,"1.Find the Length of the String");
                        logger.log(Level.INFO,"Enter the String to find length");
                        String string = sc.nextLine();
                        try {
                            logger.log(Level.INFO,"Length of the string is " + so.findStringLength(string));
                        } catch (ArrayIndexOutOfBoundsException e) {
                        	logger.log(Level.WARNING,"An Array Index Out of Bound Exception Occured",e);
                        } catch (CustomException e) {
                        	logger.log(Level.WARNING,"An Exception Occured",e);
                        }
                        break;

                    case 2:
                        logger.log(Level.INFO,"2.String to Char Array Convert");
                        logger.log(Level.INFO,"Enter the String");
                        String string2 = sc.nextLine();
                        try {
                            char[] array = so.convertStringToArray(string2);
                            logger.log(Level.INFO,"Converted Array is ");
                            logger.log(Level.INFO,Arrays.toString(array));
                        } catch (CustomException e) {
                        	logger.log(Level.WARNING,"An Exception Occured",e);
                        }
                        break;

                    case 3:
                        logger.log(Level.INFO,"3.Find the Penultimate");
                        logger.log(Level.INFO,"Enter the String");
                        String string3 = sc.nextLine();
                        try {
                            logger.log(Level.INFO,"Enter the N to fine the Nth nthPenultimate");
                            int nthPenultimate = sc.nextInt();
                            char penultimate = so.toFindPenultimate(string3, nthPenultimate);
                            logger.log(Level.INFO,"The penultimate character is " + penultimate);
                        } catch (InputMismatchException e) {
                            logger.log(Level.WARNING,"Please enter the correct position!! Avoid entering Character and String for an Integer",e);
                            sc.nextLine();
                        } catch (CustomException e) {
                        	logger.log(Level.WARNING,"An Exception Occured",e);
                        }
                        break;

                    case 4:
                        logger.log(Level.INFO,"4.Find the Frequency of the Character");
                        logger.log(Level.INFO,"Enter the String");
                        String string4 = sc.nextLine();

                        logger.log(Level.INFO,"Enter the character to find the occurance");
                        char ch1 = sc.next().charAt(0);
                        try {
                            int freq = so.toFindFrequency(string4, ch1);
                            logger.log(Level.INFO,"The Frequency of the given character is " + freq);
                        } catch (CustomException e) {
                        	logger.log(Level.WARNING,"An Exception Occured",e);
                        }
                        break;

                    case 5:
                        logger.log(Level.INFO,"5.The Greatest Position Of the Character");
                        logger.log(Level.INFO,"Enter the String");
                        String string5 = sc.nextLine();
                        logger.log(Level.INFO,"Enter the character to find the greatest position");
                        char ch2 = sc.next().charAt(0);
                        try {
                            int position = so.toFindGreatestPosition(string5, ch2);
                            if (position == -1) {
                                System.out.printf("The %c is not present in the String", ch2);
                            } else {
                                logger.log(Level.INFO,"The Greatest position of the given character is " + position);
                            }
                        } catch (CustomException e) {
                        	logger.log(Level.WARNING,"An Exception Occured",e);
                        }
                        break;

                    case 6:
                        logger.log(Level.INFO,"6.Find the last N Characters");
                        logger.log(Level.INFO,"Enter the String");
                        String string6 = sc.nextLine();
                        try {
                            logger.log(Level.INFO,"Enter the Last N position");
                            int nthPosition = sc.nextInt();
                            String lastsub = so.toFindLastNCharacter(string6, nthPosition);
                            logger.log(Level.INFO,"The Last N Chatacters are " + lastsub);
                        } catch (InputMismatchException e) {
                            logger.log(Level.WARNING,"Please enter the correct position!! Avoid entering Character and String for an Integer",e);
                            sc.nextLine();
                        } catch (CustomException e) {
                        	logger.log(Level.WARNING,"An Exception Occured",e.getCause());
                        }
                        break;

                    case 7:
                        logger.log(Level.INFO,"7.Find the First N Characters");
                        logger.log(Level.INFO,"Enter the String");
                        String string7 = sc.nextLine();
                        int start = 0;
                        try {
                            logger.log(Level.INFO,"Enter the first N Position");
                            int firstNPosition = sc.nextInt();
                            String firstsub = so.toFindFirstNCharacter(string7, start, firstNPosition);
                            logger.log(Level.INFO,"The first N characters are " + firstsub);
                        } catch (InputMismatchException e) {
                            logger.log(Level.WARNING,"Please enter the correct position!! Avoid entering Character and String for an Integer",e);
                            sc.nextLine();
                        } catch (CustomException e) {
                        	logger.log(Level.WARNING,"An Exception Occured",e);
                        }
                        break;

                    case 8:
                        logger.log(Level.INFO,"8.Replace the First N Characters");
                        logger.log(Level.INFO,"Enter the String");
                        String string8 = sc.nextLine();
                        logger.log(Level.INFO,"Enter the String to Replace");
                        String replace = sc.nextLine();
                        try {
                            logger.log(Level.INFO,"Enter the number of '(N)' characters to replace");
                            int replacePositions = sc.nextInt();
                            String replacesub = so.toReplaceFirstNCharacter(string8, replacePositions, replace);
                            logger.log(Level.INFO,"The Replaced String is " + replacesub);
                        } catch (InputMismatchException e) {
                            logger.log(Level.WARNING,"Please enter the correct position!! Avoid entering Character and String for an Integer",e);
                            sc.nextLine();
                        } catch (CustomException e) {
                        	logger.log(Level.WARNING,"An Exception Occured",e);
                        }
                        break;

                    case 9:
                        logger.log(Level.INFO,"9.String starts with ent");
                        logger.log(Level.INFO,"Enter the String");
                        String string9 = sc.nextLine();

                        String starts = "ent";
                        try {

                            if (so.isStartsWith(string9, starts)) {
                                logger.log(Level.INFO,"Yes String starts with " + starts);
                            } else {
                                logger.log(Level.INFO,"No String starts with " + starts);
                            }
                        } catch (CustomException e) {
                        	logger.log(Level.WARNING,"An Exception Occured",e);
                        }
                        break;

                    case 10:
                        logger.log(Level.INFO,"10.String ends with le");
                        logger.log(Level.INFO,"Enter the String");
                        String string10 = sc.nextLine();

                        String ends = "le";
                        try {
                            if (so.isEndsWith(string10, ends)) {
                                logger.log(Level.INFO,"Yes String ends with " + ends);
                            } else {
                                logger.log(Level.INFO,"No String ends with " + ends);
                            }
                        } catch (CustomException e) {
                        	logger.log(Level.WARNING,"An Exception Occured",e);
                        }
                        break;

                    case 11:
                        logger.log(Level.INFO,"11.Convert Lower to Upper");
                        logger.log(Level.INFO,"Enter the String");
                        String string11 = sc.nextLine();
                        try {
                            logger.log(Level.INFO,"The Converted String is " + so.convertLowerToUpper(string11));
                        } catch (CustomException e) {
                        	logger.log(Level.WARNING,"An Exception Occured",e);
                        }
                        break;

                    case 12:
                        logger.log(Level.INFO,"12.Convert Upper to Lower");
                        logger.log(Level.INFO,"Enter the String");
                        String string12 = sc.nextLine();
                        try {
                            logger.log(Level.INFO,"The Converted String is" + so.convertUpperToLower(string12));
                        } catch (CustomException e) {
                        	logger.log(Level.WARNING,"An Exception Occured",e);
                        }
                        break;

                    case 13:
                        logger.log(Level.INFO,"13.Reverse the String");
                        logger.log(Level.INFO,"Enter the String");
                        String string13 = sc.nextLine();
                        try {
                            logger.log(Level.INFO,"Reversed String is " + so.reverseString(string13));
                        } catch (CustomException e) {
                        	logger.log(Level.WARNING,"An Exception Occured",e);
                        }
                        break;
                    case 14:
                        logger.log(Level.INFO,"14.Multiple Strings");
                        logger.log(Level.INFO,"Enter the String");
                        String string14 = sc.nextLine();
                        try {
                            logger.log(Level.INFO,so.multipleStringPrint(string14));
                        } catch (CustomException e) {
                        	logger.log(Level.WARNING,"An Exception Occured",e);
                        }
                        break;

                    case 15:
                        logger.log(Level.INFO,"15.Multiple Strings and Concatenate String");
                        logger.log(Level.INFO,"Enter the String");
                        String string15 = sc.nextLine();

                        String splitedString1 = "\\s";
                        try {
                            String concatenated = so.stringConcatenate(string15, splitedString1);
                            logger.log(Level.INFO,"Concatenated String is " + concatenated);
                        } catch (CustomException e) {
                        	logger.log(Level.WARNING,"An Exception Occured",e);
                        }
                        break;

                    case 16:
                        logger.log(Level.INFO,"16.Multiple Strings & enclose each String into a String array");
                        logger.log(Level.INFO,"Enter the String");
                        String string16 = sc.nextLine();
                        String splitedString2 = "\\s";
                        try {
                            String[] stringArray = so.stringToArray(string16, splitedString2);
                            logger.log(Level.INFO,Arrays.toString(stringArray));
                        } catch (CustomException e) {
                        	logger.log(Level.WARNING,"An Exception Occured",e);
                        }
                        break;

                    case 17:
                        logger.log(Level.INFO,"17.Two Strings Equal or Not");
                        logger.log(Level.INFO,"Enter First the String");
                        String string17_1 = sc.nextLine();
                        logger.log(Level.INFO,"Enter Second the String");
                        String string17_2 = sc.nextLine();
                        try {
                            if (so.isStringEqual(string17_1, string17_2)) {
                                logger.log(Level.INFO,"Yes String are Equal");
                            } else {
                                logger.log(Level.INFO,"No String are Not Equal");
                            }
                        } catch (CustomException e) {
                        	logger.log(Level.WARNING,"An Exception Occured",e);
                        }
                        break;

                    case 18:
                        logger.log(Level.INFO,"18.Two Strings Equal or Not and ignoreCase");
                        logger.log(Level.INFO,"Enter First the String");
                        String string18_1 = sc.nextLine();

                        logger.log(Level.INFO,"Enter Second the String");
                        String string18_2 = sc.nextLine();

                        try {
                            if (so.isStringEqualIgnoreCase(string18_1, string18_2)) {
                                logger.log(Level.INFO,"Yes, Strings are Equal");
                            } else {
                                logger.log(Level.INFO,"No, Strings are Not Equal");
                            }
                        } catch (CustomException e) {
                        	logger.log(Level.WARNING,"An Exception Occured",e);
                        }
                        break;

                    case 19:
                        logger.log(Level.INFO,"19.String with no space either at the beginning or end.");
                        logger.log(Level.INFO,"Enter the String");
                        String string19 = sc.nextLine();
                        try {
                            String afterTrim = so.trimString(string19);
                            logger.log(Level.INFO,"After triming the String " + afterTrim);
                        } catch (CustomException e) {
                        	logger.log(Level.WARNING,"An Exception Occured",e);
                        }
                        break;

                    case 20:
                        logger.log(Level.INFO,"20.Merge String with '-'");
                        String joiner = "-";
                        try {
                        		logger.log(Level.INFO,"Enter the count of strings");
                        		int count = sc.nextInt();
                        		sc.nextLine();
                        		logger.log(Level.INFO,"Enter the Strings");
                              String[] strArray = new String[count];
                              for(int i=0;i<count;i++){
                              	strArray[i] = sc.nextLine();
                              }
                              logger.log(Level.INFO,"After merging the String " + so.mergeStrings(strArray, joiner));
                        } catch (CustomException e) {
                        	logger.log(Level.WARNING,"An Exception Occured",e);
                        }
                        break;

                    case 0:
                    	logger.log(Level.INFO,"Exiting...");
                    	isProgramAlive = false;
                        break;
                    default:
                        logger.log(Level.INFO,"Please Enter the valid choice");
                }
            }
        } catch (Exception e) {
        	logger.log(Level.WARNING,"An Exception Occured",e);
        }
        sc.close();
    }

    public static void printDetails() {
        logger.log(Level.INFO,"------------------------------------------------------------");
        logger.log(Level.INFO,"1:Find the Length of the String");
        logger.log(Level.INFO,"2:String to Char Array Convert");
        logger.log(Level.INFO,"3:Find the Penultimate");
        logger.log(Level.INFO,"4:Find the Frequency of the Character");
        logger.log(Level.INFO,"5:The Greatest Position Of the Character");
        logger.log(Level.INFO,"6:Find the last Five Characters");
        logger.log(Level.INFO,"7:Find the First three Characters");
        logger.log(Level.INFO,"8:Replace the First three Characters");
        logger.log(Level.INFO,"9:String starts with ent");
        logger.log(Level.INFO,"10:String ends with le");
        logger.log(Level.INFO,"11:Convert Lower to Upper");
        logger.log(Level.INFO,"12:Convert Upper to Lower");
        logger.log(Level.INFO,"13:Reverse the String");
        logger.log(Level.INFO,"14:Multiple Strings");
        logger.log(Level.INFO,"15:Multiple Strings and Concatenate String");
        logger.log(Level.INFO,"16:Multiple Strings & enclose each String into a String array");
        logger.log(Level.INFO,"17:Two Strings Equal or Not");
        logger.log(Level.INFO,"18:Two Strings Equal or Not and ignoreCase");
        logger.log(Level.INFO,"19:String with no space either at the beginning or end.");
        logger.log(Level.INFO,"20:Merge String with '-'");
        logger.log(Level.INFO,"0.Exit");
    }
}