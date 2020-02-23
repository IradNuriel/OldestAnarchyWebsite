package aditionalsDataTypes;

public class Message {
	private String senderName;
	private String info;
	public Message(String senderName, String info) {
		this.senderName = senderName;
		this.info = info;
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
