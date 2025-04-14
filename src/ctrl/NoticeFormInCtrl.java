package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/notice_form_in")
public class NoticeFormInCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public NoticeFormInCtrl() { super(); }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
    	
    	HttpSession session = request.getSession();
    	AdminInfo loginInfo = (AdminInfo)session.getAttribute("loginInfo");
    	if (loginInfo == null) {  		
			RequestDispatcher dispatcher = request.getRequestDispatcher("login_form");
			dispatcher.forward(request, response);
		}
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/bbs/notice_form_in.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
