package dbproject.board;

import java.sql.Date;

public class Board {

	private String subject;
	private String content;
	private String userId;
	private Date date;
	private int readcnt;
	private int num;

	public Board() {
	}
	
	public Board(String subject, String content, String userId) {
		this.subject = subject;
		this.content = content;
		this.userId = userId;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getReadcnt() {
		return readcnt;
	}
	
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "Board [subject=" + subject + ", content=" + content + ", userId=" + userId + ", date=" + date
				+ ", readcnt=" + readcnt + ", num=" + num + "]";
	}
	
	
}
