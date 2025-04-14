package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;

public class ChartModelSvc {
	public HashMap<String, HashMap<String, Integer>> getModelBuyList(int year) {
		HashMap<String, HashMap<String, Integer>> modelBuyList = new HashMap<String, HashMap<String, Integer>>();
		Connection conn = getConnection();
		ChartProcDao chartProcDao = ChartProcDao.getInstance();
		chartProcDao.setConnection(conn);
		
		modelBuyList.put("a01", chartProcDao.getModelBuyList(year, "a01"));
		modelBuyList.put("a02", chartProcDao.getModelBuyList(year, "a02"));
		modelBuyList.put("a03", chartProcDao.getModelBuyList(year, "a03"));
		modelBuyList.put("s01", chartProcDao.getModelBuyList(year, "s01"));
		modelBuyList.put("s02", chartProcDao.getModelBuyList(year, "s02"));
		modelBuyList.put("s03", chartProcDao.getModelBuyList(year, "s03"));
		close(conn);
		
		return modelBuyList;
	}

	public HashMap<String, HashMap<String, Integer>> getModelSellList(int year) {
		HashMap<String, HashMap<String, Integer>> modelSellList = new HashMap<String, HashMap<String, Integer>>();
		Connection conn = getConnection();
		ChartProcDao chartProcDao = ChartProcDao.getInstance();
		chartProcDao.setConnection(conn);
		
		modelSellList.put("a01", chartProcDao.getModelSellList(year, "a01"));
		modelSellList.put("a02", chartProcDao.getModelSellList(year, "a02"));
		modelSellList.put("a03", chartProcDao.getModelSellList(year, "a03"));
		modelSellList.put("s01", chartProcDao.getModelSellList(year, "s01"));
		modelSellList.put("s02", chartProcDao.getModelSellList(year, "s02"));
		modelSellList.put("s03", chartProcDao.getModelSellList(year, "s03"));
		close(conn);
		
		return modelSellList;
	}
	
}
