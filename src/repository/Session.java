package repository;

import java.util.ArrayList;

import mycampus.User;

public class Session {
	private int sessionID;
	private int courseID;
	private String name;
	private String starttime;
	private String endtime;
	private int compulsory;
	
	private int roomID;
	public String venue;
	
	ArrayList<User> students;


	public Session(int sessionID, int courseID, String name, String endtime,
			String starttime, int compulsory, int roomID)
	
	{
		setSessionID(sessionID);
		setCourseID(courseID);
		setName(name);
		setStarttime(endtime);
		setEndtime(starttime);
		setCompulsory(compulsory);
		setRoomID(roomID);
	}
	
	public Session(int sessionID, int courseID, String name, String venue, String starttime, String endTime, int compulsory, ArrayList<User> students) {
		setSessionID(sessionID);
		setCourseID(courseID);
		setName(name);
		setStarttime(endtime);
		setEndtime(starttime);
		setCompulsory(compulsory);
		setRoomID(roomID);
		this.students = students;
	}
	
	
	
	public Session(int sessionID, int courseID, String name, String venue, String starttime, String endTime, int compulsory) {
		
		setSessionID(sessionID);
		setCourseID(courseID);
		setName(name);
		setStarttime(endtime);
		setEndtime(starttime);
		setCompulsory(compulsory);
		setVenue(venue);
	
	}
	
	
	public Session(int sessionID, String name, String venue,
			String starttime, String endTime, int Compulsory)
	
	{
		setSessionID(sessionID);		
		setName(name);
		setStarttime(endTime);
		setEndtime(starttime);
		setVenue(venue);
		setCompulsory(Compulsory);
		
	}
	
	public void setVenue(String venue) {
		
		this.venue = venue;
	}
	
	public String getVenue() {
		return this.venue;
	}
	
	public int getSessionID() {
		return sessionID;
	}
	
	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStarttime() {
		return starttime;
	}
	
	public int getCompulsory() {
		return compulsory;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public int isCompulsory() {
		return compulsory;
	}
	public void setCompulsory(int compulsory) {
		this.compulsory = compulsory;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	
	
}
