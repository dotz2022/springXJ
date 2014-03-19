package dashboard;

import java.sql.SQLException;
import java.util.Scanner;

import mycampus.IMyCampusService;
import mycampus.User;

public class Login {

	
	private static Scanner sc = new Scanner(System.in);
	private IMyCampusService campusService;
	
	public Login(IMyCampusService campusService) {
		
		this.campusService = campusService;

	}
	
	
	
	
	public boolean loginAccount() throws SQLException {
		
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
			return false;
		}
		
		
		if (loginSucessfully == true) {
			
			switch(user.roleID) {
			case 1:  roleName = "Lecturer";
                     break;
			
			case 2: roleName = "Administration";	
					 break;
			
			case 3: roleName = "Student";
					 break;
			
			case 4: roleName = "Tutor";
					 break;
			
			}
			
			System.out.println("\n==== Welcome " + user.getUserName() + " (" + roleName + ") ===\n");
		
		}
		
		
		
		
			return true;
	}
	
	
	
	

}
