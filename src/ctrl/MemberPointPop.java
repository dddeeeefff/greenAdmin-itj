package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/member_point")
public class MemberPointPop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberPointPop() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String miid = request.getParameter("miid");
		int point = Integer.parseInt(request.getParameter("mipoint"));
		System.out.println(point);
		
		request.setAttribute("miid", miid);
		request.setAttribute("point", point);
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/member_point.jsp");
		dispatcher.forward(request, response);

	}

}
