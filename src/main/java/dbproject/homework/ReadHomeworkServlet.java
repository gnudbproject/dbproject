package dbproject.homework;

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

@WebServlet("/homeworks/viewHomework")
public class ReadHomeworkServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(ReadHomeworkServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		UploadDAO uploadDao = new UploadDAO();
		Homework homework = new Homework();
		
		HttpSession session = req.getSession();
		
		String userId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		
		int num = Integer.parseInt( req.getParameter("num") );
		String homework_userId = req.getParameter("homework_userId");
		
		if(!userId.equals(homework_userId)) {
			req.setAttribute("isNotUser", true);
		}
		
		else req.setAttribute("isUser", true);
		
		try {
			uploadDao.updateReadcont(num);
			homework = uploadDao.viewHomework(num);
			
			
			req.setAttribute("isView", true);
			req.setAttribute("homework", homework);
			
			RequestDispatcher rd = req.getRequestDispatcher("/homework_form.jsp");
			rd.forward(req, resp);
			
		} catch (Exception e) {
			logger.debug("HomeworkviewServlet error : " + e.getMessage());
		} 
	}
}
