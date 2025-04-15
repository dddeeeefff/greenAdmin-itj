package ctrl;

import java.io.*;
import java.net.URLEncoder;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;


@WebServlet("/sell_proc_status")
public class SellProcStatusCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SellProcStatusCtrl() {   super(); }


	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String siid = request.getParameter("si_id");
		String getStatus = request.getParameter("getStatus");
		
		SellInfo si = new SellInfo();
		si.setSi_id(siid);
		si.setSi_status(getStatus);
		
		SellProcStatusSvc sellProcStatusSvc = new SellProcStatusSvc();
		
		int result = sellProcStatusSvc.upStatus(si);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (result >= 1) {
    		out.println("<script>");
    		out.println("alert('수정이 완료되었습니다.');");
    		out.println("location.href='sell_view?siid=" + siid + "';");
    		out.println("</script>");
    	} else {
			out.println("<script>");
			out.println("alert('상태 변경에 실패했습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		out.close();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
