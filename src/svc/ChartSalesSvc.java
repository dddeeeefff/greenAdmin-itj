package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;

public class ChartSalesSvc {
	public HashMap<String, Integer> getChartSalesList(int year) {
		Connection conn = getConnection();
		ChartProcDao chartProcDao = ChartProcDao.getInstance();
		chartProcDao.setConnection(conn);
		
		HashMap<String, Integer> chartSalesList = chartProcDao.getChartSalesList(year);
		close(conn);
		
		return chartSalesList;
	}

}
