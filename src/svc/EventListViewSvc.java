package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class EventListViewSvc {
	public int getView(int beidx, String opt) {
		int result = 0;
		Connection conn = getConnection();
		EventProcDao eventProcDao = EventProcDao.getInstance();
		eventProcDao.setConnection(conn);
		
		result = eventProcDao.getView(beidx, opt);

		if (result == 1)	commit(conn);
		else				rollback(conn);
		close(conn);
		
		return result;
	}
}
