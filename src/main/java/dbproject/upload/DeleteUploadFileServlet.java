package dbproject.upload;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/files/delete")
public class DeleteUploadFileServlet extends HttpServlet {
	public static Logger logger = LoggerFactory.getLogger(DeleteUploadFileServlet.class); 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UploadDAO uploadDao=new UploadDAO();
		String fileName=request.getParameter("fileName");
		File f=new File("C:/web-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/dbproject/upload/"+fileName);
		//형근: 현재는 절대주소라서 자신의 로컬 환경에 맞는 경로 변경이 필요하다 따라서 추후에 상대경로로 변경하거나 서버 경로로 변경 필요함  
		try {
			if(f.delete()){
				logger.debug("파일 삭제 성공");
			}
			else{
				logger.debug("파일 삭제 실패");
			}
			uploadDao.removeFile(fileName);
		} catch (SQLException e) {
			logger.debug("DeleteFileServlet error:"+e.getMessage());
		}
		finally{
			response.sendRedirect("/subjects/subjectList");
		}
	}
}
