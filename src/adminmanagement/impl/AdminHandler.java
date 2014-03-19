package adminmanagement.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import repository.IRepositoryService;
import adminmanagement.IAdminService;
import adminmanagement.Timetable_Slot;
import repository.Session;


public class AdminHandler implements IAdminService {
	
	private static Scanner sc = new Scanner(System.in);

  
	static Connection connector;
	IRepositoryService reposService;
	
	public AdminHandler(IRepositoryService query) {
	
	
		this.reposService = query;
		try {
			this.connector = reposService.getDBConnector();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void adminMenu() throws SQLException, NumberFormatException, IOException {		
		
		while (sc.next() != "e") {
		String getInput;
	
				System.out.println("\nTimetable Slot Menu");
				System.out.println("1. Create timetable slot for a session");
				System.out.println("2. Assign a room to a timetable slot.");
				System.out.println("3. Check that there are no timetable slot clashes between courses ");
				System.out.println("B. Back"); // Quit this menu
				System.out.print(">> ");
				
				getInput = sc.next();
				
				if (getInput.equals("1")) {
					// Retrieve from database for any unassigned session
					boolean IsUnAssignAvailable = retrieveUnAssignSession();
					
					if (IsUnAssignAvailable) { 
						getInput = sc.next();
						
						if (Integer.parseInt(getInput) > 0 || 
							Integer.parseInt(getInput) < (getUnAssignSession().size() - 1)) {
							// Retrieve timetable slot for display
							RetrieveTimetableSlot(getInput);
							
						}
					}
					
				} else if (getInput.equals("2")) {
					
					int room_num;

					System.out.println("Room numer : " + 1 + "." + "E6 ROOM 1");
					System.out.println("Room numer : " + 2 + "." + "E5 ROOM 5");
					System.out.println("Room numer : " + 3 + "." + "E2 LAB ROOM 3");
					System.out.println("Room numer : " + 4 + "." + "E1 LAB ROOM 1");
	
					System.out.println("Enter room number which will be assign to a timetable.");
					System.out.print(">> ");
					room_num = Integer.parseInt(sc.next());

					System.out.println("Enter start date and time of the session.");
					System.out.println("Format : dd/mm/yyyy hh:mm:ss");
					System.out.print(">> ");
					getInput = sc.next();
					
					System.out.println("Assigning of room done!");
					
				}else if (getInput.equals("2")) {
					checkForClashTimeTableSlots();
				}
				
		}


	}

	
	public static ArrayList<Session> getUnAssignSession() throws SQLException {
		
		Connection connection = connector;

		ArrayList<Session> sessions = new ArrayList<Session>();
		String sqlString = 
				
			"SELECT * FROM SESSION WHERE SESSION.SESSIONID NOT IN (SELECT TIMETABLESLOT.SESSIONID FROM TIMETABLESLOT)";

		PreparedStatement preparedStatement =
			connection.prepareStatement(sqlString);
				
		ResultSet result = preparedStatement.executeQuery();

		while (result.next()){
			
			int sessionID = 
					result.getInt("SessionID");
			System.out.println(sessionID + "---------------------");
			
			int courseID=
					result.getInt("CourseID");
			
			String sessionName =
					result.getString("SessionName");
			
	        String startTime =
	        		result.getString("StartTime");
	        
	        String endTime =
	        		result.getString("EndTime");
	        
	        int compulsory =
	        		result.getInt("Compulsory");
	        
            int roomID =
            		result.getInt("RoomID");
      
            Session session = new Session(sessionID, courseID, sessionName, startTime, endTime, compulsory, roomID);
  
	     	sessions.add(session);
		
		}	
		return sessions;
		
	}
	
	
	private static void RetrieveTimetableSlot(String sessionNum) throws IOException, NumberFormatException, SQLException {
		// Check whether if a timetable is already created
		// Database retrieve code here..
		//listOfTbSlots = SearchFromDB(QueryString); // IF SEARCH BY DATE & TIME, WILL RETURN ONLY ONE RESULT
		
		String getInput;
	
		//write query insert sample data into timetable TABLE
		//write query to retrieve data from timetable tABLE
	
		
		Connection connection = connector;
		ArrayList<Session> sessions = new ArrayList<Session>();
		Session selectedSession = null;
		
		for (Session session :getUnAssignSession()) {
		
			 if (session.getSessionID() == Integer.parseInt(sessionNum)) {
			
				 selectedSession = session;
			 }
			 
		}
		
		
		String sqlString = 
			"SELECT * FROM TIMETABLESLOT WHERE STARTTIME = '" + selectedSession.getStarttime() + "' AND ENDTIME = '" + selectedSession.getEndtime() + "'";
	
	
		PreparedStatement preparedStatement = 
			connection.prepareStatement(sqlString);
				
		ResultSet result = preparedStatement.executeQuery();
	
		int a =0;
		while (result.next()) {
			a = 1;
		}
	
		if (a == 0) {
			// If the timetableslot is already created on that date
			
				// Insert a new timetableslot into database 
				System.out.println("No timetable slot was created during the provided date/time!");
				timetableSlot_Prompt(sessionNum);
				System.out.println("Enter 'Y' to add session. (This will a create timetable slot!)");
				System.out.println("Enter 'N' to go back.");
				System.out.print("(Y/N)>> ");

				if (sc.next().equals("Y")) {

					String sqlString3 = 
							"INSERT INTO TIMETABLESLOT (STARTTIME, ENDTIME, SESSIONID) VALUES"
									+ "('" + selectedSession.getStarttime() + "','" + selectedSession.getEndtime() + "',"
									+ selectedSession.getSessionID() + ")";
			
						PreparedStatement preparedStatement3 = 
							connection.prepareStatement(sqlString3);
								
					    preparedStatement3.executeUpdate();
						
						System.out.println("**Session successfully inserted!**");
					
				}else {
					
				}
				
				
	
		} else {

			
			Integer sessionID = 0;
			Integer timetableSlot = 0;
			while (result.next()) {

				sessionID = result.getInt("SessionID");
				timetableSlot = result.getInt("TimeTableSlotID");
				
			}
			
			if (sessionID == 0) {
				
				timetableSlot_Prompt(sessionNum);
				System.out.println("Enter 'Y' to add session.");
				System.out.println("Enter 'N' to go back.");
				System.out.print("(Y/N)>> ");
				getInput = sc.next();
				
				if (getInput.toUpperCase().equals("Y")) {
					
					String sqlString2 = "UPDATE TIMETABLESLOT SET SESSIONID =" + sessionID +
							"WHERE TIMETABLESLOTID =" + timetableSlot;
					
					PreparedStatement preparedStatement1 = 
							connection.prepareStatement(sqlString2);
								
						preparedStatement1.executeUpdate();
	
						System.out.println("***Sucessfully updated session into timeTableSlot***");
				}
				
			}
	
		}

	}
	
	
	private static void timetableSlot_Prompt(String getInput) throws NumberFormatException, SQLException {
		System.out.println("Your session slot...");
		System.out.println("Session ID : " +  getUnAssignSession().get(Integer.parseInt(getInput) - 1).getSessionID());
		System.out.println("Start Time : " + getUnAssignSession().get(Integer.parseInt(getInput) - 1).getStarttime());
		System.out.println("End Time : "  + getUnAssignSession().get(Integer.parseInt(getInput) - 1).getEndtime());
	}
	
	public void checkForClashTimeTableSlots() throws IOException {
	
	/*
	 * As a administrator
	 * I want to check that there are no timetable slot clashes between courses
	 * So that students are able to complete the course.
	 */
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Timetable_Slot ts1 = new Timetable_Slot(01, "01/01/2014 09:00:00", "01/01/2014 10:00:00", 01);
	Timetable_Slot ts3 = new Timetable_Slot(02, "01/01/2014 09:30:00", "01/01/2014 10:30:00", 02); 
	br = new BufferedReader(new InputStreamReader(System.in));
	
	String getInput = null;
		
		try {
			do {
				System.out.println("\nCheck whether if any timetable slot clashes");
				System.out.println("1. Check for clashes");
				System.out.println("B. Back"); // Quit this menu
				System.out.print(">> ");
				getInput = br.readLine();
				
				if (getInput.equals("1")) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy hh:mm:ss");
					Date tbDate1 = sdf.parse(ts1.getEndtime());
					Date tbDate2 = sdf.parse(ts3.getStarttime());
					System.out.println(sdf.format(tbDate1));
					System.out.println(sdf.format(tbDate2));
					if (tbDate1.after(tbDate2))
						System.out.println(MessageFormat.format("There is a clash in timetable between {0} and {1}!",
								ts1.getTimetableslotID(), ts3.getTimetableslotID()));
				}
				
			} while (!getInput.toUpperCase().equals("B"));
			
		} catch (Exception e) {
			System.out.println("Invalid input.");
		}
		finally {
			br.close();
		}
	} 
	
	
	
	
	
	@Override
	public boolean retrieveUnAssignSession() throws SQLException {
		// TODO Auto-generated method stub
				
				
		         ArrayList<Session> sessions = getUnAssignSession();
				
				if (sessions.size() > 0) {
					System.out.println("****** START: List of Unassigned Session(s) ******");
					
					for (int i = 0; i < sessions.size(); i++) {
						System.out.println("\nSession Slot : " + (i + 1));
						System.out.println("Course ID : " + sessions.get(i).getCourseID());
						System.out.println("Start Time : " + sessions.get(i).getStarttime());
						System.out.println("End Time : " + sessions.get(i).getEndtime());
						
						if (sessions.get(i).isCompulsory() == 0) {
							
							System.out.println("Attendance : Not Compuslory");
						} else if (sessions.get(i).isCompulsory() == -1) {
							System.out.println("Attendance : Compuslory");
						}
					}
					
					System.out.println("\n****** END: List of Unassigned Session(s) ******");
					System.out.println("Which session slot to add into timetable?");
					System.out.print(">> ");

	return true;
	} else {
		System.out.println("Sorry. There are no unassigned session at this time.\n");
		
	
		
		return false;
	}


				
	}
	

	@Override
	public void retrieveTimeTableSlot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void timeTableSlot_Menu() {
		// TODO Auto-generated method stub
		
	}

	
}
