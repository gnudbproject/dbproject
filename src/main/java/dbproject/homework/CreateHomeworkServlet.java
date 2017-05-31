package dbproject.homework;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/homeworks/createHomework")
public class CreateHomeworkServlet extends HttpServlet{
	Logger logger=LoggerFactory.getLogger(CreateHomeworkServlet.class);
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UploadDAO uploadDao = new UploadDAO();
		request.setCharacterEncoding("UTF-8");
		String homeworkName = request.getParameter("homeworkName");
		String content = request.getParameter("content");
		String userId = request.getParameter("userId");
		String subjectName = request.getParameter("subjectName");
		
		Homework homework = new Homework(homeworkName, content, userId,subjectName);
	
		try {
			logger.debug("homework:"+homework.toString());
			uploadDao.addHomework(homework);
		} catch (SQLException e) {
			System.out.println(e);
		}

		
		response.sendRedirect("/homeworks/homeworkList");
	
	}
}
