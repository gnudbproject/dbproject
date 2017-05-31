package dbproject.homework;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet("/homeworks/updateHomework")
public class UpdateHomeworkServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(UpdateHomeworkServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		int num = Integer.parseInt( req.getParameter("num") );
		String homeworkName = req.getParameter("homework");
		String content = req.getParameter("content");
		
		Homework homework = new Homework();
		homework.setHomeworkNum(num);
		homework.setHomeworkName(homeworkName);
		homework.setHomeworkContent(content);
		
		UploadDAO uploadDao = new UploadDAO();
		
		try {
			logger.debug("테스트 : " + homework);
			uploadDao.updateHomework(homework);
			resp.sendRedirect("/homeworks/homeworkList");
		} catch (Exception e) {
			logger.debug("updateHomework Servlet error" + e);
		}
		
	}
}