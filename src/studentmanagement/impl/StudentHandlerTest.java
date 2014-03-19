package studentmanagement.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.*;

import static org.junit.Assert.*;
import repository.Session;
import junit.framework.TestCase;
import static org.junit.Assert.*;
import junit.framework.TestCase;


public class StudentHandlerTest extends TestCase {
	

	/*public void testGetAllSessionByCourse() throws SQLException {
		
		ResultSet A = new StudentHandler().getAllSessionByCourse(1);
		ArrayList<Integer> ListOfSessionID = new ArrayList<Integer>();
		int[] id = {1,2,3};
		while(A.next())
		{
			ListOfSessionID.add(A.getInt("SessionID"));
		}
		
		for(int i = 0; i < id.length ; i++)
		{
			assertEquals(""+id[i], ""+ListOfSessionID.get(i));
		}
		
	}*/
	
	/*public void testViewAllCompulsorySessions() {
		
		ArrayList<Session> sessions = new ArrayList<Session>();
		
		Session session = new Session(1, 1, "PSD LAB3", "E6 Room 1", "09:00", "11:00", 1);
		Session session1 = new Session(2, 1, "PSD LEC1", "E5 Room 1", "09:00", "11:00", 1);
		Session session2 = new Session(3, 1, "PSD LEC5", "E5 Room 1", "14:00", "16:00", 1);
		
		sessions.add(session);
		sessions.add(session1);
		sessions.add(session2);
		
		StudentHandler handler = new StudentHandler();

	
		//System.out.println((handler.viewAllCompulsorySession(1)).toArray());
		
		for (int i =0; i < sessions.size(); i++) {
		
			
			assertEquals(sessions.get(i).toString(), sessions.get(i).toString());
		
		}
		

	}*/
	
	
	/*public void testSessionBookTimeTableSlot() {
		String selectedSessionID = "1,2,3,4";
		StudentHandler studenthandler = new StudentHandler();
	
		
		String[] selectedSessionIDList = selectedSessionID.split("\\,");
		 for (int i =0; i < selectedSessionIDList.length;i++) {
	try {
				 assertEquals(studenthandler.bookTimeTableSlot(Integer.parseInt(selectedSessionIDList[i]), "abcdefgH"), true);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }

		 
	}*/
	

	/*public void testGetSession() throws SQLException {

		ResultSet A = new StudentHandler().getSession(1);
		int sessionID = 0;
		int id = 1;
		while(A.next())
		{
			sessionID = A.getInt("SessionID");
		}
		
			assertEquals(""+id , ""+sessionID);
		
	}

	public void testSessionApproval() throws SQLException {

		ResultSet A = new StudentHandler().getSession(1);
		int seatsleft = 0;
		int shouldbeseatsleft = 0;
		while(A.next())
		{
			seatsleft = A.getInt("SeatsTaken");
		}
		
			assertEquals(""+shouldbeseatsleft, ""+seatsleft);
		
	}*/

	/*public void testAddUserInSession() {
		//If this part triggered with values inside, can it work correctly ?
		fail("Not yet implemented");
	}

	public void testCheckCompulsorySession() {
		//If this part triggered with values inside, can it work correctly ?
		fail("Not yet implemented");
	}

	public void testStudentMenu() {
		//If this part triggered with values inside, can it work correctly ?
		fail("Not yet implemented");
	}*/

}
