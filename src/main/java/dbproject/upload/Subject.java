package dbproject.upload;

import java.sql.Date;

public class Subject {
	private int subjectNum;
	private String userId;
	private String subjectName;
	private String subjectContent;
	private int subjectReadCnt;
	private Date subjectDate;
	private int re_ref;
	private int re_lev;
	private int re_seq;
	
	public Subject(){
		
	}
	
	public Subject(String subjectName, String subjectContent, String userId) {
			this.subjectName = subjectName;
			this.subjectContent = subjectContent;
			this.userId = userId;
	}
	public int getRe_ref() {
		return re_ref;
	}
	public void setRe_ref(int re_ref) {
		this.re_ref = re_ref;
	}
	public int getRe_lev() {
		return re_lev;
	}
	public void setRe_lev(int re_lev) {
		this.re_lev = re_lev;
	}
	public int getRe_seq() {
		return re_seq;
	}
	public void setRe_seq(int re_seq) {
		this.re_seq = re_seq;
	}
	public int getSubjectNum() {
		return subjectNum;
	}
	public void setSubjectNum(int subjectNum) {
		this.subjectNum = subjectNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectContent() {
		return subjectContent;
	}
	public void setSubjectContent(String subjectContent) {
		this.subjectContent = subjectContent;
	}
	public int getSubjectReadCnt() {
		return subjectReadCnt;
	}
	public void setSubjectReadCnt(int subjectReadCnt) {
		this.subjectReadCnt = subjectReadCnt;
	}
	public Date getSubjectDate() {
		return subjectDate;
	}
	public void setSubjectDate(Date subjectDate) {
		this.subjectDate = subjectDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((subjectContent == null) ? 0 : subjectContent.hashCode());
		result = prime * result + ((subjectDate == null) ? 0 : subjectDate.hashCode());
		result = prime * result + ((subjectName == null) ? 0 : subjectName.hashCode());
		result = prime * result + subjectNum;
		result = prime * result + subjectReadCnt;
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
		Subject other = (Subject) obj;
		if (subjectContent == null) {
			if (other.subjectContent != null)
				return false;
		} else if (!subjectContent.equals(other.subjectContent))
			return false;
		if (subjectDate == null) {
			if (other.subjectDate != null)
				return false;
		} else if (!subjectDate.equals(other.subjectDate))
			return false;
		if (subjectName == null) {
			if (other.subjectName != null)
				return false;
		} else if (!subjectName.equals(other.subjectName))
			return false;
		if (subjectNum != other.subjectNum)
			return false;
		if (subjectReadCnt != other.subjectReadCnt)
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
		return "Subject [subjectNum=" + subjectNum + ", userId=" + userId + ", subjectName=" + subjectName
				+ ", subjectContent=" + subjectContent + ", subjectReadCnt=" + subjectReadCnt + ", subjectDate="
				+ subjectDate + "]";
	}
	
	
	
}
