package dbproject.homework;

import java.sql.Date;

public class File {
	private int fileNum;
	private String filePath;
	private String fileName;
	private Date uploadTime;
	private String author;
	private int homeworkNum;
	private String homeworkName;
	
	
	public String getHomeworkName() {
		return homeworkName;
	}
	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getFileNum() {
		return fileNum;
	}
	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getHomeworkNum() {
		return homeworkNum;
	}
	public void setHomeworkNum(int homeworkNum) {
		this.homeworkNum = homeworkNum;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + fileNum;
		result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
		result = prime * result + homeworkNum;
		result = prime * result + ((uploadTime == null) ? 0 : uploadTime.hashCode());
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
		File other = (File) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (fileNum != other.fileNum)
			return false;
		if (filePath == null) {
			if (other.filePath != null)
				return false;
		} else if (!filePath.equals(other.filePath))
			return false;
		if (homeworkNum != other.homeworkNum)
			return false;
		if (uploadTime == null) {
			if (other.uploadTime != null)
				return false;
		} else if (!uploadTime.equals(other.uploadTime))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "File [fileNum=" + fileNum + ", filePath=" + filePath + ", fileName=" + fileName + ", uploadTime="
				+ uploadTime + ", author=" + author + ", homeworkNum=" + homeworkNum + ", homeworkName=" + homeworkName
				+ "]";
	}
	
	
	
}
