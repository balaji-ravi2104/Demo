package runner;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;
import com.inheritance.bird.Duck;
import com.inheritance.bird.ParrotMod;
import com.inheritance.car.Car;
import com.inheritance.car.Swift;
import com.inheritance.car.Scross;
import com.inheritance.car.XUV;
import exception.CustomException;
import utils.UtilMethods;
import java.util.InputMismatchException;

public class Runner {
	private static final Logger logger = Logger.getLogger(Runner.class.getName());

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice;
		boolean isProgramAlive = true;
		try {
			while (isProgramAlive) {
				try {
					logger.log(Level.INFO, "Enter your choice");
					choice = sc.nextInt();
				} catch (InputMismatchException e) {
					logger.log(Level.WARNING,
							"Please enter the correct choice!!! Avoid entering the Characters for an Integer!!", e);
					sc.nextLine();
					continue;
				}

				sc.nextLine();
				switch (choice) {
				case 1:
					logger.log(Level.INFO, "1.Create an instance of Swift and get Details");
					try {
						Swift swift = new Swift();
						logger.log(Level.INFO, "Enter the seat size of the Swift");
						swift.setSeats(sc.nextInt());
						logger.log(Level.INFO, "Enter the Airbag size of the Swift");
						swift.setAirBag(sc.nextInt());
						sc.nextLine();
						logger.log(Level.INFO, "Enter the Model of the Swift");
						swift.setModel(sc.nextLine());
						logger.log(Level.INFO, "Enter the Color of the Swift");
						swift.setColor(sc.nextLine());
						logger.log(Level.INFO, "The details of the Swift");
						logger.log(Level.INFO, "Swift Seats " + swift.getSeats());
						logger.log(Level.INFO, "Swift Airbag " + swift.getAirBag());
						logger.log(Level.INFO, "Swift Model " + swift.getModel());
						logger.log(Level.INFO, "Swift Color " + swift.getColor());
					} catch (InputMismatchException e) {
						logger.log(Level.WARNING, "Please enter the valid input", e);
						sc.nextLine();
					} catch (CustomException e) {
						logger.log(Level.WARNING, "An Exception Occured", e);
					}
					break;
				case 2:
					logger.log(Level.INFO, "1.Create an instance of Scross and get Details");
					try {
						Scross scross = new Scross();
						logger.log(Level.INFO, "Enter the seat size of the Scross");
						scross.setSeats(sc.nextInt());
						logger.log(Level.INFO, "Enter the Airbag size of the Scross");
						scross.setAirBag(sc.nextInt());
						sc.nextLine();
						logger.log(Level.INFO, "Enter the Model of the Scross");
						scross.setModel(sc.nextLine());
						logger.log(Level.INFO, "Enter the Color of the Scross");
						scross.setColor(sc.nextLine());
						logger.log(Level.INFO, "Enter the Making year of the Scross");
						scross.setYearOfMake(sc.nextInt());
						sc.nextLine();
						logger.log(Level.INFO, "Enter the Engine Number of the Scross");
						scross.setEngineNumber(sc.nextLine());
						logger.log(Level.INFO, "Enter the Type of the Scross");
						scross.setType(sc.nextLine());

						logger.log(Level.INFO, "Details of the Scross");
						logger.log(Level.INFO, "Scross Seats " + scross.getSeats());
						logger.log(Level.INFO, "Scross Airbag " + scross.getAirBag());
						logger.log(Level.INFO, "Scross Model " + scross.getModel());
						logger.log(Level.INFO, "Scross Color " + scross.getColor());
						logger.log(Level.INFO, "Scross Year Of Making " + scross.getYearOfMake());
						logger.log(Level.INFO, "Scross Engine Number " + scross.getEngineNumber());
						logger.log(Level.INFO, "Scross Type " + scross.getType());

					} catch (InputMismatchException e) {
						logger.log(Level.WARNING, "Please enter the valid input", e);
						sc.nextLine();
					} catch (CustomException e) {
						logger.log(Level.WARNING, "An Exception Occured", e);
					}
					break;
				case 3:
					logger.log(Level.INFO, "3.Create instance of Swift,Scross and XUV and Print Corresponding Type");
					try {
						Swift swift1 = new Swift();
						Scross scross1 = new Scross();
						XUV xuv = new XUV();
						carObject(swift1);
						carObject(scross1);
						carObject(xuv);
					} catch (CustomException e) {
						logger.log(Level.WARNING, "An Exception Occured", e);
					}
					break;
				case 4:
					logger.log(Level.INFO, "4.In Swift Object Accepting diff types of Object");
					try {
						Swift swift2 = new Swift();
						swiftObject(swift2);
						// Car car = new Swift(); //incompatible types: Car cannot be converted to Swift
						// swiftObject(car);
						// Scross scross2 = new Scross();
						// swiftObject(scross2);
						// XUV xuv1 = new XUV();
						// swiftObject(xuv1);
					} catch (CustomException e) {
						logger.log(Level.WARNING, "An Exception Occured", e);
					}
					break;
				case 5:
					logger.log(Level.INFO, "Calling Maintenance method in different Ways");
					Scross scross3 = new Scross();
					scross3.maintenance(); // Maruti SCross under maintenance

					Car car1 = new Scross();
					car1.maintenance(); // Maruti SCross under maintenance

					Car car2 = new Car();
					car2.maintenance(); // Car Under maintenance

					Swift swift3 = new Swift();
					swift3.maintenance(); // Car Under maintenance
					break;
				case 6:
					logger.log(Level.INFO, "6.Calling Car Constructor using XUV Constructor");
					XUV xuv2 = new XUV();

					// XUV xuv3 = new XUV("XUV using overloaded constructor"); //constructor XUV in
					// class XUV cannot be applied to given types
					break;
				case 7:
					logger.log(Level.INFO, "7.BirdAbstract Calss Methods Calling");
					// BirdAbstract birdAbstract = new BirdAbstract(); // BirdAbstract is abstract;
					// cannot be instantiated
					// birdAbstract.fly();
					// birdAbstract.speak();

					ParrotMod parrotMod = new ParrotMod();
					parrotMod.fly();
					parrotMod.speak();
					break;
				case 8:
					logger.log(Level.INFO, "8.Bird Calss Methods Calling");
					Duck duck = new Duck();
					duck.fly();
					duck.speak();
					break;
				case 0:
					logger.log(Level.INFO,"Exiting...");
					isProgramAlive = false;
					break;
				default:
					logger.log(Level.INFO, "Please enter the valid choice");
				}
			}
		} catch (Exception e) {
			logger.log(Level.INFO, "Error " + e.getMessage());
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}

	private static void carObject(Car car) throws CustomException {
		UtilMethods.isNull(car, "Car Object Cannot be NUll");
		if (car instanceof Swift) {
			logger.log(Level.INFO, "Hatch");
		}
		if (car instanceof Scross) {
			logger.log(Level.INFO, "Sedan");     
		}
		if (car instanceof XUV) {
			logger.log(Level.INFO, "SUV");
		}
	}

	private static void swiftObject(Swift swift) throws CustomException {
		UtilMethods.isNull(swift, "Swift Object cannot be NUll");
		logger.log(Level.INFO, swift.getClass().toString());
	}
}
