package repository.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import repository.Session;
import repository.IRepositoryService;
import repository.Room;
import repository.Course;

public class RepositoryHandler implements IRepositoryService {
	
	private DBMS dbms;
	
	public RepositoryHandler () {
		
	}
	
	
	
	public RepositoryHandler (DBMS dbms)
		throws SQLException{
		
		this.dbms = dbms;
			
	//	dbms.updateSessionTable();
		
		//dbms.dropTableUsers();
		if (!checkTableExists("course")) {
			 
			/* 
			 *  CREATE TABLE FOR COURSES
			 */
			createCourseTable();
	        
	
		} else if (!checkTableExists("Session")) {
			
			/* 
			 *  CREATE TABLE FOR SESSION
			 */
			createSessionTable();
			
		} else if (!checkTableExists("StudentInSession")) {
			
			createStudentSessionTable();
			
		} else if (!checkTableExists("Room")) {
			
			/* 
			 *  CREATE TABLE FOR ROOM
			 */
			
			createRoomTable();
			dbms.insertDummyDataRoom();
			
		} else if (!checkTableExists("TimeTableSlot")) {
			
			createTimeTableSlotTable();
			
			
		}
	}
	
	
	
	private void createCourseTable()
			throws SQLException {
			String fields =
				"COURSEID INT NOT NULL, " +
				"CourseName VARCHAR(32)," +
				"PRIMARY KEY(COURSEID)";
			
			dbms.createTable("Course", fields);
		}
	
	private Boolean checkTableExists(String databaseName) 
			throws SQLException {
			return dbms.tableExists(databaseName);
		}
	
	
	
	private void createTimeTableSlotTable()
			throws SQLException {
			String fields =
				"TimeTableSlotID INT NOT NULL " +
			    "  GENERATED ALWAYS AS " +
			     "    IDENTITY (START WITH 1, INCREMENT BY 1) ," +
				"StartTime VARCHAR(32)," +
				"EndTime VARCHAR(32)," +
				"SessionID INT," + 
				"PRIMARY KEY(TimeTableSlotID)";
			
			dbms.createTable("TimeTableSlot", fields);
		}
	


	
	
	private void createSessionTable()
			throws SQLException {
			String fields =
				"SessionID INT NOT NULL " +
			    "  GENERATED ALWAYS AS " +
			    "    IDENTITY (START WITH 1, INCREMENT BY 1) ," +
			    "Venue VARCHAR(32),"  +
				"CourseID INT," +
				"SessionName VARCHAR(32)," +
				"StartTime VARCHAR(32)," +
				"EndTime VARCHAR(32)," +
				"Compulsory INT," +
				"RoomID INT," +
				"seatstaken INT," +
				"PRIMARY KEY(SessionID)";

			dbms.createTable("Session", fields);
		}
	
	
	private void createRoomTable() throws SQLException {
		
		String fields = 
				"RoomID INT NOT NULL " +
			    "  GENERATED ALWAYS AS " +
			    "    IDENTITY (START WITH 1, INCREMENT BY 1) ," +
			    "Venue VARCHAR(32),"  +
			    "CAPACITY INT," +
			    "PRIMARY KEY(RoomID)";
		
		dbms.createTable("Room", fields);
		
	}
	
	private void createStudentSessionTable()
			throws SQLException {
			String fields =
				"SessionID INT NOT NULL, " +
				"GUID VARCHAR(32) NOT NULL";

			dbms.createTable("StudentInSession", fields);
		}

	
	/*public User parseUserRow(ResultSet result) {

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

	}*/


/*	@Override
	public ArrayList<mycampus.Course> getAllCourses() throws SQLException {
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
	}*/
	
	
	



	@Override
	public void getAllSessions() {
		// TODO Auto-generated method stub
		
	}
	


	@Override
	public ArrayList<repository.Course> getAllCourses() throws SQLException {
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
	public ArrayList<repository.Room> getAllRooms() throws SQLException {
		// TODO Auto-generated method stub
		
		ArrayList<Room> allRooms = new ArrayList<Room>();
		
		ResultSet result = dbms.getAllRooms();
		
		try {
			
			while (result.next()){
		
				int roomID = 
						result.getInt("RoomID");
				String roomVenue =
						result.getString("Venue");
				
				int roomCapacity =
						result.getInt("Capacity");
			
				Room room = new Room(roomID, roomVenue, roomCapacity);
				
				allRooms.add(room);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allRooms;
	}
	
	

	public boolean createSession(HashMap<String, String> m) throws SQLException {
		// TODO Auto-generated method stub
		
		dbms.createSession(m);
	
		return true;
	}
	
	@Override
	public ArrayList<repository.Session> getAllSessionsViaCourse(int sessionID) {
		
		
		ResultSet result = null;
		
		ArrayList<Session> allsessions = new ArrayList<Session>();
		
		try {
			result = dbms.getAllSessionsViaCourse(sessionID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (result.next()){
		
				int sessionID1 = result.getInt("sessionID");
				String name = result.getString("sessionname");
				String venue = result.getString("venue");
				String startTime = result.getString("startTime");
				String endTime = result.getString("endtime");
				int compulsory = result.getInt("compulsory");

				Session session = new Session(sessionID1, name, venue, startTime, endTime, compulsory);
				
			 allsessions.add(session);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return allsessions;
		
	}

	@Override
	public Connection getDBConnector() throws SQLException {
		// TODO Auto-generated method stub
		return dbms.getDatabaseConnection();
	}

	@Override
	public boolean viewOwnSession(int courseID, String GUID) {
		
		ResultSet result = null;
		
		try {
			result = dbms.viewOwnSession(GUID);
			
			while (result.next()) {
				
				  System.out.println(result.getInt("SessionID"));

				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return true;
	}

	
	
	


}
