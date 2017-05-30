package dbproject.attend;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dbproject.board.Board;
import dbproject.user.User;
import dbproject.user.UserDAO;

public class AttendDAO {
	private static final Logger logger = LoggerFactory.getLogger(AttendDAO.class);
	
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void SourceReturn() throws SQLException {
		
		if(this.conn != null) {
			conn.close();
		}
		if(this.pstmt != null) {
			pstmt.close();
		}
		if(this.rs != null) {
			rs.close();
		}
		
	}
	
	public Connection getConnection() throws SQLException {
		Properties props = new Properties();
		InputStream in = UserDAO.class.getResourceAsStream("/db.properties");
		try {
			props.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String driver = props.getProperty("jdbc.driver");
		String url = props.getProperty("jdbc.url");
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");

		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return null;
		}
	}
	
	
	public int getAllSubjectCount() throws SQLException {

		String sql = "select count(*) from subjects"; 
		
		int count = 0;
		
		try {
			conn = getConnection();
			// 실행을 위한 쿼리 및 파라미터 저장
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); // 쿼리 실행 
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		finally {
				SourceReturn();
		}
		
		return count;
	}
	
	public String[] getAllSubjectName(int subjectCount) throws SQLException {
		
		String[] subjectName = new String[subjectCount];
		
		String sql = "select * from subjects";
		
		try{
			conn = getConnection();
			// 실행을 위한 쿼리 및 파라미터 저장
			pstmt = conn.prepareStatement(sql);
			int temp = 0;
			
			rs = pstmt.executeQuery(); // 쿼리 실행 
			
			while(rs.next()) {
				subjectName[temp] = rs.getString("subjectname");
				temp++;
			}
			
		}
		catch(Exception e){
			logger.debug("get Error : "+ e );
		}
		return subjectName;
	}
	
	public int getSubjectCount(String userId) throws SQLException {

		String sql = "select count(*) from attend where userid = ?"; 
		
		int count = 0;
		
		try {
			conn = getConnection();
			// 실행을 위한 쿼리 및 파라미터 저장
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery(); // 쿼리 실행 
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		finally {
				SourceReturn();
		}
		
		return count;
	}
	
	public String[] getSubjectName(int subjectCount, String userId) throws SQLException {
		
		String[] subjectName = new String[subjectCount];
		
		String sql = "select * from attend where userid = ?";
		
		
		try{
			conn = getConnection();
			// 실행을 위한 쿼리 및 파라미터 저장
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			int temp = 0;
			
			rs = pstmt.executeQuery(); // 쿼리 실행 
			
			while(rs.next()) {
				subjectName[temp] = rs.getString("subjectname");
				temp++;
			}
			
		}
		catch(Exception e){
			logger.debug("get Error : "+ e );
		}
		return subjectName;
	}
	
	
	public void setCday(String subjectName, int cday) throws SQLException {
		
		String sql = "update subjects set cday = ? where subjectname = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cday);
			pstmt.setString(2, subjectName);

			pstmt.executeUpdate();

		} finally {
			SourceReturn();
		}
		
	}
	
	public int[] getCday(int subjectCount) throws SQLException {
		
		int[] cday = new int[subjectCount];
		
		String sql = "select * from subjects";
		
		try{
			conn = getConnection();
			// 실행을 위한 쿼리 및 파라미터 저장
			pstmt = conn.prepareStatement(sql);
			int temp = 0;
			
			rs = pstmt.executeQuery(); // 쿼리 실행 
			
			while(rs.next()) {
				cday[temp] = rs.getInt("cday");
				temp++;
			}
			
		}
		catch(Exception e){
			logger.debug("get Error : "+ e );
		}
		return cday;
	}
	
	public String[] getCheckyn(int subjectCount) throws SQLException {
		
		String[] checkyn = new String[subjectCount];
		
		String sql = "select * from subjects";
		
		try{
			conn = getConnection();
			// 실행을 위한 쿼리 및 파라미터 저장
			pstmt = conn.prepareStatement(sql);
			int temp = 0;
			
			rs = pstmt.executeQuery(); // 쿼리 실행 
			
			while(rs.next()) {
				checkyn[temp] = rs.getString("checkyn");
				temp++;
			}
			
		}
		catch(Exception e){
			logger.debug("get Error : "+ e );
		}
		return checkyn;
	}
	
