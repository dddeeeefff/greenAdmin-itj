package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class FreeProcDao {
// 자유게시판 관련 쿼리 작업(목록, 등록, 수정, 삭제)들을 모두 처리하는 클래스
	private static FreeProcDao freeProcDao;
	private Connection conn;
	private FreeProcDao() {}
	
	public static FreeProcDao getInstance() {
		
		if (freeProcDao == null)	freeProcDao = new FreeProcDao();
		
		return freeProcDao;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public int getFreeListCount(String where) {
	// 자유 게시판의 검색된 결과의 레코드(게시글) 개수를 리턴하는 메소드
		int rcnt = 0;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select count(*) from t_bbs_free " + where;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())		rcnt = rs.getInt(1);
			
		} catch (Exception e) {
			System.out.println("FreeProcDao 클래스의 getFreeListCount() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);		close(stmt);
		}
		
		return rcnt;
	}
	
	public ArrayList<BbsFree> getFreeList(String where, int cpage, int psize) {
	// 자유 게시판에서 검색된 결과의 레코드(게시글) 목록을 ArrayList로 리턴하는 메소드
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BbsFree> freeList = new ArrayList<BbsFree>();
		BbsFree bf = null;
		
		try {
			String sql = "select bf_idx, mi_id, bf_title, bf_reply, bf_read, date(bf_date) wdate, bf_isdel from t_bbs_free " 
				+ where + " order by bf_idx desc limit " + ((cpage - 1) * psize) + ", " + psize;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				bf = new BbsFree();
				bf.setBf_idx(rs.getInt("bf_idx"));
				bf.setMi_id(rs.getString("mi_id"));
				bf.setBf_title(rs.getString("bf_title"));
				bf.setBf_reply(rs.getInt("bf_reply"));
				bf.setBf_read(rs.getInt("bf_read"));
				bf.setBf_date(rs.getString("wdate"));
				bf.setBf_isdel(rs.getString("bf_isdel"));
				freeList.add(bf);
			}
			
		} catch (Exception e) {
			System.out.println("FreeProcDao 클래스의 getFreeList() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);		close(stmt);
		}
		
		return freeList;
	}

	public int freeProcDel(int bfidx, String kind) {
		int result = 0;
		Statement stmt = null;
		
		try {
			String sql = "update t_bbs_free set bf_isdel = '" + kind + "' where bf_idx = " + bfidx;
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			System.out.println("FreeProcDao 클래스의 freeProcDel() 메소드 오류");
		} finally {
			close(stmt);
		}
		
		return result;
	}

	public BbsFree getFreeInfo(int bfidx) {
		Statement stmt = null;
		ResultSet rs = null;
		BbsFree bf = null;
		
		try {
			String sql = "select * from t_bbs_free where bf_isdel = 'n' and bf_idx = " + bfidx;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				bf = new BbsFree();
				bf.setBf_idx(bfidx);
				bf.setMi_id(rs.getString("mi_id"));
				bf.setBf_title(rs.getString("bf_title"));
				bf.setBf_content(rs.getString("bf_content"));
				bf.setBf_img(rs.getString("bf_img"));
				bf.setBf_read(rs.getInt("bf_read"));
				bf.setBf_date(rs.getString("bf_date"));
				
			}
			
		} catch (Exception e) {
			System.out.println("FreeProcDao 클래스의 getFreeInfo() 메소드 오류");
		} finally {
			close(rs);		close(stmt);
		}
		
		return bf;
	}
}
