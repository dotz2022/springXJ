
package lecturermanagement.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import lecturermanagement.ILecturerService;
import repository.IRepositoryService;
import repository.Room;
import repository.Course;
import mycampus.IMyCampusService;
import repository.Session;

public class LecturerHandler implements ILecturerService {

	IRepositoryService repositoryService;
	IMyCampusService campusService;

	private static Scanner sc = new Scanner(System.in);
	String courseSelection = null;
	Course courseSelected = null;
	static Connection connector;
	public LecturerHandler(IMyCampusService campusService, IRepositoryService repositoryService) throws SQLException {
		
		try {
			this.connector = repositoryService.getDBConnector();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.campusService = campusService;
		this.repositoryService = repositoryService;
	
	}
	
	@Override
	public void generatelecturerMenu() throws SQLException {
		
		while (true) {
        System.out.println("\n============== List of Tasks Available ==============\n");

		System.out.println("\n===== 1. Import Course =====");
		System.out.println("\n===== 2. Add Session To A Course =====");
		System.out.println("\n===== 3. View Session Details =====");
		System.out.println("\n===== 4. Log Out =====");
	
		courseSelection = sc.next();
		
		if (courseSelection != "e") {
		
		if (courseSelection.equals("1")) {
			
			ArrayList<Course> allCourses = this.campusService.getAllCourses();
	
			System.out.println("\n===== List of courses =====\n");
			
			for (Course course : allCourses) {
				
				System.out.println(course.getCourseID() + "." + " " + course.getCourseName());
			}
			
			String input = sc.next();
			for (Course course : allCourses) {
				
				if (course.courseID == Integer.parseInt(input)) {
					courseSelected = course;
				}
			}
			
			courseSelection = null;
			lecturerImportCourse(courseSelected);
		
			continue;
		} else if (courseSelection.equals("2")) {
			
			ArrayList<Course> allCourses  =  repositoryService.getAllCourses();
			
			
			  System.out.println("\n============== Select A Course To Proceed ==============\n");
				
			for (Course course: allCourses) {
				
				System.out.println(course.getCourseID() + ". " + course.getCourseName());
			}
			
			courseSelection = sc.next();
			
            for (Course course : allCourses) {
				
				if (course.courseID == Integer.parseInt(courseSelection)) {
					courseSelected = course;
				}
			}
			
			
			if (courseSelected != null) {
				
				 String information = courseSelected.getCourseID() + ",";
				 System.out.println("\n== Input data with the following sequence seperated by comma (,) ==\n");
				 System.out.println("\n== SessionName  startTime  endTime   Compulsory  Frequency(a one off, or recurs weekly or fortnightly)== \n");
				 
				 
				 @SuppressWarnings("resource")
				Scanner keyboard = new Scanner(System.in);
				 keyboard.useDelimiter("\n");
				 information += keyboard.nextLine().trim();
				 System.out.println(information);
				 
				 
				 System.out.println("\n=== Choose Your Room ===\n");
				 
				 System.out.println("Room ID      Room Venue     Room Capacity");
				 
				 for (Room room : repositoryService.getAllRooms()) {

					 System.out.println(room.getRoomID() + "          " +
					 room.getRoomVenue() + "     " + room.getRoomCapacity()); 
				 }
				 
				 
				 courseSelection = sc.next();
				 
				 for (Room room : repositoryService.getAllRooms()) {

					 if (Integer.parseInt(courseSelection.trim()) == room.getRoomID()) {
						 
						 information += "," + room.getRoomID();
						 
					 }
				 }
		
				 String[] seperateData = information.split(",");
		
				 
				 
				 
				 HashMap<String, String> informationz = new HashMap<String, String>();
				 
				 informationz.put("CourseID", seperateData[0]);
				 informationz.put("SessionName", seperateData[1]);
				 informationz.put("StartTime", seperateData[2]);
				 informationz.put("EndTime", seperateData[3]);
				 informationz.put("Compulsory", seperateData[4]);
				 informationz.put("RoomID", seperateData[5]);
				 informationz.put("FREQUENCY", seperateData[6]);
				 
				  
				 System.out.println(informationz.get("CourseID"));
				 System.out.println(informationz.get("SessionName"));
				 System.out.println(informationz.get("StartTime"));
				 System.out.println(informationz.get("EndTime"));
				 System.out.println(informationz.get("Compulsory"));
				 System.out.println(informationz.get("RoomID"));
				 System.out.println(informationz.get("FREQUENCY"));

				 
				repositoryService.createSession(informationz);
				
				System.out.println("******Session successfully created!*******");
				generatelecturerMenu();
				courseSelection = null;
				continue;
			}
			
		}
 
      	 else if (courseSelection.equals("3")) {
			
      		 int courseID = 1;
      		 
      		repositoryService.viewOwnSession(courseID, "1");
      		 
      		 System.out.println("ALL SESSIONS IN THIS COURSE");
      		 
			System.out.println("1. PSD3 LAB 3, 09:00, 11:00, LIEWPAKSAN, E6 Room 1");
			System.out.println(" ** All students inside this session**");
			System.out.println(" 1. TERRENCE LOW");
			
			System.out.println("2. PSD3 LEC 3, 09:00, 11:00, LIEWPAKSAN, E6 Room 1");
			System.out.println(" ** All students inside this session**");
			System.out.println(" 1. TERRENCE LOW");
			courseSelection = null;
			continue;
	}
       } else {
    	   
    	   break;
       }
		
		}
	}
	
	
	

	public void lecturerImportCourse(Course course) throws SQLException {
	
		Connection connection = connector;

				String statementString = 
			       "INSERT INTO Course (CourseID, CourseName) " + 
				   "VALUES (" + course.getCourseID() + ", '" + course.getCourseName() + "')";
				
						
				PreparedStatement statement = 
					connection.prepareStatement(statementString);
					
				try {
					if(statement.execute()) {
	
						System.out.println("You have imported " + course.getCourseName());
						
					}
					
				} catch (Exception e) {
					
					System.out.println("You have already imported this course!");
				}
				
		
	}



	@Override
	public void getAllSessions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean importCourse(Course course) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Course> getAllCourses() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Room> getAllRooms() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createSession(HashMap<String, String> m) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Session> getAllSessionsViaCourse(int sessionID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean viewOwnSession(int courseID, String GUID) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
