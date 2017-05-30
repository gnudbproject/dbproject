package dbproject.upload;


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

			String sql = "select count(*) from subject";
			
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
		
		public List getSubjectList (int page, int limit) throws SQLException{
			
			List list = new ArrayList(); // 목록 리턴을 위한 변수
			
			// 목록를 조회하기 위한 쿼리
			String sql = "select * from subject order by re_ref desc, re_seq asc limit ?, ?"; 
			
			// 조회범위
			int startrow = (page-1) * 10; // ex )  0, 10, 20, 30 ...
			int endrow = limit;  			 // ex ) limit 만큼 리스트에 나열
			
			try{
				conn = getConnection();
				// 실행을 위한 쿼리 및 파라미터 저장
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startrow);
				pstmt.setInt(2, endrow);
				
				rs = pstmt.executeQuery(); // 쿼리 실행 
				
				while(rs.next()){
					Subject subject = new Subject();
					subject.setSubjectNum(rs.getInt("subjectNum"));
					subject.setUserId(rs.getString("userId"));
					subject.setSubjectName(rs.getString("subjectName"));
					subject.setSubjectContent(rs.getString("subjectContent"));
					subject.setSubjectReadCnt(rs.getInt("subjectReadcnt"));
					subject.setSubjectDate(rs.getDate("subjectDate"));
					subject.setRe_ref(rs.getInt("re_ref"));
					subject.setRe_lev(rs.getInt("re_lev"));
					subject.setRe_seq(rs.getInt("re_seq"));
					
					list.add(subject); // 행을 하나씩 리스트에 추가
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
		
		public Subject findBySubjectInfo(int num) throws SQLException {
			String sql = "select * from subject where subjectNum = ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);

				rs = pstmt.executeQuery(); // 결과를 받아와 저장

				if (!rs.next()) {
					return null;
				}

				return new Subject(rs.getString("subjectName"), rs.getString("subjectContent"), rs.getString("userId"));

			} finally {
				SourceReturn();
			}
		}
		
		public void addSubject(Subject subject) throws SQLException {
			String sql = "insert into subject(subjectName,subjectContent,userId,subjectReadcnt,re_ref,re_lev,re_seq) values(?,?,?,?,?,?,?)";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, subject.getSubjectName());
				pstmt.setString(2, subject.getSubjectContent());
				pstmt.setString(3, subject.getUserId());
				pstmt.setInt(4, subject.getSubjectReadCnt());
				pstmt.setInt(5, subject.getRe_ref());
				pstmt.setInt(6, subject.getRe_lev());
				pstmt.setInt(7, subject.getRe_seq());
				
				pstmt.executeUpdate();

			} finally {
				SourceReturn();
			}
		}
		
		public void removeSubject(int num) throws SQLException {
			String sql = "delete from subject where subjectNum = ?";
					
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, num);
				
				pstmt.executeUpdate();
				
			} finally {
				SourceReturn();
			}
		}
		
		public Subject viewSubject(int num) throws SQLException{
			String sql = "select * from subject where subjectNum = ?";
			Subject subject = new Subject();
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					subject.setSubjectNum( rs.getInt("subjectNum") );
					subject.setSubjectName( rs.getString("subjectName") );
					subject.setSubjectContent( rs.getString("subjectContent") );
					subject.setUserId( rs.getString("userId") );
					subject.setSubjectReadCnt( rs.getInt("subjectReadcnt") );
					subject.setSubjectDate( rs.getDate("subjectDate") );
					subject.setRe_lev( rs.getInt("re_ref") );
					subject.setRe_ref( rs.getInt("re_ref") );
					subject.setRe_seq( rs.getInt("re_seq") );
				}
			} catch (Exception e) {
				logger.debug("viewSubject error:"+e.getMessage());
			} finally {
				SourceReturn();
			}
			return subject;
		}
		
		public void updateReadcont(int num) throws SQLException{
			String sql = "update subject set subjectReadcnt = subjectReadcnt + 1 where subjectNum = ?";
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
		
		public void updateSubject(Subject subject) throws SQLException {
			String sql = "update subject set subjectName = ?, subjectContent = ? ,subjectDate=? where subjectNum = ?";
					
			conn = getConnection();
			
			Timestamp date=new Timestamp(new Date().getTime());
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, subject.getSubjectName());
				pstmt.setString(2, subject.getSubjectContent());
				pstmt.setTimestamp(3, date);
				pstmt.setInt(4, subject.getSubjectNum());
				
				pstmt.execute();
				logger.debug("UpdateSubject : " + subject);
			} catch (Exception e) {
				logger.debug("UpdateSubject error : " + e);
				logger.debug(subject + "");
			} finally {
				SourceReturn();
			}
		}
		
		
		public void addFile(String File_Path,String File_Name,String Author,int subjectNum) throws SQLException{
			String sql="insert into Files(File_Path,File_Name,Author,subjectNum) values(?,?,?,?)";
			try{
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, File_Path);
			pstmt.setString(2, File_Name);
			pstmt.setString(3, Author);
			pstmt.setInt(4, subjectNum);
			pstmt.executeUpdate();
			}catch(SQLException e){
				logger.debug("addFile error:"+e.getMessage());
			}
			finally{
			SourceReturn();
			}
		}
		
		public List getUploadList (String userId,int subjectNum) throws SQLException{
			String sql,sql2;	
			List list = new ArrayList(); // 목록 리턴을 위한 변수
			
					try{
						
						conn=getConnection();
						sql2="select subjectName from files join subject on ? = subject.subjectNum";
						
						if(userId.equals("master")){
							sql="select * from files where subjectNum=?";
							pstmt=conn.prepareStatement(sql);
							pstmt.setInt(1,subjectNum );
							pstmt2=conn.prepareStatement(sql2);
							
						}
						else{
							sql="select * from files where Author=? AND subjectNum=?";
							pstmt=conn.prepareStatement(sql);
							pstmt.setString(1, userId);
							pstmt.setInt(2, subjectNum);
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
							file.setSubjectNum(rs.getInt("subjectNum"));
							pstmt2.setInt(1, rs.getInt("subjectNum"));  // subjectNum을 이용하여 subjectName을 찾는다.
							rs2=pstmt2.executeQuery();
							while(rs2.next()){
								file.setSubjectName(rs2.getString("subjectName"));
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
