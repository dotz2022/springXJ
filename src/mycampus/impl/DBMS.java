package mycampus.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DBMS {
	private static final String connectionString =
			"jdbc:derby:data/mycampus;create=true";

	
	
	public ResultSet getAllCourseRows()
			throws SQLException{
			Connection connection = getDatabaseConnection();
			
			String sqlString = 
				"SELECT * FROM COURSE";
			
			PreparedStatement preparedStatement =
				connection.prepareStatement(sqlString);
					
			ResultSet result = preparedStatement.executeQuery();
			
			return result;	
		}
	
	
	public ResultSet getCourse(int courseID)
			throws SQLException{
			Connection connection = getDatabaseConnection();
			
			String sqlString = 
				"SELECT * FROM COURSE WHERE COURSEID =" + courseID;
			
			PreparedStatement preparedStatement =
				connection.prepareStatement(sqlString);
					
			ResultSet result = preparedStatement.executeQuery();
			
			return result;	
		}
	
	

	/**
	Insert into dummy user data into MYCampus
	 */
	static public void insertDummyUsers()
		throws SQLException {
						
		Connection connection = 
			getDatabaseConnection();

		String statementString = 
			"INSERT INTO USERS (GUID, UserName, Password, RoleID) VALUES"
			+ "('2109933C', 'XiangJie', 'random', 1) ,"
			+ "('1234567C', 'LiewPaKSan', 'random', 2) ,"
			+ "('abcdefgH', 'Frankie', 'random', 3)";

		PreparedStatement statement = 
			connection.prepareStatement(statementString);
	
		statement.execute();
	}
	
	public boolean insertTestUsers(String GUID, String userName, String password, int roleID)
			throws SQLException {
							
			Connection connection = 
				getDatabaseConnection();

			String statementString = 
				"INSERT INTO USERS (GUID, UserName, Password, RoleID) VALUES"
				+ "('" + GUID + "', '" + userName + "', '" + password + "', " + roleID + ")";
		

			PreparedStatement statement = 
				connection.prepareStatement(statementString);
		
			statement.execute();
			return true;
		}
	
	
	/**
	Insert into dummy course data into MYCampus
	 */
	public void insertDummyCourses()
		throws SQLException {
						
		Connection connection = 
			getDatabaseConnection();

		String statementString = 
			"INSERT INTO COURSE (CourseName) VALUES"
			+ "('PSD3') ,"
			+ "('ALG3') ,"
			+ "('DIM3') ,"
			+ "('PL3') ,"
			+ "('AP3') ";

		PreparedStatement statement = 
			connection.prepareStatement(statementString);
	
		statement.execute();
	}


	
	/**
	Check TableExists
	 */
	public Boolean tableExists(String tableName) 
			throws SQLException{
			
		Connection connection =
			getDatabaseConnection();
			
		DatabaseMetaData metaData = 
			connection.getMetaData();
							
		ResultSet resultSet = 
			metaData.getTables(
				null,
				null,
				tableName.toUpperCase(),
				new String[]{"TABLE"});
			
		Boolean result = resultSet.next();

		return result;
	}
		

	public void createTable(String tableName, String specification)
		throws SQLException{
		
		String statementString =
			"CREATE TABLE "+ tableName +
		    "  (" + specification +"  )"; 
	
		Connection connection =
			getDatabaseConnection();
			
		Statement statement = connection.createStatement();
		statement.execute(statementString);

	}
	

	
	public ResultSet login(String userName, String password)
			throws SQLException{
			Connection connection = getDatabaseConnection();
			
			String sqlString = 
				"SELECT * FROM Users Where GUID = '" + userName + "' AND " +
			    "password = '" + password + "'";
			
			PreparedStatement preparedStatement =
				connection.prepareStatement(sqlString);
					
			ResultSet result = preparedStatement.executeQuery();
			
			return result;	
		}
	
		
	private static Connection getDatabaseConnection ()
		throws SQLException{
				
		return DriverManager.getConnection(connectionString);
	}	
	
	

	public void dropTableUsers()
		throws SQLException {
						
		Connection connection = 
			getDatabaseConnection();

		String statementString = 
			"DROP TABLE Users";
	
		PreparedStatement statement = 
			connection.prepareStatement(statementString);
	
		statement.execute();
	}


}
