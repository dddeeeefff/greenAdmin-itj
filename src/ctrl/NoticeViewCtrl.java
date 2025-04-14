package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;


@WebServlet("/notice_view")
public class NoticeViewCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NoticeViewCtrl() {  super(); }

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
    	AdminInfo loginInfo = (AdminInfo)session.getAttribute("loginInfo");
    	if (loginInfo == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login_form");
			dispatcher.forward(request, response);
		}
		
		int bnidx = Integer.parseInt(request.getParameter("bnidx"));
    	System.out.println(bnidx);
    	
    	NoticeViewSvc noticeViewSvc = new NoticeViewSvc();
    	BbsNotice noticeDetailInfo = noticeViewSvc.getNoticeDetailInfo(bnidx);
    	
    	request.setAttribute("noticeDetailInfo", noticeDetailInfo);
    	
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/bbs/notice_view.jsp");
		dispatcher.forward(request, response);
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
