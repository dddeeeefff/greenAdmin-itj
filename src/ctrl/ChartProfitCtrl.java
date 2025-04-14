package ctrl;

import java.io.*;
import java.util.*;
import java.time.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;

@WebServlet("/chart_profit")
public class ChartProfitCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ChartProfitCtrl() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int year = LocalDate.now().getYear();
		if (request.getParameter("year") != null)		year = Integer.parseInt(request.getParameter("year"));
		
		ChartProfitSvc chartProfitSvc = new ChartProfitSvc();
		HashMap<String, Integer> chartBuyList = chartProfitSvc.getChartBuyList(year);
		HashMap<String, Integer> chartSellList = chartProfitSvc.getChartSellList(year);
		
		String uri = request.getRequestURI().substring(12);
		request.setAttribute("uri", uri);
		request.setAttribute("chartBuyList", chartBuyList);
		request.setAttribute("chartSellList", chartSellList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("chart/chart_profit.jsp");
		dispatcher.forward(request, response);
	}

}
