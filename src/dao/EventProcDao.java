package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class EventProcDao {
// 이벤트 게시판 관련 쿼리 작업(목록, 등록, 수정, 삭제)들을 모두 처리하는 클래스
	private static EventProcDao eventProcDao;
	private Connection conn;
	private EventProcDao() {}
	
	public static EventProcDao getInstance() {
		if (eventProcDao == null)	eventProcDao = new EventProcDao();
		
		return eventProcDao;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public int getEventListCount(String where) {
	// 이벤트 게시판의 검색된 결과의 레코드(게시글) 개수를 리턴하는 메소드
		Statement stmt = null;
		ResultSet rs = null;
		int rcnt = 0;
		
		try {
			String sql = "select count(*) from t_bbs_event " + where;
			//System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())	rcnt = rs.getInt(1);
			
		} catch(Exception e) {
			System.out.println("EventProcDao 클래스의 getEventListCount() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return rcnt;
	}
	
	public ArrayList<BbsEvent> getEventList(String where, int cpage, int psize) {
	// 이벤트 게시판의 검색된 결과의 레코드(게시글) 목록을 ArrayList로 리턴하는 메소드
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BbsEvent> eventList = new ArrayList<BbsEvent>();
		// 검색된 게시글들을 저장할 ArrayList<BbsEvent>
		BbsEvent be = null;
		// freeList에 저장할 하나의 게시글 정보를 담을 인스턴스
		
		try {
			String sql = "select be_idx, be_title, ai_idx, be_read, be_isview, left(be_date, 10) wdate " + 
					" from t_bbs_event " + where + " order by be_idx desc limit " + 
					((cpage - 1) * psize) + ", " + psize;
			//System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				be = new BbsEvent();
				be.setBe_idx(rs.getInt("be_idx"));
				be.setBe_title(rs.getString("be_title"));
				be.setAi_idx(rs.getInt("ai_idx"));
				be.setBe_read(rs.getInt("be_read"));
				be.setBe_date(rs.getString("wdate"));
				be.setBe_isview(rs.getString("be_isview"));
				eventList.add(be);
			}
			
		} catch(Exception e) {
			System.out.println("EventProcDao 클래스의 getEventList() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return eventList;
	}
	
	public int getView(int beidx, String opt) {
	// 미게시 or 진행중 or 종료 여부를 결정하는 메소드
		Statement stmt = null;
		int result = 0;
		
		try { 
			String sql = "update t_bbs_event set be_isview = '" + opt + "' where be_idx = '" + beidx + "'";
			// System.out.println(sql);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);

		} catch(Exception e) {
			System.out.println("EventProcDao 클래스의 getView() 메소드 오류");
			e.printStackTrace();
		} finally {	
			close(stmt);
		}
		
		return result;
	}
	
	public int readUpdate(int beidx) {
	// 게시글의 조회수를 증가시키는 메소드
		Statement stmt = null;
		int result = 0;
		
		try {
			String sql = "update t_bbs_event set be_read = be_read + 1 where be_idx = " + beidx;
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
		} catch(Exception e) {
			System.out.println("EventProcDao 클래스의 readUpdate() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return result;
	}
	
	public BbsEvent getEventInfo(int beidx) {
	// 게시글의 정보들을 BbsEvent형 인스턴스로 리턴하는 메소드
		Statement stmt = null;
		ResultSet rs = null;
		BbsEvent be = null;
		
		try {
			String sql = "select be_idx, be_title, ai_idx, left(be_date, 10) wdate, be_sdate, be_edate, " + 
					" be_read, be_img, be_content from t_bbs_event where be_idx = " + beidx;
			System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				be = new BbsEvent();
				be.setBe_idx(beidx);
				be.setBe_title(rs.getString("be_title"));
				be.setAi_idx(rs.getInt("ai_idx"));
				be.setBe_date(rs.getString("wdate"));
				be.setBe_sdate(rs.getString("be_sdate"));
				be.setBe_edate(rs.getString("be_edate"));
				be.setBe_read(rs.getInt("be_read"));
				be.setBe_img(rs.getString("be_img"));
				be.setBe_content(rs.getString("be_content"));
			}
			
		} catch(Exception e) {
			System.out.println("EventProcDao 클래스의 getEventInfo() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return be;
	}
	
	public int eventProcIn(BbsEvent be) {
		Statement stmt = null;
		int idx = getNextIdx();	// 등록할 게시글의 글번호를 저장할 변수
		int result = 0;
		
		try {
			String sql = "insert into t_bbs_event (be_idx, ai_idx, be_title, be_content, be_img, be_sdate, be_edate) " + 
					" values ('" + idx + "', 1, '" + be.getBe_title() + "', '" + be.getBe_content() + 
					"', '" + be.getBe_img() + "', '" + be.getBe_sdate() + "', '" + be.getBe_edate() + "')";
			System.out.println(sql);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);	
			
		} catch(Exception e) {
			System.out.println("EventProcDao 클래스의 eventProcIn() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return result;
	}
	
	public int getNextIdx() {
	// 글번호 순서를 저장하는 메소드
		Statement stmt = null;
		ResultSet rs = null;
		int idx = 1;
		
		try {
			String sql = "select max(be_idx) + 1 from t_bbs_event";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.next())	idx = rs.getInt(1);
			// 쿼리의 결과가 있으면 그 값을 글번호로 사용
			
		} catch(Exception e) {
			System.out.println("EventProcDao 클래스의 getNextIdx() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return idx;
	}
	
	public BbsEvent getFormUp(int beidx) {
		Statement stmt = null;
		ResultSet rs = null;
		BbsEvent be = null;
		
		try {
			String sql = "select be_idx, ai_idx, be_title, be_content, be_read, be_img, be_sdate, be_edate, be_date " + 
					" from t_bbs_event where be_idx = '" + beidx + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				be = new BbsEvent();
				be.setBe_idx(rs.getInt("be_idx"));
				be.setAi_idx(rs.getInt("ai_idx"));
				be.setBe_title(rs.getString("be_title"));
				be.setBe_content(rs.getString("be_content"));
				be.setBe_read(rs.getInt("be_read"));
				be.setBe_img(rs.getString("be_img"));
				be.setBe_sdate(rs.getString("be_sdate"));
				be.setBe_edate(rs.getString("be_edate"));
				be.setBe_date(rs.getString("be_date"));
			}
			
		} catch(Exception e) {
			System.out.println("EventProcDao 클래스의 getFormUp() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return be;
	}
	
	public int eventProcUp(BbsEvent be, int idx) {
		Statement stmt = null;
		int result = 0;
		
		try {
			String sql = "update t_bbs_event set be_title = '" + be.getBe_title() + "', be_content = '" + be.getBe_content() + 
					"', be_img = '" + be.getBe_img() + "', be_sdate = '" + be.getBe_sdate() + "', be_edate = '" + be.getBe_edate() + "'" + 
					" where be_idx = '" + idx + "'";
			System.out.println(sql);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);	
			
		} catch(Exception e) {
			System.out.println("EventProcDao 클래스의 eventProcIn() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return result;
	}
}
