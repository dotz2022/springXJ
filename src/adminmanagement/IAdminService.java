package adminmanagement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public interface IAdminService {

	/*KiANHOCK*/
	public void adminMenu()  throws SQLException, NumberFormatException, IOException;  //kianhock
	
	public boolean retrieveUnAssignSession() throws SQLException;    //retrieve session from database that havent been assign
	
	public void retrieveTimeTableSlot(); //require database
	
	public void timeTableSlot_Menu();
	
	/*------------------------------------------*/

	
}
