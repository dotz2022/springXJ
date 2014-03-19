package studentmanagement.impl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import repository.Session;
import repository.Course;
import repository.IRepositoryService;
import studentmanagement.IStudentService;



public class StudentHandler implements IStudentService {
	
	
	private static Scanner sc = new Scanner(System.in);


	IRepositoryService systemService;
	
	
private static final String connectionString = "jdbc:derby:data/systemDB;create=true";
	

public StudentHandler() {

	
  }


   public StudentHandler(IRepositoryService systemService) {
	
	this.systemService = systemService;

    }
	
	public ResultSet getAllSessionByCourse(int courseID)
			throws SQLException{
			Connection connection = getDatabaseConnection();
			
			String sqlString = 
					"SELECT SESSIONID, seatstaken, SESSION.ROOMID, SessionName, ROOM.VENUE, STARTTIME, ENDTIME, COMPULSORY"
							+ " FROM SESSION, ROOM WHERE ROOM.ROOMID = SESSION.ROOMID AND COURSEID=" + courseID;
			
			
			
			PreparedStatement preparedStatement =
				connection.prepareStatement(sqlString);
					
			ResultSet result = preparedStatement.executeQuery();
			
			return result;	
		}
	
	private static Connection getDatabaseConnection ()
			throws SQLException{
					
			return DriverManager.getConnection(connectionString);
		}


	
	
	public ResultSet getSession(int sessionID) 
			throws SQLException {
		Connection connection = getDatabaseConnection();
		
		String sqlString = 
			"SELECT S.SessionID, S.SessionName, S.Starttime, S.Endtime, S.Compulsary, R.venue FROM Session S, Room R WHERE S.roomID = R.roomID AND S.SessionID = " + sessionID + " ;";
		
		PreparedStatement preparedStatement =
			connection.prepareStatement(sqlString);
				
		ResultSet result = preparedStatement.executeQuery();
		
		return result;	
	}

	public int sessionApproval(int sessionID) 
			throws SQLException {
		Connection connection = getDatabaseConnection();
		
		String sqlString = 
			"SELECT SIS.SeatsTaken FROM (SELECT SessionID SessionID, COUNT(GUID) SeatsTaken FROM StudentInSession WHERE SessionID = "+ sessionID +" group by session) SIS, Session S, room R WHERE (R.Capacity - SIS.SeatsTaken) > 0 AND SIS.SessionID = S.SessionID AND S.roomID = R.room.ID;";
		
		PreparedStatement preparedStatement =
			connection.prepareStatement(sqlString);
				
		ResultSet result = preparedStatement.executeQuery();
		
		return result.getRow();
	}




	public void addUserInSession(String userId, int sessionID) 
				throws SQLException{
		Connection connection = getDatabaseConnection();
	
			String statementString = 
				"INSERT INTO UserInSession (sessionID, GUID) VALUES ('" + sessionID + "','" + userId + "')";
	
			PreparedStatement statement = 
				connection.prepareStatement(statementString);
		
			statement.execute();
	}



