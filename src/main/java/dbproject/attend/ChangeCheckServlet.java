package dbproject.attend;

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

@WebServlet("/attend/ChangeCheckyn")
public class ChangeCheckServlet extends HttpServlet {
	
	public static final String SESSION_USER_ID = "userId";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		AttendDAO attendDAO = new AttendDAO();
		
//		String userId = request.getParameter(SESSION_USER_ID);
		String subjectName = request.getParameter("subjectName");
		String checkyn = request.getParameter("checkyn");
		String changeyn;
		if(checkyn.equals("n")) {
			changeyn = "y";
		}
		else {
			changeyn = "n";
		}
		int cday = Integer.parseInt(request.getParameter("cday"));
		int maxDay = Integer.parseInt(request.getParameter("maxDay"));
		if(cday > maxDay) {
			cday = 0;
		}
		else if(cday < 0) {
			cday = maxDay;
		}
		
		try {
			attendDAO.setCheckyn(subjectName, changeyn);
			attendDAO.setCday(subjectName, cday);
			attendDAO.setAttendyn(subjectName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println(subjectName);
//		System.out.println(checkyn);
//		System.out.println(changeyn);
//		System.out.println(cday);
		
		response.sendRedirect("/attend/Attendlist");
		
	}

	private void errorForward(HttpServletRequest request, HttpServletResponse response, String errorMessage)
		throws ServletException, IOException {
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher rd = request.getRequestDispatcher("/attend/Attendlist");
			rd.forward(request, response);
		}

}
