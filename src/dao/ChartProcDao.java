package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;

public class ChartProcDao {
	private static ChartProcDao chartProcDao;
	private Connection conn;
	private ChartProcDao() {}
	
	public static ChartProcDao getInstance() {
		if (chartProcDao == null)		chartProcDao = new ChartProcDao();
		
		return chartProcDao;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public HashMap<String, Integer> getChartSalesList(int year) {
		HashMap<String, Integer> chartSalesList = new HashMap<String, Integer>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select month(si_date) months, sum(si_pay) sell from t_sell_info where si_status = 'e' " 
				+ " and year(si_date) = '" + year + "' group by months order by months";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				do {
					chartSalesList.put(rs.getInt("months") + "월", rs.getInt("sell"));
					
				} while (rs.next());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ChartProcDao 클래스의 getChartSalesList() 메소드 오류");
		} finally {
			close(rs);		close(stmt);
		}
		
		return chartSalesList;
		
	}

	public HashMap<String, Integer> getChartBuyList(int year) {
		HashMap<String, Integer> chartBuyList = new HashMap<String, Integer>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select month(si_date) months, sum(si_pay) sell from t_sell_info where si_status = 'e' " 
				+ " and year(si_date) = '" + year + "' group by months order by months";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				do {
					chartBuyList.put(rs.getInt("months") + "월", rs.getInt("sell"));
					
				} while (rs.next());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ChartProcDao 클래스의 getChartBuyList() 메소드 오류");
		} finally {
			close(rs);		close(stmt);
		}
		
