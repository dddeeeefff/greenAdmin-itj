package ctrl;

import java.io.*;
import java.util.*;
import java.time.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;

@WebServlet("/chart_series")
public class ChartSeriesCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ChartSeriesCtrl() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int year = LocalDate.now().getYear();
		if (request.getParameter("year") != null)		year = Integer.parseInt(request.getParameter("year"));
		
		ChartSeriesSvc chartSeriesSvc = new ChartSeriesSvc();
		HashMap<String, HashMap<String, Integer>> buySeriesList = chartSeriesSvc.getBuySeriesList(year);
		HashMap<String, HashMap<String, Integer>> sellSeriesList = chartSeriesSvc.getSellSeriesList(year);

		String uri = request.getRequestURI().substring(12);
		request.setAttribute("uri", uri);
		request.setAttribute("buySeriesList", buySeriesList);
		request.setAttribute("sellSeriesList", sellSeriesList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("chart/chart_series.jsp");
		dispatcher.forward(request, response);
	}

}
