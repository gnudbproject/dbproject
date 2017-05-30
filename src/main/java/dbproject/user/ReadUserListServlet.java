package dbproject.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/users/userList")
public class ReadUserListServlet extends HttpServlet {
	public static Logger logger=LoggerFactory.getLogger(ReadUserListServlet.class);
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO userDao=new UserDAO();
		List<User> users=new ArrayList<User>();
		try {
			users=userDao.getUserList();
			logger.debug("users:"+users.get(0).toString());
			request.setAttribute("users", users);
		} catch (SQLException e) {
			logger.debug("ReadUserListServlet error:"+e.getMessage());
		}
		RequestDispatcher rd=request.getRequestDispatcher("/user_manage.jsp");
		rd.forward(request, response);
	}
}
