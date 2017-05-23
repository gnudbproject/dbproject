package dbproject.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import dbproject.support.MyvalidatorFactory;

@WebServlet("/users/check")
public class userCheckServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("id");
		
		UserDAO userDAO = new UserDAO();

		try {
			if (userDAO.findByUserId(userId) != null) {
				request.setAttribute("errorMessage", "중복된 아이디가 존재합니다.");
			}else{
				request.setAttribute("errorMessage", "사용가능한 아이디입니다.");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/idCheck.jsp");
		rd.forward(request, response);
	}
	
}
