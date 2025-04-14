<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp" %>
<%
request.setCharacterEncoding("utf-8");
BbsEvent be = (BbsEvent)request.getAttribute("be");

int cpage = Integer.parseInt(request.getParameter("cpage"));
String args = "?cpage=" + cpage;	// 링크에서 사용할 쿼리스트링

String keyword = request.getParameter("keyword");
if (keyword != null && !keyword.equals("")) {
	args += "&keyword=" + keyword;
}
%>
<style>
#page-contents {
	width:1000px;	margin:50px 0 50px 500px;
}
.eventHead { 
	font-size:20px;	padding:5px;
	height:40px;
	border-bottom:1px solid black; 
	display:flex;	justify-content: space-between;
}
.eventContent {
	font-size:20px; margin:10px 0;
	height:100px;
	display:flex;	justify-content: space-between;
}
.eventText { margin:20px 0; }
.btn_box { display:flex; justify-content: space-between; }
.btnProc, .btnList { width:80px; height:30px; cursor:pointer; }
</style>
<div id="page-contents">
<form name="frmView" method="get" action="event_form_up" >
<input type="hidden" name="beidx" value="<%=be.getBe_idx() %>" />
	<div class="eventHead">
		<%=be.getBe_title() %>
	</div>
	<div class="eventHead">
		<span>작성자 : 관리자<%=be.getAi_idx() %></span>
		<span>작성일 : <%=be.getBe_date() %></span>
	</div>
	<div class="eventContent">
		<span>이벤트 기간 : <%=be.getBe_sdate() %>&nbsp;~&nbsp;<%=be.getBe_edate() %></span>
		<span>조회수 : <%=be.getBe_read() %></span>
	</div>
	<div class="eventPic">
		<img src="/greenAdmin/bbs/upload/<%=be.getBe_img() %>" />
	</div>
	<div class="eventText">
		<%=be.getBe_content().replaceAll("\r\n", "<br />") %>
	</div>
	<div class="btn_box">
		<input type="submit" class="btnProc" value="수정" />
		<input type="button" class="btnList" value="목록" onclick="location.href='event_list';" />
	</div>
</form>
</div>
</body>
</html>
