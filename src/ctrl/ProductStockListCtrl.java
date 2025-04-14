package ctrl;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/product_stock_list")
public class ProductStockListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ProductStockListCtrl() {  super();  }

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int cpage = 1, psize = 10, bsize = 10, rcnt = 0, pcnt = 0, spage = 0;
		// ���� ������ ��ȣ, ������ ũ��, ��� ũ��, ���ڵ�(�Խñ�) ����, ������ ����, ���� ������ ���� ������ ������
		if (request.getParameter("cpage") != null) 
			cpage = Integer.parseInt(request.getParameter("cpage"));
		// cpage ���� ������ �޾Ƽ� int������ ����ȯ ��Ŵ(���Ȼ��� ������ ��������� �ؾ� �ϱ� ����)
		
		
		
		// �˻� ���� where�� ����
		String where = "";
		String schtype = request.getParameter("schtype");	// �˻�����(�𵨸�, �귣��)
		String keyword = request.getParameter("keyword");	// �˻���
		if (schtype == null || keyword == null) {
			schtype = "";	keyword = "";	
		} else if(!schtype.equals("") && !keyword.equals("")) {
			URLEncoder.encode(keyword, "UTF-8");
			if(schtype.equals("pi_name"))
			where +=  " and b." + schtype + " like '%" + keyword + "%'";
			else if(schtype.equals("pb_name"))
			where +=  " and c." + schtype + " like '%" + keyword + "%'";
		}
		
		ProductStockListSvc productStockListSvc = new ProductStockListSvc();
		ArrayList<ProductOption> productStockList = productStockListSvc.getStockList(cpage, psize, where);
		rcnt = productStockListSvc.getStockListCount(where);
		pcnt = rcnt / psize;
		if (rcnt % psize > 0)	pcnt++;	// ��ü ������ ��(������ ������ ��ȣ)
		spage = (cpage - 1) / psize * psize + 1;	// ��� ���� ������ ��ȣ
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setBsize(bsize);	pageInfo.setCpage(cpage);
		pageInfo.setPcnt(pcnt);		pageInfo.setPsize(psize);
		pageInfo.setRcnt(rcnt);		pageInfo.setSpage(spage);
		pageInfo.setSchtype(schtype);
		pageInfo.setKeyword(keyword);
		// ����¡ ���� ������� �˻� �� ���� �������� PageInfo �ν��Ͻ��� ����

		String uri = request.getRequestURI().substring(12);
		request.setAttribute("uri", uri);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("productStockList", productStockList);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/product/product_stock_list.jsp");
		dispatcher.forward(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
