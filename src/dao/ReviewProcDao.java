package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class ReviewProcDao {
	private static ReviewProcDao reviewProcDao;
	private Connection conn;
	private ReviewProcDao() {}
	
	public static ReviewProcDao getInstance() {
		if (reviewProcDao == null)		reviewProcDao = new ReviewProcDao();
		
		return reviewProcDao;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public ArrayList<BbsReview> getReviewList(int cpage, int psize, String where){
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BbsReview> reviewList = new ArrayList<BbsReview>();
		BbsReview br = null;

		
		try {
			String sql = "select * from t_bbs_review " + where
				+ " order by br_idx desc limit " + ((cpage - 1) * psize) + ", " + psize;
			System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				br = new BbsReview();
				br.setBr_idx(rs.getInt("br_idx"));
				br.setMi_id(rs.getString("mi_id"));
				br.setSi_id(rs.getString("pi_id"));
				br.setPo_idx(rs.getInt("po_idx"));
				br.setBr_name(rs.getString("br_name"));
				br.setBr_title(rs.getString("br_title"));
				br.setBr_content(rs.getString("br_content"));
				br.setBr_img(rs.getString("br_img"));
				br.setBr_read(rs.getInt("br_read"));
				br.setBr_date(rs.getString("br_date"));
				br.setBr_isdel(rs.getString("br_isdel"));
				reviewList.add(br);
				
			}
			
		}catch(Exception e) {
			System.out.println("ReviewProcDao 占쎈�占쏙옙占썬끉占쏙옙 getReviewList() 筌롳옙占쏙옙占쏙옙 占썬끇占�");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return reviewList;
	}
	
	public int getReviewListCount(String where) {
		int rcnt = 0;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select count(br_idx) from t_bbs_review " + where;
			System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())	rcnt = rs.getInt(1);
			
		}catch(Exception e){
			System.out.println("ReviewProcDao 占쎈�占쏙옙占썬끉占쏙옙 getReviewListCount() 筌롳옙占쏙옙占쏙옙 占썬끇占�");
			e.printStackTrace();
		}finally {
			close(rs);	close(stmt);
		}
		return rcnt;
	}
	
	public int changeView(int bridx, String opt) {
		Statement stmt = null;
		int result = 0;
		
		try { 
			String sql = "update t_bbs_review set br_isdel = '" + opt + "' where br_idx = '" + bridx + "'";
			System.out.println(sql);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);

		} catch(Exception e) {
			System.out.println("ReviewProcDao 占쎈�占쏙옙占썬끉占쏙옙 changeView() 筌롳옙占쏙옙占쏙옙 占썬끇占�");
			e.printStackTrace();
		} finally {	
			close(stmt);
		}
		
		return result;
	}
	
	public BbsReview getReviewDetailInfo(int bridx) {
		Statement stmt = null;
		ResultSet rs = null;
		BbsReview reviewDetailInfo = new BbsReview();
		
		try {
			String sql = "select * from t_bbs_review where br_idx = " + bridx;
			System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				reviewDetailInfo.setBr_title(rs.getString("br_title"));
				reviewDetailInfo.setBr_name(rs.getString("br_name"));
				reviewDetailInfo.setMi_id(rs.getString("mi_id"));
				reviewDetailInfo.setBr_date(rs.getString("br_date"));
				reviewDetailInfo.setBr_read(rs.getInt("br_read"));
				reviewDetailInfo.setBr_img(rs.getString("br_img"));
				reviewDetailInfo.setBr_content(rs.getString("br_content"));
			}
		}catch(Exception e) {
			System.out.println("NoticeProcDao 占쎈�占쏙옙占썬끉占쏙옙 getReviewDetailInfo() 筌롳옙占쏙옙占쏙옙 占썬끇占�");
			e.printStackTrace();
		}finally {
			close(rs);	close(stmt);
		}
		return reviewDetailInfo;
	}
}
