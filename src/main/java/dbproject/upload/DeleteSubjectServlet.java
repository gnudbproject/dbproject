package dbproject.upload;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/subjects/removeSubject")
public class DeleteSubjectServlet extends HttpServlet {
	public static Logger logger=LoggerFactory.getLogger(DeleteSubjectServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UploadDAO uploadDao=new UploadDAO();
		try {
			uploadDao.removeSubject(Integer.parseInt(request.getParameter("subjectNum")));
		} catch (Exception e) {
			logger.debug("DeleteSubjectServlet error:"+e.getMessage());
		}
		response.sendRedirect("/subjects/subjectList");
	}
}
