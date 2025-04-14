package svc;

import static db.JdbcUtil.*;
import java.sql.*;
import java.util.*;
import dao.*;
import vo.*;

public class NoticeProcUpSvc {
	public int noticeProcUp(BbsNotice bn, int aiidx) {
		int result = 0;
		Connection conn = getConnection();
		NoticeProcDao noticeProcDao = NoticeProcDao.getInstance();
		noticeProcDao.setConnection(conn);
		
		result = noticeProcDao.noticeProcUp(bn, aiidx);
		if (result == 1)		commit(conn);
		else					rollback(conn);
		close(conn);
		
		return result;
	}
}
