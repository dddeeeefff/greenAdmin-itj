package svc;

import static db.JdbcUtil.*;
import java.sql.*;
import java.util.*;
import dao.*;
import vo.*;

public class MemberListSvc {

	public ArrayList<MemberInfo> getMemberList(String where, int cpage, int psize) {
		ArrayList<MemberInfo> memberList = new ArrayList<MemberInfo>();
		Connection conn = getConnection();
		MemberProcDao memberProcDao = MemberProcDao.getInstance();
		memberProcDao.setConnection(conn);
		
		memberList = memberProcDao.getMemberList(where, cpage, psize);
		close(conn);
		
		return memberList;
	}

	public int getMemberListCount(String where) {
		int rcnt = 0;
		Connection conn = getConnection();
		MemberProcDao memberProcDao = MemberProcDao.getInstance();
		memberProcDao.setConnection(conn);;
		
		rcnt = memberProcDao.getMemberListCount(where);
		close(conn);
		
		return rcnt;
	}
}
