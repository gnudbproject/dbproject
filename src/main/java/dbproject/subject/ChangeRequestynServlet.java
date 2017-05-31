package dbproject.subject;

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
import javax.servlet.http.HttpSession;

import dbproject.user.LoginServlet;
import dbproject.user.SessionUtils;

@WebServlet("/subject/ChangeRequestyn")
public class ChangeRequestynServlet extends HttpServlet {
	
//	public static final String SESSION_USER_ID = "userId";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//		HttpSession session = request.getSession();
		
		SubjectDAO subjectDAO = new SubjectDAO();
		
//		String userId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		String subjectName = request.getParameter("subjectName");
		String requestyn = request.getParameter("requestyn");
		String changeyn;
		if(requestyn.equals("n")) {
			changeyn = "y";
		}
		else {
			changeyn = "n";
		}
		
		try {
			subjectDAO.setRequestyn(subjectName, changeyn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println(subjectName);
//		System.out.println(checkyn);
//		System.out.println(changeyn);
//		System.out.println(cday);
		
		response.sendRedirect("/subject/Subjectlist");
		
	}

	private void errorForward(HttpServletRequest request, HttpServletResponse response, String errorMessage)
		throws ServletException, IOException {
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher rd = request.getRequestDispatcher("/subject/Subjectlist");
			rd.forward(request, response);
		}

}
