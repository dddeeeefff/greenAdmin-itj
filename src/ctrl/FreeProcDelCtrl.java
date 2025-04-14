package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/free_proc_del")
public class FreeProcDelCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FreeProcDelCtrl() { super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int bfidx = Integer.parseInt(request.getParameter("bfidx"));
		String kind = request.getParameter("kind");

		FreeProcDelSvc freeProcDelSvc = new FreeProcDelSvc();
		int result = freeProcDelSvc.freeProcDel(bfidx, kind);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
	}

}
