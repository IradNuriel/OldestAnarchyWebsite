package aditionalsDataTypes;

import javax.servlet.http.HttpSession;

public class Message {
	private String senderName;
	private String info;
	private HttpSession senderSession;
	public Message(String senderName, String info,HttpSession senderSession) {
		this.senderName = senderName;
		this.info = info;
		this.senderSession=senderSession;
	}

	public HttpSession getSenderSession() {
		return senderSession;
	}
	public void setSenderSession(HttpSession senderSession) {
		this.senderSession = senderSession;
	}
	
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
