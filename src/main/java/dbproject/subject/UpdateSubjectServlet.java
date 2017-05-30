package dbproject.subject;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/subjects/updateSubject")
public class UpdateSubjectServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(UpdateSubjectServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		int num = Integer.parseInt( req.getParameter("num") );
		String subjectName = req.getParameter("subject");
		String content = req.getParameter("content");
		
		Subject subject = new Subject();
		subject.setSubjectNum(num);
		subject.setSubjectName(subjectName);
		subject.setSubjectContent(content);
		
		SubjectDAO subjectDao = new SubjectDAO();
		
		try {
			logger.debug("테스트 : " + subject);
			subjectDao.updateSubject(subject);
			resp.sendRedirect("/subjects/subjectList");
		} catch (Exception e) {
			logger.debug("updateSubject Servlet error" + e);
		}
		
	}
}