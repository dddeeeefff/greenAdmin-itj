package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/event_list_view")
public class EventListViewCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public EventListViewCtrl() { super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int beidx = Integer.parseInt(request.getParameter("beidx"));
		String opt = request.getParameter("opt");
		
		EventListViewSvc eventListViewSvc = new EventListViewSvc();
		
		BbsEvent be = new BbsEvent();
		be.setBe_idx(beidx);
		
		int result = eventListViewSvc.getView(beidx, opt);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		// System.out.println("result : " + result);
		out.close();
	}
}
