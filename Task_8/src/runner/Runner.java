package runner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import democlasses.Rainbow;
import democlasses.SingletonDemo;
//import democlasses.SingletonDemo.SingletonDemo1;
import democlasses.StringHolder;
import democlasses.Student;
import exception.CustomException;
import file.operations.FileHelper;
import time.operations.TimeHelper;

public class Runner {
	private static final Logger logger = Logger.getLogger(Runner.class.getName());
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice, count;
		String fileName, stringKey, stringValue;
		boolean runner = true;
		FileHelper fh = new FileHelper();
		TimeHelper th = new TimeHelper();
		String logFileName = "Runner.log";
		FileHandler fileHandler = null;
		try {
			fileHandler = createFileHandler(logFileName);
			logger.addHandler(fileHandler);
			logger.setLevel(Level.ALL);
		}catch (IOException e) {
			printExceptionDetails(e);
		}
		try {
			while (runner) {
				printDetails();
				try {
					logger.log(Level.INFO,"Enter your choice");
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
					logger.log(Level.WARNING,"Illegal Arrgument Exception Occured", e);
					sc.nextLine();
					continue;
				}
				sc.nextLine();
				switch (choice) {
				case 1:
					logger.log(Level.INFO,"1. Create a File and Write Data on the File");
					try {
						logger.log(Level.INFO,"Enter the File Name to Add Data");
						fileName = sc.nextLine();
						logger.log(Level.INFO,"Enter the Number of string to be Added");
						count = sc.nextInt();
						if (choice < 0) {
							throw new IllegalArgumentException("Count must be greater than Zero!!");
						}
						sc.nextLine();
						String[] array = new String[count];
						logger.log(Level.INFO,"Enter a String to Write in the File");
						for (int i = 0; i < count; i++) {
							array[i] = sc.nextLine();
						}
						fh.createFileAndAddValues(fileName, array, "\n");
						logger.log(Level.INFO,"File Created Successfullt and Data Added");
					} catch (IllegalArgumentException e) {
						logger.log(Level.WARNING,"Illegal Arrgument Exception Occured", e);
					} catch (InputMismatchException e) {
						logger.log(Level.WARNING,"Please enter the Valid Input",e);
						sc.nextLine();
					} catch (IOException e) {
						printExceptionDetails(e);
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 2:
					logger.log(Level.INFO,"2.Store Data in Properties Class and Write it on the File");
					try {
						logger.log(Level.INFO,"Enter the Count of Key and Values to Save In Properties");
						count = sc.nextInt();
						if (count < 0) {
							throw new IllegalArgumentException("Count must be greater than Zero!!");
						}
						sc.nextLine();
						Properties prop = fh.getNewProperties();
						for (int i = 0; i < count; i++) {
							logger.log(Level.INFO,"Enter the Key");
							stringKey = sc.nextLine();
							logger.log(Level.INFO,"Enter the Value");
							stringValue = sc.nextLine();
							fh.setPropertiesData(prop, stringKey, stringValue);
						}
						logger.log(Level.INFO,prop.toString());
						logger.log(Level.INFO,"Enter the file Name");
						fileName = sc.nextLine();  
						logger.log(Level.INFO,"Enter the comments to add the File");
						String comment = sc.nextLine();
						fh.createFileWithProperites(fileName, comment, prop);
						logger.log(Level.INFO,"Properties Stored Successfully!!");
					} catch (IllegalArgumentException e) {
						logger.log(Level.WARNING,"Illegal Arrgument Exception Occured", e);
					} catch (InputMismatchException e) {
						logger.log(Level.WARNING,"Please enter the Valid Input",e);
						sc.nextLine();
					} catch (IOException e) {
						printExceptionDetails(e);
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 3:
					logger.log(Level.INFO,"3.Read Data from the file add it to the Properties Class");
					try {
						logger.log(Level.INFO,"Enter the file name to read the data");
						fileName = sc.nextLine();
						Properties prop = fh.readProperties(fileName);
						prop.list(System.out);
						logger.log(Level.INFO,"Reading Data From the File Successfully");
					} catch (FileNotFoundException e) {
						logger.log(Level.INFO,"File not found");
					} catch (IOException e) {
						printExceptionDetails(e);
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 5:
					logger.log(Level.INFO,"5.Print Values, While Printing Object");
					try {
						logger.log(Level.INFO,"Enter the String");
						stringValue = sc.nextLine();
						StringHolder stringHolder = new StringHolder(stringValue);
						logger.log(Level.INFO,stringHolder.toString());
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 6:
					logger.log(Level.INFO,"6.POJO class Operations with Parametrized Constructor");
					try {
						logger.log(Level.INFO,"Enter the String");
						stringValue = sc.nextLine();
						logger.log(Level.INFO,"Enter the Integer");
						count = sc.nextInt();
						Student student = new Student(stringValue, count);
						logger.log(Level.INFO,"The Student Object is "+student);
					} catch (InputMismatchException e) {
						logger.log(Level.WARNING,"Please enter the Valid Input",e);
						sc.nextLine();
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 7:
					logger.log(Level.INFO,"7.POJO class Operations With default Constructor");
					try {
						logger.log(Level.INFO,"Enter the Student Name");
						stringValue = sc.nextLine();
						logger.log(Level.INFO,"Enter the Student Number");
						count = sc.nextInt();
						Student student = new Student();
						student.setString(stringValue);
						student.setNum(count);
						logger.log(Level.INFO,"String :" + student.getString());
						logger.log(Level.INFO,"Number :" + student.getNum());
					} catch (InputMismatchException e) {
						logger.log(Level.WARNING,"Please enter the Valid Input",e);
						sc.nextLine();
					} catch (CustomException e) {
						printExceptionDetails(e);
					}
					break;
				case 8:
					logger.log(Level.INFO,"8.Working with Enums");
					try {
						logger.log(Level.INFO,"Values in the Enum");
						for (Rainbow rainbow : Rainbow.values()) {
							logger.log(Level.INFO,"Name :" + rainbow + " Value :" + rainbow.getValue() + " Index :"
									+ rainbow.ordinal());
						}
					} catch (Exception e) { 
						printExceptionDetails(e);
					}
					break;
				case 9:
					logger.log(Level.INFO,"9.Singletom Demo");
					try {
						SingletonDemo singeltomDemo1 = SingletonDemo.getInstance();
						SingletonDemo singeltomDemo2 = SingletonDemo.getInstance();
						
					    //SingletonDemo1 singletonDemo1 = SingletonDemo1.INSTANCE; // ENUM
					    
					    //logger.log(Level.INFO,singletonDemo1.INSTANCE.toString()); // O/P : INSTANCE

						if (singeltomDemo1.equals(singeltomDemo2)) {
							logger.log(Level.INFO,"Same");
							logger.log(Level.INFO,singeltomDemo1.toString());
						} else {
							logger.log(Level.INFO,"Different");
						}
					} catch (Exception e) {
						printExceptionDetails(e);
					}
					break;
				case 10:
					logger.log(Level.INFO,"10.Time Based Operations");
					try {
						logger.log(Level.INFO,"10.1 Get currentTime with Date, seconds etc");
						LocalDateTime currentTime = th.getCurrentTime();
						logger.log(Level.INFO,"The Current time is " + currentTime);

						long timeInMills = th.getCurrentTimeInmilliSeconds();
						logger.log(Level.INFO,"10.2 Current Time in MilliSeconds is " + timeInMills);

						logger.log(Level.INFO,"10.3 Enter the Zone to Get time and data etc..");
					
					    printZoneDetails(th.getZoneDetails());
						
						String zone = sc.nextLine();
						ZoneId id = ZoneId.of(zone);   
						
						ZonedDateTime currentTimeByZone = th.getCurrentTimeByZoneId(id);
						logger.log(Level.INFO, String.format("The Current time in the %s is %s", zone, currentTimeByZone));

						DayOfWeek day = th.getWeekday(timeInMills);
						logger.log(Level.INFO,"10.4 Week Day for the Current MillSeconds is - " + day);

						Month month = th.getMonthByMilliSeconds(timeInMills);
						logger.log(Level.INFO,"10.5 Month for the Current MillSeconds is - " + month);
    
						int year = th.getYearByMilliSeconds(timeInMills);
						logger.log(Level.INFO,"10.6 Year for the Current MillSeconds is - " + year);

					} catch (DateTimeException e) {
						printExceptionDetails(e);
					}
					break;
				case 0:
					logger.log(Level.INFO,"Exiting");
					runner = false;
					break;
				default:
					logger.log(Level.INFO,"Please Enter the Valid Choice");
				}
			}
		} catch (Exception e) {
			logger.log(Level.WARNING,"An Exception Occured",e);
		}finally {
			if(sc!=null) {
				sc.close();
			}
			if(fileHandler!=null) {
				fileHandler.close();
			}
		}
	}

	private static void printDetails() {
		logger.log(Level.INFO,"--------------------------------------------------------------");
		logger.log(Level.INFO,"--------------------------------------------------------------");
		logger.log(Level.INFO,"1. Create a File and Write Data on the File");
		logger.log(Level.INFO,"2.Store Data in Properties Class and Write it on the File");
		logger.log(Level.INFO,"3.Read Data from the file add it to the Properties Class");
		logger.log(Level.INFO,"5.Print Values, While Printing Object");
		logger.log(Level.INFO,"6.POJO class Operations with Parametrized Constructor");
		logger.log(Level.INFO,"7.POJO class Operations With default Constructor");
		logger.log(Level.INFO,"8.Working with Enums");
		logger.log(Level.INFO,"9.Singletom Demo");
		logger.log(Level.INFO,"10.Time Based Operations");
	}


	private static void printZoneDetails(Set<String> zoneDetails) {
		for (String zoneDetail : zoneDetails) {
		    logger.log(Level.INFO,zoneDetail);
		}
	}
	
	private static void printExceptionDetails(Exception e) {
			logger.log(Level.WARNING,"Exception : ",e);
	}
	
	 private static FileHandler createFileHandler(String logFileName) throws IOException {
	        FileHandler fileHandler = new FileHandler(logFileName, true);
	        fileHandler.setFormatter(new SimpleFormatter());
	        return fileHandler;
	}
}
