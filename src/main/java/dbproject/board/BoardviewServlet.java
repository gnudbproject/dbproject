package dbproject.board;

import java.util.ArrayList;
import java.util.List;import java.io.IOException;
import java.sql.SQLException;

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
import dbproject.user.User;
import dbproject.user.UserDAO;

@WebServlet("/board/viewBoard")
public class BoardviewServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(BoardviewServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		BoardDAO boardDao = new BoardDAO();
		Board board = new Board();
		
		List list = new ArrayList(); // 추가 sh 		 

		HttpSession session = req.getSession();
		
		String userId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		
		int num = Integer.parseInt( req.getParameter("num") );
		String board_userId = req.getParameter("board_userId");
		
		if(!userId.equals(board_userId)) {
			req.setAttribute("isNotUser", true);
		}
		
		else req.setAttribute("isUser", true);
		
		
		try {
			boardDao.updateReadcont(num);
			board = boardDao.viewBoard(num);
			
			list = boardDao.getReBoardList(num);//추가 sh
			
			if(board == null) {
				logger.debug("Board View Fail");
			}
			
			req.setAttribute("isView", true);
			req.setAttribute("board", board);
			logger.debug("확인");
			req.setAttribute("list", list); //추가 sh
			req.setAttribute("isCreate", false);//추가 sh

			logger.debug("확인2");
			
			
			// 이곳에 댓글 정보도
			
			RequestDispatcher rd = req.getRequestDispatcher("/board_form.jsp");
			rd.forward(req, resp);
			
		} catch (Exception e) {
			logger.debug("BoardviewServlet error : " + e);
		} 
	}
}
