package dbproject.subject;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dbproject.support.SessionUtils;
import dbproject.user.LoginServlet;

@WebServlet("/subjects/viewSubject")
public class ReadSubjectServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(ReadSubjectServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		SubjectDAO subjectDao = new SubjectDAO();
		Subject subject = new Subject();
		
		HttpSession session = req.getSession();
		
		String userId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		
		int num = Integer.parseInt( req.getParameter("num") );
		String subject_userId = req.getParameter("subject_userId");
		
		if(!userId.equals(subject_userId)) {
			req.setAttribute("isNotUser", true);
		}
		
		else req.setAttribute("isUser", true);
		
		try {
			subjectDao.updateReadcont(num);
			subject = subjectDao.viewSubject(num);
			
			
			req.setAttribute("isView", true);
			req.setAttribute("subject", subject);
			
			RequestDispatcher rd = req.getRequestDispatcher("/subject_form.jsp");
			rd.forward(req, resp);
			
		} catch (Exception e) {
			logger.debug("SubjectviewServlet error : " + e.getMessage());
		} 
	}
}
