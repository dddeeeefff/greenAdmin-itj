<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ include file="../_inc/inc_head.jsp"%>
<%
request.setCharacterEncoding("utf-8");
DecimalFormat formatter = new DecimalFormat("###,###");
MemberInfo memberInfo = (MemberInfo)request.getAttribute("memberInfo");
MemberStatus memberStatus = (MemberStatus)request.getAttribute("memberStatus");
String kind = request.getParameter("kind");
if (kind == null)		kind = "a";
ArrayList<SellInfo> sellInfoList = null;
ArrayList<BuyInfo> buyInfoList = null;
ArrayList<MemberPoint> pointList = null;
ArrayList<MemberQuestion> questionList = null;
if (kind.equals("a")) {
	sellInfoList = (ArrayList<SellInfo>)request.getAttribute("sellInfoList");
} else if (kind.equals("b")) {
	buyInfoList = (ArrayList<BuyInfo>)request.getAttribute("buyInfoList"); 
} else if (kind.equals("c")) {
	pointList = (ArrayList<MemberPoint>)request.getAttribute("pointList"); 
} else if (kind.equals("d")) {
	questionList = (ArrayList<MemberQuestion>)request.getAttribute("questionList"); 
}

PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
int bsize = pageInfo.getBsize(), cpage = pageInfo.getCpage();
int psize = pageInfo.getPsize(), pcnt = pageInfo.getPcnt();
int spage = pageInfo.getSpage(), rcnt = pageInfo.getRcnt();

String status = memberInfo.getMi_status();
if (status.equals("a"))		status = "정상";
else						status = "탈퇴";
%>
<style>
tr {
	height: 50px;
}

th {
	font-size: 20px;
}

hr {
	margin: 10px 0;
}

#page-contents {
	width: 1000px;
	margin: 50px 0 50px 500px;
}

#page-contents h2 {
	margin: 20px 0;
	padding-bottom: 20px;
	font-size: 2.3em;
	text-align: center;
	border-bottom: 1px solid #000;
}
#page-content span {
	width: 100%;
	height: 1px;
	border-top: 1px solid #000;
}


.container{
	margin: 0 auto;
}

ul.tabs{
	margin: 0px;
	padding: 0px;
	list-style: none;
	display: flex;
}
ul.tabs li{
	width:100%;
	background: none;
	color: #222;
	display: inline-block;
	cursor: pointer;
	border: 1px solid #000;
	text-align: center;
}

ul.tabs li.current{
	background: #ededed;
	color: #222;
}
ul.tabs li a{width:100%;display:inline-block;padding:10px 15px}

.tab-content{
	margin: 20px 0;
}

.tab-content.current{
	display: inherit;
}
.tab-content td {
	text-align: center;
}


.btn {
	font-size: 2.1em;
    margin: 20px;
    padding: 10px 20px 10px 20px;
}






</style>

<div id="page-contents">
	<div>
		<h2>회원 관리</h2>
		<span></span>
		<table width="1000" align="center">
			<tr>
				<th width="15%">아이디</th>
				<td width="35%"><%=memberInfo.getMi_id() %></td>
				<th width="15%">이름</th>
				<td width="35%"><%=memberInfo.getMi_name() %></td>
			</tr>
			<tr>
				<th width="15%">생년월일</th>
				<td width="35%"><%=memberInfo.getMi_birth() %></td>
				<th width="15%">성별</th>
				<td width="35%"><%=memberInfo.getMi_gender() %></td>
			</tr>
			<tr>
				<th width="15%">연락처</th>
				<td width="35%"><%=memberInfo.getMi_phone() %></td>
				<th width="15%">이메일</th>
				<td width="35%"><%=memberInfo.getMi_email() %></td>
			</tr>
			<tr>
				<th width="15%">포인트</th>
				<td width="35%"><%=formatter.format(memberInfo.getMi_point()) %> P</td>
				<th width="15%">상태</th>
				<td width="35%"><%=status %></td>
			</tr>
			<tr>
				<th width="15%">집주소</th>
				<td width="*" colspan="3"><%=memberInfo.getMi_addr1() %> <%=memberInfo.getMi_addr2() %></td>
			</tr>
