package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class NoticeProcDelSvc {
	public int changeView(int bnidx, String opt) {
		int result = 0;
		Connection conn = getConnection();
		NoticeProcDao noticeProcDao = NoticeProcDao.getInstance();
		noticeProcDao.setConnection(conn);
		
		result = noticeProcDao.changeView(bnidx, opt);
		
		if (result == 1)	commit(conn);
		else				rollback(conn);
		close(conn);
		
		return result;
	}
}
