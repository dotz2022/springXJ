package mycampus;

public class User {

	public String GUID;
	public String userName;
	public String password;
	public int roleID;
	public String roleName;
	
	public User() {
		
		
	}

	
	
	public User(String GUID, String userName, String password, int roleID){
		
		
		this.GUID = GUID;
		this.userName = userName;
		this.password = password;
		this.roleID = roleID;
		if (roleID == 1) {
			
			this.roleName = "Lecturer";
			
		}else if (roleID == 2) {
			this.roleName = "Administrator";
		}else if (roleID == 3) {
		this.roleName = "Student";	
		
		}
	}
	
	
	public String getGUID() {
		return GUID;
	}

	public void setGUID(String gUID) {
		GUID = gUID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	
	

	public String getRoleName() {
		
		return this.roleName;
	}
	
	
	
	
}
