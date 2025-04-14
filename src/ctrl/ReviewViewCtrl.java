package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;


@WebServlet("/review_view")
public class ReviewViewCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReviewViewCtrl() {  super(); }

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
    	AdminInfo loginInfo = (AdminInfo)session.getAttribute("loginInfo");
    	if (loginInfo == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login_form");
			dispatcher.forward(request, response);
		}
		
		int bridx = Integer.parseInt(request.getParameter("bridx"));
    	
    	ReviewViewSvc reviewViewSvc = new ReviewViewSvc();
    	BbsReview reviewDetailInfo = reviewViewSvc.getReviewDetailInfo(bridx);
    	
    	request.setAttribute("reviewDetailInfo", reviewDetailInfo);
    	
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/bbs/review_view.jsp");
		dispatcher.forward(request, response);
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
