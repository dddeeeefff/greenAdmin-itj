package svc;

import static db.JdbcUtil.*;
import java.sql.*;
import java.util.*;
import dao.*;
import vo.*;

public class ReviewViewSvc {
	public BbsReview getReviewDetailInfo(int bridx) {
		BbsReview reviewDetailInfo = null;
		Connection conn = getConnection();
		ReviewProcDao reviewProcDao = ReviewProcDao.getInstance();
		reviewProcDao.setConnection(conn);
		
		reviewDetailInfo = reviewProcDao.getReviewDetailInfo(bridx);
		close(conn);
		
		return reviewDetailInfo;
	}
}
