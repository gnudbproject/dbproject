package dbproject.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbproject.support.SessionUtils;
import dbproject.user.LoginServlet;


@WebServlet("/replyReply")
public class reBoardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		String userId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		
		int num = Integer.parseInt( req.getParameter("num") );
		String content = req.getParameter("reContent");

		
		BoardDAO boardDao = new BoardDAO();

		try {
			boardDao.addReBoard(num, userId, content );
			resp.sendRedirect("/board/viewBoard?num="+num);
		} catch (Exception e) {}
		
	}
}
