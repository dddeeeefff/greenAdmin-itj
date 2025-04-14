<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp" %>
<%
request.setCharacterEncoding("utf-8");

ArrayList<BbsFree> freeList = (ArrayList<BbsFree>)request.getAttribute("freeList");
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");

int bsize = pageInfo.getBsize(), cpage = pageInfo.getCpage(), psize = pageInfo.getPsize();
int rcnt = pageInfo.getRcnt(), pcnt = pageInfo.getPcnt(), spage = pageInfo.getSpage();
String schtype = pageInfo.getSchtype(), keyword = pageInfo.getKeyword();

String schargs = "", args = "";
if (schtype != null && !schtype.equals("") && keyword != null && !keyword.equals("")) {		// 검색 조건(schtype)과 검색어(keyword)가 있으면 검색관련 쿼리스트링 생성
	schargs = "&schtype=" + schtype + "&keyword=" + keyword;	
}
args = "&cpage=" + cpage + schargs;
%>
<script>
	function del(bfidx, select) {
		var isDel = select.value;
		$.ajax({
			type : "POST", 
			url : "free_proc_del", 
			data : {"bfidx" : bfidx, "kind" : isDel}, 
			success : function(chkRs) {
				if (chkRs == 0) {
					alert("게시 여부 변경에 실패하였습니다.\n다시 시도해 보세요.");
				} else {
					location.reload();
				}
			}
		});
	}
</script>
	<link rel="stylesheet" href="src/css/free_list.css">
    <div class="page-contents">
        <div class="content-wrapper">
            <h3>자유 게시판</h3>
            <div class="frmSch">
                <form name="frmSch">
                    <select name="schtype">
                        <option value="">검색 항목</option> 
                        <option value="writer" <% if (schtype.equals("writer")) { %>selected="selected"<% } %>>작성자</option> 
                        <option value="title" <% if (schtype.equals("title")) { %>selected="selected"<% } %>>제목</option> 
                        <option value="content" <% if (schtype.equals("content")) { %>selected="selected"<% } %>>내용</option> 
                    </select>
                    <input type="text" name="keyword" value="<%=keyword %>" />
                    <input type="submit" value="검색"  />
                </form>
            </div>
            <div class="list">
                <table>
                    <tr class="th">
                        <th class="num">글  번호</th>
                        <th class="title">제목</th>
                        <th class="writer">작성자</th>
                        <th class="read">조회수</th>
                        <th class="date">등록일</th>
                        <th class="del">게시 여부</th>
                    </tr>
<%
if (freeList.size() > 0) {
	int num = rcnt - (psize * (cpage - 1));
	for (int i = 0; i < freeList.size(); i++) {
		BbsFree bf = freeList.get(i);
		
		String title = bf.getBf_title();
		if (title.length() > 30)		title = title.substring(0, 24) + "...";
		title = "<a href='free_view?bfidx=" + bf.getBf_idx() + args + "'>" + title + "</a>";
		if (bf.getBf_reply() > 0)		title += "  <span>[" + bf.getBf_reply() + "]</span>";
		
		String isDel = bf.getBf_isdel();
%>
                    <tr class="td">
                    <td class="num"><%=num %></td>
                    <td class="title"><%=title %></td>
                    <td class="writer"><%=bf.getMi_id() %></td>
                    <td class="read"><%=bf.getBf_read() %></td>
                    <td class="date"><%=bf.getBf_date() %></td>
                	<td class="del">
                		<select name="del" onchange="del(<%=bf.getBf_idx() %>, this);">
                			<option value="y" <% if (isDel.equals("y")) { %>selected="selected"<% } %>>미게시</option>
                			<option value="n" <% if (isDel.equals("n")) { %>selected="selected"<% } %>>게시</option>
                		</select>
                	</td>
                </tr>
<%
		num--;
	}
} else {
%>
				</table>
				<p>검색 결과가 없습니다.</p>
<% } %>
<% if (rcnt > 0) {
	out.println("</table>");
	out.println("<div class='pagination'>");
	String lnk = "free_list?cpage=";
	pcnt = rcnt / psize;
	if (rcnt % psize > 0)		pcnt++;		// 전체 페이지 수(마지막 페이지 번호)
	
	if (cpage == 1) {
		out.println("<p class='first'>[처음]</p>");
		out.println("<p>[이전]</p>");
	} else {
		out.println("<a class='first' href='" + lnk + 1 + schargs + "'>[처음]</a>");
		out.println("<a href='" + lnk + (cpage - 1) + schargs + "'>[이전]</a>");
	}
	
	spage = (cpage - 1) / bsize * bsize + 1;		// 현재 블록에서의 시작 페이지 번호
	for (int i = 1, j = spage; i <= bsize && j <= pcnt; i++, j++) {
	// i : 블록에서 보여줄 페이지의 개수 만큼 루프를 돌릴 조건으로 사용되는 변수
	// j : 실제 출력할 페이지 번호로 전체 페이지 개수(마지막 페이지 번호)를 넘지 않게 사용해야 함
		if (cpage == j)		out.println("<p><strong>" + j + "</strong></p>");
		else 				out.println("<a href='" + lnk + j + schargs + "'>" + j + "</a>");
	}
	
	if (cpage == pcnt) {
		out.println("<p>[다음]</p>");
		out.println("<p>[마지막]</p>");
	} else {
		out.println("<a href='" + lnk + (cpage + 1) + schargs + "'>[다음]</a>");
		out.println("<a href='" + lnk + pcnt + schargs + "'>[마지막]</a>");
	}
	out.println("</div>");
}
%>
            </div>
        </div>
    </div>
</body>
</html>