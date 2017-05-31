package dbproject.homework;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dbproject.board.Board;
import dbproject.board.BoardDAO;
import dbproject.user.UserDAO;

public class UploadDAO {


	private static final Logger logger = LoggerFactory.getLogger(UploadDAO.class);
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		
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
			if(this.pstmt2 != null) {
				pstmt2.close();
			}
			if(this.rs2 != null) {
				rs2.close();
			}
			
		}
		public Connection getConnection() throws SQLException {
			Properties props = new Properties();
			InputStream in = UploadDAO.class.getResourceAsStream("/db.properties");
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
		
		public int getListCount() throws SQLException {

			String sql = "select count(*) from homeworks";
			
			int count = 0;
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
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
		
		public List getHomeworkList (int page, int limit,String subjectname) throws SQLException{
			
			List list = new ArrayList(); // 목록 리턴을 위한 변수
			
			// 목록를 조회하기 위한 쿼리
			String sql = "select * from homeworks where subjectname=? order by homeworkNum limit ?,?"; 
			
			// 조회범위
						int startrow = (page-1) * 10; // ex )  0, 10, 20, 30 ...
						int endrow = limit;  			 // ex ) limit 만큼 리스트에 나열
			
			try{
				conn = getConnection();
				// 실행을 위한 쿼리 및 파라미터 저장
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, subjectname);
				pstmt.setInt(2, startrow);
				pstmt.setInt(3, endrow);
				
				rs = pstmt.executeQuery(); // 쿼리 실행 
				
				while(rs.next()){
					Homework homework = new Homework();
					homework.setHomeworkNum(rs.getInt("homeworkNum"));
					homework.setUserId(rs.getString("userId"));
					homework.setHomeworkName(rs.getString("homeworkName"));
					homework.setHomeworkContent(rs.getString("homeworkContent"));
					homework.setHomeworkReadCnt(rs.getInt("homeworkReadcnt"));
					homework.setHomeworkDate(rs.getDate("homeworkDate"));
					
					list.add(homework); // 행을 하나씩 리스트에 추가
				}
				return list;
				
			}catch(Exception e){
				logger.debug("getBoardList Error : "+ e );
			}
			
			finally{ // DB 관련들 객체를 종료
				SourceReturn();
			}
			
			return null;
		}
		
		public Homework findByHomeworkInfo(int num) throws SQLException {
			String sql = "select * from homeworks where homeworkNum = ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);

				rs = pstmt.executeQuery(); // 결과를 받아와 저장

				if (!rs.next()) {
					return null;
				}

				return new Homework(rs.getString("homeworkName"), rs.getString("homeworkContent"), rs.getString("userId"));

			} finally {
				SourceReturn();
			}
		}
		
		public void addHomework(Homework homework) throws SQLException {
			String sql = "insert into homeworks(homeworkName,homeworkContent,userId,homeworkReadcnt,subjectName) values(?,?,?,?,?)";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, homework.getHomeworkName());
				pstmt.setString(2, homework.getHomeworkContent());
				pstmt.setString(3, homework.getUserId());
				pstmt.setInt(4, homework.getHomeworkReadCnt());
				pstmt.setString(5, homework.getSubjectName());
				
				pstmt.executeUpdate();

			} finally {
				SourceReturn();
			}
		}
		
		public void removeHomework(int num) throws SQLException {
			String sql = "delete from homeworks where homeworkNum = ?";
					
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, num);
				
				pstmt.executeUpdate();
				
			} finally {
				SourceReturn();
			}
		}
		
		public Homework viewHomework(int num) throws SQLException{
			String sql = "select * from homeworks where homeworkNum = ?";
			Homework homework = new Homework();
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					homework.setHomeworkNum( rs.getInt("homeworkNum") );
					homework.setHomeworkName( rs.getString("homeworkName") );
					homework.setHomeworkContent( rs.getString("homeworkContent") );
					homework.setUserId( rs.getString("userId") );
					homework.setHomeworkReadCnt( rs.getInt("homeworkReadcnt") );
					homework.setHomeworkDate( rs.getDate("homeworkDate") );
	
				}
			} catch (Exception e) {
				logger.debug("viewHomework error:"+e.getMessage());
			} finally {
				SourceReturn();
			}
			return homework;
		}
		
		public void updateReadcont(int num) throws SQLException{
			String sql = "update homeworks set homeworkReadcnt = homeworkReadcnt + 1 where homeworkNum = ?";
			conn = getConnection();
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				logger.debug("updateReadcont error : " + e);
			} finally {
				SourceReturn();
			}
		}
		
		public void updateHomework(Homework homework) throws SQLException {
			String sql = "update homeworks set homeworkName = ?, homeworkContent = ? ,homeworkDate=? where homeworkNum = ?";
					
			conn = getConnection();
			
			Timestamp date=new Timestamp(new Date().getTime());
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, homework.getHomeworkName());
				pstmt.setString(2, homework.getHomeworkContent());
				pstmt.setTimestamp(3, date);
				pstmt.setInt(4, homework.getHomeworkNum());
				
				pstmt.execute();
				logger.debug("UpdateHomework : " + homework);
			} catch (Exception e) {
				logger.debug("UpdateHomework error : " + e);
				logger.debug(homework + "");
			} finally {
				SourceReturn();
			}
		}
		
		
		public void addFile(String File_Path,String File_Name,String Author,int homeworkNum) throws SQLException{
			String sql="insert into Files(File_Path,File_Name,Author,homeworkNum) values(?,?,?,?)";
			try{
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, File_Path);
			pstmt.setString(2, File_Name);
			pstmt.setString(3, Author);
			pstmt.setInt(4, homeworkNum);
			pstmt.executeUpdate();
			}catch(SQLException e){
				logger.debug("addFile error:"+e.getMessage());
			}
			finally{
			SourceReturn();
			}
		}
		
		public List getUploadList (String userId,int homeworkNum) throws SQLException{
			String sql,sql2;	
			List list = new ArrayList(); // 목록 리턴을 위한 변수
			
					try{
						
						conn=getConnection();
						sql2="select homeworkName from files join homeworks on ? = homeworks.homeworkNum";
						
						if(userId.equals("master")){
							sql="select * from files where homeworkNum=?";
							pstmt=conn.prepareStatement(sql);
							pstmt.setInt(1,homeworkNum );
							pstmt2=conn.prepareStatement(sql2);
							
						}
						else{
							sql="select * from files where Author=? AND homeworkNum=?";
							pstmt=conn.prepareStatement(sql);
							pstmt.setString(1, userId);
							pstmt.setInt(2, homeworkNum);
							pstmt2=conn.prepareStatement(sql2);
						}
						rs=pstmt.executeQuery();
						while(rs.next()){
							File file=new File();
							file.setFileNum(rs.getInt("File_Num"));
							file.setFilePath(rs.getString("File_Path"));
							file.setFileName(rs.getString("File_Name"));
							file.setUploadTime(rs.getDate("Upload_Time"));
							file.setAuthor(rs.getString("Author"));
							file.setHomeworkNum(rs.getInt("homeworkNum"));
							pstmt2.setInt(1, rs.getInt("homeworkNum"));  // homeworkNum을 이용하여 homeworkName을 찾는다.
							rs2=pstmt2.executeQuery();
							while(rs2.next()){
								file.setHomeworkName(rs2.getString("homeworkName"));
							}
							list.add(file);
							}
						return list;
				}
				catch(SQLException e){
					logger.debug("getUploadList error:"+e.getMessage());
				}
				finally{
					SourceReturn();
				}
			return null;
		}
		
		public void removeFile(String  fileName) throws SQLException {
			try {
				String sql="delete from files where File_Name=?";
				conn=getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, fileName);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				logger.debug("removeFile error:"+e.getMessage());
			}
			finally{
				SourceReturn();
			}
			
		}
}
