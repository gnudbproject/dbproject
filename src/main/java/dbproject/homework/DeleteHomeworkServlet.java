package dbproject.homework;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/homeworks/removeHomework")
public class DeleteHomeworkServlet extends HttpServlet {
	public static Logger logger=LoggerFactory.getLogger(DeleteHomeworkServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UploadDAO uploadDao=new UploadDAO();
		try {
			uploadDao.removeHomework(Integer.parseInt(request.getParameter("homeworkNum")));
		} catch (Exception e) {
			logger.debug("DeleteHomeworkServlet error:"+e.getMessage());
		}
		response.sendRedirect("/homeworks/homeworkList");
	}
}
