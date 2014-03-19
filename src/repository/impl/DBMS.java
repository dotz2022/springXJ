package repository.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import repository.Course;



public class DBMS {
	private static final String connectionString =
			"jdbc:derby:data/systemdb;create=true";

	
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
	
	
	
	public ResultSet getAllSessionsViaCourse(int courseID)
			throws SQLException{
			Connection connection = getDatabaseConnection();
			
			String sqlString = 
				"SELECT SESSIONID, SESSION.ROOMID, SessionName, ROOM.VENUE, STARTTIME, ENDTIME, COMPULSORY"
				+ " FROM SESSION, ROOM WHERE ROOM.ROOMID = SESSION.ROOMID AND COURSEID=" + courseID;
			
			PreparedStatement preparedStatement =
				connection.prepareStatement(sqlString);
					
			ResultSet result = preparedStatement.executeQuery();
	
	
			return result;	
			
	
		}
	

	
	

	/**
	Insert into dummy user data into MYCampus
	 */
	public void insertDummyUsers()
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
	
	
	void updateSessionTable() throws SQLException {
		
Connection connection = 
	getDatabaseConnection();

String statementString = "ALTER TABLE Session" +
		" ADD FREQUENCY VARCHAR(30)";

PreparedStatement statement = 
	connection.prepareStatement(statementString);

statement.execute();

		
	}
	
	
	/**
	Insert into dummy course data into MYCampus
	 */
	public void insertSession()
		throws SQLException {
						
		Connection connection = 
			getDatabaseConnection();

		String statementString = 
			"INSERT INTO SESSION (CourseName) VALUES"
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
	Insert session into systemdb
	 */
	public void createSession(HashMap<String, String> m)
		throws SQLException {
		
		Connection connection = 
			getDatabaseConnection();

		String statementString = 
			"INSERT INTO SESSION (CourseID, SessionName, startTime, endTime, Compulsory, RoomID, FREQUENCY) VALUES"
		    + " (" + m.get("CourseID") + ", '" + m.get("SessionName") + "','"
		    + m.get("StartTime")  + "', '" + m.get("EndTime") + "', "
		    + m.get("Compulsory") + ", " + m.get("RoomID") + ", '" + m.get("FREQUENCY") + "')";
		
		PreparedStatement statement = 
			connection.prepareStatement(statementString);
	
		statement.execute();
	}
	

	/**
	Insert imporCourse into System DB
	 */
	public void insertImportedCourse()
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
	Insert imporCourse into System DB
	 */
	public void insertDummyDataRoom()
		throws SQLException {
						
		Connection connection = 
			getDatabaseConnection();
		
		String statementString = 
			"INSERT INTO ROOM (Venue, CAPACITY) VALUES"
			+ "('E6 Lecture Room', 60 ) ,"
			+ "('E3 Lecture Room', 60) ,"
			+ "('E1 Lab Room', 50) ,"
			+ "('E4 Lab Room', 50) ,"
			+ "('Convention Hall', 1000) ";

		PreparedStatement statement = 
			connection.prepareStatement(statementString);
	
		statement.execute();
	}
	
	/**
	Insert imporCourse into System DB
	 */
	public void insertDummyLectureSession()
		throws SQLException {
						
		Connection connection = 
			getDatabaseConnection();
		
		String statementString = 
			"INSERT INTO StudentInSession (SessionID, GUID) VALUES"
			+ "(3, '2109933C') ,"
			+ "(4, '2109933C') ,"
			+ "(5, '2109933C') ,"
			+ "(6, '2109933C') ";
		
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
	
	public ResultSet getAllRooms()
			throws SQLException{
			Connection connection = getDatabaseConnection();
			
			String sqlString = 
				"SELECT * FROM ROOM";
			
			PreparedStatement preparedStatement =
				connection.prepareStatement(sqlString);
					
			ResultSet result = preparedStatement.executeQuery();
			
			return result;	
		}
	
	
	public ResultSet viewOwnSession(String userID) 
			throws SQLException{
			Connection connection = getDatabaseConnection();
			
			String sqlString = 
				"SELECT * FROM studentinSession WHERE GUID= '" + userID  + "'";
			
			PreparedStatement preparedStatement =
				connection.prepareStatement(sqlString);
					
			ResultSet result = preparedStatement.executeQuery();
			
			return result;	
		}
	
	public void insertCourseRow(Course course)
			throws SQLException {
							
			Connection connection = 
				getDatabaseConnection();

			String statementString = 
		       "INSERT INTO Course (CourseID, CourseName) " + 
			   "VALUES (" + course.getCourseID() + ", '" + course.getCourseName() + "')";
			
					
			PreparedStatement statement = 
				connection.prepareStatement(statementString);
				
			try {
				statement.execute();
				
			} catch (Exception e) {
				
				System.out.println("You have already imported this course!");
			}
			
		}
		
public static Connection getDatabaseConnection ()
		throws SQLException{
				
		return DriverManager.getConnection(connectionString);
	}	
	
	

	public void dropTableUsers()
		throws SQLException {
						
		Connection connection = 
			getDatabaseConnection();

		String statementString = 
			"DROP TABLE SESSION";
		PreparedStatement statement = 
			connection.prepareStatement(statementString);
	
		statement.execute();
	}

	

}
