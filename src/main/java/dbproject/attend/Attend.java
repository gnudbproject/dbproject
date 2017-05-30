package dbproject.attend;

public class Attend {

	private String[] subjectName;
	private String userId;
	private String stamp;

	public Attend() {
	}
	
	public Attend(String[] subjectName, String userId, String stamp) {
		for(int i = 0;  i < subjectName.length; i++) {
			this.subjectName[i] = subjectName[i];
		}
		this.userId = userId;
		this.stamp = stamp;
	}
	
	public String[] getSubjectName() {
		return subjectName;
	}
	
	public void setSubjectName(String[] subjectName) {
		for(int i = 0; i < subjectName.length; i++) {
			this.subjectName[i] = subjectName[i];
		}
	}
		
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getStamp() {
		return stamp;
	}
	
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	
	public String toString() {
		return "Attend [userId=" + userId + ", stamp=" + stamp +"]";
	}

}