<%
if (status.equals("탈퇴")) {
%>
			<tr>
				<th width="15%">탈퇴 사유</th>
				<td width="*" colspan="3"><%=memberStatus.getMs_reason() %></td>
			</tr>
			<tr>
				<th width="15%">탈퇴 일자</th>
				<td width="*" colspan="3"><%=memberStatus.getMs_date() %></td>
			</tr>
<% }else { %>
		<tr style="text-align:center">
			<td colspan="2">
			<input type="button" value="포인트 관리" class="btn"
			onclick="window.open('member_point?miid=<%=memberInfo.getMi_id() %>&mipoint=<%=memberInfo.getMi_point() %>', 'window_name', 'width=400, height=520, location=no, status=no, scrollbars=yes');" />
			</td>
			
			<td colspan="2">
			<input type="button" value="회원 탈퇴" class="btn" 
			onclick="window.open('member_status?miid=<%=memberInfo.getMi_id() %>', 'window_name', 'width=720, height=300, location=no, status=no, scrollbars=yes');" />
			</td>
		</tr>
<%} %>
		</table>
	</div>

 <div class="container">

	<ul class="tabs">
		<li><a href="member_view?kind=a&miid=<%=memberInfo.getMi_id() %>" <% if (kind.equals("a")) { %> style="background:#333; color:#fff"<% } %>>회원 구매 내역</a></li>
		<li><a href="member_view?kind=b&miid=<%=memberInfo.getMi_id() %>" <% if (kind.equals("b")) { %> style="background:#333; color:#fff"<% } %>>회원 판매 내역</a></li>
		<li><a href="member_view?kind=c&miid=<%=memberInfo.getMi_id() %>" <% if (kind.equals("c")) { %> style="background:#333; color:#fff"<% } %>>회원 포인트 내역</a></li>
		<li><a href="member_view?kind=d&miid=<%=memberInfo.getMi_id() %>" <% if (kind.equals("d")) { %> style="background:#333; color:#fff"<% } %>>회원 문의 내역</a></li>
	</ul>

<% if (kind.equals("a") || kind.equals("b")) { %>
	<div id="tab-1" class="tab-content current">
		<table width="1000" align="center" border="1" cellpadding="3" cellspacing="0">

			<tr>
				<th width="10%">구분</th>
				<th width="25%">주문번호</th>
				<th width="*%">제품명</th>
				<th width="15%">가격</th>
				<th width="15%">상태</th>
				<th width="15%">날짜</th>
			</tr>
<%
	if ((sellInfoList != null && sellInfoList.size() > 0) || (buyInfoList != null && buyInfoList.size() > 0)) {
		String sellbuy = "";
		int size = 0;
		if (kind.equals("a"))		size = sellInfoList.size();
		else if (kind.equals("b"))		size = buyInfoList.size();
		for (int i = 0 ; i < size; i++) {
			Object info = null;
			String oId = "";
			String mName = "";
			int pay = 0;
			String oStatus = "";
			String oDate = "";
			if (kind.equals("a")) {
				sellbuy ="구매";
				info = (SellInfo)sellInfoList.get(i);
				oId = ((SellInfo)info).getSi_id();
				mName = ((SellInfo)info).getSd_mname();
				pay = ((SellInfo)info).getSi_pay();
				oStatus = ((SellInfo)info).getSi_status();
				oDate = ((SellInfo)info).getSi_date().substring(0, 10);
				switch (oStatus) {
					case "a":
						oStatus = "입금대기중";
						break;
					case "b":
						oStatus = "배송준비중";
						break;
					case "c":
						oStatus = "배송중";
						break;
					case "d":
						oStatus = "배송완료";
						break;
					case "e":
						oStatus = "구매 완료 ";
						break;
					case "f":
						oStatus = "주문취소";
						break;
				}
			} else if (kind.equals("b")) {
				sellbuy ="판매";
				info = buyInfoList.get(i);
				oId = ((BuyInfo)info).getBi_id();
				mName = ((BuyInfo)info).getPi_name();
				pay = ((BuyInfo)info).getBi_pay();
				oStatus = ((BuyInfo)info).getBi_status();
				oDate = ((BuyInfo)info).getBi_date().substring(0, 10);
				switch (oStatus) {
					case "a":
						oStatus = "판매 신청";
						break;
					case "b":
						oStatus = "판매 취소";
						break;
					case "c":
						oStatus = "승인 거절";
						break;
					case "d":
						oStatus = "1차 검수 완료";
						break;
					case "e":
						oStatus = "배송 대기";
						break;
					case "f":
						oStatus = "배송중";
						break;
					case "g":
						oStatus = "상품 도착";
						break;
					case "h":
						oStatus = "2차 검수 완료";
						break;
					case "i":
						oStatus = "대금 수령 선택";
						break;
					case "j":
						oStatus = "입금 대기";
						break;
					case "k":
						oStatus = "판매 완료";
						break;
				}

			}
					
%>
			<tr>
				<td width="10%"><%=sellbuy %></td>
				<td width="25%"><%=oId %></td>
				<td width="*%"><%=mName %></td>
				<td width="15%"><%=formatter.format(pay) %> 원</td>
				<td width="15%"><%=oStatus %></td>
				<td width="15%"><%=oDate %></td>
			</tr>
<%
		}
	} else {
%> 
			<tr>
				<td colspan="6">검색 결과가 없습니다.</td>
			</tr>
<%
	}
%>
		</table>
	</div>
<%
} else if (kind.equals("c")) {
%>
	<div id="tab-3" class="tab-content">
		<table width="1000" align="center" border="1" cellpadding="3" cellspacing="0">
			<tr>
				<th width="10%">구분</th>
				<th width="20%">사유</th>
				<th width="20%">변동내용</th>
				<th width="20%">합계</th>
				<th width="*">상세 내역</th>
			</tr>
<%
	if (pointList.size() > 0) {
		ArrayList<MemberPoint> pointList_temp = new ArrayList<MemberPoint>();
		MemberPoint memberPoint_temp = null;
		int changePoint = 0;
	
		for (int i = 0; i < pointList.size(); i++) {
	
			MemberPoint memberPoint = pointList.get(i);
			
			String pointStatus = memberPoint.getMp_su();
			int point = memberPoint.getMp_point();
			int idx = memberPoint.getMp_idx();
			switch (pointStatus) {
				case "u":
					pointStatus = "사용";
					point = point * -1;
					break;
				case "s":
					pointStatus = "적립";
					break;
			}
			changePoint += point;
			memberPoint_temp = new MemberPoint();
			memberPoint_temp.setMp_su(status);
			memberPoint_temp.setMp_desc(memberPoint.getMp_desc());
			memberPoint_temp.setMp_point(point);
			memberPoint_temp.setMp_changePoint(changePoint);
			memberPoint_temp.setMp_detail(memberPoint.getMp_detail());
			pointList_temp.add(memberPoint_temp);
		}
		for (int i = 0 ; i < pointList_temp.size() ; i++) {
			MemberPoint memberPoint = pointList_temp.get(pointList_temp.size() - i - 1);

%>
			<tr>
				<td width="10%"><%=memberPoint.getMp_su() %></td>
				<td width="20%"><%=memberPoint.getMp_desc() %></td>
				<td width="20%"><%=formatter.format(memberPoint.getMp_point()) %></td>
				<td width="20%"><%=formatter.format(memberPoint.getMp_changePoint()) %></td>
				<td width="*"><%=memberPoint.getMp_detail() %></td>
			</tr>
			<%} %>
		</table>
	</div>
<%		
	}
} else if (kind.equals("d")) { 	
	%>
	<div id="tab-4" class="tab-content">
		<table width="1000" align="center" border="1" cellpadding="3" cellspacing="0">
			<tr>
				<th width="15%">상태</th>
				<th width="*">제목</th>
				<th width="15%">등록일</th>
			</tr>
			
<%
	if (questionList.size() > 0) {
		for (int i = 0; i < questionList.size(); i++) {
			MemberQuestion memberQuestion = questionList.get(i);
			String questionStatus = memberQuestion.getBmq_status();
			int idx = memberQuestion.getBmq_idx();
			switch (questionStatus) {
				case "a":
					questionStatus = "답변대기중";
					break;
				case "b":
					questionStatus = "답변완료";
					break;
			}
			String date = (memberQuestion.getBmq_date()).substring(0,10);
%>			
			<tr>
				<td width="15%"><%=questionStatus %></td>
				<td width="*"><%=memberQuestion.getBmq_title() %></td>
				<td width="15%"><%=date %></td>
			</tr>
<%		}
	} else {
		%> 
		<tr>
			<td colspan="6">검색 결과가 없습니다.</td>
		</tr>
<%
	}
%>
		</table>
	</div>
<% 
}%>
<table width="100%" cellpadding="5" class="page">
<tr style="text-align: center;">

