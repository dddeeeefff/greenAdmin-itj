package svc;

import static db.JdbcUtil.*;
import java.sql.*;
import java.util.*;
import dao.*;
import vo.*;

public class MemberStatusUpSvc {

	public int memberStatusUp(MemberStatus memberStatus, AdminInfo loginInfo) {
		int result = 0;
		Connection conn = getConnection();
		MemberProcDao memberProcDao = MemberProcDao.getInstance();
		memberProcDao.setConnection(conn);
		result = memberProcDao.getMemberStatusUp(memberStatus, loginInfo);
		
		if(result == 1)	commit(conn);
		else 			rollback(conn);
		close(conn);
		
		
		return result;
	}

}
