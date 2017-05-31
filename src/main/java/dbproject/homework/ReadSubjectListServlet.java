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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dbproject.attend.AttendDAO;

@WebServlet("/subjects/subjectList")
public class ReadSubjectListServlet extends HttpServlet {
	public Logger logger = LoggerFactory.getLogger(ReadSubjectListServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session =request.getSession();
		AttendDAO attendDAO = new AttendDAO();
		
		int subjectCount;
		String[] subjectNames;
		String[] allSubjectName;
		String userId=(String)session.getAttribute("userId");
		try {

			subjectCount = attendDAO.getSubjectCount(userId);
			subjectNames = new String[subjectCount];
			subjectNames = attendDAO.getSubjectName(subjectCount, userId);

			request.setAttribute("subjectCount", subjectCount);
			request.setAttribute("subjectNames", subjectNames);

			RequestDispatcher rd = request.getRequestDispatcher("/homework.jsp");
			rd.forward(request, response);

		} catch (SQLException e) {
			logger.debug("ReadSubjectListServlet error:"+e.getMessage());
		}

	}
}
