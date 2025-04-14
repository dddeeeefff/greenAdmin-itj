package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;

public class ChartProfitSvc {
	public HashMap<String, Integer> getChartBuyList(int year) {
		Connection conn = getConnection();
		ChartProcDao chartProcDao = ChartProcDao.getInstance();
		chartProcDao.setConnection(conn);
		
		HashMap<String, Integer> chartBuyList = chartProcDao.getChartBuyList(year);
		close(conn);
		
		return chartBuyList;
	}

	public HashMap<String, Integer> getChartSellList(int year) {
		Connection conn = getConnection();
		ChartProcDao chartProcDao = ChartProcDao.getInstance();
		chartProcDao.setConnection(conn);
		
		HashMap<String, Integer> chartSellList = chartProcDao.getChartSellList(year);
		close(conn);
		
		return chartSellList;
	}
	
}
