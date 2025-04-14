package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/event_proc_up")
@MultipartConfig(
	fileSizeThreshold = 0, 
	maxFileSize = 1024 * 1024 * 5,		// 5MB 용량 제한
	location = "/Users/chokangseok/Documents/backend/greenAdmin-itj/WebContent/bbs/upload"
	//location = "../../../../webapps/greenAdmin/bbs/upload"
)
public class EventProcUpCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public EventProcUpCtrl() { super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	
		response.setContentType("text/html; charset=utf-8");
		
		BbsEvent be = new BbsEvent();
		be.setBe_title(request.getParameter("be_title"));	
		be.setBe_sdate(request.getParameter("sdate"));
		be.setBe_edate(request.getParameter("edate"));
		be.setBe_content(request.getParameter("be_content"));
		
		Part part = request.getPart("be_img");
		// 업로드되는 파일을 Part형 인스턴스에 저장
		
		String contentDisposition = part.getHeader("content-disposition");
		// 예) form-data; name="file1"; filename="업로드할파일명.확장자"
		// System.out.println("contentDisposition : " +  contentDisposition);
		
		String uploadFileName = getUploadFileName(contentDisposition);
		
		part.write(uploadFileName);
		
		be.setBe_img(uploadFileName);
		
		EventProcUpSvc eventProcUpSvc = new EventProcUpSvc();
		// System.out.println(be.getBe_img());
		request.setAttribute("be", be);
		
		int idx = Integer.parseInt(request.getParameter("beidx"));
		
		int result = eventProcUpSvc.eventProcUp(be, idx);
		
		if (result == 1) {
			response.sendRedirect("event_view?cpage=1&beidx=" + idx);
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시 글 등록에 실패했습니다.\n다시 시도하세요.');");
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
