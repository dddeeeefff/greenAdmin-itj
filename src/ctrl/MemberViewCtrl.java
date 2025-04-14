package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/member_view")
public class MemberViewCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberViewCtrl() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		AdminInfo loginInfo = (AdminInfo)session.getAttribute("loginInfo");
		
		if (loginInfo == null) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 경로로 들어오셨습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		
		
		int cpage = 1, psize = 10, bsize = 10, rcnt = 0, pcnt = 0, spage = 0;
		if (request.getParameter("cpage") != null) 
			cpage = Integer.parseInt(request.getParameter("cpage"));
		
		String miid = request.getParameter("miid");
		String kind = request.getParameter("kind");
		if (kind == null)		kind = "a";
		MemberViewSvc memberViewSvc = new MemberViewSvc();
		MemberInfo memberInfo = memberViewSvc.getMemberInfo(miid);

		if (kind.equals("a")) {
			ArrayList<SellInfo> sellInfoList = memberViewSvc.getSellInfoList(miid, cpage, psize);
			rcnt = memberViewSvc.getSellInfoListCount(miid);
			request.setAttribute("sellInfoList", sellInfoList);	
		} else if (kind.equals("b")) {
			ArrayList<BuyInfo> buyInfoList = memberViewSvc.getBuyInfoList(miid, cpage, psize);
			rcnt = memberViewSvc.getBuyInfoListCount(miid);
			request.setAttribute("buyInfoList", buyInfoList);
		} else if (kind.equals("c")) {
			ArrayList<MemberPoint> pointList = memberViewSvc.getPointList(miid, cpage, psize);
			rcnt = memberViewSvc.getPointListCount(miid);
			request.setAttribute("pointList", pointList);
		} else if (kind.equals("d")) {
			ArrayList<MemberQuestion> questionList = memberViewSvc.getQuestionList(miid, cpage, psize);
			rcnt = memberViewSvc.getQuestionListCount(miid);
			request.setAttribute("questionList", questionList);
		}

		PageInfo pageInfo = new PageInfo();
		pageInfo.setBsize(bsize);
		pageInfo.setCpage(cpage);
		pageInfo.setPcnt(pcnt);
		pageInfo.setPsize(psize);
		pageInfo.setRcnt(rcnt);
		pageInfo.setSpage(spage);

		request.setAttribute("pageInfo", pageInfo);
		
		if (memberInfo == null) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 경로로 들어오셨습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		if (memberInfo.getMi_status().equals("b")) {
			MemberStatus memberStatus = memberViewSvc.getMemberStatus(miid);
			request.setAttribute("memberStatus", memberStatus);
		}

		
		
		
		String uri = request.getRequestURI().substring(12);
		request.setAttribute("uri", uri);
		request.setAttribute("memberInfo", memberInfo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/member_view.jsp");
		dispatcher.forward(request, response);
		
	}

	
	
}