		return chartBuyList;
		
	}

	public HashMap<String, Integer> getChartSellList(int year) {
		HashMap<String, Integer> chartSellList = new HashMap<String, Integer>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select month(bi_date) months, sum(bi_pay) buy from t_buy_info where bi_status = 'k' " 
				+ " and year(bi_date) = '" + year + "' group by months order by months";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				do {
					chartSellList.put(rs.getInt("months") + "월", rs.getInt("buy"));
					
				} while (rs.next());	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ChartProcDao 클래스의 getChartSellList() 메소드 오류");
		} finally {
			close(rs);		close(stmt);
		}
		
		return chartSellList;
		
	}
	
	public HashMap<String, Integer> getBrandSellList(int year, String brand) {
		HashMap<String, Integer> brandSellList = new HashMap<String, Integer>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select month(si_date) months, count(si_id) cnt from t_sell_info where si_status = 'e' " 
				+ " and year(si_date) = '" + year + "' and left(si_id, 1) = '" + brand + "' group by months " 
				+ " order by months";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				do {
					brandSellList.put(rs.getInt("months") + "월", rs.getInt("cnt"));
					
				} while (rs.next());	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ChartProcDao 클래스의 getBrandSellList() 메소드 오류");
		} finally {
			close(rs);		close(stmt);
		}
		
		return brandSellList;
	}

	public HashMap<String, Integer> getBrandBuyList(int year, String brand) {
		HashMap<String, Integer> brandBuyList = new HashMap<String, Integer>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select month(bi_date) months, count(bi_id) cnt from t_buy_info where bi_status = 'k' " 
				+ " and year(bi_date) = '" + year + "' and left(bi_id, 1) = '" + brand + "' group by months " 
				+ " order by months";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				do {
					brandBuyList.put(rs.getInt("months") + "월", rs.getInt("cnt"));
					
				} while (rs.next());	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ChartProcDao 클래스의 getBrandBuyList() 메소드 오류");
		} finally {
			close(rs);		close(stmt);
		}
		
		return brandBuyList;
	}

	public HashMap<String, Integer> getBuySeriesList(int year, String series) {
		HashMap<String, Integer> buySeriesList = new HashMap<String, Integer>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select left(pi_id, 3) series, month(bi_date) months, count(bi_id) cnt from t_buy_info " 
				+ " where bi_status = 'k' and year(bi_date) = '" + year + "' and left(pi_id, 3) = '" + series 
				+ "' group by series, months order by series, months";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				do {
					buySeriesList.put(rs.getInt("months") + "월", rs.getInt("cnt"));
					
				} while (rs.next());	
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ChartProcDao 클래스의 getBuySeriesList() 메소드 오류");
		} finally {
			close(rs);		close(stmt);
		}
		
		return buySeriesList;
	}

	public HashMap<String, Integer> getSellSeriesList(int year, String series) {
		HashMap<String, Integer> sellSeriesList = new HashMap<String, Integer>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select left(b.pi_id, 3) series, month(a.si_date) months, count(a.si_id) cnt " 
				+ " from t_sell_info a, t_sell_detail b where a.si_status = 'e' and year(si_date) = '" + year 
				+ "' and left(b.pi_id, 3) = '" + series + "' group by series, months order by series, months";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				do {
					sellSeriesList.put(rs.getInt("months") + "월", rs.getInt("cnt"));
				} while (rs.next());	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ChartProcDao 클래스의 getSellSeriesList() 메소드 오류");
		} finally {
			close(rs);		close(stmt);
		}
		
		return sellSeriesList;
	}

	public HashMap<String, Integer> getModelBuyList(int year, String bm) {
		HashMap<String, Integer> modelBuyList = new HashMap<String, Integer>();
		Statement stmt = null;
		ResultSet rs = null;
		char brand = bm.charAt(0);
		String model = bm.substring(1);
				
		try {
			String sql = "select left(bi_id, 1) brand, right(pi_id, 2) model, month(bi_date) months, count(bi_id) cnt " 
				+ " from t_buy_info where bi_status = 'k' and year(bi_date) = '" + year 
				+ "' and left(bi_id, 1) = '" + brand + "' and right(pi_id, 2) = '" + model 
				+ "' group by brand, model, months order by model, months";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				do {
					modelBuyList.put(rs.getInt("months") + "월", rs.getInt("cnt"));
				} while (rs.next());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ChartProcDao 클래스의 getModelBuyList() 메소드 오류");
		} finally {
			close(rs);		close(stmt);
		}
		
		return modelBuyList;
	}

	public HashMap<String, Integer> getModelSellList(int year, String bm) {
		HashMap<String, Integer> modelSellList = new HashMap<String, Integer>();
		Statement stmt = null;
		ResultSet rs = null;
		char brand = bm.charAt(0);
		String model = bm.substring(1);
				
		try {
			String sql = "select left(a.si_id, 1) brand, right(b.pi_id, 2) model, month(a.si_date) months, " 
				+ " count(a.si_id) cnt from t_sell_info a, t_sell_detail b where a.si_status = 'e' " 
				+ " and year(si_date) = '" + year + "' and left(a.si_id, 1) = '" + brand 
				+ "' and right(b.pi_id, 2) = '" + model + "' group by brand, model, months order by model, months";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				do {
					modelSellList.put(rs.getInt("months") + "월", rs.getInt("cnt"));
				} while (rs.next());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ChartProcDao 클래스의 getModelSellList() 메소드 오류");
		} finally {
			close(rs);		close(stmt);
		}
		
		return modelSellList;
	}

	public HashMap<String, Integer> getOrderBuyList(int year) {
		HashMap<String, Integer> orderBuyList = new HashMap<String, Integer>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try { 
			String sql = "select month(bi_date) months, count(bi_id) cnt from t_buy_info where bi_status = 'k' " 
				+ " and year(bi_date) = '" + year + "' group by months order by months";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				do {
					orderBuyList.put(rs.getInt("months") + "월", rs.getInt("cnt"));
				} while (rs.next());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ChartProcDao 클래스의 getOrderBuyList() 메소드 오류");
		} finally {
			close(rs);		close(stmt);
		}
		
		return orderBuyList;
	}

	public HashMap<String, Integer> getOrderSellList(int year) {
		HashMap<String, Integer> orderSellList = new HashMap<String, Integer>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// sell_info 기준? or sell_detail 기준?
			String sql = "select month(b.sd_cdate) months, if (sum(b.sd_cnt) is null, 0, sum(b.sd_cnt)) cnt " 
				+ " from t_sell_info a, t_sell_detail b where a.si_id = b.si_id and a.si_status = 'e' " 
				+ " and year(b.sd_cdate) = '" + year + "' group by months order by months";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				do {
					orderSellList.put(rs.getInt("months") + "월", rs.getInt("cnt"));
				} while (rs.next());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ChartProcDao 클래스의 getOrderSellList() 메소드 오류");
		} finally {
			close(rs);		close(stmt);
		}
		
		return orderSellList;
	}
}
