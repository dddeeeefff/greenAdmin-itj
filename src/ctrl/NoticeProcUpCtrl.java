package ctrl;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/notice_proc_up")
@MultipartConfig(
	fileSizeThreshold = 0,
	maxFileSize = 1024 * 1024 * 5,		// 5MB 용량 제한
	location = "../../../../webapps/greenAdmin/bbs/notice_img"
	//location = "D:/cks/web/work/greenAdmin/WebContent/bbs/notice_img"
)
public class NoticeProcUpCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;     
    public NoticeProcUpCtrl() { super(); }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
    	AdminInfo loginInfo = (AdminInfo)session.getAttribute("loginInfo");
    	if (loginInfo == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login_form");
			dispatcher.forward(request, response);
		}
    	int aiidx = loginInfo.getAi_idx();
    	  	
    	BbsNotice bn = new BbsNotice();
    	bn.setBn_idx(Integer.parseInt(request.getParameter("bnidx")));
    	bn.setBn_title(request.getParameter("addTitle"));
    	bn.setBn_content(request.getParameter("addContent"));
    	
    	Part part = request.getPart("addFile");
    	
    	String contentDisposition = part.getHeader("content-disposition");
		
		
		String uploadFileName = getUploadFileName(contentDisposition);
    	bn.setBn_img(uploadFileName);
		
		part.write(uploadFileName);
		
		NoticeProcUpSvc noticeProcUpSvc = new NoticeProcUpSvc();
		
		int result = noticeProcUpSvc.noticeProcUp(bn, aiidx);
		
		if (result == 1) {
			response.sendRedirect("notice_view?bnidx=" + bn.getBn_idx());
		} else {
			
			out.println("<script>");
			out.println("alert('寃��� 湲� �깅��� �ㅽ�⑦���듬����.\n�ㅼ�� �������몄��.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
	}
	private String getUploadFileName(String contentDisposition) {
		String uploadFileName = null;
		String[] contentSplitStr = contentDisposition.split(";");
		
		int fIdx = contentSplitStr[2].indexOf("\"");
		int sIdx = contentSplitStr[2].lastIndexOf("\"");
		
		uploadFileName = contentSplitStr[2].substring(fIdx +1, sIdx);
		
		return uploadFileName;
	}
}
