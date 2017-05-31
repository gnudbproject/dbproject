package dbproject.homework;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet("/files/uploadlist")
public class ReadUploadListServlet extends HttpServlet{
	public static Logger logger=LoggerFactory.getLogger(ReadUploadListServlet.class);
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String userId;
		if(session.getAttribute("isMaster")!=null){
			userId="master";
		}
		else{
			userId=(String)session.getAttribute("userId");
		}
		List uploadlist=new ArrayList();
		UploadDAO uploadDao=new UploadDAO();
		try {
			uploadlist=uploadDao.getUploadList(userId,Integer.parseInt(request.getParameter("homeworkNum")));
			request.setAttribute("files", uploadlist);
			RequestDispatcher rd=request.getRequestDispatcher("/upload_list.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			logger.debug("ReadUploadListServlet error:"+e.getMessage());
		}
	}
}
