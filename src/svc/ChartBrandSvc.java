package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;

public class ChartBrandSvc {
	public HashMap<String, HashMap<String, Integer>> getBrandSellList(int year) {
		HashMap<String, HashMap<String, Integer>> brandSellList = new HashMap<String, HashMap<String, Integer>>();
		Connection conn = getConnection();
		ChartProcDao chartProcDao = ChartProcDao.getInstance();
		chartProcDao.setConnection(conn);
		
		HashMap<String, Integer> appleSellList = chartProcDao.getBrandSellList(year, "a");
		HashMap<String, Integer> samsungSellList = chartProcDao.getBrandSellList(year, "s");
		brandSellList.put("애플", appleSellList);
		brandSellList.put("삼성", samsungSellList);
		close(conn);
		
		return brandSellList;
		
	}

	public HashMap<String, HashMap<String, Integer>> getBrandBuyList(int year) {
		HashMap<String, HashMap<String, Integer>> brandBuyList = new HashMap<String, HashMap<String, Integer>>();
		Connection conn = getConnection();
		ChartProcDao chartProcDao = ChartProcDao.getInstance();
		chartProcDao.setConnection(conn);
		
		HashMap<String, Integer> appleBuyList = chartProcDao.getBrandBuyList(year, "a");
		HashMap<String, Integer> samsungBuyList = chartProcDao.getBrandBuyList(year, "s");
		brandBuyList.put("애플", appleBuyList);
		brandBuyList.put("삼성", samsungBuyList);
		close(conn);
		
		return brandBuyList;
		
	}
	
}
