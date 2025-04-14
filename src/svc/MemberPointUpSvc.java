package svc;

import static db.JdbcUtil.*;
import java.sql.*;
import java.util.*;
import dao.*;
import vo.*;

public class MemberPointUpSvc {

	public int memberPointUp(MemberPoint memberPoint, AdminInfo loginInfo) {
		int result = 0;
		Connection conn = getConnection();
		MemberProcDao memberProcDao = MemberProcDao.getInstance();
		memberProcDao.setConnection(conn);
		result = memberProcDao.getMemberPointUp(memberPoint, loginInfo);
		
		if(result == 2)	commit(conn);
		else 			rollback(conn);
		close(conn);
		
		
		return result;
	}

}
