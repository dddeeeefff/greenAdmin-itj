package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/member_status_up")
public class MemberStatusUpCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberStatusUpCtrl() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		String miid = request.getParameter("miid");
		String reason = request.getParameter("reason");

		MemberStatus memberStatus = new MemberStatus();
		memberStatus.setMi_id(miid);
		memberStatus.setMs_reason(reason);
		MemberStatusUpSvc memberStatusUpSvc = new MemberStatusUpSvc();
		int result = memberStatusUpSvc.memberStatusUp(memberStatus,loginInfo);
		if (result == 0) {
			System.out.println("탈퇴처리가 실패했습니다.");
		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('탈퇴 처리되었습니다.');");
			out.println("opener.location.reload();");
			out.println("window.close();");
			out.println("</script>");
			out.close();
		}
	}

}
