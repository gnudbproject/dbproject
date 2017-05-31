package dbproject.homework;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbproject.support.SessionUtils;
import dbproject.user.LoginServlet;
import dbproject.user.User;
import dbproject.user.UserDAO;

@WebServlet("/homeworks/createHomeworkForm")
public class CreateFormHomeworkServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String userId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		
	
		UserDAO userDao = new UserDAO();
		
		try {
			request.setAttribute("isCreate", true);
			User user = userDao.findByUserId(userId);
			request.setAttribute("user", user);
			Homework homework =new Homework();
			request.setAttribute("homework", homework);
			
			RequestDispatcher rd = request.getRequestDispatcher("/homework_form.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
		}

	}
}
