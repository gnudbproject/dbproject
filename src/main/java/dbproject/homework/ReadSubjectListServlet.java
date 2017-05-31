package dbproject.homework;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dbproject.subject.SubjectDAO;

@WebServlet("/subjects/subjectList")
public class ReadSubjectListServlet extends HttpServlet {
	public Logger logger = LoggerFactory.getLogger(ReadSubjectListServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SubjectDAO subjectDAO = new SubjectDAO();

		int subjectCount;
		int allSubjectCount;
		String[] subjectName;
		String[] allSubjectName;
		try {

			allSubjectCount = subjectDAO.getAllSubjectCount();
			allSubjectName = new String[allSubjectCount];
			allSubjectName = subjectDAO.getAllSubjectName(allSubjectCount);

			request.setAttribute("allSubjectCount", allSubjectCount);
			request.setAttribute("allSubjectName", allSubjectName);

			RequestDispatcher rd = request.getRequestDispatcher("/homework.jsp");
			rd.forward(request, response);

		} catch (SQLException e) {
			logger.debug("ReadSubjectListServlet error:"+e.getMessage());
		}

	}
}
