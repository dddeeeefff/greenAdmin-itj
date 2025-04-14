package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class EventProcUpSvc {
	public int eventProcUp(BbsEvent be, int idx) {
		int result = 0;
		Connection conn = getConnection();
		EventProcDao eventProcDao = EventProcDao.getInstance();
		eventProcDao.setConnection(conn);
		
		result = eventProcDao.eventProcUp(be, idx);
		if (result == 1)	commit(conn);
		else				rollback(conn);
		close(conn);

		return result;
	}
}
