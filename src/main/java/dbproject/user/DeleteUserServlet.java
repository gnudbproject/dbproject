package dbproject.user;

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

@WebServlet("/users/dropuser")
public class DeleteUserServlet extends HttpServlet {
	
	private static final Logger logger = LoggerFactory.getLogger(UpdateUserServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String userId=request.getParameter("userId");
		HttpSession session = request.getSession();
		
		try {
			UserDAO userDao = new UserDAO();
			userDao.removeUser(userId);
			
			//만약 제거한 아이디가 본인 아이디이면 로그아웃처리
			String sessionId=SessionUtils.getStringValue(session, "userId");  
			if(sessionId.equals(userId)){
				session.removeAttribute("userId");
				if(!SessionUtils.isEmpty(session, "isMaster"))
					session.removeAttribute("isMaster");
				if(!SessionUtils.isEmpty(session, "subjectName"))
					session.removeAttribute("subjectName");
				response.sendRedirect("/");
			}
			//본인 아이디가 아닐경우
			else{
			RequestDispatcher rd=request.getRequestDispatcher("/users/userList");
			rd.forward(request, response);
			}
			
		} catch (Exception e) {
			logger.debug("drop fail:" + e);
		}
		
		
	}
}
