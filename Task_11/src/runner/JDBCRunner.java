package runner;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import exception.CustomException;
import jdbc.operations.JDBCOperations;
import pojo.classes.Dependent;
import pojo.classes.Employee;
public class JDBCRunner {
	private static final Logger logger = Logger.getLogger(JDBCRunner.class.getName());

	public static <E> void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		JDBCOperations jdbcOperations = new JDBCOperations();
		int choice;
		boolean isProgramAlive = true;	
		try {
			while (isProgramAlive) {
				try {
					logger.log(Level.INFO, "Enter your choice");
					choice = sc.nextInt();
					if (choice < 0) {
						throw new CustomException("Choice must be greater than Zero!!");
					}
				} catch (InputMismatchException e) {
					logger.log(Level.WARNING,
							"Please enter the correct choice!!! Avoid entering the Characters for an Integer!!");
					sc.nextLine();
					continue;
				} catch (CustomException e) {
					logger.log(Level.WARNING, e.getMessage());
					sc.nextLine();
					continue;
				}
				sc.nextLine();
				switch (choice) {
				case 1:
					logger.info("1.Creating A Employee Table");
					try {
						jdbcOperations.createTable();
						logger.info("Table Created Successfully!!");
					} catch (CustomException e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 2:
					logger.info("2.Inserting into Employee Table");
					try {
						logger.info("Enter the Employee Name");
						String name = sc.nextLine();
						logger.info("Enter the Mobile Number");
						String mobile = sc.nextLine();
						logger.info("Enter the Email ID");
						String email = sc.nextLine();
						logger.info("Enter the Department");
						String dept = sc.nextLine();
						
						int noRowsAffected = jdbcOperations.insertValues(employeeDetails(name, mobile, email, dept));
						logger.info("Number of rows Affected " + noRowsAffected);
					} catch (InputMismatchException e) {
						logger.warning("Please Enter the Valid Input");
					} catch (CustomException e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 3:
					logger.info("3.Reterving Details By Using Fields");
					try {
						logger.info("Enter the Field to Get Details");
						String field = sc.nextLine();
						logger.info("Enter the value to get Details");
						String value = sc.nextLine();
						List<E> resultList = jdbcOperations.getDetailsByField(field,value);
						printEmployeeList(resultList);
					} catch (CustomException e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 4:
					logger.info("4.Update the Employee Detail");
					try {
						logger.info("Enter the Field to Update");
						String field = sc.nextLine();
						logger.info("Enter the value to Update");
						String value = sc.nextLine();
						logger.info("Enter the Base field to Update");
						String baseField = sc.nextLine();
						logger.info("Enter the Base field value to Update");
						String baseFieldValue = sc.nextLine();
						int rowsAffected = jdbcOperations.updateEmployee(baseField,baseFieldValue,field,value);
						logger.info("Number of rows Affected " + rowsAffected);
					} catch (CustomException e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 5:
					logger.info("5.Get first N number of Employees Details");
					try {
						logger.info("Enter the N value to get the Details");
						int n = sc.nextInt();
						if(n<0) {
							throw new CustomException("Please Enter the valid N value to get the Detail. Please Enter the Positive Number");
						}
						List<E> resultList = jdbcOperations.getFirstNEmployee(n);
						printEmployeeList(resultList);
					} catch (CustomException e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 6:
					logger.info("6.Get first N number of Employees Details Order By Field");
					try {
						logger.info("Enter the N value to get the Details");
						int n = sc.nextInt();
						if(n<0) {
							throw new CustomException("Please Enter the valid N value to get the Detail. Please Enter the Positive Number");
						}
						sc.nextLine();
						logger.info("Enter the Field to be Ordered");
						String field = sc.nextLine();
						logger.info("Please Choose the order type");
						logger.info("1.Ascending Order");
						logger.info("2.Descending Order");
						logger.info("Enter your choice");
						int type = sc.nextInt();
						if(type<0 || type>2) {
							throw new CustomException("Please Enter the Valid Choice Either 1 or 2");
						}
						String order = type == 1?"ASC":"DESC";
						List<E> resultList = jdbcOperations.getFirstNEmployeeInOrder(n,field,order);
						printEmployeeList(resultList);
					} catch (CustomException e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 7:
					logger.info("7.Delete Employee");
					try {
						logger.info("Enter the field to delete the Employee");
						String field = sc.nextLine();
						logger.info("Enter the Field value for deleting");
						String value = sc.nextLine();
						int rowsAffected = jdbcOperations.deleteEmployeeByField(field,value);
						logger.info("Number of rows Affected " + rowsAffected);
					}catch (CustomException e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 8:
					logger.info("8.Creating the dependent table for the employee table");
					try {
						jdbcOperations.createDependentTable();
						logger.info("Dependent Table Created Successfully!!");
					}catch (CustomException e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 9:
					logger.info("9.Inserting Values into Dependent Table");
					try {
						logger.info("Enter the Employee Id");
						int id = sc.nextInt();
						if(id<=0) {
							throw new CustomException("Employee Id should be a Positive or greater than zero.");
						}
						sc.nextLine();
						logger.info("Enter the Dependent Name");
						String name = sc.nextLine();
						logger.info("Enter the Dependent Age");
						int age = sc.nextInt();
						sc.nextLine();
						logger.info("Enter the Dependent RelationShip With Employee");
						String status = sc.nextLine();
						int noRowsAffected = jdbcOperations.insertDependentValues(dependentDetails(id,name,age,status));
						logger.info("Number of rows Affected " + noRowsAffected);
					}catch (CustomException e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 10:
					logger.info("10.Displaying Dependent Details");
					try {
						logger.info("Enter the Field to Get Details");
						String field = sc.nextLine();
						logger.info("Enter the Field Value to Get Details");
						String value = sc.nextLine();
						List<E> dependentList = jdbcOperations.getDependentDetail(field,value);
						printDependentList(dependentList);
					}catch (CustomException e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 11:
					logger.info("11.Display First N Dependent Details Order By Employee Name");
					try {
						logger.info("Enter the N value to get the Detail");
						int n = sc.nextInt();
						if(n<0) {
							throw new CustomException("Please Enter the valid N value to get the Detail. Please Enter the Positive Number");
						}
						sc.nextLine();
						logger.info("Enter the Field to be Ordered");
						String field = sc.nextLine();
						logger.info("Please Choose the order type");
						logger.info("1.Ascending Order");
						logger.info("2.Descending Order");
						logger.info("Enter your choice");
						int type = sc.nextInt();
						if(type<0 || type>2) {
							throw new CustomException("Please Enter the Valid Choice Either 1 or 2");
						}
						String order = type == 1?"ASC":"DESC";
						List<E> dependentList = jdbcOperations.getFirstNDependent(n,field,order);
						printDependentList(dependentList);
					}catch (CustomException e) {
						logger.log(Level.WARNING, e.getMessage());
					}
					break;
				case 0:
					isProgramAlive = false;
					logger.info("Exiting..");
					break;
				default:
					logger.info("Please Enter the valid Choice");
				}
			}
		} catch (Exception e) {
			logger.log(Level.WARNING, "An Exception Occured", e);
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}

	private static <E> void printDependentList(List<E> dependentList) {
		System.out.println("Dependent Details:");
		System.out.println("--------------------------------------------------");
		for(E dependent:dependentList) {
			System.out.println("Employee ID :"+((Dependent) dependent).getId());
			System.out.println("Employee Name :"+((Dependent) dependent).getName());
			System.out.println("Dependent Name :"+((Dependent) dependent).getDependentName());
			System.out.println("Dependent Age :"+((Dependent) dependent).getAge());
			System.out.println("RelationShip with Employee :"+((Dependent) dependent).getRelationship());
			System.out.println("--------------------------------------------------");
		}
	}

	private static <E> void printEmployeeList(List<E> resultList) {
		System.out.println("Employee Details:");
		System.out.println("--------------------------------------------------");
		for (E employee : resultList) {
			System.out.println("ID: " + ((Employee) employee).getId());
			System.out.println("Name: " + ((Employee) employee).getName());
			System.out.println("Mobile: " + ((Employee) employee).getMobile());
			System.out.println("Email: " + ((Employee) employee).getEmail());
			System.out.println("Department: " + ((Employee) employee).getDept());
			System.out.println("--------------------------------------------------");
		}
	}
	
	private static Employee employeeDetails(String name,String mobile,String email,String dept) {
		Employee employee = new Employee();
		employee.setName(name);
		employee.setMobile(mobile);
		employee.setEmail(email);
		employee.setDept(dept);
		return employee;
	}
	
	private static final Dependent dependentDetails(int id,String name,int age,String relationship) {
		Dependent dependent = new Dependent();
		dependent.setId(id);
		dependent.setDependentName(name);
		dependent.setAge(age);
		dependent.setRelationship(relationship);
		return dependent;
	}

//	private static void printResultSetDetails(ResultSet resultSet) throws CustomException {
//		try {
//		    ResultSetMetaData metaData = resultSet.getMetaData();
//		    int columnCount = metaData.getColumnCount();
//		    System.out.println(columnCount);
//		    for (int i = 1; i <= columnCount; i++) {
//		        System.out.print(metaData.getColumnName(i) + "\t");
//		    }
//		    System.out.println();
//
//
//		    while (resultSet.next()) {
//		        
//		        for (int i = 1; i <= columnCount; i++) {
//		           
//		            int columnType = metaData.getColumnType(i);
//		            if (columnType == Types.INTEGER) {
//		                System.out.print(resultSet.getInt(i) + "\t");
//		            } else if (columnType == Types.FLOAT || columnType == Types.DOUBLE) {
//		                System.out.print(resultSet.getDouble(i) + "\t");
//		            } else if (columnType == Types.BOOLEAN) {
//		                System.out.print(resultSet.getBoolean(i) + "\t");
//		            } else if (columnType == Types.DATE || columnType == Types.TIMESTAMP) {
//		                System.out.print(resultSet.getDate(i) + "\t");
//		            } else {
//		                System.out.print(resultSet.getString(i) + "\t");
//		            }
//		        }
//		        System.out.println();
//		    }
//		} catch (SQLException e) {
//		    throw new CustomException("Error While Displaying Details",e);
//		}
//
//	}
}
