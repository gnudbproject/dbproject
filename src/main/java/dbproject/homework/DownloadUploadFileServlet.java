package dbproject.homework;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/files/download")
public class DownloadUploadFileServlet extends HttpServlet {
	public static Logger logger=LoggerFactory.getLogger(DownloadUploadFileServlet.class);
@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName=request.getParameter("fileName");
		String strFilenameOutput=new String(fileName.getBytes("euc-kr"),"8859_1");
		File f=new File("D:/V.2/DBproject/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/dbproject/upload/"+fileName);
		byte b[]=new byte[(int)f.length()];
		response.setHeader("Content-Disposition","attachment;filename="+strFilenameOutput);
		response.setHeader("Content-Length",String.valueOf(f.length()));
		if(f.isFile()){
			BufferedInputStream fin=new BufferedInputStream(new FileInputStream(f));
			BufferedOutputStream outs=new BufferedOutputStream(response.getOutputStream());
			int read=0;
			while((read=fin.read(b))!=-1){outs.write(b,0,read);}
			outs.close();
			fin.close();
			logger.debug("download 완료");
		}
		else
			logger.debug("download 실패");
	}
}
