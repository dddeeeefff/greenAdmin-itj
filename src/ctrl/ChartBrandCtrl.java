package ctrl;

import java.io.*;
import java.util.*;
import java.time.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;

@WebServlet("/chart_brand")
public class ChartBrandCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ChartBrandCtrl() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int year = LocalDate.now().getYear();
		if (request.getParameter("year") != null)		year = Integer.parseInt(request.getParameter("year"));
		
		ChartBrandSvc chartBrandSvc = new ChartBrandSvc();
		HashMap<String, HashMap<String, Integer>> brandSellList = chartBrandSvc.getBrandSellList(year);
		HashMap<String, HashMap<String, Integer>> brandBuyList = chartBrandSvc.getBrandBuyList(year);

		String uri = request.getRequestURI().substring(12);
		request.setAttribute("uri", uri);
		request.setAttribute("brandSellList", brandSellList);
		request.setAttribute("brandBuyList", brandBuyList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("chart/chart_brand.jsp");
		dispatcher.forward(request, response);
	}

}
