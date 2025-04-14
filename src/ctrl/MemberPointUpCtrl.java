package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/member_point_up")
public class MemberPointUpCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberPointUpCtrl() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		AdminInfo loginInfo = (AdminInfo) session.getAttribute("loginInfo");

		if (loginInfo == null) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 경로로 들어오셨습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}

		MemberPoint memberPoint = new MemberPoint();
		memberPoint.setMi_id(request.getParameter("miid"));
		memberPoint.setMp_desc(request.getParameter("reason"));
		memberPoint.setMp_point(Integer.parseInt(request.getParameter("point")));
		memberPoint.setMp_su(request.getParameter("su"));
		System.out.println(memberPoint.getMp_point());
		
		MemberPointUpSvc memberPointUpsvc = new MemberPointUpSvc();
		int result = memberPointUpsvc.memberPointUp(memberPoint,loginInfo);
		response.setContentType("text/html; charset=utf-8;");
		PrintWriter out = response.getWriter();
		out.println(result);
	}
}
