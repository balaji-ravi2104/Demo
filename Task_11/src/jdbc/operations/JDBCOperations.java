package jdbc.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exception.CustomException;
import pojo.classes.Dependent;
import pojo.classes.Employee;
import utils.JDBCConnector;
import utils.SQLQuery;

public class JDBCOperations {

	public void createTable() throws CustomException {
		try (Connection connection = JDBCConnector.getConnection();
				Statement statement = connection.createStatement()) {
			statement.execute(SQLQuery.TABLE_CREATE_QUERY);
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error in Creating table " + e.getMessage(), e);
		}
	}

	public void createDependentTable() throws CustomException {
		try (Connection connection = JDBCConnector.getConnection();
				Statement statement = connection.createStatement()) {
			statement.execute(SQLQuery.DEPENDENT_TABLE_CREATE_QUERY);
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error in Creating Dependent Table " + e.getMessage(), e);
		}
	}

	public int insertValues(Employee employee) throws CustomException {
		try (Connection connection = JDBCConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.INSERT_QUERY)) {
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getMobile());
			preparedStatement.setString(3, employee.getEmail());
			preparedStatement.setString(4, employee.getDept());
			return preparedStatement.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error in Inserting table " + e.getMessage(), e);
		}
	}
	
	public int insertDependentValues(Dependent dependent) throws CustomException {
		try (Connection connection = JDBCConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.INSERT_DEPENDENT_QUERY)) {
			preparedStatement.setInt(1, dependent.getId());
			preparedStatement.setString(2, dependent.getDependentName());
			preparedStatement.setInt(3, dependent.getAge());
			preparedStatement.setString(4, dependent.getRelationship());
			return preparedStatement.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error in Inserting Data to the Table " + e.getMessage(), e);
		}
	}

	public <E> List<E> getDetailsByField(String field, String value) throws CustomException {
		String query = String.format(SQLQuery.SELECT_BY_FIELD,field);
		try (Connection connection = JDBCConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, value);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return processResultSet(resultSet);
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error in Reterving Data From table " + e.getMessage(), e);
		}
	}

	public int updateEmployee(String baseField,String baseFieldvalue,String updateField ,String updateValue) throws CustomException {
		String query = String.format(SQLQuery.UPDATE_QUERY,updateField,baseField);
		try (Connection connection = JDBCConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, updateValue);
			preparedStatement.setString(2, baseFieldvalue); 
			return preparedStatement.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error in Updating Table " + e.getMessage(), e);
		}
	}

	public <E> List<E> getFirstNEmployee(int n) throws CustomException {
		try (Connection connection = JDBCConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.GET_FIRST_N_EMPLOYEE)) {
			preparedStatement.setInt(1, n);
			ResultSet resultSet = preparedStatement.executeQuery();
			return processResultSet(resultSet);
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error in Getting Data from the Table  " + e.getMessage(), e);
		}
	}

	public <E> List<E> getFirstNEmployeeInOrder(int n,String field,String order) throws CustomException {
		String query = String.format(SQLQuery.GET_FIRST_N_EMPLOYEE_IN_ORDER, field,order);
		try (Connection connection = JDBCConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, n);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return processResultSet(resultSet);
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error in Getting Data from the Table " + e.getMessage(), e);
		}
	}

	public int deleteEmployeeByField(String field,String value) throws CustomException {
		String query = String.format(SQLQuery.DELETE_EMPLOYEE, field);
		try (Connection connection = JDBCConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, value);
			return preparedStatement.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error in deleting Data from the Table " + e.getMessage(), e);
		}
	}

	public <E> List<E> getDependentDetail(String field,String value) throws CustomException {
		String query = String.format(SQLQuery.SELECT_DEPENDENT_QUERY, field);
		try (Connection connection = JDBCConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, value);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return processDependentResultSet(resultSet);
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error in Getting Data from the Dependent Table " + e.getMessage(), e);
		}
	}

	public <E> List<E> getFirstNDependent(int n,String field,String order) throws CustomException {
		String query = String.format(SQLQuery.GET_FIRST_N_DEPENDENT_DETAILS, field,order);
		try (Connection connection = JDBCConnector.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(query)) {
			preparedStatement.setInt(1, n);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return processDependentResultSet(resultSet); 
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new CustomException("Error in Getting Data from the Dependent Table " + e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	private <E> List<E> processDependentResultSet(ResultSet resultSet) throws SQLException {
		List<E> dependents = new ArrayList<>();
		while (resultSet.next()) {
			Dependent dependent = new Dependent();
			dependent.setId(resultSet.getInt(1));
			dependent.setName(resultSet.getString(2));
			dependent.setDependentName(resultSet.getString(3));
			dependent.setAge(resultSet.getInt(4));
			dependent.setRelationship(resultSet.getString(5));

			dependents.add((E) dependent);
		}
		return dependents;
	}

	@SuppressWarnings("unchecked")
	private <E> List<E> processResultSet(ResultSet resultSet) throws SQLException {
		List<E> employees = new ArrayList<>();
		while (resultSet.next()) {
			Employee employee = new Employee();
			employee.setId(resultSet.getInt(1));
			employee.setName(resultSet.getString(2));
			employee.setMobile(resultSet.getString(3));
			employee.setEmail(resultSet.getString(4));
			employee.setDept(resultSet.getString(5));

			employees.add((E) employee);
		}
		return employees;
	}

}