	public ResultSet[] checkCompulsorySession(String userID) throws SQLException {
		
		Connection connection = getDatabaseConnection();
				
		String sqlString = 
				"SELECT c.coursename, s.sessionname"
				+ "FROM courselist cl, course c, session s, timetableslot t, cl.courseid, c.courseid, userinsession uis"
				+ "WHERE cl.courseid = c.courseid"
				+ "AND c.courseid = s.courseid"
				+ "AND s.sessionid = t.sessionid"
				+ "AND uis.sessionid = s.sessionid"
				+ "AND s.compulsory = \"1\""
				+ "AND cl.guid =" + userID //which is the GUID stored
				+ "AND cl.guid = uis.guid";
		
		String sqlString2 = 
				"SELECT c.coursename, s.sessionname"
				+ "FROM courselist cl, course c, session s, timetableslot t, cl.courseid, c.courseid, userinsession uis"
				+ "WHERE cl.courseid = c.courseid"
				+ "AND c.courseid = s.courseid"
				+ "AND s.sessionid = t.sessionid"
				+ "AND uis.sessionid = s.sessionid"
				+ "AND s.compulsory = \"1\""
				+ "AND cl.guid =" + userID //which is the GUID stored
				+ "AND cl.guid <> uis.guid";
		
		ResultSet[] output = new ResultSet[2];
		
		PreparedStatement preparedStatement =
				connection.prepareStatement(sqlString);
					
			output[0] =  preparedStatement.executeQuery();
			
		preparedStatement =
				connection.prepareStatement(sqlString2);
					
			output[1] = preparedStatement.executeQuery();
		
		
		return output;
		
	}

	
	public void studentMenu() {
		 ResultSet result = null;
		String input = null;
		ArrayList<Course> allCourses = new ArrayList<Course>(); 
		ArrayList<Session> allCourseSessions = new ArrayList<Session>();
		System.out.println("====== List of Tasks =====");
		
		System.out.println("1. Select Course");
		System.out.println("2. View Sessions that are compuslory");
		
		input = sc.next();

		if (input.equals("1")) {
		
				try {
					allCourses = systemService.getAllCourses();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				for (Course course : allCourses) {
					
					System.out.println(course.getCourseID() + ". " + course.getCourseName());
				
				}

		//Ask the user which course
		
		System.out.println("Please Select the course ID");
	
		input = sc.next();
		
		
		 allCourseSessions = systemService.getAllSessionsViaCourse(Integer.parseInt(input));
		
		 
		 
	    System.out.println("=======View all sessions to book======");
	    
	    
	    for (Session session: allCourseSessions) {
	    	

	 System.out.println(session.getSessionID() + " " + session.getName() + " " + 
	 session.getStarttime() + " " + session.getEndtime() + " " +  session.getVenue() + " " +
	 session.getCompulsory());
	
	    }
	
	    String selectedSessionID = sc.nextLine();
	    String[] selectedSessionIDList = selectedSessionID.split("\\,");
	    
	    
	   if (selectedSessionIDList.length > 0) {
	
		  try {
			  for (int i =0; i < selectedSessionIDList.length;i++) {
				  
				  
				  bookTimeTableSlot(Integer.parseInt(selectedSessionIDList[i]), "abcdefgH");
			  }

			   System.out.println("All Sessions book Sucessfully!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }


		} else if (input.equals("2")) {
			
	
			String compulsory = null;
			  System.out.println("=======View all compuslory sessions======\n");
				for (Session session : viewAllCompulsorySession(1)) {
					
					
					if (session.getCompulsory() == 1) {
						compulsory = "Compulsory";
					} else {
						compulsory = "Not compulsory";
						
					}
					
					System.out.println(session.getSessionID() + ". " + session.getName() + " " + session.getVenue() +
							
							" " + session.getStarttime() + " " + session.getEndtime() + " " + compulsory);
	
				}
			  
			
		}
	}
	
	
	
	public ArrayList<Session> viewAllCompulsorySession(int courseID) {
		
		ArrayList<Session> compulsorySessions = new ArrayList<Session>();
	
		Session session = new Session(1, 1, "PSD LAB3", "E6 Room 1", "09:00", "11:00", 1);
		Session session1 = new Session(2, 1, "PSD LEC1", "E5 Room 1", "09:00", "11:00", 1);
		Session session2 = new Session(3, 1, "PSD LEC5", "E5 Room 1", "14:00", "16:00", 1);
	
		compulsorySessions.add(session);
		compulsorySessions.add(session1);
		compulsorySessions.add(session2);
		
		return compulsorySessions;
	}


	@Override
	public boolean bookTimeTableSlot(int sessionID, String userID)
			throws SQLException {
		Connection connection = getDatabaseConnection();
	
		String sqlString = 
				"INSERT INTO StudentInSession (SessionID, GUID) VALUES"
				+ "(" + sessionID + ",'" + userID + "')";
		
		PreparedStatement preparedStatement =
				connection.prepareStatement(sqlString);
		
	
		preparedStatement.execute();
		
		return true;
	}



}
