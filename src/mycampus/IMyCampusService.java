package mycampus;

import java.sql.SQLException;
import java.util.ArrayList;
import repository.Course;

public interface IMyCampusService {
	
	
	public User authenticate(String id, String password) throws SQLException;
		

	public ArrayList<Course> getAllCourses() throws SQLException;
	
	public Course getSelectedCourse();
	
     
}
