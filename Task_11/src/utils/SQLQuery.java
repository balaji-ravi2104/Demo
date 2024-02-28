package utils;

public class SQLQuery {
	public static final String TABLE_CREATE_QUERY = "Create table Employee "
			+ "(EMPLOYEE_ID int AUTO_INCREMENT primary key,NAME varchar(50),"
			+ "MOBILE varchar(15),EMAIL varchar(50),DEPARTMENT varchar(50))"
			+ "AUTO_INCREMENT=101;";
		
	public static final String INSERT_QUERY = "INSERT INTO Employee (NAME, "
			+ "MOBILE, EMAIL, DEPARTMENT) VALUES (?, ?, ?, ?)";
	
	public static final String SELECT_BY_FIELD = "SELECT * FROM Employee WHERE %s = ?";
	
	public static final String UPDATE_QUERY = "UPDATE Employee SET %s = ? WHERE %s = ?";
	
	public static final String GET_FIRST_N_EMPLOYEE = "SELECT *FROM Employee LIMIT ?";
	
	public static final String GET_FIRST_N_EMPLOYEE_IN_ORDER = "SELECT * FROM Employee "
			+ "ORDER BY %s %s LIMIT ?;";
	
	public static final String DELETE_EMPLOYEE = "DELETE FROM Employee WHERE %s = ?";
	
	public static final String DEPENDENT_TABLE_CREATE_QUERY = "CREATE TABLE Dependent "
			+ "(Employee_id int, Name varchar(50),Age int,Relationship varchar(50), "
			+ "foreign key (Employee_id) references Employee(Employee_id) ON DELETE CASCADE)";
	
	public static final String INSERT_DEPENDENT_QUERY = "insert into Dependent values(?,?,?,?)";
	
	public static final String SELECT_DEPENDENT_QUERY = "SELECT Employee.employee_id,Employee.Name,"
			+ "Dependent.Name,Dependent.Age,Dependent.Relationship from Employee INNER JOIN Dependent "
			+ "on Employee.Employee_id = Dependent.Employee_id WHERE Employee.%s = ?";
	
	public static final String GET_FIRST_N_DEPENDENT_DETAILS = "SELECT Employee.Employee_id,Employee.Name,"
			+ "Dependent.Name,Dependent.Age,Dependent.Relationship FROM Employee JOIN Dependent ON "
			+ "Employee.Employee_id = Dependent.Employee_id JOIN (SELECT Employee_id from Employee LIMIT ?) "
			+ "AS sub ON Employee.Employee_id = sub.Employee_id ORDER BY Employee.%s %s";
}
