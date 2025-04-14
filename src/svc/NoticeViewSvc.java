package svc;

import static db.JdbcUtil.*;
import java.sql.*;
import java.util.*;
import dao.*;
import vo.*;

public class NoticeViewSvc {
	public BbsNotice getNoticeDetailInfo(int bnidx) {
		BbsNotice noticeDetailInfo = null;
		Connection conn = getConnection();
		NoticeProcDao noticeProcDao = NoticeProcDao.getInstance();
		noticeProcDao.setConnection(conn);
		
		noticeDetailInfo = noticeProcDao.getNoticeDetailInfo(bnidx);
		close(conn);
		
		return noticeDetailInfo;
	}
}
