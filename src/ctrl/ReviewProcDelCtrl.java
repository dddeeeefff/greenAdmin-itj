package ctrl;

import java.io.*;
import java.net.URLEncoder;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/review_proc_del")
public class ReviewProcDelCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReviewProcDelCtrl() { super();  }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int bridx = Integer.parseInt(request.getParameter("bridx"));
		System.out.println(bridx);
		String opt = request.getParameter("opt");
		
		ReviewProcDelSvc reviewProcDelSvc = new ReviewProcDelSvc();
		
		BbsReview br = new BbsReview();
		br.setBr_idx(bridx);
		
		int result = reviewProcDelSvc.changeView(bridx, opt);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		// System.out.println("result : " + result);
		out.close();
	}

}
