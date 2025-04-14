<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp" %>
<%
request.setCharacterEncoding("utf-8");
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
ArrayList<BbsNotice> noticeList = (ArrayList<BbsNotice>)request.getAttribute("noticeList");

int cpage = pageInfo.getCpage(), psize = pageInfo.getPsize();
int rcnt = pageInfo.getRcnt(), spage = pageInfo.getSpage();
int bsize = pageInfo.getBsize(), pcnt = pageInfo.getPcnt();
String keyword = pageInfo.getKeyword();

String args = "", sargs = "";

if (keyword != null && !keyword.equals("")){
sargs += "&keyword=" + keyword;
}
args = "&cpage=" + cpage + sargs;
%>
<style>
tr { height:50px; }
th { font-size:20px; background-color:#EAEAEA; }
#page-contents {
	width:1000px;	margin:50px 0 50px 500px;
}
#schkeyword { margin:30px 0; }
#pdt { width:200px; height:30px; }
#btnsch { width:70px; height:37px; font-size:13px; }
#isView { width:100px; font-size:20px;}
.btn { width:70px; font-size:20px;}
.pageinfo { margin:30px 0; }
.nothing { margin:80px 0; font-size:25px; }
h2{ font-size: 2.3em; }
.searchKeyword{ padding : 10px 18px; width:500px; }
.searchButton{ padding : 10px 18px; height : 37px; cursor:pointer; }
.regist{ display:flex; justify-content: flex-end; }
.registButton{ width:80px; height:30px; background-color:#A6A6A6; cursor:pointer; }
</style>
<script>
function makeSch() {
// 키워드 검색
	var frm = document.frm;
	
	document.frm.submit();
}

function isViewProc(opt, bnidx) {
// 게시 or 미게시 여부 설정
	$.ajax({
		type : "POST",
		url : "notice_proc_del",
		data : { "bnidx" : bnidx, "opt" : opt }, 
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
	<div class="schkeyword">
		<form name="frm" action="notice_list" method="get">
			<div id="schkeyword">
				<p align="right">
				<input type="text" style="display:none;" />
				<input type="text" class="searchKeyword" name="keyword" id="keyword" placeholder="검색어를 입력해주세요." value="" />&nbsp;&nbsp;
				<input type="button" class="searchButton" value="찾기" id="btnsch" onclick="makeSch()" />
				</p>
			</div>
		</form>
	</div>
	<h2>공지사항</h2>
	<br />
	<div class="notice_list">
			<form name="frmisview">
				<table width="1000" align="center" border="1" cellpadding="3" cellspacing="0">
					<tr>
						<th width="8%">글번호</th>
						<th width="40%">제목</th>
						<th width="7%">작성자</th>
						<th width="7%">조회수</th>
						<th width="13%">등록일</th>
						<th width="13%">게시여부</th>
					</tr>
					<%
					if (rcnt > 0) {	// 검색된 상품 목록이 있을 경우
						String lnk = "notice_list?cpage=";
						for (int i = 0 ; i < noticeList.size() ; i++) {
							BbsNotice bn = noticeList.get(i);
							String isView = bn.getBn_isview();
							String admin = "";
							if(bn.getAi_idx() == 1 || bn.getAi_idx() == 2)	admin = "관리자";
							pcnt = rcnt / psize;
							if (rcnt % psize > 0)	pcnt++;	// 전체 페이지 수(마지막 페이지 번호)
							
					%>
					<input type="hidden" name="bnidx" value="<%=bn.getBn_idx() %>" />
					<tr align="center">
						<td><%=bn.getBn_idx() %></td>
						<td><a href="notice_view?bnidx=<%=bn.getBn_idx() %>"><%=bn.getBn_title() %></a></td>		
						<td><%=admin %></td>
						<td><%=bn.getBn_read() %></td>
						<td><%=bn.getBn_date().substring(0, 10) %></td>
						<td>
							<select style="cursor:pointer;" name="isView" id="isView" onchange="isViewProc(this.value, '<%=bn.getBn_idx() %>');">
								<option value="n" id="opt" name="opt" <% if (isView.equals("n")) { %>selected="selected"<% } %>>미게시</option>
								<option value="y" id="opt" name="opt" <% if (isView.equals("y")) { %>selected="selected"<% } %>>게시</option>
							</select>
						</td>
					</tr>
					<% 
						}
					
					out.println("</table>");
						
					String tmp = sargs;
					// 페이징 영역 링크에서 사용할 쿼리 스트링
					out.println("<br />");
					out.println("<p class='pageinfo' align='center'>");
					
					if (cpage == 1) {
						out.println("[&lt;&lt;]&nbsp;&nbsp;[&lt;]&nbsp;");
					} else {
						out.print("<a href=\"notice_list?cpage=1" + tmp + "\">");
						out.println("[&lt;&lt;]</a>&nbsp;&nbsp;");
						out.print("<a href=\"notice_list?cpage=" + (cpage - 1) + tmp + "\">");
						out.println("[&lt;]</a>&nbsp;");
					}
					
					for (int i = 1, j = spage ; i < bsize && j <= pcnt ; i++, j++) {
						if (cpage == j) {
							out.println("&nbsp;<strong>" + j + "</strong>&nbsp;");
						} else {
							out.print("&nbsp;<a href=\"notice_list?cpage=");
							out.println(j + tmp + "\">" + j +"</a>&nbsp;");
						}
					}
					
					if (cpage == pcnt) {
						out.println("&nbsp;[&gt;]&nbsp;&nbsp;[&gt;&gt;]");
					} else {
						out.print("&nbsp;<a href=\"notice_list?cpage=" + (cpage + 1) + tmp + "\">");
						out.println("[&gt;]</a>&nbsp;&nbsp;");
						out.print("<a href=\"notice_list?cpage=" + pcnt + tmp + "\">");
						out.println("[&gt;&gt;]</a>");
					}
						out.println("</p>");
					} else {		// 검색된 상품 목록이 없을 경우
						out.println("</table>");
						out.println("<p class='nothing' align='center'>검색 결과가 없습니다.</p>");
					} %>
				</table>
			</form>
			
			<div class="regist">
				<button class="registButton" type="button" onclick="location.href='notice_form_in';">글 등록</button>
			</div>
			
		</div>
</div>
</body>
</html>