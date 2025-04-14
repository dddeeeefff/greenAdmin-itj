package ctrl;

import java.io.*;
import java.util.*;
import java.time.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;

@WebServlet("/chart_model")
public class ChartModelCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ChartModelCtrl() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int year = LocalDate.now().getYear();
		if (request.getParameter("year") != null)		year = Integer.parseInt(request.getParameter("year"));
		
		ChartModelSvc chartModelSvc = new ChartModelSvc();
		HashMap<String, HashMap<String, Integer>> modelBuyList = chartModelSvc.getModelBuyList(year);
		HashMap<String, HashMap<String, Integer>> modelSellList = chartModelSvc.getModelSellList(year);

		String uri = request.getRequestURI().substring(12);
		request.setAttribute("uri", uri);
		request.setAttribute("modelSellList", modelSellList);
		request.setAttribute("modelBuyList", modelBuyList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("chart/chart_model.jsp");
		dispatcher.forward(request, response);
	}

}
