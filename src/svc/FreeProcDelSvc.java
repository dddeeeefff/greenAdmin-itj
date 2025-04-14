package svc;

import static db.JdbcUtil.*;
import java.sql.*;
import java.util.*;
import dao.*;
import vo.*;

public class FreeProcDelSvc {

	public int freeProcDel(int bfidx, String kind) {
		int result = 0;
		Connection conn = getConnection();
		FreeProcDao freeProcDao = FreeProcDao.getInstance();
		freeProcDao.setConnection(conn);
		
		result = freeProcDao.freeProcDel(bfidx, kind);
		if (result == 1)		commit(conn);
		else					rollback(conn);
		close(conn);
		
		return result;
	}

}
