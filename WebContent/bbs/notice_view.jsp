<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp" %>
<%
request.setCharacterEncoding("utf-8");
BbsNotice noticeDetailInfo = (BbsNotice)request.getAttribute("noticeDetailInfo");
String ad = "";
if (noticeDetailInfo.getAi_idx() == 1 || noticeDetailInfo.getAi_idx() == 2)	ad = "관리자";
%>
<style>
#page-contents {
	width:1000px;	margin:50px 0 50px 500px;
}
.n1{ padding:12px 7px; }
.btn{ width:80px; height:30px; background-color:#A6A6A6; cursor:pointer; }
.b0{ display:flex; justify-content: space-between; }
</style>
<div id="page-contents">
	<div class="n1">글번호 : <span><%=noticeDetailInfo.getBn_idx() %></span></div>
	<div class="n1" style="border-top:1px solid black;"><span><%=noticeDetailInfo.getBn_title() %></span></div>
	<div class="n1" style="display:flex; justify-content: space-between; border-top:1px solid black; border-bottom:1px solid black;">
		<div>작성자 : <span><%=ad %></span></div>
		<div>등록일 : <span><%=noticeDetailInfo.getBn_date().substring(0, 10) %></span></div>
	</div>
	<div class="n1" style="display:flex; justify-content: flex-end;">조회수 : <span>&nbsp;<%=noticeDetailInfo.getBn_read() %></span></div>
	<div class="n1"><img src="/greenAdmin/bbs/notice_img/<%=noticeDetailInfo.getBn_img() %>" /></div>
	<div class="n1"><pre><%=noticeDetailInfo.getBn_content() %></pre></div>
	<div class="b0">
		<div class="b1">
			<input class="btn" type="button" value="수정" onclick="location.href='notice_form_up?&bnidx=<%=noticeDetailInfo.getBn_idx() %>';" />&nbsp;
			<input type="hidden" name="bnidx" value="<%=noticeDetailInfo.getBn_idx() %>" />
		</div>
		<div class="b2">
			<input class="btn" type="button" value="목록" onclick="location.href='notice_list';" />
		</div>
	</div>
</div>
</body>
</html>