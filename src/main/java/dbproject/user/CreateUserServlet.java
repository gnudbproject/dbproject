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
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import dbproject.support.MyvalidatorFactory;

@WebServlet("/users/create")
public class CreateUserServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		int power = Integer.parseInt(request.getParameter("power"));
		
		UserDAO userDAO = new UserDAO();
		User user = new User(userId, password, name, age, email, gender,power);
		
		
		// Validator 유효성 체크
		Validator validator = MyvalidatorFactory.createValidator();
		Set<ConstraintViolation<User>> constraintViolations = validator.validate( user );
		
		if ( constraintViolations.size() > 0) {
			request.setAttribute("user", user);
			String errorMessage = constraintViolations.iterator().next().getMessage();
			errorForward(request, response, errorMessage);
			return;
		}
		
		
		try {
			userDAO.addUser(user);
		} catch (SQLException e) {
		}
		if(session.getAttribute("isMaster")!=null){
			response.sendRedirect("/main.jsp");
		}
		else{
			response.sendRedirect("/");
		}
	}
	
	private void errorForward(HttpServletRequest request, HttpServletResponse response, String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = request.getRequestDispatcher("/form.jsp");
		rd.forward(request, response);
	}
}
