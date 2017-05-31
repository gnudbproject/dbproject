package dbproject.homework;

import java.sql.Date;

public class Homework {
	private int homeworkNum;
	private String userId;
	private String homeworkName;
	private String homeworkContent;
	private int homeworkReadCnt;
	private Date homeworkDate;
	private String subjectName;
	
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Homework(){
		
	}
	
public Homework(String subjectName){
		this.subjectName=subjectName;
	}
	
public Homework(String homeworkName, String homeworkContent, String userId) {
	this.homeworkName = homeworkName;
	this.homeworkContent = homeworkContent;
	this.userId = userId;
}

	public Homework(String homeworkName, String homeworkContent, String userId,String subjectName) {
			this.homeworkName = homeworkName;
			this.homeworkContent = homeworkContent;
			this.subjectName=subjectName;
			this.userId = userId;
	}
	
	public int getHomeworkNum() {
		return homeworkNum;
	}
	public void setHomeworkNum(int homeworkNum) {
		this.homeworkNum = homeworkNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHomeworkName() {
		return homeworkName;
	}
	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
	}
	public String getHomeworkContent() {
		return homeworkContent;
	}
	public void setHomeworkContent(String homeworkContent) {
		this.homeworkContent = homeworkContent;
	}
	public int getHomeworkReadCnt() {
		return homeworkReadCnt;
	}
	public void setHomeworkReadCnt(int homeworkReadCnt) {
		this.homeworkReadCnt = homeworkReadCnt;
	}
	public Date getHomeworkDate() {
		return homeworkDate;
	}
	public void setHomeworkDate(Date homeworkDate) {
		this.homeworkDate = homeworkDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((homeworkContent == null) ? 0 : homeworkContent.hashCode());
		result = prime * result + ((homeworkDate == null) ? 0 : homeworkDate.hashCode());
		result = prime * result + ((homeworkName == null) ? 0 : homeworkName.hashCode());
		result = prime * result + homeworkNum;
		result = prime * result + homeworkReadCnt;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Homework other = (Homework) obj;
		if (homeworkContent == null) {
			if (other.homeworkContent != null)
				return false;
		} else if (!homeworkContent.equals(other.homeworkContent))
			return false;
		if (homeworkDate == null) {
			if (other.homeworkDate != null)
				return false;
		} else if (!homeworkDate.equals(other.homeworkDate))
			return false;
		if (homeworkName == null) {
			if (other.homeworkName != null)
				return false;
		} else if (!homeworkName.equals(other.homeworkName))
			return false;
		if (homeworkNum != other.homeworkNum)
			return false;
		if (homeworkReadCnt != other.homeworkReadCnt)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Homework [homeworkNum=" + homeworkNum + ", userId=" + userId + ", homeworkName=" + homeworkName
				+ ", homeworkContent=" + homeworkContent + ", homeworkReadCnt=" + homeworkReadCnt + ", homeworkDate="
				+ homeworkDate + ", subjectName=" + subjectName + "]";
	}
	
	
	
}
