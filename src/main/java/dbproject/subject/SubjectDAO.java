package dbproject.subject;

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

public class SubjectDAO {
	private static final Logger logger = LoggerFactory.getLogger(SubjectDAO.class);
	
	
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
	
	public void addSubject(String subjectName, int day) throws SQLException{
		String sql = "insert into subjects values(?,?,'n','n',0)";

		// null 로 초기화
		Connection conn = null;
		PreparedStatement pstmt = null;

		// JDBC 리소스 반환

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, subjectName);
			pstmt.setInt(2, day);

			pstmt.executeUpdate();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			if (conn != null) {
				conn.close();
			}
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
	
	public void removeSubject(String subjectName) throws SQLException{
		String sql = "delete from subjects where subjectname = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subjectName);

			pstmt.executeUpdate();
		} finally {

			if (conn != null) {
				conn.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}
		}
	}
	
	public void removeAttendSubject(String subjectName) throws SQLException{
		String sql = "delete from attend where subjectname = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subjectName);

			pstmt.executeUpdate();
		} finally {

			if (conn != null) {
				conn.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}
		}
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public int getRequestynCount() throws SQLException {
//
//		String sql = "select count(*) from subjects where requestyn = 'y'"; 
//		
//		int count = 0;
//		
//		try {
//			conn = getConnection();
//			// 실행을 위한 쿼리 및 파라미터 저장
//			pstmt = conn.prepareStatement(sql);
//			
//			rs = pstmt.executeQuery(); // 쿼리 실행 
//			
//			if(rs.next()) {
//				count = rs.getInt(1);
//			}
//			
//		} catch (Exception e) {
//			logger.debug(e.getMessage());
//		}
//		finally {
//				SourceReturn();
//		}
//		
//		return count;
//	}
	
	public String[] getRequestyn(int allSubjectCount) throws SQLException {
		
		String[] requestyn = new String[allSubjectCount];
		
		String sql = "select * from subjects";
		
		
		try{
			conn = getConnection();
			// 실행을 위한 쿼리 및 파라미터 저장
			pstmt = conn.prepareStatement(sql);
			
			int temp = 0;
			
			rs = pstmt.executeQuery(); // 쿼리 실행 
			
			while(rs.next()) {
				requestyn[temp] = rs.getString("requestyn");
				temp++;
			}
			
		}
		catch(Exception e){
			logger.debug("get Error : "+ e );
		}
		return requestyn;
	}
	
	public void setRequestyn(String subjectName, String changeyn) throws SQLException {
		
		String sql = "update subjects set requestyn = ? where subjectname = ?";
		
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
	
	public void subjectRequest(String userId, String subjectName, String stamp) throws SQLException{
		String sql = "insert into attend values(?,?,?,'n')";

		// null 로 초기화
		Connection conn = null;
		PreparedStatement pstmt = null;

		// JDBC 리소스 반환

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, subjectName);
			pstmt.setString(3, stamp);
			
			pstmt.executeUpdate();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			if (conn != null) {
				conn.close();
			}
		}
	}
	
}
