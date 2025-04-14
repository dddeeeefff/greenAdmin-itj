package svc;

import static db.JdbcUtil.*;
import java.sql.*;
import java.util.*;
import dao.*;
import vo.*;

public class MemberViewSvc {

	public MemberInfo getMemberInfo(String miid) {
		MemberInfo memberInfo = null;
		Connection conn = getConnection();
		MemberProcDao memberProcDao = MemberProcDao.getInstance();
		memberProcDao.setConnection(conn);
		memberInfo = memberProcDao.getMemberInfo(miid);
		close(conn);
		
		return memberInfo;
	}

	public MemberStatus getMemberStatus(String miid) {
		MemberStatus memberStatus = null;
		Connection conn = getConnection();
		MemberProcDao memberProcDao = MemberProcDao.getInstance();
		memberProcDao.setConnection(conn);
		memberStatus = memberProcDao.getMemberStatus(miid);
		close(conn);
		
		return memberStatus;
	}

	public ArrayList<SellInfo> getSellInfoList(String miid, int cpage, int psize) {
		ArrayList<SellInfo> sellInfoList = null;
		Connection conn = getConnection();
		MemberProcDao memberProcDao = MemberProcDao.getInstance();
		memberProcDao.setConnection(conn);
		sellInfoList = memberProcDao.getSellInfoList(miid,cpage,psize);
		close(conn);
		
		return sellInfoList;
	}

	public ArrayList<BuyInfo> getBuyInfoList(String miid, int cpage, int psize) {
		ArrayList<BuyInfo> buyInfoList = null;
		Connection conn = getConnection();
		MemberProcDao memberProcDao = MemberProcDao.getInstance();
		memberProcDao.setConnection(conn);
		buyInfoList = memberProcDao.getBuyInfoList(miid, cpage, psize);
		close(conn);
		
		return buyInfoList;
	}

	public ArrayList<MemberPoint> getPointList(String miid, int cpage, int psize) {
		ArrayList<MemberPoint> pointList = new ArrayList<MemberPoint>();
		Connection conn = getConnection();
		MemberProcDao memberProcDao = MemberProcDao.getInstance();
		memberProcDao.setConnection(conn);
		
		pointList = memberProcDao.getPointList(miid, cpage, psize);
		close(conn);
		
		return pointList;
	}

	public ArrayList<MemberQuestion> getQuestionList(String miid, int cpage, int psize) {
		ArrayList<MemberQuestion> questionList = new ArrayList<MemberQuestion>();
		Connection conn = getConnection();
		MemberProcDao memberProcDao = MemberProcDao.getInstance();
		memberProcDao.setConnection(conn);;
		
		questionList = memberProcDao.getQuestionList(miid, cpage, psize);
		close(conn);
		
		return questionList;
	}

	public int getSellInfoListCount(String miid) {
		int rcnt = 0;
		Connection conn = getConnection();
		MemberProcDao memberProcDao = MemberProcDao.getInstance();
		memberProcDao.setConnection(conn);
		
		rcnt = memberProcDao.getSellInfoListCount(miid);
		close(conn);
		
		return rcnt;
	}

	public int getBuyInfoListCount(String miid) {
		int rcnt = 0;
		Connection conn = getConnection();
		MemberProcDao memberProcDao = MemberProcDao.getInstance();
		memberProcDao.setConnection(conn);
		
		rcnt = memberProcDao.getBuyInfoListCount(miid);
		close(conn);
		
		return rcnt;
	}

	public int getPointListCount(String miid) {
		int rcnt = 0;
		Connection conn = getConnection();
		MemberProcDao memberProcDao = MemberProcDao.getInstance();
		memberProcDao.setConnection(conn);
		
		rcnt = memberProcDao.getPointListCount(miid);
		close(conn);
		
		return rcnt;
	}

	public int getQuestionListCount(String miid) {
		int rcnt = 0;
		Connection conn = getConnection();
		MemberProcDao memberProcDao = MemberProcDao.getInstance();
		memberProcDao.setConnection(conn);
		
		rcnt = memberProcDao.getQuestionListCount(miid);
		close(conn);
		
		return rcnt;
	}


}
