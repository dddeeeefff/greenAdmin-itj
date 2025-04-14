package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class EventProcInSvc {
	public int eventProcIn(BbsEvent be) {
		int result = 0;
		Connection conn = getConnection();
		EventProcDao eventProcDao = EventProcDao.getInstance();
		eventProcDao.setConnection(conn);
		
		result = eventProcDao.eventProcIn(be);
		if (result == 1)	commit(conn);
		else				rollback(conn);
		close(conn);

		return result;
	}
	
	public int getNextIdx() {
		int idx = 0;
		Connection conn = getConnection();
		EventProcDao eventProcDao = EventProcDao.getInstance();
		eventProcDao.setConnection(conn);
		
		idx = eventProcDao.getNextIdx();	
		return idx;
	}
}
