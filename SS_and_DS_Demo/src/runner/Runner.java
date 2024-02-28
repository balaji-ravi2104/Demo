package runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import classes.Employee;
import exception.CustomException;
import operations.Helper;

public class Runner {
	private static final Logger logger = Logger.getLogger(Runner.class.getName());       

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Helper helper = new Helper();
		try {
			int choice = 0, count, id; 
			boolean isProgramAlive = true;
			String fileName, empName, empDept;
			while (isProgramAlive) {
				logger.info("Enter the choice");
				choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 1:
					try {
						logger.info("1.Serializing an Object");
						logger.info("Enter the file name to Serialize an Object");
						fileName = sc.nextLine();
						logger.info("Enter the number of object to be saved");
						count = sc.nextInt();
						List<Employee> empList = new ArrayList<>();
						for (int i = 1; i <= count; i++) {
							Employee employee = new Employee();
							logger.info("Enter the Employee Id");
							id = sc.nextInt();
							sc.nextLine();
							logger.info("Enter the Employee Name");
							empName = sc.nextLine();
							logger.info("Enter the Employee Dept");
							empDept = sc.nextLine();

							employee.setId(id);
							employee.setName(empName);
							employee.setDept(empDept);

							empList.add(employee);
						}
						helper.serializeListOfObject(empList, fileName);
						logger.info("Employees Object Serialized Successfully!!!");
					} catch (CustomException e) {
						logger.info("Error : " + e.getMessage());
					}
					break;
				case 2:
					logger.info("1.DeSerializing an Object");
					try {
						logger.info("Enter the file name to DeSerialize an Objects");
						fileName = sc.nextLine();
						List<Employee> empList = helper.deserializeObjects(fileName);
						helper.deserializeObjects(fileName);

						printEmployeeDetails(empList);
					} catch (CustomException e) {
						//e.printStackTrace();
						logger.info("Error : " + e.getMessage());
					}
					break;
				case 0:
					isProgramAlive = false;
					logger.info("Exiting..");
					break;
				default:
					logger.info("Please Enter the Valid choice");
					break;
				}
			}
		} catch (Exception e) {
			logger.warning("Error : "+e.getMessage());
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}

	private static void printEmployeeDetails(List<Employee> empList) {
		for (Employee employee : empList) {
			logger.info("----------------------------------------------");
			logger.info("Employee Id :" + employee.getId());
			logger.info("Employee Name :" + employee.getName());
			logger.info("Employee Dept :" + employee.getDept());
			  logger.info("Employee serialVersionUID :" + employee.getSerialversionuid()); // Accessing serialVersionUID
			logger.info("----------------------------------------------");
		}
	}

}
