package dbproject.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//추가 sh
@WebServlet("/board/removeReBoard")
public class RemoveReBoardServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(RemoveReBoardServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		int reNum = Integer.parseInt( req.getParameter("reNum") );
		int bNum = Integer.parseInt( req.getParameter("bNum") );

		BoardDAO boardDao = new BoardDAO();
		
		try {
			boardDao.removeReBoard(reNum);
			resp.sendRedirect("/board/viewBoard?num="+bNum);
		} catch (Exception e) {
			logger.debug("RemoveBoardServlet error" + e);
		}
		
	}
}