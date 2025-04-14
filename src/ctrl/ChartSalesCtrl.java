package ctrl;

import java.io.*;
import java.time.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/chart_sales")
public class ChartSalesCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ChartSalesCtrl() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int year = LocalDate.now().getYear();
		if (request.getParameter("year") != null)		year = Integer.parseInt(request.getParameter("year"));
		
		ChartSalesSvc chartSalesSvc = new ChartSalesSvc();
		HashMap<String, Integer> chartSalesList = chartSalesSvc.getChartSalesList(year);
		
		String uri = request.getRequestURI().substring(12);
		request.setAttribute("uri", uri);
		request.setAttribute("chartSalesList", chartSalesList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("chart/chart_sales.jsp");
		dispatcher.forward(request, response);
	}

}