<td width="80%">
<%
if (rcnt > 0) {	// 게시글이 있으면 - 페이지 영역을 보여줌
	String lnk = "member_view?kind=" + kind + "&miid=" + memberInfo.getMi_id() + "&cpage=";
	pcnt = rcnt / psize;
	if (rcnt % psize > 0)	pcnt++;	// 전체 페이지 수(마지막 페이지 번호) 
	
	if (cpage == 1) {
		out.println("[처음]&nbsp;&nbsp;&nbsp;[이전]&nbsp;&nbsp;");
	} else {
		out.println("<a href='" + lnk + 1 + "'>[처음]</a>&nbsp;&nbsp;&nbsp;");
		out.println("<a href='" + lnk + (cpage - 1) + "'>[이전]</a>&nbsp;&nbsp;");
	}
	
	spage = (cpage - 1) / bsize * bsize + 1;	// 현재 블록에서의 시작 페이지 번호
	for (int i = 1, j = spage ; i <= bsize && j <= pcnt ; i++, j++) {
	// i : 블록에서 보여줄 페이지의 개수만큼 루프를 돌릴 조건으로 사용되는 변수
	// j : 실제 출력할 페이지 번호로 전체 페이지 개수(마지막 페이지 번호)를 넘지 않게 사용해야 함
		if (cpage == j) {
			out.println("&nbsp;<strong>" + j + "</strong>&nbsp;");
		} else {
			out.print("&nbsp;<a href='" + lnk + j +"'>");
			out.println(j + "</a>&nbsp;");
		}
	}
	
	if (cpage == pcnt) {
		out.println("&nbsp;&nbsp;[다음]&nbsp;&nbsp;&nbsp;[마지막]");
	} else {
		out.println("&nbsp;&nbsp;<a href='" + lnk + (cpage + 1) + "'>[다음]</a>");
		out.println("&nbsp;&nbsp;&nbsp;<a href='" + lnk + pcnt + "'>[마지막]</a>");
	}
}
%>
</td>
</tr>
</table>
</div>


</div>
</body>
</html>
