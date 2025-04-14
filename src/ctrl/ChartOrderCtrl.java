package ctrl;

import java.io.*;
import java.util.*;
import java.time.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;

@WebServlet("/chart_order")
public class ChartOrderCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ChartOrderCtrl() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int year = LocalDate.now().getYear();
		if (request.getParameter("year") != null)		year = Integer.parseInt(request.getParameter("year"));
		
		ChartOrderSvc chartOrderSvc = new ChartOrderSvc();
		HashMap<String, Integer> orderBuyList = chartOrderSvc.getOrderBuyList(year);
		HashMap<String, Integer> orderSellList = chartOrderSvc.getOrderSellList(year);

		String uri = request.getRequestURI().substring(12);
		request.setAttribute("uri", uri);
		request.setAttribute("orderSellList", orderSellList);
		request.setAttribute("orderBuyList", orderBuyList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("chart/chart_order.jsp");
		dispatcher.forward(request, response);
	}

}
