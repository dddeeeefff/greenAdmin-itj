package svc;

import static db.JdbcUtil.*;
import java.sql.*;
import java.util.*;
import dao.*;
import vo.*;

public class NoticeProcInSvc {
	public int getNextIdx() {
		int idx = 0;
		Connection conn = getConnection();
		NoticeProcDao noticeProcDao = NoticeProcDao.getInstance();
		noticeProcDao.setConnection(conn);
		
		idx = noticeProcDao.getNextIdx();
		
		return idx;
	}
	
	public int noticeProcIn(BbsNotice bn, int aiidx) {
		int result = 0;
		Connection conn = getConnection();
		NoticeProcDao noticeProcDao = NoticeProcDao.getInstance();
		noticeProcDao.setConnection(conn);
		
		result = noticeProcDao.noticeProcIn(bn, aiidx);
		if (result == 1)		commit(conn);
		else					rollback(conn);
		close(conn);
		
		return result;
	}
}
