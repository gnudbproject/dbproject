package dbproject.visitors;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class VisitorsNotesServlet
 */
@WebServlet("/visitorsnotes")
public class VisitorsNotesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VisitorsNotesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<VisitorsNote> visitorsNotesList = new ArrayList<>();
		VisitorsDao visitorsDao = new VisitorsDao();

		int page = 1;
		int limit = 10;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int listCount = 0;
		try {
			// 총 리스트 수를 받아옴
			listCount = visitorsDao.getListCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 리스트를 받아옴
		try {
			visitorsNotesList = visitorsDao.getVisitorsNotesList(page, limit);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 총 페이지 수로 0.95를 더해서 올림 처리
		int maxPage = (int) ((double) listCount / limit + 0.95);
		// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
		int startPage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		// 현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
		int endPage = startPage + 10 - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("visitorsNotesList", visitorsNotesList);
		RequestDispatcher rd = request.getRequestDispatcher("/visitorsnotes.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		request.setAttribute("page", null);
		VisitorsDao visitorsDao = new VisitorsDao();
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userId");
		String text = request.getParameter("notetext");
		try {
			visitorsDao.AddVisitorsNote(userid, text);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
