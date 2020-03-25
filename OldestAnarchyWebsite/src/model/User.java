package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class User {
	private final String DEF_ROLE="regular";
	
	String nickName, password, role, lastDateEntered;
	int mID;	//model id of the user, retrieved from DB
	
	public String getDate() {
		return lastDateEntered;
	}
	public void setLastDateEntered() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		lastDateEntered=dtf.format(now);  
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getmID() {
		return mID;
	}
	public void setmID(int mID) {
		this.mID = mID;
	}
	
}
