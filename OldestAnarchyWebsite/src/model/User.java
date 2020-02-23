package model;

public class User {
	private final String DEF_ROLE="regular";
	
	String nickName, password, role;
	int mID;	//model id of the user, retrieved from DB
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		if (role.equals(""))
			this.role = DEF_ROLE;
		else
			this.role = role;
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
