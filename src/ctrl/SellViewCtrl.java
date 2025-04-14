package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/sell_view")
public class SellViewCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SellViewCtrl() {  super(); }


    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	
    	String siid = request.getParameter("siid");
    	/*
    	String miid = request.getParameter("miid");
    	String sipayment = request.getParameter("sipayment");
    	int sipay = Integer.parseInt(request.getParameter("sipay"));
    	int siupoint = Integer.parseInt(request.getParameter("siupoint"));
    	String siinvoice = request.getParameter("siinvoice");
    	String sistatus = request.getParameter("sistatus");
    	*/
    	
		HttpSession session = request.getSession();
		AdminInfo adminInfo = (AdminInfo)session.getAttribute("adminInfo");
		
		if (adminInfo != null) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 경로로 들어오셨습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		
    	
    	SellViewSvc sellViewSvc = new SellViewSvc();
    	ArrayList<SellDetail> detailList = sellViewSvc.getDetailList(siid);
    	SellInfo sellInfo = sellViewSvc.getSellInfo(siid);

		String uri = request.getRequestURI().substring(10);
		request.setAttribute("uri", uri);
    	request.setAttribute("detailList", detailList);
    	request.setAttribute("sellInfo", sellInfo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/order/sell_view.jsp");
		dispatcher.forward(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	

}
