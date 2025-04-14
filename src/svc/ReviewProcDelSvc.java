package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class ReviewProcDelSvc {
	public int changeView(int bridx, String opt) {
		int result = 0;
		Connection conn = getConnection();
		ReviewProcDao reviewProcDao = ReviewProcDao.getInstance();
		reviewProcDao.setConnection(conn);
		
		result = reviewProcDao.changeView(bridx, opt);
		
		if (result == 1)	commit(conn);
		else				rollback(conn);
		close(conn);
		
		return result;
	}
}
