package ctrl;

import java.io.*;
import java.net.URLEncoder;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/non_confirm_sell_list")
public class NonConfirmSellListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;      
    public NonConfirmSellListCtrl() { super();  }
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	request.setCharacterEncoding("utf-8");
		
		int cpage = 1, psize = 10, bsize = 10, rcnt = 0, pcnt = 0, spage = 0;
		// ���� ������ ��ȣ, ������ ũ��, ��� ũ��, ���ڵ�(�Խñ�) ����, ������ ����, ���� ������ ���� ������ ������
		if (request.getParameter("cpage") != null) 
			cpage = Integer.parseInt(request.getParameter("cpage"));
		// cpage ���� ������ �޾Ƽ� int������ ����ȯ ��Ŵ(���Ȼ��� ������ ��������� �ؾ� �ϱ� ����)
		
		
		
		// �˻� ���� where�� ����
		String where = "";
		String schtype = request.getParameter("schtype");	// �˻�����(���̵�, �ֹ���ȣ)
		String keyword = request.getParameter("keyword");	// �˻���
		if (schtype == null || keyword == null) {
			schtype = "";	keyword = "";	
		} else if(!schtype.equals("") && !keyword.equals("")) {
			URLEncoder.encode(keyword, "UTF-8");
			where += schtype + "_id " + "like '%" + keyword + "%'";
		}
		
		NonConfirmSellListSvc nonConfirmSellListSvc = new NonConfirmSellListSvc();
		rcnt = nonConfirmSellListSvc.getNonSellListCount(where);
			
		// �˻��� ����� �� ������ ��ü ���������� ���� �� ���
		
		ArrayList<SellInfo> sellList = nonConfirmSellListSvc.getNonSellList(cpage, psize, where);
		// �˻��� �ֹ��� �� ������ ������ ��ǰ ����� �޾ƿ�
		
		
		
		pcnt = rcnt / psize;
		if (rcnt % psize > 0)	pcnt++;		// ��ü ������ ��(������ ������ ��ȣ)
		spage = (cpage - 1) / psize * psize + 1;	// ��� ���� ������ ��ȣ
		
		
		
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setBsize(bsize);
		pageInfo.setCpage(cpage);
		pageInfo.setPcnt(pcnt);
		pageInfo.setPsize(psize);
		pageInfo.setRcnt(rcnt);
		pageInfo.setSpage(spage);	
		pageInfo.setSchtype(schtype);
		pageInfo.setKeyword(keyword);

		

		String uri = request.getRequestURI().substring(12);
		request.setAttribute("uri", uri);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("sellList", sellList);
		
		
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/order/non_confirm_sell_list.jsp");
		dispatcher.forward(request, response);
    	
    	
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
