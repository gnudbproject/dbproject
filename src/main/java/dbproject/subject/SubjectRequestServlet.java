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

@WebServlet("/subject/SubjectRequest")
public class SubjectRequestServlet extends HttpServlet {
	
	public static final String SESSION_USER_ID = "userId";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		SubjectDAO subjectDAO = new SubjectDAO();
		
		String userId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		String subjectName = request.getParameter("subjectName");
		int maxDay = Integer.parseInt(request.getParameter("maxDay"));
		
		String stamp = "";
		
		for(int i = 0; i < maxDay; i++) {
			stamp = stamp + "0";
		}
		
		
		try {
			subjectDAO.subjectRequest(userId, subjectName, stamp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println(subjectName);
//		System.out.println(maxDay);
//		System.out.println(stamp);
		
		response.sendRedirect("/subject/Subjectlist");
		
	}

	private void errorForward(HttpServletRequest request, HttpServletResponse response, String errorMessage)
		throws ServletException, IOException {
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher rd = request.getRequestDispatcher("/subject/Subjectlist");
			rd.forward(request, response);
		}

}
