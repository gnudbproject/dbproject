package dbproject.visitors;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import dbproject.user.UserDAO;

public class VisitorsDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void SourceReturn() throws SQLException {
		if (this.conn != null) {
			conn.close();
		}
		if (this.pstmt != null) {
			pstmt.close();
		}
		if (this.rs != null) {
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
			return null;
		}
	}

	public int getListCount() throws SQLException {
		String sql = "SELECT COUNT(*) FROM visitorsbook";
		int count = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
		} finally {
			SourceReturn();
		}
		return count;
	}

	public ArrayList<VisitorsNote> getVisitorsNotesList(int page, int limit) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		VisitorsNote note = null;
		Timestamp timestamp = null;
		ArrayList<VisitorsNote> list = new ArrayList<>();
		String sql = "SELECT vbcode, visitorsbook.userid, vbtext, vbdate, name FROM visitorsbook INNER JOIN users ON visitorsbook.userid = users.userid ORDER BY vbdate DESC LIMIT ?, 10";
		int startrow = (page - 1) * 10; // 읽기 시작할 row 번호..

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				note = new VisitorsNote();
				note.setVbcode(rs.getInt("vbcode"));
				note.setUserid(rs.getString("userid"));
				note.setVbtext(rs.getString("vbtext"));
				timestamp = rs.getTimestamp("vbdate");
				note.setVbdate(new Date(timestamp.getTime()));
				note.setName(rs.getString("name"));
				list.add(note);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SourceReturn();
		}
		return list;
	}

	public void AddVisitorsNote(String userid, String vbtext) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO visitorsbook (userid, vbtext) VALUES (?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, vbtext);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SourceReturn();
		}
	}
}
