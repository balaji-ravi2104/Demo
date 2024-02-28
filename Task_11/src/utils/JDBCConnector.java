package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.CustomException;

public class JDBCConnector {
	private static final String url = "jdbc:mysql://localhost:3306/employeeDB";
	private static final String userName = "root";
	private static final String password = "Balaji@123";

	public static Connection getConnection() throws ClassNotFoundException, CustomException {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			throw new CustomException("An Error Occured During the Connection Creation",e);
		} 
		return connection;
	}
}
