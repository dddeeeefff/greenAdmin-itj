package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/member_status")
public class MemberStatusPop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberStatusPop() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String miid = request.getParameter("miid");
		
		request.setAttribute("miid", miid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/member_status.jsp");
		dispatcher.forward(request, response);

	}

}
