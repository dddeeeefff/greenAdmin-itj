<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp" %>
<%
request.setCharacterEncoding("utf-8");
BbsReview reviewDetailInfo = (BbsReview)request.getAttribute("reviewDetailInfo");
%>
<style>
#page-contents {
	width:1000px;	margin:50px 0 50px 500px;
}
.n1{ padding:12px 7px; }
.btn{ width:80px; height:30px; background-color:#A6A6A6; }
.b0{ display:flex; justify-content: space-between; }
</style>
<div id="page-contents">
	<div class="n1" style="border-top:1px solid black;"><span>(<%=reviewDetailInfo.getBr_name() %>) <%=reviewDetailInfo.getBr_title() %></span></div>
	<div class="n1" style="display:flex; justify-content: space-between; border-top:1px solid black; border-bottom:1px solid black;">
		<div>작성자 : <span><%=reviewDetailInfo.getMi_id() %></span></div>
		<div>등록일 : <span><%=reviewDetailInfo.getBr_date().substring(0, 10) %></span></div>
	</div>
	<div class="n1" style="display:flex; justify-content: flex-end;">조회수 : <span>&nbsp;<%=reviewDetailInfo.getBr_read() %></span></div>
	<div class="n1"><img src="/greenPhone/bbs/review_img/<%=reviewDetailInfo.getBr_img() %>" /></div>
	<div class="n1"><pre><%=reviewDetailInfo.getBr_content() %></pre></div>
	<div class="b0">
		<div class="b1">
			<input class="btn" type="button" value="목록" onclick="location.href='review_list';" />
		</div>
	</div>
</div>
</body>
</html>