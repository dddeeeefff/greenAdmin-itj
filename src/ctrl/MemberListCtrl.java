package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/member_list")
public class MemberListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberListCtrl() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cpage = 1, psize = 10, bsize = 10, rcnt = 0, pcnt = 0, spage = 0;
		// 현재 페이지 번호, 페이지 크기, 블록 크기, 레코드(게시글) 개수, 페이지 개수, 시작 페이지 등을 저장할 변수들
		if (request.getParameter("cpage") != null) 
			cpage = Integer.parseInt(request.getParameter("cpage"));
		// cpage 값이 있으면 받아서 int형으로 형변환 시킴(보안상의 이유와 산술연산을 해야 하기 때문)
		
		String uid = request.getParameter("uid");
		if (uid == null)		uid = "";
		String name = request.getParameter("name");
		if (name == null)		name = "";
		String gender = request.getParameter("gender");
		if (gender == null)		gender = "";
		String p1 = request.getParameter("p1");
		if (p1 == null)		p1 = "";
		String p2 = request.getParameter("p2");
		if (p2 == null)		p2 = "";
		String p3 = request.getParameter("p3");
		if (p3 == null)		p3 = "";
		String phone = "";
		if (!p1.equals("") && !p2.equals("") && !p3.equals("")) {
			phone = p1 + "-" + p2 + "-" + p3;
		}
		String status = request.getParameter("status");
		if (status == null)		status = "";
		String where = " where 1 = 1 ";
		if (!uid.equals(""))		where += " and mi_id like '%" + uid + "%' ";
		if (!name.equals(""))		where += " and mi_name like '%" + name + "%' ";
		if (!gender.equals(""))		where += " and mi_gender = '" + gender + "' ";
		if (!phone.equals(""))		where += " and mi_phone = '" + phone + "' ";
		if (!status.equals(""))		where += " and mi_status = '" + status + "' ";
		
		
		MemberListSvc memberListSvc = new MemberListSvc();
		ArrayList<MemberInfo> memberList = memberListSvc.getMemberList(where, cpage, psize);
		rcnt = memberListSvc.getMemberListCount(where);
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setBsize(bsize);
		pageInfo.setCpage(cpage);
		pageInfo.setPcnt(pcnt);
		pageInfo.setPsize(psize);
		pageInfo.setRcnt(rcnt);
		pageInfo.setSpage(spage);

		String uri = request.getRequestURI().substring(12);
		request.setAttribute("uri", uri);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("memberList", memberList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/member/member_list.jsp");
		dispatcher.forward(request, response);

		

	}
}
