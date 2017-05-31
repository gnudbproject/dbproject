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
import javax.servlet.http.HttpSession;

import dbproject.user.LoginServlet;
import dbproject.user.SessionUtils;

@WebServlet("/attend/AttendCheck")
public class AttendCheckServlet extends HttpServlet {
	
	public static final String SESSION_USER_ID = "userId";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		
		AttendDAO attendDAO = new AttendDAO();
		
		String userId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		String subjectName = request.getParameter("subjectName");
		String stamp = request.getParameter("stamp");
		
		String suserCday = request.getParameter("suserCday");
		int userCday = Integer.parseInt(suserCday);
		
		String changeStamp = "";
		
		
		String[] cStamp = stamp.split("");
		
		cStamp[userCday - 1] = "1";
		for(int i = 0; i < cStamp.length; i++) {
			changeStamp = changeStamp + cStamp[i];
		}
		
		
		try {
			attendDAO.attendCheck(changeStamp, userId, subjectName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(userCday);
		System.out.println(subjectName);
		System.out.println(changeStamp);
		System.out.println(stamp);
		
		response.sendRedirect("/attend/Attendlist");
		
	}

	private void errorForward(HttpServletRequest request, HttpServletResponse response, String errorMessage)
		throws ServletException, IOException {
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher rd = request.getRequestDispatcher("/attend/Attendlist");
			rd.forward(request, response);
		}

}
