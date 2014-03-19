package lecturermanagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import repository.Course;

public interface ILecturerService {
	public void generatelecturerMenu()  throws SQLException;  //kianhock

	public void getAllSessions();
	
	public boolean importCourse(Course course) throws SQLException;
	
	public ArrayList<repository.Course> getAllCourses() throws SQLException;
	
	public ArrayList<repository.Room> getAllRooms() throws SQLException;
	
	public boolean createSession(HashMap<String, String> m) throws SQLException;
	
	public ArrayList<repository.Session> getAllSessionsViaCourse(int sessionID);
	
	public boolean viewOwnSession(int courseID, String GUID);

	
}
