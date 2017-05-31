package dbproject.subject;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbproject.attend.AttendDAO;

@WebServlet("/subject/AddSubject")
public class AddSubjectServlet extends HttpServlet {
	
	public static final String SESSION_USER_ID = "userId";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		request.setCharacterEncoding("UTF-8");
		SubjectDAO subjectDAO = new SubjectDAO();
		AttendDAO attendDAO=new AttendDAO();
		
		String userId = (String)session.getAttribute(SESSION_USER_ID);
		String subjectName = request.getParameter("subjectName");
		int day = Integer.parseInt(request.getParameter("day"));
		
		try {
			subjectDAO.subjectRequest(userId, subjectName, "master");
			subjectDAO.addSubject(subjectName, day);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("/subject/Subjectlist");
		
	}

	private void errorForward(HttpServletRequest request, HttpServletResponse response, String errorMessage)
		throws ServletException, IOException {
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher rd = request.getRequestDispatcher("/subject/Subjectlist");
			rd.forward(request, response);
		}

}
