package repository;

public class Room {

	public int roomID;
	public String roomVenue;
	public int roomCapacity;
	
	
	public Room(int roomID, String roomVenue, int roomCapacity) {
		
		this.roomID = roomID;
		this.roomVenue = roomVenue;
		this.roomCapacity = roomCapacity;
	}
	
	
	public int getRoomID() {
		return roomID;
	}


	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}


	public String getRoomVenue() {
		return roomVenue;
	}


	public void setRoomVenue(String roomVenue) {
		this.roomVenue = roomVenue;
	}


	public int getRoomCapacity() {
		return roomCapacity;
	}


	public void setRoomCapacity(int roomCapacity) {
		this.roomCapacity = roomCapacity;
	}


	
}
