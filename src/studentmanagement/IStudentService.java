package studentmanagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IStudentService {

	
	
/*MAOJIE */
	
	public void studentMenu();
	
   public void addUserInSession(String userId, int sessionID) throws SQLException;
	
	public int sessionApproval(int sessionID) throws SQLException;
	
	public ResultSet getAllSessionByCourse(int courseID) throws SQLException;
	
	public ResultSet getSession(int sessionID) throws SQLException;
	
	public boolean bookTimeTableSlot(int sessionID, String userID) throws SQLException;
	/* ----------------------------------------------- */
	
	
	/*TERRENCE */
	
	public ResultSet[] checkCompulsorySession(String userID) throws SQLException;
}
