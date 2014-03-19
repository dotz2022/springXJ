package dashboard;

import java.sql.SQLException;

import java.util.Scanner;

import mycampus.IMyCampusService;
import mycampus.User;


import adminmanagement.*;


public class dashboardHandler {
	
	private static Scanner sc = new Scanner(System.in);
	private IMyCampusService campusService;

	
	public dashboardHandler(IMyCampusService campusService) {
		
		this.campusService = campusService;
	

	}

	
	
	public User loginAccount() throws SQLException {
		
		String guid;
		String password;
		User user;
		Boolean loginSucessfully = false;
		String roleName = null;
		
		System.out.println("\n============== Welcome to UTSBS ==============\n");
		System.out.println("GUID : ");

		guid = sc.next();
		
		System.out.println("Password : ");
		
		password = sc.next(); 
	
		user = campusService.authenticate(guid, password);
		
		if (user != null) {
			System.out.println("\n===== Login Successfully! =====\n");
			loginSucessfully = true;
			
		} else {
			
			System.out.println("\n===== Invalid Account! =====\n");
			loginSucessfully = false;
			
		}
		
		
		if (loginSucessfully == true) {
			
			switch(user.roleID) {
			case 1:  roleName = "Lecturer";

			System.out.println("\n==== Welcome " + user.getUserName() + " (" + roleName + ") ===\n");
                     break;
			
			case 2: roleName = "Administration";	
			
			System.out.println("\n==== Welcome " + user.getUserName() + " (" + roleName + ") ===\n");
			
	
					 break;
			
			case 3: roleName = "Student";
			System.out.println("\n==== Welcome " + user.getUserName() + " (" + roleName + ") ===\n");
					 break;
			
			case 4: roleName = "Tutor";
			System.out.println("\n==== Welcome " + user.getUserName() + " (" + roleName + ") ===\n");
					 break;
			
			}
			
		
		    

			
		}
		
			return user;
	}
	
	
	
	

}
