package mycampus;

public class Course {
	
	public int courseID;
	public String courseName;

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	
	public Course(int courseID, String courseName) {
		
		
		this.courseID = courseID;
		this.courseName = courseName;
	}
	
	
	
	
}