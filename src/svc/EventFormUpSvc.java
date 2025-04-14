package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class EventFormUpSvc {
	public BbsEvent getFormUp(int beidx) {
		BbsEvent be = new BbsEvent();
		Connection conn = getConnection();
		EventProcDao eventProcDao = EventProcDao.getInstance();
		eventProcDao.setConnection(conn);
		
		be = eventProcDao.getFormUp(beidx);

		return be;
	}
}
