package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;

public class ChartOrderSvc {

	public HashMap<String, Integer> getOrderBuyList(int year) {
		Connection conn = getConnection();
		ChartProcDao chartProcDao = ChartProcDao.getInstance();
		chartProcDao.setConnection(conn);
		
		HashMap<String, Integer> orderBuyList = chartProcDao.getOrderBuyList(year);		
		close(conn);
		
		return orderBuyList;
	}

	public HashMap<String, Integer> getOrderSellList(int year) {
		Connection conn = getConnection();
		ChartProcDao chartProcDao = ChartProcDao.getInstance();
		chartProcDao.setConnection(conn);
		
		HashMap<String, Integer> orderSellList = chartProcDao.getOrderSellList(year);
		close(conn);
		
		return orderSellList;
	}

}