	public void setCheckyn(String subjectName, String changeyn) throws SQLException {
		
		String sql = "update subjects set checkyn = ? where subjectname = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, changeyn);
			pstmt.setString(2, subjectName);

			pstmt.executeUpdate();

		} finally {
			SourceReturn();
		}
		
	}
	
	public void setAttendyn(String subjectName) throws SQLException {
		
		String sql = "update attend set attendyn = 'n' where subjectname = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, subjectName);

			pstmt.executeUpdate();

		} finally {
			SourceReturn();
		}
		
	}
	
	public String[] getStamp(int subjectCount, String userId) throws SQLException {
		
		String[] stamp = new String[subjectCount];
		
		String sql = "select * from attend where userid = ?";
		
		
		try{
			conn = getConnection();
			// 실행을 위한 쿼리 및 파라미터 저장
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			int temp = 0;
			
			rs = pstmt.executeQuery(); // 쿼리 실행 
			
			while(rs.next()) {
				stamp[temp] = rs.getString("stamp");
				temp++;
			}
			
		}
		catch(Exception e){
			logger.debug("get Error : "+ e );
		}
		return stamp;
	}
	
	public String[] getAttendyn(int subjectCount) throws SQLException {
		
		String[] attempyn = new String[subjectCount];
		
		String sql = "select * from attend where userid = ?";
		
		
		try{
			conn = getConnection();
			// 실행을 위한 쿼리 및 파라미터 저장
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "leee");
			
			int temp = 0;
			
			rs = pstmt.executeQuery(); // 쿼리 실행 
			
			while(rs.next()) {
				attempyn[temp] = rs.getString("attempyn");
				temp++;
			}
			
		}
		catch(Exception e){
			logger.debug("get Error : "+ e );
		}
		return attempyn;
	}
	
	public int getUserCheckynCount(String userId) throws SQLException {

		String sql = "select count(distinct attend.subjectname) from attend, subjects where attend.userid = ? and attend.subjectname = subjects.subjectname and attend.attendyn = 'n' and subjects.checkyn = 'y'";
		
		int count = 0;
		
		try {
			conn = getConnection();
			// 실행을 위한 쿼리 및 파라미터 저장
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery(); // 쿼리 실행 
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		finally {
				SourceReturn();
		}
		
		return count;
	}
	
	
	public String[] getUserCheckyn(String userId, int userCheckynCount) throws SQLException {
		
		String[] userCheckyn = new String[userCheckynCount];
		
		String sql = "select distinct attend.subjectname from attend, subjects where attend.userid = ? and attend.subjectname = subjects.subjectname and attend.attendyn = 'n' and subjects.checkyn = 'y'";
		
		try{
			conn = getConnection();
			// 실행을 위한 쿼리 및 파라미터 저장
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			int temp = 0;
			
			rs = pstmt.executeQuery(); // 쿼리 실행 
			
			while(rs.next()) {
				userCheckyn[temp] = rs.getString("subjectname");
				temp++;
			}
			
		}
		catch(Exception e){
			logger.debug("get Error : "+ e );
		}
		return userCheckyn;
	}
	
	public String[] getUserStamp(String userId, int userCheckynCount) throws SQLException {
		
		String[] userStamp = new String[userCheckynCount];
		
		String sql = "select distinct attend.stamp from attend, subjects where attend.userid = ? and attend.subjectname = subjects.subjectname and attend.attendyn = 'n' and subjects.checkyn = 'y'";
		
		try{
			conn = getConnection();
			// 실행을 위한 쿼리 및 파라미터 저장
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			int temp = 0;
			
			rs = pstmt.executeQuery(); // 쿼리 실행 
			
			while(rs.next()) {
				userStamp[temp] = rs.getString("stamp");
				temp++;
			}
			
		}
		catch(Exception e){
			logger.debug("get Error : "+ e );
		}
		return userStamp;
	}
	
	public int[] getUserCday(String userId, int userCheckynCount) throws SQLException {
		
		int[] userCday = new int[userCheckynCount];
		
		String sql = "select distinct subjects.cday from attend, subjects where attend.userid = ? and attend.subjectname = subjects.subjectname and attend.attendyn = 'n' and subjects.checkyn = 'y'";
		
		try{
			conn = getConnection();
			// 실행을 위한 쿼리 및 파라미터 저장
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			int temp = 0;
			
			rs = pstmt.executeQuery(); // 쿼리 실행 
			
			while(rs.next()) {
				userCday[temp] = rs.getInt("cday");
				temp++;
			}
			
		}
		catch(Exception e){
			logger.debug("get Error : "+ e );
		}
		return userCday;
	}
	
	public String[] getMaxDay(int subjectCount) throws SQLException {
		
		String[] maxDay = new String[subjectCount];
		
		String sql = "select * from subjects";
		
		try{
			conn = getConnection();
			// 실행을 위한 쿼리 및 파라미터 저장
			pstmt = conn.prepareStatement(sql);
			int temp = 0;
			
			rs = pstmt.executeQuery(); // 쿼리 실행 
			
			while(rs.next()) {
				maxDay[temp] = rs.getString("day");
				temp++;
			}
			
		}
		catch(Exception e){
			logger.debug("get Error : "+ e );
		}
		return maxDay;
	}
	
	public void attendCheck(String changeStamp, String userId, String subjectName) throws SQLException {
		
		String sql = "update attend set stamp = ?, attendyn = 'y' where userid = ? and subjectname = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, changeStamp);
			pstmt.setString(2, userId);
			pstmt.setString(3, subjectName);

			pstmt.executeUpdate();

		} finally {
			SourceReturn();
		}
		
	}
	
	
	
	public int getAllAttendCount() throws SQLException {

		String sql = "select count(*) from attend"; 
		
		int count = 0;
		
		try {
			conn = getConnection();
			// 실행을 위한 쿼리 및 파라미터 저장
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); // 쿼리 실행 
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		finally {
				SourceReturn();
		}
		
		return count;
	}
	
	
	
	public String[] getAllAttenduserId(int allAttendCount) throws SQLException {
		
		String[] allAttenduserId = new String[allAttendCount];
		
		String sql = "select * from attend";
		
		try{
			conn = getConnection();
			// 실행을 위한 쿼리 및 파라미터 저장
			pstmt = conn.prepareStatement(sql);
			int temp = 0;
			
			rs = pstmt.executeQuery(); // 쿼리 실행 
			
			while(rs.next()) {
				allAttenduserId[temp] = rs.getString("userId");
				temp++;
			}
			
		}
		catch(Exception e){
			logger.debug("get Error : "+ e );
		}
		return allAttenduserId;
	}
	
	
	public String[] getAllAttendSubjectName(int allAttendCount) throws SQLException {
		
		String[] allAttendSubjectName = new String[allAttendCount];
		
		String sql = "select * from attend";
		
		try{
			conn = getConnection();
			// 실행을 위한 쿼리 및 파라미터 저장
			pstmt = conn.prepareStatement(sql);
			int temp = 0;
			
			rs = pstmt.executeQuery(); // 쿼리 실행 
			
			while(rs.next()) {
				allAttendSubjectName[temp] = rs.getString("subjectname");
				temp++;
			}
			
		}
		catch(Exception e){
			logger.debug("get Error : "+ e );
		}
		return allAttendSubjectName;
	}
	
	
	public String[] getAllAttenduStamp(int allAttendCount) throws SQLException {
		
		String[] allAttendStamp = new String[allAttendCount];
		
		String sql = "select * from attend";
		
		try{
			conn = getConnection();
			// 실행을 위한 쿼리 및 파라미터 저장
			pstmt = conn.prepareStatement(sql);
			int temp = 0;
			
			rs = pstmt.executeQuery(); // 쿼리 실행 
			
			while(rs.next()) {
				allAttendStamp[temp] = rs.getString("stamp");
				temp++;
			}
			
		}
		catch(Exception e){
			logger.debug("get Error : "+ e );
		}
		return allAttendStamp;
	}
	
	
	
	
	
	
}
