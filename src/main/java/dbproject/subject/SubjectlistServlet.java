package dbproject.subject;

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

@WebServlet("/subject/Subjectlist")
public class SubjectlistServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		boolean yn = false;
		if(session.getAttribute("isMaster")!=null) {
			yn = true;
		}
		String userId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		
		String masterYN;
		
//		List list = new ArrayList();
		SubjectDAO subjectDAO = new SubjectDAO();
		 
		int subjectCount;
		int allSubjectCount;
		String[] subjectName;
		String[] allSubjectName;
		String[] stamp;
		String[] checkyn;
		String[] requestyn;
		String[] maxDay;
		
		
		try {
			allSubjectCount = subjectDAO.getAllSubjectCount();
			checkyn = new String[allSubjectCount];
			checkyn = subjectDAO.getCheckyn(allSubjectCount);
			allSubjectName = new String[allSubjectCount];
			allSubjectName = subjectDAO.getAllSubjectName(allSubjectCount);
			requestyn = subjectDAO.getRequestyn(allSubjectCount);
			maxDay = subjectDAO.getMaxDay(allSubjectCount);
			
			if(yn){
				masterYN = "/subjectmaster.jsp";
//				stamp = new String[allSubjectCount];
//				stamp = attendDao.getStamp(allSubjectCount);
			}
			else {
				masterYN = "/subject.jsp";
				subjectCount = subjectDAO.getSubjectCount(userId);
				subjectName = new String[subjectCount];
				subjectName = subjectDAO.getSubjectName(subjectCount, userId);
				stamp = new String[subjectCount];
				stamp = subjectDAO.getStamp(subjectCount, userId);
				
				request.setAttribute("subjectCount", subjectCount); 
				request.setAttribute("subjectName", subjectName);
				
				request.setAttribute("stamp", stamp);
			}
		
			request.setAttribute("checkyn", checkyn);
			request.setAttribute("requestyn", requestyn);
			request.setAttribute("allSubjectCount", allSubjectCount); 	
			request.setAttribute("allSubjectName", allSubjectName);
			request.setAttribute("maxDay", maxDay);
			
			RequestDispatcher rd = request.getRequestDispatcher(masterYN);
			rd.forward(request, response);
	
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
