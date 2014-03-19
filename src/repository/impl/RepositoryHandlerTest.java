package repository.impl;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import repository.impl.DBMS;
import mycampus.impl.MyCampusHandler;

import org.junit.Test;

import repository.Course;
import repository.Session;

public class RepositoryHandlerTest {

	@Test
	public void testCreateSession() {
		
		 HashMap<String, String> informationz = new HashMap<String, String>();
		 
		 informationz.put("CourseID",  "1");
		 informationz.put("SessionName", "NS3 LAB 3");
		 informationz.put("StartTime", "09:00");
		 informationz.put("EndTime", "11:00");
		 informationz.put("Compulsory", "1");
		 
		 informationz.put("RoomID", "1");;
		 informationz.put("FREQUENCY", "ONE OFF");
	
		 try {
			 assertTrue(new RepositoryHandler(new DBMS()).createSession(informationz));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
/*	@Test
	public void testImportCourse() {
	
		RepositoryHandler handler = new RepositoryHandler();
		Course psd3 = new Course(180, "MM3");
		boolean z = false;
		try {
		z = new RepositoryHandler(new DBMS()).importCourse(psd3);
	
		System.out.println(z);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		assertTrue(z);
		
	}/*
	
	
	

	/*@Test
	public void testNFSupport100Course() {
		
		int cid = 7;
		
		int cnameid = 7;
	
		for (int i = 0; i<100; i++){
		
			String course = "AP";

			course = course + " " + cnameid+"";
			
			Course course1 = new Course(cid, course);
			try {
				assertTrue(new RepositoryHandler(new DBMS()).importCourse(course1));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("CourseID: " + cid +" " + "Course Name: " + course);
			cid++;
			cnameid++;
		}
	}*/
	/*
	@Test
	public void testViewOwnSession() {
		boolean a = false;

		try {
			 a = new RepositoryHandler(new DBMS()).viewOwnSession(1, "2109933C");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(a);
		
	}
	*/
	
	
	/*@Test
	public void test10differentSessions() {
	
		ArrayList<Session> a = new ArrayList<Session>();

		try {
			 a = new RepositoryHandler(new DBMS()).getAllSessionsViaCourse(1);
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue((10 <= a.size()));
	}*/
	
	
	
	/*
	@Test
	public void testCreateSession() {
		
		 HashMap<String, String> informationz = new HashMap<String, String>();
		 
		 informationz.put("CourseID",  "1");
		 informationz.put("SessionName", "NS3 LAB 2");
		 informationz.put("StartTime", "09:00");
		 informationz.put("EndTime", "11:00");
		 informationz.put("Compulsory", "1");
		 
		 informationz.put("RoomID", "1");
		 informationz.put("FREQUENCY", "ONE OFF");
	
		 try {
			 assertTrue(new RepositoryHandler(new DBMS()).createSession(informationz));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	//10 different session in each course
	/*@Test
	public void testCreateSession() {
		
		 HashMap<String, String> informationz = new HashMap<String, String>();
		 
		 informationz.put("CourseID", "7");
		 informationz.put("SessionName", "AP LAB 1"); 
		 informationz.put("StartTime", "09:00");
		 informationz.put("EndTime", "11:00");
		 informationz.put("Compulsory", "1");
		 informationz.put("RoomID", "1");
	
		
		try {
			new RepositoryHandler(new DBMS()).createSession(informationz);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	*/
	

}
