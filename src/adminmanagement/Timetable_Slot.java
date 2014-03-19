package adminmanagement;

public class Timetable_Slot {
	private int timetableslotID;
	private String starttime;
	private String endtime;
	private int sessionID;
	
	public Timetable_Slot(int timetableslotID, String starttime, String endtime, int sessionID) {
		setTimetableslotID(timetableslotID);
		setStarttime(starttime);
		setEndtime(endtime);
		setSessionID(sessionID);
	}
	
	public int getTimetableslotID() {
		return timetableslotID;
	}
	public void setTimetableslotID(int timetableslotID) {
		this.timetableslotID = timetableslotID;
	}
	public String getStarttime() {
		return starttime;
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
	public int getSessionID() {
		return sessionID;
	}
	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}
}
