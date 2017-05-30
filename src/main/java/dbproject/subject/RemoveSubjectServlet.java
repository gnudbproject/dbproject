package dbproject.subject;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/subjects/removeSubject")
public class RemoveSubjectServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(RemoveSubjectServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		int num = Integer.parseInt( req.getParameter("num") );
		
		SubjectDAO subjectDao = new SubjectDAO();
		
		try {
			subjectDao.removeSubject(num);

			resp.sendRedirect("/board/Boardlist");
		} catch (Exception e) {
			logger.debug("RemoveBoardServlet error" + e);
		}
		
	}
}