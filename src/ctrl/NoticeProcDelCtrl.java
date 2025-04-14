package ctrl;

import java.io.*;
import java.net.URLEncoder;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/notice_proc_del")
public class NoticeProcDelCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeProcDelCtrl() { super();  }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int bnidx = Integer.parseInt(request.getParameter("bnidx"));
		System.out.println(bnidx);
		String opt = request.getParameter("opt");
		System.out.println(opt);
		
		NoticeProcDelSvc noticeProcDelSvc = new NoticeProcDelSvc();
		
		BbsNotice bn = new BbsNotice();
		bn.setBn_idx(bnidx);
		
		int result = noticeProcDelSvc.changeView(bnidx, opt);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		// System.out.println("result : " + result);
		out.close();
	}

}
