package mycampus.impl;


import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import mycampus.IMyCampusService;
import mycampus.User;
import repository.Course;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;



public class MyCampusHandler implements IMyCampusService {
	


	private DBMS dbms;
	User user;
	
	
	public MyCampusHandler (DBMS dbms)
		throws SQLException{
		
		
		this.dbms = dbms;
		

		if (!checkTableExists("course")) {
			 
			/* 
			 *  CREATE DUMMY DATA FOR COURSES
			 */
			createCourseTable();
	      	dbms.insertDummyCourses();
	
		} else if (!checkTableExists("Users")) {
			
			/* 
			 *  CREATE DUMMY DATA FOR USERS
			 */
			createUserTable();
		 	dbms.insertDummyUsers();
	
		} 
	}
	
	
	
	private void createCourseTable()
			throws SQLException {
			String fields =
				"COURSEID INT NOT NULL " +
				"  GENERATED ALWAYS AS " +
				"  IDENTITY (START WITH 1, INCREMENT BY 1) ," +
				"CourseName VARCHAR(32)," +
				"PRIMARY KEY(COURSEID)";
			
			dbms.createTable("Course", fields);
		}
	
	private void createUserTable()
			throws SQLException {
			String fields =
				"GUID VARCHAR(30) NOT NULL, " +
				"UserName VARCHAR(32)," +
				"Password VARCHAR(32)," +
				"RoleID INT," +
				"PRIMARY KEY(GUID)";
			
			dbms.createTable("Users", fields);
		}
	
	
	
	
	private Boolean checkTableExists(String databaseName) 
			throws SQLException {
			return dbms.tableExists(databaseName);
		}
	

	@Override
	public mycampus.User authenticate(String id, String password) throws SQLException {
		// TODO Auto-generated method stub
		
		
	   ResultSet result = dbms.login(id, password);
	    
	   User loginUser = parseUserRow(result);

		return loginUser;
	}
	
	
	public User parseUserRow(ResultSet result) {

		User loginUser = null;

		try {
		
		while (result.next()){
	
			String GUID = 
					result.getString("GUID");
			String password =
					result.getString("Password");
			String name =
					result.getString("UserName");
			int roleID =
					result.getInt("RoleID");

		    loginUser = new User(GUID, name, password, roleID);
		 
		}	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return loginUser;

	}


	@Override
	public ArrayList<Course> getAllCourses() throws SQLException {
		// TODO Auto-generated method stub
		
		ArrayList<Course> allCourses = new ArrayList<Course>();
		
		ResultSet result = dbms.getAllCourseRows();
		
		try {
			
			while (result.next()){
		
				int courseID = 
						result.getInt("CourseID");
				String courseName =
						result.getString("CourseName");
			
				Course course = new Course(courseID, courseName);
				
					allCourses.add(course);
			 
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allCourses;
	}
	
	

	@Override
	public Course getSelectedCourse() {
		// TODO Auto-generated method stub
		return null;
	}



}
