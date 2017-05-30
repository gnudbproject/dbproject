package dbproject.upload;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbproject.subject.SubjectDAO;

@WebServlet("/subjects/createSubject")
public class CreateSubjectServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UploadDAO uploadDao = new UploadDAO();
		request.setCharacterEncoding("UTF-8");
		String subjectName = request.getParameter("subject");
		String content = request.getParameter("content");
		String userId = request.getParameter("userId");
		
		Subject subject = new Subject(subjectName, content, userId);
	
		try {
		
			uploadDao.addSubject(subject);
		} catch (SQLException e) {
			System.out.println(e);
		}

		
		response.sendRedirect("/subjects/subjectList");
	
	}
}
