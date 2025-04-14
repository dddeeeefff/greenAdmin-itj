package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/event_form_up")
public class EventFormUpCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public EventFormUpCtrl() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		int beidx = Integer.parseInt(request.getParameter("beidx"));
		EventFormUpSvc eventFormUpSvc = new EventFormUpSvc();
		
		BbsEvent be = eventFormUpSvc.getFormUp(beidx);
		
		request.setAttribute("be", be);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("bbs/event_form_up.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
