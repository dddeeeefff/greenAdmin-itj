<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp" %>
<%
request.setCharacterEncoding("utf-8");
ArrayList<BbsEvent> eventList = (ArrayList<BbsEvent>)request.getAttribute("eventList");
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
int bsize = pageInfo.getBsize(), cpage = pageInfo.getCpage();
int psize = pageInfo.getPsize(), pcnt = pageInfo.getPcnt();
int spage = pageInfo.getSpage(), rcnt = pageInfo.getRcnt();
String keyword = pageInfo.getKeyword();

String schargs = "", args = "";
if (keyword != null && !keyword.equals("")) {
// 검색어(keyword)가 있으면 검색관련 쿼리스트링 생성
	schargs = "&keyword=" + keyword;
}
args = "&cpage=" + cpage + schargs;
%>
<style>
tr { height:50px; }
th { font-size:20px; }
input::placeholder { font-size:20px; }
#page-contents {
	width:1000px;	margin:50px 0 50px 500px;
}
#schEvent { text-align:center; }
#keyword { width:400px; height:50px; vertical-align:top; font-size:20px; }
#btnsch { width:70px; height:50px; font-size:20px; }
#btnschB { width:100px; height:50px; font-size:20px; }
#isView { width:100px; font-size:20px;}
</style>
<script>
function isView(opt, beidx) {
// 미게시 or 진행중 or 종료 여부 설정
	$.ajax({
		type : "POST",
		url : "event_list_view",
		data : { "beidx" : beidx, "opt" : opt }, 
		success : function(chkRs) {
			if (chkRs == 1) {
				location.reload();
			} else {
				alert("수정이 되지 않았습니다.\n다시 시도하세요.");
			}
			
		}
	})
}
</script>
<div id="page-contents">
	<form name="frmSch" method="get">
	<div id="schEvent">
		<input type="text" name="keyword" id="keyword" placeholder="&nbsp;&nbsp;제목을 입력하세요" value="<%=keyword %>" />&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" value="검색" id="btnsch" />
	</div>
	</form>
	<br />
	<h2>이벤트</h2><br />
	<table width="1000" align="center" border="1" cellpadding="3" cellspacing="0">
		<tr>
			<th width="10%">글 번호</th>
			<th width="*%">제목</th>
			<th width="10%">작성자</th>
			<th width="10%">조회수</th>
			<th width="15%">작성일</th>
			<th width="13%">진행 상황</th>
		</tr>
		<%
		if (eventList.size() > 0) {
			int num = rcnt - (psize * (cpage - 1));
			for (int i = 0 ; i < eventList.size() ; i ++) {
				BbsEvent be = eventList.get(i);
				String isView = be.getBe_isview();
				String title = be.getBe_title();
				if (title.length() > 30)
					title = title.substring(0, 27) + "...";
				title = "<a href='event_view?beidx=" + be.getBe_idx() + args + "'>" + title + "</a>";
		%>
		<tr align="center">
			<td><%=num %></td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%=title %></td>
			<td>관리자<%=be.getAi_idx() %></td>
			<td><%=be.getBe_read() %></td>
			<td><%=be.getBe_date() %></td>
			<td>
				<select name="isView" id="isView" onchange="isView(this.value, '<%=be.getBe_idx() %>');">
					<option value="n" id="opt" name="opt" <% if (isView.equals("n")) { %>selected="selected"<% } %>>미게시</option>
					<option value="i" id="opt" name="opt" <% if (isView.equals("i")) { %>selected="selected"<% } %>>진행중</option>
					<option value="e" id="opt" name="opt" <% if (isView.equals("e")) { %>selected="selected"<% } %>>종료</option>
				</select>
			</td>
		</tr>
		<%
				num--;
			}
		} else {	// 게시글 목록이 없으면
			out.print("<tr><td colspan='6' align='center'>");
			out.println("검색 결과가 없습니다.</td></tr>");
		}
		%>
	</table>
	<br />
	<table width="1000" cellpadding="5">
		<tr>
			<td align="center">
			<%
			if (rcnt > 0) {	// 게시글이 있으면 - 페이징 영역을 보여줌
				String lnk = "event_list?cpage=";
				pcnt = rcnt / psize;
				if (rcnt % psize > 0)	pcnt++;	// 전체 페이지 수(마지막 페이지 번호)
				
				if (cpage == 1) {
					out.println("[&lt;&lt;]&nbsp;&nbsp;&nbsp;[&lt;]&nbsp;&nbsp;");
				} else {
					out.println("<a href='" + lnk + 1 + schargs + "'>[&lt;&lt;]</a>&nbsp;&nbsp;&nbsp;");
					out.println("<a href='" + lnk + (cpage - 1) + schargs + "'>[&lt;]</a>&nbsp;&nbsp;");
				}
				
				spage = (cpage - 1) / bsize * bsize + 1;	// 현재 블록에서의 시작 페이지 번호
				for (int i = 1, j = spage ; i <= bsize && j <= pcnt ; i++, j++) {
				// i : 블록에서 보여줄 페이지의 개수만큼 루프를 돌릴 조건으로 사용되는 변수
				// j : 실제 출력할 페이지 번호로 전체 페이지 개수(마지막 페이지 번호)를 넘지 않게 사용해야 함
					if (cpage == j) {
						out.println("&nbsp;<strong>" + j + "</strong>&nbsp;");
					} else {
						out.print("&nbsp;<a href='" + lnk + j + schargs + "'>");
						out.println(j + "</a>&nbsp;");
					}
				}
				
				if (cpage == pcnt) {
					out.println("&nbsp;&nbsp;[&gt;]&nbsp;&nbsp;&nbsp;[&gt;&gt;]");
				} else {
					out.println("&nbsp;&nbsp;<a href='" + lnk + (cpage + 1) + schargs + "'>[&gt;]</a>");
					out.println("&nbsp;&nbsp;&nbsp;<a href='" + lnk + pcnt + schargs + "'>[&gt;&gt;]</a>");
				}
			}
			%>
			</td>
		</tr>
		<tr align="right">
			<td>
				<input type="button" id="btnschB" value="글 등록" onclick="location.href='event_form_in';" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" id="btnschB" value="전체 글" onclick="location.href='event_list';" />
			</td>
		</tr>
	</table>
</div>
</body>
</html>
