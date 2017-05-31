package dbproject.board;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dbproject.user.UserDAO;

public class BoardDAO {
	private static final Logger logger = LoggerFactory.getLogger(BoardDAO.class);
	
	
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
	
	public int getListCount() throws SQLException {

		String sql = "select count(*) from boards";
		
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
	
	public List getBoardList (int page, int limit) throws SQLException{
		
		List list = new ArrayList(); // 목록 리턴을 위한 변수
		
		// 목록를 조회하기 위한 쿼리
		String sql = "select * from boards order by num desc limit ?,?"; 
		
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
				Board board = new Board();
				board.setNum(rs.getInt("Num"));
				board.setUserId(rs.getString("UserId"));
				board.setSubject(rs.getString("Subject"));
				board.setContent(rs.getString("Content"));
				board.setReadcnt(rs.getInt("Readcnt"));
				board.setDate(rs.getDate("Date"));
				
				list.add(board); // 행을 하나씩 리스트에 추가
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
	
	public Board findByBoardInfo(int num) throws SQLException {
		String sql = "select * from boards where Num = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery(); // 결과를 받아와 저장

			if (!rs.next()) {
				return null;
			}

			return new Board(rs.getString("subject"), rs.getString("content"), rs.getString("userId"));

		} finally {
			SourceReturn();
		}
	}
	
	public void addBoard(Board board) throws SQLException {
		String sql = "insert into boards(Subject,Content,userId,Readcnt) values(?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getSubject());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getUserId());
			pstmt.setInt(4, board.getReadcnt());

			pstmt.executeUpdate();

		} finally {
			SourceReturn();
		}
	}
	
	
	
	
	
	
	public void removeBoard(int num) throws SQLException {
		String sql = "delete from boards where Num = ?";
				
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			
		} finally {
			SourceReturn();
		}
	}
	
	public Board viewBoard(int num) throws SQLException{
		String sql = "select * from boards where Num = ?";
		Board board = new Board();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board.setNum( rs.getInt("Num") );
				board.setSubject( rs.getString("SubJect") );
				board.setContent( rs.getString("Content") );
				board.setUserId( rs.getString("UserId") );
				board.setReadcnt( rs.getInt("Readcnt") );
				board.setDate( rs.getDate("Date") );
				
			}
			logger.debug(board + "");
		} catch (Exception e) {
		} finally {
			SourceReturn();
		}
		return board;
	}
	
	public void updateReadcont(int num) throws SQLException{
		String sql = "update boards set Readcnt = Readcnt + 1 Where Num = ?";
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
	
	public void updateBoard(Board board) throws SQLException {
		String sql = "update boards set SubJect = ?, Content = ? ,Date=? where Num = ?";
				
		conn = getConnection();
		
		Timestamp date=new Timestamp(new Date().getTime());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getSubject());
			pstmt.setString(2, board.getContent());
			pstmt.setTimestamp(3, date);
			pstmt.setInt(4, board.getNum());
			
			pstmt.execute();
			logger.debug("UpdateBoard : " + board);
		} catch (Exception e) {
			logger.debug("UpdateBoard error : " + e);
			logger.debug(board + "");
		} finally {
			SourceReturn();
		}
	}
	
	
	
	
	//추가 sh 밑으로 전부
	public List<reBoardDTO> getReBoardList (int num) throws SQLException{
		
		List<reBoardDTO> list = new ArrayList<reBoardDTO>(); // 목록 리턴을 위한 변수
		
		// 목록를 조회하기 위한 쿼리
		String sql = "select * from reBoard where boardNum = ? order by Num desc";
			
		try{
			conn = getConnection();
			// 실행을 위한 쿼리 및 파라미터 저장
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery(); // 쿼리 실행 
			
			while(rs.next()){
				reBoardDTO board = new reBoardDTO();
				board.setNum( rs.getInt("Num") );
				board.setContent( rs.getString("Content") );
				board.setUserId( rs.getString("UserId") );
				board.setBoardNum( rs.getInt("BoardNum") );
				
				list.add(board); // 행을 하나씩 리스트에 추가
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
	
	public void addReBoard(int bNum, String userId, String content ) throws SQLException {
		String sql = "insert into reBoard values(?,?,?,?)";
		int num = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select max(Num) from reBoard");
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				num = rs.getInt(1)+1;
			else
				num = 1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, userId);
			pstmt.setString(3, content);
			pstmt.setInt(4, bNum);

			pstmt.executeUpdate();

		} finally {
			SourceReturn();
		}
	}

	public void removeReBoard(int num) throws SQLException {
		String sql = "delete from reBoard where Num = ?";
				
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			
		} finally {
			SourceReturn();
		}
	}
	
	public void removeAllReBoard(int num) throws SQLException {
		String sql = "delete from reBoard where boardNum = ?";
				
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			
		} finally {
			SourceReturn();
		}
	}
	
}
