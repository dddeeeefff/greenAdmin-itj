package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class FreeViewSvc {
	
	public BbsFree getFreeInfo(int bfidx) {
		BbsFree bf = null;
		Connection conn = getConnection();
		FreeProcDao freeProcDao = FreeProcDao.getInstance();
		freeProcDao.setConnection(conn);
		
		bf = freeProcDao.getFreeInfo(bfidx);
		close(conn);
		
		return bf;
	}

}
