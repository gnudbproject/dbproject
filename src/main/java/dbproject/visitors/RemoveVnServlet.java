package dbproject.visitors;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbproject.board.BoardDAO;

/**
 */
@WebServlet("/rmvnote")
public class RemoveVnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveVnServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int code = Integer.parseInt(request.getParameter("code"));
		int page = Integer.parseInt(request.getParameter("page"));

		VisitorsDao visitorsDao = new VisitorsDao();

		try {
			visitorsDao.RemoveVisitorsNote(code);
			response.sendRedirect("/visitorsnotes?page=" + page);
		} catch (Exception e) {
		}
	}
}
