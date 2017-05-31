package dbproject.user;

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

public class UserDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
	
	
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
	
	public Connection getConnection() {
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
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void addUser(User user) throws SQLException {
		String sql = "insert into users values(?,?,?,?,?,?,?)";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getAge());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getGender());
			pstmt.setInt(7, user.getPower());

			pstmt.executeUpdate();

		} finally {
			SourceReturn();
		}
	}

	public User findByUserId(String userId) throws SQLException {
		String sql = "select * from users where userId = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery(); // 결과를 받아와 저장

			if (!rs.next()) {
				return null;
			}

			return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("age"),
					rs.getString("email"), rs.getString("gender"),rs.getInt("power"));

		} finally {

			SourceReturn();
		}
	}
	
	
	public void removeUser(String userId) throws SQLException {
		String sql = "delete from users where userId = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			pstmt.executeUpdate();
		} finally {
			SourceReturn();
		}
	}

	public void updateUser(User user) throws SQLException {

		String sql = "update users set password = ?, name = ?, age = ?, email = ?, gender = ? , power=? where userId = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getAge());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getGender());
			pstmt.setInt(6, user.getPower());
			pstmt.setString(7, user.getUserId());

			pstmt.executeUpdate();

		} finally {

			SourceReturn();
		}
	}
	public List getUserList() throws SQLException{
		String sql="select * from users";
		List userList=new ArrayList();
		try{
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				User user=new User();
				user.setUserId(rs.getString("userId"));
				user.setName(rs.getString("name"));
				user.setAge(rs.getString("age"));
				user.setEmail(rs.getString("email"));
				user.setGender(rs.getString("gender"));
				user.setPower(rs.getInt("power"));
				userList.add(user);
			}
			
		}catch(SQLException e){
			logger.debug("getUserList error:"+e.getMessage());
		}
		finally{
			SourceReturn();
			return userList;
		}
	}
}
