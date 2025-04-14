package svc;

import static db.JdbcUtil.*;
import java.sql.*;
import java.util.*;
import dao.*;
import vo.*;

public class NoticeFormUpSvc {
	public BbsNotice getNoticeUpInfo(int bnidx) {
		BbsNotice noticeUpInfo = null;
		Connection conn = getConnection();
		NoticeProcDao noticeProcDao = NoticeProcDao.getInstance();
		noticeProcDao.setConnection(conn);
		
		noticeUpInfo = noticeProcDao.getNoticeUpInfo(bnidx);
		close(conn);
		
		return noticeUpInfo;
	}
}
