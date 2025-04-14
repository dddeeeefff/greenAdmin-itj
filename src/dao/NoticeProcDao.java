package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class NoticeProcDao {
	private static NoticeProcDao noticeProcDao;
	private Connection conn;
	private NoticeProcDao() {}
	
	public static NoticeProcDao getInstance() {
		if (noticeProcDao == null)		noticeProcDao = new NoticeProcDao();
		
		return noticeProcDao;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public ArrayList<BbsNotice> getNoticeList(int cpage, int psize, String where){
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BbsNotice> noticeList = new ArrayList<BbsNotice>();
		BbsNotice bn = null;

		
		try {
			String sql = "select * from t_bbs_notice " + where
				+ " order by bn_idx desc limit " + ((cpage - 1) * psize) + ", " + psize;
			System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				bn = new BbsNotice();
				bn.setBn_idx(rs.getInt("bn_idx"));
				bn.setBn_title(rs.getString("bn_title"));
				bn.setAi_idx(rs.getInt("ai_idx"));
				bn.setBn_read(rs.getInt("bn_read"));
				bn.setBn_date(rs.getString("bn_date"));
				bn.setBn_isview(rs.getString("bn_isview"));
				
				noticeList.add(bn);
				
			}
			
		}catch(Exception e) {
			System.out.println("NoticeProcDao 클래스의 getNoticeList() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return noticeList;
	}
	
	public int getNoticeListCount(String where) {
		int rcnt = 0;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select count(bn_idx) from t_bbs_notice " + where;
			System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())	rcnt = rs.getInt(1);
			
		}catch(Exception e){
			System.out.println("NoticeProcDao 클래스의 getNoticeListCount() 메소드 오류");
			e.printStackTrace();
		}finally {
			close(rs);	close(stmt);
		}
		return rcnt;
	}
	
	public int changeView(int bnidx, String opt) {
		Statement stmt = null;
		int result = 0;
		
		try { 
			String sql = "update t_bbs_notice set bn_isview = '" + opt + "' where bn_idx = '" + bnidx + "'";
			System.out.println(sql);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);

		} catch(Exception e) {
			System.out.println("NoticeProcDao 클래스의 changeView() 메소드 오류");
			e.printStackTrace();
		} finally {	
			close(stmt);
		}
		
		return result;
	}
	
	public BbsNotice getNoticeDetailInfo(int bnidx) {
		Statement stmt = null;
		ResultSet rs = null;
		BbsNotice noticeDetailInfo = new BbsNotice();
		
		try {
			String sql = "select * from t_bbs_notice where bn_idx = " + bnidx;
			System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				noticeDetailInfo.setBn_idx(rs.getInt("bn_idx"));
				noticeDetailInfo.setAi_idx(rs.getInt("ai_idx"));
				noticeDetailInfo.setBn_title(rs.getString("bn_title"));
				noticeDetailInfo.setBn_img(rs.getString("bn_img"));
				noticeDetailInfo.setBn_content(rs.getString("bn_content"));
				noticeDetailInfo.setBn_read(rs.getInt("bn_read"));
				noticeDetailInfo.setBn_date(rs.getString("bn_date"));
			}
		}catch(Exception e) {
			System.out.println("NoticeProcDao 클래스의 getNoticeDetailInfo() 메소드 오류");
			e.printStackTrace();
		}finally {
			close(rs);	close(stmt);
		}
		return noticeDetailInfo;
	}
	
	public int getNextIdx() {
		Statement stmt = null;
		ResultSet rs = null;
		int idx = 1;
		try {
			stmt = conn.createStatement();
			String sql = "select if(max(bn_idx) is null, 1, max(bn_idx) + 1) idx from t_bbs_notice";
			rs = stmt.executeQuery(sql);
			if (rs.next())	idx = rs.getInt(1);

		}catch(Exception e) {
			System.out.println("NoticeProcDao 클래스의 getNextIdx() 메소드 오류");
			e.printStackTrace();
		}finally {
			close(rs);	close(stmt);
		}
		return idx;
	}
	
	public int noticeProcIn(BbsNotice bn, int aiidx) {
		int result = 0;
		Statement stmt = null;
		int bnidx = getNextIdx();
		try {
			String sql = "insert into t_bbs_notice(bn_idx, ai_idx, bn_title, bn_img, bn_content, bn_read, bn_isview) " 
			 + "values('" + bnidx + "', '" + aiidx + "', '" + bn.getBn_title() + "', '" + bn.getBn_img() + "', '" + bn.getBn_content() + "', 0, 'n')";
			System.out.println(sql);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		}catch(Exception e) {
			System.out.println("NoticeProcDao 클래스의 noticeProcIn() 메소드 오류");
			e.printStackTrace();
		}finally {
			close(stmt);
		}
		return result;
	}
	
	public BbsNotice getNoticeUpInfo(int bnidx) {
		Statement stmt = null;
		ResultSet rs = null;
		BbsNotice noticeUpInfo = new BbsNotice();
		
		try {
			String sql = "select * from t_bbs_notice where bn_idx = " + bnidx;
			System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				noticeUpInfo.setBn_idx(rs.getInt("bn_idx"));
				noticeUpInfo.setAi_idx(rs.getInt("ai_idx"));
				noticeUpInfo.setBn_title(rs.getString("bn_title"));
				noticeUpInfo.setBn_img(rs.getString("bn_img"));
				noticeUpInfo.setBn_content(rs.getString("bn_content"));
				noticeUpInfo.setBn_read(rs.getInt("bn_read"));
				noticeUpInfo.setBn_date(rs.getString("bn_date"));
			}
		}catch(Exception e) {
			System.out.println("NoticeProcDao 클래스의 getNoticeUpInfo() 메소드 오류");
			e.printStackTrace();
		}finally {
			close(rs);	close(stmt);
		}
		return noticeUpInfo;
	}
	
	public int noticeProcUp(BbsNotice bn, int aiidx) {
		int result = 0;
		Statement stmt = null;
		try {
			String sql = "update t_bbs_notice set ai_idx = '" + aiidx + "', bn_title = '" + bn.getBn_title() + "', bn_img = '" + bn.getBn_img() + "', bn_content = '" + bn.getBn_content() + "' where bn_idx = '" + bn.getBn_idx() + "'";
			System.out.println(sql);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		}catch(Exception e) {
			System.out.println("NoticeProcDao 클래스의 noticeProcUp() 메소드 오류");
			e.printStackTrace();
		}finally {
			close(stmt);
		}
		return result;
	}
}
