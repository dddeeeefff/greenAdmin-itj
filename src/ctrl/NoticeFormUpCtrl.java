package ctrl;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/notice_form_up")
public class NoticeFormUpCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeFormUpCtrl() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
    	
    	HttpSession session = request.getSession();
    	AdminInfo loginInfo = (AdminInfo)session.getAttribute("loginInfo");
    	if (loginInfo == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login_form");
			dispatcher.forward(request, response);
		}
    	
    	int bnidx = Integer.parseInt(request.getParameter("bnidx"));
    	System.out.println(bnidx);
    	
    	NoticeFormUpSvc noticeFormUpSvc = new NoticeFormUpSvc();
    	BbsNotice noticeUpInfo = noticeFormUpSvc.getNoticeUpInfo(bnidx);
    	
    	request.setAttribute("noticeUpInfo", noticeUpInfo);
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/bbs/notice_form_up.jsp");
		dispatcher.forward(request, response);
    	
	}

}
