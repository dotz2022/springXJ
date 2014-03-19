package mycampus.impl;

import static org.junit.Assert.*;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dashboard.dashboardHandler;
import mycampus.User;
import java.util.Random;
public class MyCampusHandlerTest {

	
	/*
	User user = new User("2109933C", "XiangJie", "random", 1);
	User loginUser = null;
 
	@Test
	public void testAuthenticate() {
	
		dashboardHandler a = new dashboardHandler();
		
		try {
			loginUser =  new MyCampusHandler(new DBMS()).authenticate("2109933C", "random");

	
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		assertEquals(user.getGUID(), loginUser.getGUID());
		
		System.out.println("Authenticate successful");
		System.out.println("Test case successful");
		
		
		
	}
	*/
/*	@Test
	public void testUserRoles() {
		
		
	   assertEquals("Lecturer", loginUser.getRoleName());
		
		System.out.println("You are logged in as " + loginUser.getRoleName());
	}*/

   @Test
    public void test1000Users() {
    	DBMS dbms = new DBMS();
    	
    	Random randomGenerator = new Random();
    	
    	String GUID;
    	String userName = null;
    	String password = "random";
    	int role = 1;
    	for (int i =0; i < 1000; i++) {
    		
    		userName = "";
    		
    		int randomInt = randomGenerator.nextInt(100000);
    		
    		 String alphabet = "abcdefghijklmnoqrstuvwxyz";
    		  
    		
    			 Random r = new Random();
    			 for (int a =0; a<6;a++) {
    			 char c = (char)(r.nextInt(26) + 'a');
    			 
    			 userName += c;
    			 }
    			 
    			 
    			 char a = (char)(r.nextInt(26) + 'a');
    			 
    		   GUID = randomInt+"" + a;
    		   
    		   	try {
					assertTrue(dbms.insertTestUsers(GUID, userName, password, role));
					System.out.println("The system can support at least 1000 different users");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		
    		
    	}

    	
    }
	
	
	
	
	

}
