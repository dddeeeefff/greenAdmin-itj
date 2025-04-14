package ctrl;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;


@WebServlet("/review_list")
public class ReviewListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReviewListCtrl() {  super(); }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	
    	HttpSession session = request.getSession();
    	AdminInfo loginInfo = (AdminInfo)session.getAttribute("loginInfo");
    	if (loginInfo == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login_form");
			dispatcher.forward(request, response);
		}
    	
    	int cpage = 1, psize = 10, bsize = 10, rcnt = 0, pcnt = 0, spage = 0;
		// 현재 페이지 번호, 페이지 크기, 블록 크기, 레코드(게시글) 개수, 페이지 개수, 시작 페이지 등을 저장할 변수들
		if (request.getParameter("cpage") != null) 
			cpage = Integer.parseInt(request.getParameter("cpage"));
		// cpage 값이 있으면 받아서 int형으로 형변환 시킴(보안상의 이유와 산술연산을 해야 하기 때문)
		
		// 검색 조건 where절 생성
				String where = "";
				String keyword = request.getParameter("keyword");	// 검색어
				if (keyword == null) {	keyword = "";	
				} else if(!keyword.equals("")) {
					URLEncoder.encode(keyword, "UTF-8");
					where +=  " where br_title like '%" + keyword + "%'";
				}
				System.out.println(where);
				
				ReviewListSvc reviewListSvc = new ReviewListSvc();
				rcnt = reviewListSvc.getReviewListCount(where);
				
				ArrayList<BbsReview> reviewList = reviewListSvc.getReviewList(cpage, psize, where);
				// 검색된 공지사항의 총 개수로 보여줄 목록을 받아옴
				
				pcnt = rcnt / psize;
				if (rcnt % psize > 0)	pcnt++;		// 전체 페이지 수(마지막 페이지 번호)
				spage = (cpage - 1) / psize * psize + 1;	// 블록 시작 페이지 번호
				
				PageInfo pageInfo = new PageInfo();
				pageInfo.setBsize(bsize);
				pageInfo.setCpage(cpage);
				pageInfo.setPcnt(pcnt);
				pageInfo.setPsize(psize);
				pageInfo.setRcnt(rcnt);
				pageInfo.setSpage(spage);	
				pageInfo.setKeyword(keyword);
				
				String uri = request.getRequestURI().substring(12);
				request.setAttribute("uri", uri);
				request.setAttribute("pageInfo", pageInfo);
				request.setAttribute("reviewList", reviewList);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/bbs/review_list.jsp");
				dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
