package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;

public class ChartSeriesSvc {
	public HashMap<String, HashMap<String, Integer>> getBuySeriesList(int year) {
		HashMap<String, HashMap<String, Integer>> buySeriesList = new HashMap<String, HashMap<String, Integer>>();
		Connection conn = getConnection();
		ChartProcDao chartProcDao = ChartProcDao.getInstance();
		chartProcDao.setConnection(conn);
		
		buySeriesList.put("a11", chartProcDao.getBuySeriesList(year, "a11"));
		buySeriesList.put("a12", chartProcDao.getBuySeriesList(year, "a12"));
		buySeriesList.put("a13", chartProcDao.getBuySeriesList(year, "a13"));
		buySeriesList.put("a14", chartProcDao.getBuySeriesList(year, "a14"));
		buySeriesList.put("s20", chartProcDao.getBuySeriesList(year, "s20"));
		buySeriesList.put("s21", chartProcDao.getBuySeriesList(year, "s21"));
		buySeriesList.put("s22", chartProcDao.getBuySeriesList(year, "s22"));
		buySeriesList.put("s23", chartProcDao.getBuySeriesList(year, "s23"));
		close(conn);
		
		return buySeriesList;
	}
	
	public HashMap<String, HashMap<String, Integer>> getSellSeriesList(int year) {
		HashMap<String, HashMap<String, Integer>> sellSeriesList = new HashMap<String, HashMap<String, Integer>>();
		Connection conn = getConnection();
		ChartProcDao chartProcDao = ChartProcDao.getInstance();
		chartProcDao.setConnection(conn);
		
		sellSeriesList.put("a11", chartProcDao.getSellSeriesList(year, "a11"));
		sellSeriesList.put("a12", chartProcDao.getSellSeriesList(year, "a12"));
		sellSeriesList.put("a13", chartProcDao.getSellSeriesList(year, "a13"));
		sellSeriesList.put("a14", chartProcDao.getSellSeriesList(year, "a14"));
		sellSeriesList.put("s20", chartProcDao.getSellSeriesList(year, "s20"));
		sellSeriesList.put("s21", chartProcDao.getSellSeriesList(year, "s21"));
		sellSeriesList.put("s22", chartProcDao.getSellSeriesList(year, "s22"));
		sellSeriesList.put("s23", chartProcDao.getSellSeriesList(year, "s23"));
		close(conn);
		
		return sellSeriesList;
	}
	
}
