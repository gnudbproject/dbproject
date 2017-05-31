package dbproject.attend;

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

@WebServlet("/attend/Attendlist")
public class AttendlistServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String userId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		
		boolean yn = false;
		if(session.getAttribute("isMaster")!=null) {
			yn = true;
		}
		
		
		String masterYN;
		
//		List list = new ArrayList();
		AttendDAO attendDao = new AttendDAO();
		 
		int subjectCount;
		int allSubjectCount;
		int userCheckynCount;
		
		int allAttendCount;
		
		String[] subjectName;
		String[] allSubjectName;
		String[] stamp;
		String[] checkyn;
		String[] userCheckyn;
		String[] maxDay;
		String[] userStamp;
		
		String[] allAttenduserId;
		String[] allAttendSubjectName;
		String[] allAttendStamp;
		
		
		
		int[] cday;
		int[] userCday;
		
		try {
			allSubjectCount = attendDao.getAllSubjectCount();
			checkyn = new String[allSubjectCount];
			checkyn = attendDao.getCheckyn(allSubjectCount);
			allSubjectName = new String[allSubjectCount];
			allSubjectName = attendDao.getAllSubjectName(allSubjectCount);
			cday = attendDao.getCday(allSubjectCount);
			maxDay = attendDao.getMaxDay(allSubjectCount);
			
			if(yn){
				masterYN = "/attendmaster.jsp";
				
				allAttendCount = attendDao.getAllAttendCount();
				allAttenduserId = attendDao.getAllAttenduserId(allAttendCount);
				allAttendSubjectName = attendDao.getAllAttendSubjectName(allAttendCount);
				allAttendStamp = attendDao.getAllAttenduStamp(allAttendCount);
				
				request.setAttribute("allAttendCount", allAttendCount); 	
				request.setAttribute("allAttenduserId", allAttenduserId);
				request.setAttribute("allAttendSubjectName", allAttendSubjectName); 	
				request.setAttribute("allAttendStamp", allAttendStamp);
				
//				stamp = new String[allSubjectCount];
//				stamp = attendDao.getStamp(allSubjectCount);
			}
			else {
				masterYN = "/attend.jsp";
				subjectCount = attendDao.getSubjectCount(userId);
				subjectName = new String[subjectCount];
				subjectName = attendDao.getSubjectName(subjectCount, userId);
				
				stamp = new String[subjectCount];
				stamp = attendDao.getStamp(subjectCount, userId);
				
				userCheckynCount = attendDao.getUserCheckynCount(userId);
				userCheckyn = attendDao.getUserCheckyn(userId, userCheckynCount);
				userStamp = attendDao.getUserStamp(userId, userCheckynCount);
				
				userCday = attendDao.getUserCday(userId, userCheckynCount);
				String suserCday[] = new String[userCheckynCount];
				for(int i = 0; i < userCheckynCount; i++) {
					suserCday[i] = userCday[i] + "";
				}
				
				request.setAttribute("subjectCount", subjectCount); 
				request.setAttribute("subjectName", subjectName);
				
				request.setAttribute("stamp", stamp);
				
				request.setAttribute("userCheckynCount", userCheckynCount);
				request.setAttribute("userCheckyn", userCheckyn);
				request.setAttribute("userStamp", userStamp);
				request.setAttribute("suserCday", suserCday);
				
				
			}
			
			request.setAttribute("checkyn", checkyn);
			request.setAttribute("allSubjectCount", allSubjectCount); 	
			request.setAttribute("allSubjectName", allSubjectName);
			request.setAttribute("maxDay", maxDay);
			
			
			String scday[] = new String[allSubjectCount];
			for(int i = 0; i < allSubjectCount; i++) {
				scday[i] = cday[i] + "";
			}
			request.setAttribute("scday", scday);
			
			RequestDispatcher rd = request.getRequestDispatcher(masterYN);
			rd.forward(request, response);
	
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
